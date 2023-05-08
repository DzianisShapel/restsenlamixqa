package eu.senla.lab;

import eu.senla.lab.pojo.Application;
import eu.senla.lab.pojo.Artist;
import eu.senla.lab.pojo.User;
import eu.senla.lab.requests.Requests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static eu.senla.lab.api.SpecBuilder.getRequestSpecification;
import static eu.senla.lab.api.SpecBuilder.getResponseSpecification;
import static eu.senla.lab.constants.CoreConstants.APPLICATIONS;
import static io.restassured.RestAssured.given;

public class ApiTest {

    @Test
    public void getArtistTest(){
     Artist artist = Requests.getArtistBySlug("andy-warhol");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(artist.getName(), "Andy Warhol");
        softAssert.assertEquals(artist.getGender(), "male");
        softAssert.assertEquals(artist.getNationality(), "American");
        softAssert.assertEquals(artist.getImageVersions().size(), 4);
        softAssert.assertAll();
    }

    @Test
    public void getCurrentUserTest(){
        User user = Requests.getCurrentUser();
        Assert.assertEquals(user.getName(), "Dzianis Shapel");
    }

    @Test
    public void createApplicationTest(){
       Application app = Requests.createApplication("auto-test");
       Assert.assertEquals(app.getName(), "auto-test");
        Requests.deleteApplication(app.getId());
    }

    @Test
    public void updateApplicationTest(){
        Application createdApp = Requests.createApplication("app_to_update");
        createdApp.setName("updatedName");
        Application updatedApp = Requests.updateApplication(createdApp.getId(), createdApp);
        Assert.assertEquals(updatedApp.getName(), "updatedName");
        Requests.deleteApplication(updatedApp.getId());
    }

    @Test
    public void deleteApplicationTest(){
        Application createdApp = Requests.createApplication("app_to_delete");
        Requests.deleteApplication(createdApp.getId());
        checkThatApplicationDeleted(createdApp.getId());
    }

    private void checkThatApplicationDeleted(String id) {
        String message = given().
                spec(getRequestSpecification()).
        when().
                get(APPLICATIONS + id).
        then().spec(getResponseSpecification()).
                statusCode(404).
                extract().
                response().
                body().path("message");
        Assert.assertEquals(message, "Application Not Found");
    }
}
