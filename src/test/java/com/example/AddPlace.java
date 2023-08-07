package com.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class AddPlace extends AddPlaceData {
    
    
       
    

    public static void main(String[] args) {

     RestAssured.baseURI = "https://rahulshettyacademy.com";
String responseaddapi =given()
.log()
.all()
.queryParam("key", "qaclick123")
.header("Content-Type","application/json")
.body(AddPlaceData.addplace())
.when()
.post("/maps/api/place/add/json")
.then()
//.log()
//.all()
.assertThat()
.statusCode(200)
.body("scope",equalTo("APP"))
.body("status",equalTo("OK"))
.extract()
.response()
.asString();

System.out.println(responseaddapi);

//JsonPath js = new JsonPath(responseaddapi);
JsonPath js = Reuse.reuse(responseaddapi);
 String place_id =js.getString("place_id");
 System.out.println(place_id);


 //Put
String updated_address = "70 Summer walk, USA";
 given()
.log()
.all()
.queryParam("key","qaclick123")
.header("Content-Type","application/json")
.body("{\n" + //
        "\"place_id\":\""+place_id+"\",\n" + //
        "\"address\":\"70 Summer walk, USA\",\n" + //
        "\"key\":\"qaclick123\"\n" + //
        "}")
.when()
.put("/maps/api/place/update/json")
.then()
.log()
.all()
.assertThat()
.statusCode(200)
.body("msg",equalTo("Address successfully updated"));

//Get API

String getapi =given()
.log()
.all()
.queryParam("place_id",place_id)
.queryParam("key","qaclick123")
.when()
.get("/maps/api/place/get/json")
.then()
.log()
.all()
.assertThat()
.statusCode(200)
.body("address",equalTo("70 Summer walk, USA"))
.extract()
.response()
.asString();

//JsonPath js1 = new JsonPath(getapi);
JsonPath js1 =Reuse.reuse(getapi);
String address = js1.get("address");
Double latitude = js1.getDouble("location.latitude");
Double longitude = js1.getDouble("location.longitude");
System.out.println(address);
System.out.println(latitude);
System.out.println(longitude);


    }
}