package com.example;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.json.JSONArray;

public class ComplexJsonParse {
    
    
    public static void main(String args[]){
    JsonPath js = new JsonPath( AddPlaceData.complexdata());
    int totalcourses = js.getInt("courses.size()");
    System.out.println(totalcourses);
    int purchaseamount = js.getInt("dashboard.purchaseAmount");
     System.out.println(purchaseamount);

    //title of the first course
    
    String firsttitle = js.getString("courses[2].title");
     System.out.println(firsttitle);


     //Print All course title and their prices
     for(int i =0;i<totalcourses;i++){
       String titles = js.getString("courses["+i+"].title");
       int price = js.getInt("courses["+i+"].price");
       System.out.println(titles);
       System.out.println(price);
     }
System.out.println("Total copies of RPA");
     for(int i =0;i<totalcourses;i++){
        String title = js.getString("courses["+i+"].title");
    if(title.equalsIgnoreCase("RPA")){
        int copies = js.getInt("courses["+i+"].copies");
        System.out.println(copies);
        break;
    }
  
     }

        int finalsum = 0;
        
        for(int i =0;i<totalcourses;i++){
 
int copies = js.getInt("courses["+i+"].copies");
int price = js.getInt("courses["+i+"].price");
int totalsum = copies*price;
System.out.println(totalsum);
finalsum = finalsum+totalsum;
     }
System.out.println(finalsum);
     //Verify if sum of all course  price matches with purchase amount

if(finalsum==purchaseamount){
    System.out.println("Match");
}
else{
System.out.println(" No Match");
} 

    }

}
