package com.studentapp.studentinfo;

import com.studentapp.model.ProductsPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class StudentCRUDTest extends TestBase {

    static int idNumber;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost"; // base URI
        RestAssured.port = 3030; // port number
        RestAssured.basePath = "/products"; // endpoints
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

         idNumber = response.then().extract().path("id");
        System.out.println(idNumber);

      /* ProductsPojo s=response.getBody().as(ProductsPojo.class);
        String actualName=s.getName();
        Assert.assertEquals(actualName,"urv3i11");*/

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
                .pathParam("id", idNumber)
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
                .pathParam("id", idNumber)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);

    }

    @Test// retrive id and validate
    public void test005() {
        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", idNumber)
                .when()
                .get("/{id}");
        response.then().statusCode(404);


    }

}
