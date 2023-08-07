package com.example.POJO_2;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBodyExtractionOptions;

import com.example.Reuse;
import com.example.JIRA.Payloads;
import com.example.POJO.Response;
import io.restassured.parsing.Parser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

public class GetDefect {
    public static String session_id;
    public static String jira_id;

    public static void main(String args[]) {
        RestAssured.baseURI = "http://localhost:8080";
        SessionFilter sf = new SessionFilter();

        // Login

        String loginresponse = given()
                .header("Content-Type", "application/json")
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

        GetDefectGetSet gedef = given()
                .pathParam("issueIdOrKey", "10003")
                .queryParam("fields", "comment")
                .filter(sf)
                .expect()
                .defaultParser(Parser.JSON)
                .when()
                .get("/rest/api/2/issue/{issueIdOrKey}")
                .as(GetDefectGetSet.class);
        System.out.println(gedef.getExpand());
        System.out.println(gedef.getId());
        System.out.println(gedef.getSelf());
        System.out.println(gedef.getKey());
        System.out.println(gedef.getFields().getComment().getMaxResults());
        System.out.println(gedef.getFields().getComment().getTotal());
        System.out.println(gedef.getFields().getComment().getStartAt());


        for(int i =0;i<gedef.getFields().getComment().getComments().size();i++){
         System.out.println(gedef.getFields().getComment().getComments().get(i).getSelf());
         System.out.println(gedef.getFields().getComment().getComments().get(i).getId());
          System.out.println(gedef.getFields().getComment().getComments().get(i).getBody());   
        }


       List <Comments> cm = gedef.getFields().getComment().getComments();
       for(int j=0;j<cm.size();j++){
        if(gedef.getFields().getComment().getComments().get(j).getBody().equals("Hi this is my first comment on a JIRA defect")){
           System.out.println(gedef.getFields().getComment().getComments().get(j).getId()); 
           break;
}
       /* 
         System.out.println(gedef.getFields().getComment().getComments().get(0).getId());
          System.out.println(gedef.getFields().getComment().getComments().get(0).getBody());
           System.out.println(gedef.getFields().getComment().getComments().get(0).getAuthor().getSelf());
            System.out.println(gedef.getFields().getComment().getComments().get(0).getAuthor().getName());
             System.out.println(gedef.getFields().getComment().getComments().get(0).getAuthor().getKey());
             System.out.println(gedef.getFields().getComment().getComments().get(0).getUpdated());
             System.out.println(gedef.getFields().getComment().getComments().get(0).getCreated());
             System.out.println(gedef.getFields().getComment().getComments().get(0).getVisibility().getType());
             System.out.println(gedef.getFields().getComment().getComments().get(0).getVisibility().getValue());
             */
       }
    }

}
