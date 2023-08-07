package com.example.Library;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.example.AddPlaceData;
import com.example.Reuse;

public class AddBook {
    static String URL = "http://216.10.245.166";
    public static String bookid;

    @Test(dataProvider = "Booksdata")
    public void addbook(String isbn, String aisle) {
        RestAssured.baseURI = URL;
        String bookresponse = given()
                .log()
                .all()
                .header("Content-Type", "application/json")
                .when()
                .body(AddPlaceData.addbook(isbn, aisle))
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

    @DataProvider(name = "Booksdata")
    public Object[][] getData() {
        return new Object[][] { { "cce", "245" }, { "cce", "246" }, { "cce", "247" } };
    }

    @Test
    public void getbook() {
        RestAssured.baseURI = URL;
        String getbookrsp = given()
                .log()
                .all()
                .queryParam("ID", bookid)
                .when()
                .get("Library/GetBook.php")
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
