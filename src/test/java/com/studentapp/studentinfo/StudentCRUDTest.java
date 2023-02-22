package com.studentapp.studentinfo;

import com.studentapp.model.ProductsPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class StudentCRUDTest extends TestBase {

    int idNumber;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }

    @Test // get all list
    public void test001() {

        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);
    }

    @Test // post new and retrive id
    public void test002() {

        ProductsPojo pojo = new ProductsPojo();
        pojo.setName("urv3i11");
        pojo.setType("kbhgvs");
        pojo.setPrice(1722);
        pojo.setShipping(23);
        pojo.setUpc("gdtht");
        pojo.setDescription("desp");
        pojo.setManufacturer("auvbdi");
        pojo.setModel("fordxf");
        pojo.setUrl("dbd");
        pojo.setImage("jhkjb");


        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(pojo)
                .post();
        response.then().statusCode(201);
        int idNumber = response.then().extract().path("id");

        System.out.println(idNumber);


    }

    @Test //update id
    public void test003() {
        ProductsPojo pojo = new ProductsPojo();
        pojo.setPrice(1700);
        pojo.setShipping(230);
        pojo.setManufacturer("audi");
        pojo.setModel("ford");

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", "9999702")
                .when()
                .body(pojo)
                .patch("/{id}");
        response.then().statusCode(200);


    }

    @Test// delete it
    public void test004() {
        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", "9999702")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);



    }

    @Test// retrive id and validate
    public void test005() {
        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", "9999702")
                .when()
                .get("/{id}");
        response.then().statusCode(404);


    }

}
