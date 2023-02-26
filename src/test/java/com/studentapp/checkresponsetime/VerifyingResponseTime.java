package com.studentapp.checkresponsetime;

import com.studentapp.testbase.TestBase;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;


public class VerifyingResponseTime extends TestBase {


    @Test
    public void test001() {
        long response=given()
                .when()
                .get("/list")
                .time();
        System.out.println(response);

        given()
                .when()
                .get("/list")
                .then()
                .time(lessThan(100l), TimeUnit.MILLISECONDS);

        }

}
