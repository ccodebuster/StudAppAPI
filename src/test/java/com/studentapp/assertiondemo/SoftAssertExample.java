package com.studentapp.assertiondemo;

import com.studentapp.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class SoftAssertExample extends TestBase {

    @Test // assertion fail and program terminate
    public void hardAssert() {
        given()
                .when()
                .log().all()
                .get("/list")
                .then().log().all()
                .statusCode(200)
                .body("[1].id",equalTo(2))
                .body("[1].firstName",equalTo("Murphy1"))
                .body("[1].lastName",equalTo("Holmes"));
    }

    @Test // mutiple validation
    public void softAssert() {
        given()
                .when()
                //.log().all()
                .get("/list")
                .then()
                .statusCode(200)
                .body("[1].id",equalTo(2),"[1].firstName",equalTo("Murphy"),"[1].lastName",equalTo("Holmes"));

    }
}
