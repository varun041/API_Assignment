package com.example.POJO;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBodyExtractionOptions;
import com.example.POJO.Response;
import io.restassured.parsing.Parser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.File;

import com.example.Reuse;
import com.example.JIRA.Payloads;


public class Demo {
    public static String session_id;
    public static String jira_id;
public static void main(String args[]){
    RestAssured.baseURI = "http://localhost:8080";
SessionFilter sf = new SessionFilter();

   
//Login 

String loginresponse = 
given()
.header("Content-Type","application/json")
.body(Payloads.loginapi())
.filter(sf)
.when()
.post("rest/auth/1/session")
.then()
.log()
.all()
.assertThat()
.statusCode(200)
.extract()
.response()
.asString();

JsonPath js1 = Reuse.reuse(loginresponse);
String sessionid = js1.getString("session.value");
System.out.println(sessionid);
session_id = sessionid;
   


    //Add a comment
    
    Response rsp=  given()
    .pathParam("issueIdOrKey","10003")
    .header("Content-Type","application/json")
    //.header("Cookie",session_id)
    .body(Payloads.addcomment())
    .filter(sf)
    .log()
    .all()
   // .expect()
   // .defaultParser(Parser.JSON)
    .when()
    .post("/rest/api/2/issue/{issueIdOrKey}/comment")
    .as(Response.class);
    System.out.println(rsp.getSelf());
    System.out.println(rsp.getId());
     System.out.println(rsp.getAuthor().getSelf());
    System.out.println(rsp.getAuthor().getName());
     System.out.println(rsp.getAuthor().getKey());
     System.out.println(rsp.getAuthor().getEmailAddress());
     System.out.println(rsp.getAuthor().getActive());
     System.out.println(rsp.getAuthor().getTimeZone());
    System.out.println(rsp.getBody());
    System.out.println(rsp.getUpdateAuthor().getSelf());
    System.out.println(rsp.getUpdateAuthor().getName());
    System.out.println(rsp.getUpdateAuthor().getKey());
    System.out.println(rsp.getUpdateAuthor().getEmailAddress());
    System.out.println(rsp.getUpdateAuthor().getDisplayName());
    System.out.println(rsp.getUpdateAuthor().getActive());
    System.out.println(rsp.getCreated());
    System.out.println(rsp.getUpdated());
    System.out.println(rsp.getVisibility().getType());
    System.out.println(rsp.getVisibility().getValue());


    
}
}