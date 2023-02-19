package com.studentapp.studentinfo;


import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentPutTest extends TestBase {
    @Test
    public void put() {


        StudentPojo pojo = new StudentPojo();
        pojo.setFirstName("Akshit");
        pojo.setEmail("abc1@gmail.com");
        pojo.setProgramme("Cypress testing");


        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id","102")
                .body(pojo)
                .put("/{id}");
        response.then().log().all().statusCode(200);
        response.prettyPrint();
    }

}
