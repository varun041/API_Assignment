package com.example;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;


public class Reuse {
    public static JsonPath reuse(String body){
        JsonPath js = new JsonPath(body);
return js;
    }

    
}
