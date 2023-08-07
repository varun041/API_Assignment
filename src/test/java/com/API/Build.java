package com.API;

import io.restassured.RestAssured;

import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import org.json.JSONArray;

import org.json.JSONObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Build

{
    public static Workbook wb;
    public static FileOutputStream fileOut;

    public static List<String> buildAPI() {

        RestAssured.baseURI = "https://api-cloud.browserstack.com";

        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Basic cHVzaHBlbmRyYWt1bWFyY196U1ZxNWI6aUFVeVhDYkVkTmNZcm5NeDY2Z3E=");

        Response response = request.get("/app-automate/builds.json");

        String responseBody = response.getBody().asString();

        JSONArray buildsArray = new JSONArray(responseBody);

        ArrayList<String> buildId = new ArrayList<String>();
        wb = new XSSFWorkbook();

        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("AppProfiling");

        Row row = sheet.createRow(0);

        row.createCell(0).setCellValue("Build ID");

        for (int i = 0; i < buildsArray.length(); i++) {

            JSONObject buildObject = buildsArray.getJSONObject(i).getJSONObject("automation_build");

            String name = buildObject.getString("name");

            String hashId = buildObject.getString("hashed_id");

            if (name.contains("Brazil Regression")) {

                buildId.add(hashId);

                System.out.println(" Build id for today's date " + buildId);
                Row dataRow;
                dataRow = sheet.createRow(i + 1);

                dataRow.createCell(0).setCellValue(hashId);

            }
        }
        try {
            fileOut = new FileOutputStream("/Users/varunoberoi/Downloads/Book4.xlsx");

            wb.write(fileOut);

            fileOut.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return buildId;

    }

    public static void main(String args[]) {
        buildAPI();
    }

}