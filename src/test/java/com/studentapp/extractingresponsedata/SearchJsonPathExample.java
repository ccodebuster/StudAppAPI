package com.studentapp.extractingresponsedata;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class SearchJsonPathExample {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);

    }


    // 1) Extract the value of limit
    @Test
    public void test001() {

        int limit = response.extract().path("limit");
        System.out.println("value of limit is : " + limit);
        Assert.assertEquals(10, limit); //one way of assertion
        response.body("limit", equalTo(10)); // second way of assertion

    }

    // 2) Extract the list of IDs of all products
    @Test
    public void test002() {
        List<Integer>numofId=response.extract().path("data.id");
        System.out.println("List of Ids : "+ numofId);
        for(Integer a:numofId){
            if(a.equals(185267)){
                Assert.assertTrue(true);
            }
        }
    }

    // 3) Extract first product name from data
    @Test
    public void test003() {
        String nameOfprd=response.extract().path("data[0].name");
        System.out.println(nameOfprd);

       /* List<String> dataNames = response.extract().path("data.name");
       // System.out.println("List of IDs within the limit : " + dataNames );
        System.out.println(dataNames.get(1));*/
    }

    // 4) Get the categories list of the first data
    @Test
    public void test004() {
       HashMap<String,?> details =response.extract().path("data[0].categories[0]");
        System.out.println(details);

    }

    // 5)Print the size of data
    @Test
    public void test005() {
        List<Integer>dataSize=response.extract().path("data");
        int size=dataSize.size();
        System.out.println(size);
    }

    // 6) Get All the products Name from data
    @Test
    public void test006() {
    // homework
    }

    // 7) Get all the values for Name == Duracell - AA Batteries (8-Pack)
    @Test
    public void test007() {
       List<HashMap<String,?>> values =response.extract().path("data.findAll{it.name == 'Duracell - AA Batteries (8-Pack)'}");
        System.out.println(values);

    }

    // 8) Get the price for product Name == Duracell - D Batteries (4-Pack)
    @Test
    public void test008() {
    List<Integer>price=response.extract().path("data.findAll{it.name == 'Duracell - D Batteries (4-Pack)'}.price");
        System.out.println(price);
    }

    // 9) Get the Names of products which have price less than 16.99
    @Test
    public void test009() {
        List<String>prdName=response.extract().path("data.findAll{it.price <16.99}.name");
        System.out.println(prdName);
    }

    // 10) Get the manufacturer of products whose name Start = Ene
    @Test
    public void test010() {
        List<?>menuName=response.extract().path("data.findAll{it.name.startsWith('Ene')}.manufacturer");
        System.out.println(menuName);
    }

    // 11) Get the price of products whose name end with = Vehicles
    @Test
    public void test011() {

        List<?>priceList=response.extract().path("data.findAll{it.name==~/.*Vehicles/}.price");
        List<?>priceList1=response.extract().path("data.findAll{it.name==~/.*Black/}.price");
        System.out.println(priceList);

    }

    // 12) Get the id of product whose name is 'Energizer - N Cell E90 Batteries (2-Pack)'
    @Test
    public void test012() {
        //homework

    }

}
