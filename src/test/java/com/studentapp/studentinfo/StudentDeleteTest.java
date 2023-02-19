package com.studentapp.studentinfo;


import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentDeleteTest extends TestBase {

    @Test
    public void delete(){
       Response resposne= given()
                .pathParam("id","102")
                .when()
                .delete("/{id}");
       resposne.then().statusCode(204);
       resposne.prettyPrint();

    }


}
