package com.studentapp.loggingrequestresponse;

import com.studentapp.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class LoggingRequestDetails extends TestBase {
    /**
     * This test will print out all the request headers
     */
    @Test
    public void test001() {
        given()
                .log().headers()
                .log().ifValidationFails()
                .when()
                .get("/list1")
                .then()
                .log().ifValidationFails()
                .statusCode(200);

    }

    /**
     * This test will print out all the request Parameters
     */
    @Test
    public void test002() {
        System.out.println("---------------Printing Request Parameters------------------");
        given()
                .log().parameters()
                .when()
                .get("/list")
                .then()
                .statusCode(200);
    }

    /**
     * This test will print out the Response body
     */
    @Test
    public void test003() {
        System.out.println("---------------Printing Request Body------------------");
        given()
                .when()
                .get("/list")
                .then()
                .log().body()
                .statusCode(200);

    }

    /**
     * This test will print out All the details
     */
    @Test
    public void test004() {
        System.out.println("---------------Printing All the Request Details------------------");
        given()
                .log().all()
                .when()
                .get("/list")
                .then()
                .log().all()
                .statusCode(200);
    }


    /**
     * This test will print Request details if validation fails
     */
    @Test
    public void test005() {
        System.out.println("---------------Printing All the Request Details if validation fails------------------");
        //homework
    }
}
