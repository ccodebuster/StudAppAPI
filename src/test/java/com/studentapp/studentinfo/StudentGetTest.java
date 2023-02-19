package com.studentapp.studentinfo;

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
        response.prettyPrint();

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
                .pathParam("id", "102")
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();


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
