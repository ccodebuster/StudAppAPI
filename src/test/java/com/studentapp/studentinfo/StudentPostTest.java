package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class StudentPostTest extends TestBase {

    @Test
    public void createStudent() {

        List<String>courses= new ArrayList<>();
        courses.add("selenium");
        courses.add("postman");
        courses.add("restassured");

        StudentPojo pojo = new StudentPojo();
        pojo.setFirstName("Akshit");
        pojo.setLastName("patel");
        pojo.setEmail("ayx@gmail.com");
        pojo.setProgramme("API testing");
        pojo.setCourses(courses);

        Response response =given()
                .header("Content-Type","application/json")
                .body(pojo)
                .post();
        response.then().log().all().statusCode(201);
        response.prettyPrint();


    }
}
