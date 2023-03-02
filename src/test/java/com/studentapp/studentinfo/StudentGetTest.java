package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class StudentGetTest extends TestBase {

    @Test
    public void getAllStudentsInfo() {
        Response response = given()
                .when()
                .get("/list");

        response.then().statusCode(200);
        //response.prettyPrint();
      StudentPojo  p=response.getBody().as(StudentPojo.class);

        String name=p.getFirstName().toString();
        System.out.println(name);

       /* String res=given()
             .when()
                .get("/list")
                .toString();
        JsonPath js = new JsonPath(res);
        String name=js.getString("firstname");
        System.out.println(name);*/


    }

    @Test
    public void getSingleStudentInfo() {
        Response response= given()
                .pathParam("id", "104")
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

        StudentPojo  p=response.getBody().as(StudentPojo.class);

        String name=p.getFirstName();
        System.out.println(name);

    }

    @Test
    public void searchStudentWithParameter() {
        HashMap<String,Object>qParams= new HashMap<>();
        qParams.put("programme", "Computer Science");
        qParams.put("limit", 3);


       Response response = given()
               .log().all()
//                .queryParams("programme", "Computer Science")
//                .queryParams("limit", 3)
               .queryParams(qParams)
                .when()
                .get("/list");
       response.then().statusCode(200);
       response.prettyPrint();


    }

}
