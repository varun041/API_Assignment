package com.example.Library;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.example.AddPlaceData;
import com.example.Reuse;

public class AddBookJson {
    
    static String URL = "http://216.10.245.166";
    public static String bookid;

    @Test()
    public void addbook() throws IOException {
        RestAssured.baseURI = URL;
        String bookresponse = given()
                .log()
                .all()
                .header("Content-Type", "application/json")
                .when()
                .body(new String(Files.readAllBytes(Paths.get("/Users/varunoberoi/Java/demo/src/test/java/com/example/Library/BookData.json"))))
                .post("Library/Addbook.php")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        JsonPath js = Reuse.reuse(bookresponse);
        String id = js.getString("ID");
        System.out.println(id);
        bookid = id;
    }
}
