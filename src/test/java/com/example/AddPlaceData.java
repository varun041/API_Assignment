package com.example;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import static org.hamcrest.Matchers.*;



public class AddPlaceData {
 
  public static String addplace(){
    return "{\n" + //
        "  \"location\": {\n" + //
        "    \"lat\": -38.383494,\n" + //
        "    \"lng\": 33.427362\n" + //
        "  },\n" + //
        "  \"accuracy\": 50,\n" + //
        "  \"name\": \"Frontline house\",\n" + //
        "  \"phone_number\": \"(+91) 983 893 3937\",\n" + //
        "  \"address\": \"29, side layout, cohen 09\",\n" + //
        "  \"types\": [\n" + //
        "    \"shoe park\",\n" + //
        "    \"shop\"\n" + //
        "  ],\n" + //
        "  \"website\": \"http://google.com\",\n" + //
        "  \"language\": \"French-IN\"\n" + //
        "}\n" + //
        "";
  }

  public static String complexdata(){
    return "{\n" + //
        "\n" + //
        "\"dashboard\": {\n" + //
        "\n" + //
        "\"purchaseAmount\": 910,\n" + //
        "\n" + //
        "\"website\": \"rahulshettyacademy.com\"\n" + //
        "\n" + //
        "},\n" + //
        "\n" + //
        "\"courses\": [\n" + //
        "\n" + //
        "{\n" + //
        "\n" + //
        "\"title\": \"Selenium Python\",\n" + //
        "\n" + //
        "\"price\": 50,\n" + //
        "\n" + //
        "\"copies\": 6\n" + //
        "\n" + //
        "},\n" + //
        "\n" + //
        "{\n" + //
        "\n" + //
        "\"title\": \"Cypress\",\n" + //
        "\n" + //
        "\"price\": 40,\n" + //
        "\n" + //
        "\"copies\": 4\n" + //
        "\n" + //
        "},\n" + //
        "\n" + //
        "{\n" + //
        "\n" + //
        "\"title\": \"RPA\",\n" + //
        "\n" + //
        "\"price\": 45,\n" + //
        "\n" + //
        "\"copies\": 10\n" + //
        "\n" + //
        "}\n" + //
        "\n" + //
        "]\n" + //
        "\n" + //
        "}\n" + //
        "\n" + //
        "";
  }

  public static String addbook(String isbn, String aisle){
    String bookcontents ="{\n" + //
        "\n" + //
        "\"name\":\"Learn Appium Automation with Java\",\n" + //
        "\"isbn\":\""+isbn+"\",\n" + //
        "\"aisle\":\""+aisle+"\",\n" + //
        "\"author\":\"varun oberoi\"\n" + //
        "}\n" + //
        "";
        return bookcontents;
  }

  
  public static void main(String args[]){

  }
  }


