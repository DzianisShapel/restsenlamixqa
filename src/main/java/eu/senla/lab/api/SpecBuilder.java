package eu.senla.lab.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;

import static eu.senla.lab.constants.CoreConstants.BASE_URI;

public class SpecBuilder {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder().
                setBaseUri(BASE_URI).
                addHeader("X-Access-Token", Authorize.getAccess_token()).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpecification(){
        return  new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
