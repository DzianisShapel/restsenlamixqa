package eu.senla.lab.requests;

import eu.senla.lab.api.Authorize;
import eu.senla.lab.pojo.Application;
import eu.senla.lab.pojo.Artist;
import eu.senla.lab.pojo.User;

import static eu.senla.lab.api.SpecBuilder.getRequestSpecification;
import static eu.senla.lab.api.SpecBuilder.getResponseSpecification;
import static eu.senla.lab.constants.CoreConstants.*;
import static io.restassured.RestAssured.given;

public class Requests {

    public static Artist getArtistBySlug(String slug){
        return given().
                header("X-Xapp-Token", Authorize.getXapp_token()).
                spec(getRequestSpecification()).
        when().
                get(ARTISTS + slug).
        then().spec(getResponseSpecification()).
                statusCode(200).
                extract().response().body().as(Artist.class);
    }

    public static User getCurrentUser(){
        return given().
                spec(getRequestSpecification()).
        when().
                get(CURRENT_USER).
        then().spec(getResponseSpecification()).
                statusCode(200).
                extract().response().body().as(User.class);
    }

    public static Application createApplication(String name){
        Application application = new Application();
        application.setName(name);
        return given().
                spec(getRequestSpecification()).
                body(application).
        when().
                post(APPLICATIONS).
        then().statusCode(201).
                spec(getResponseSpecification()).
                extract().response().body().as(Application.class);
    }

    public static Application updateApplication(String id, Object object){
        return given().
                spec(getRequestSpecification()).
                body(object).
        when().
                put(APPLICATIONS + id).
        then().spec(getResponseSpecification()).
                statusCode(200).
                extract().response().body().as(Application.class);
    }

    public static Application deleteApplication(String id) {
        return given().
                spec(getRequestSpecification()).
        when().
                delete(APPLICATIONS + id).
        then().spec(getResponseSpecification()).
                statusCode(200).
                extract().
                response().body().as(Application.class);
    }
}
