package com.example.JIRA;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.File;

import com.example.Reuse;
public class AddComment {
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
   
//Creating an issue

String createresponse =
given()
.header("Content-Type","application/json")
.body(Payloads.createapi())
.filter(sf)
.when()
.post("/rest/api/2/issue")
.then()
.log()
.all()
.assertThat()
.statusCode(201)
.extract()
.response()
.asString();

JsonPath js2 = Reuse.reuse(createresponse);
String jiraid = js2.getString("id");
System.out.println(jiraid);
jira_id = jiraid;


    //Add a comment
    
    String addcomentresp=given()
    .pathParam("issueIdOrKey",jira_id)
    .header("Content-Type","application/json")
    //.header("Cookie",session_id)
    .body(Payloads.addcomment())
    .filter(sf)
    .when()
    .log()
    .all()
    .post("/rest/api/2/issue/{issueIdOrKey}/comment")
    .then()
    .log()
    .all()
    .assertThat()
    .statusCode(201)
    .extract()
    .response()
    .asString();

    JsonPath js4 = Reuse.reuse(addcomentresp);
    String comment_id = js4.getString("id");
    System.out.println(comment_id);




    // Adding an attachement
    given()
    .pathParam("issueIdOrKey",jira_id)
    .header("X-Atlassian-Token","no-check")
    .header("Content-Type","multipart/form-data")
    .filter(sf)
    .multiPart("file", new File("/Users/varunoberoi/Java/demo/src/test/java/com/example/JIRA/attachment.txt"))
    .when()
    .post("/rest/api/2/issue/{issueIdOrKey}/attachments")
    .then()
    .log()
    .all()
    .assertThat()
    .statusCode(200);

    //Get Issue

    String getissueresponse =given()
    .pathParam("issueIdOrKey",jira_id)
    .queryParam("fields", "comment")
    .filter(sf)
    .when()
    .log()
    .all()
    .get("/rest/api/2/issue/{issueIdOrKey}")
    .then()
    .log()
    .all()
    .assertThat()
    .statusCode(200)
    .extract()
    .response()
    .asString();

    JsonPath js3 = Reuse.reuse(getissueresponse);
    String commentid = js3.getString("fields.comment.comments[0].id");
    
    System.out.println(commentid);
    int comments = js3.getInt("fields.comment.comments.size()");

    for(int i =0;i<comments;i++){
        String comment = js3.getString("fields.comment.comments["+i+"].id");
        String body = js3.getString("fields.comment.comments["+i+"].body");
       // System.out.println(body);
if(comment_id.equalsIgnoreCase(comment)){  
    System.out.println(body);
}
    }



    
}



}
