package com.example.POJO;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.example.AddPlaceData;
import com.example.Reuse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Books {

    public static String bookid;

    public static void main(String args[]) throws JsonProcessingException {

        GetSet gs = new GetSet();
        
        gs.setName("Java Programming");
        gs.setIsbn("cce");
        gs.setAisle("354");
         gs.setAuthor("Varun Oberoi");
    

        RestAssured.baseURI = "http://216.10.245.166";
        String bookresponse = given()
                .log()
                .all()
                .header("Content-Type", "application/json")
                .when()
                .body(gs)
                .post("Library/Addbook.php")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .asString();



            }

        }