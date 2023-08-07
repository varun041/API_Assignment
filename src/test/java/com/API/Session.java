package com.API;

import java.io.FileOutputStream;
import java.util.ArrayList;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;

import org.json.JSONObject;

import io.restassured.RestAssured;

import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;

public class Session extends Build {
    // public static Workbook wb;
    public static FileOutputStream fileOut;

    public static List<String> sessionAPI() {

        List<String> buildIds = buildAPI(); // Use 'super' keyword to call the buildAPI() method from the parent class

        String firstBuildId = buildIds.get(0);

        RestAssured.baseURI = "https://api-cloud.browserstack.com";

        RequestSpecification request = RestAssured.given();

        String path = "/app-automate/builds/" + firstBuildId + "/sessions.json";

        request.header("Authorization", "Basic cHVzaHBlbmRyYWt1bWFyY196U1ZxNWI6aUFVeVhDYkVkTmNZcm5NeDY2Z3E=");

        Response response = request.get(path);

        String responseBody = response.getBody().asString();

        JSONArray sessionsArray = new JSONArray(responseBody);
        List<String> sessionIdList = new ArrayList<>();

        org.apache.poi.ss.usermodel.Sheet sheet = Build.wb.getSheet("AppProfiling");

        for (int i = 0; i < sessionsArray.length(); i++) {
            Row row2;
            row2 = sheet.getRow(0);

            if (row2 == null) {
                row2 = sheet.createRow(i + 1);
            }

            row2.createCell(1).setCellValue("Session ID");
            row2.createCell(2).setCellValue("Device");
            row2.createCell(3).setCellValue("OS");
            row2.createCell(4).setCellValue("duration");
            row2.createCell(5).setCellValue("os_version");
            row2.createCell(6).setCellValue("browser_version");
            row2.createCell(7).setCellValue("project_name");
            row2.createCell(8).setCellValue("reason");
            row2.createCell(9).setCellValue("status");
        }
        for (int i = 0; i < sessionsArray.length(); i++) {

            JSONObject sessionObject1 = sessionsArray.getJSONObject(i).getJSONObject("automation_session");

            System.out.println("Jsonobject: " + sessionObject1);

            String hashed_id = sessionObject1.getString("hashed_id");
            String device = sessionObject1.getString("device");
            String os = sessionObject1.getString("os");
            Integer duration = sessionObject1.getInt("duration");
            Double os_version = sessionObject1.getDouble("os_version");
            String browser_version = sessionObject1.getString("browser_version");
            String project_name = sessionObject1.getString("project_name");
            String reason = sessionObject1.getString("reason");
            String status = sessionObject1.getString("status");

            sessionIdList.add(hashed_id);

            Row dataRow2;

            dataRow2 = sheet.getRow(i + 1);

            if (dataRow2 == null) {
                dataRow2 = sheet.createRow(i + 1);

            }

            dataRow2.createCell(1).setCellValue(hashed_id);
            dataRow2.createCell(2).setCellValue(device);
            dataRow2.createCell(3).setCellValue(os);
            dataRow2.createCell(4).setCellValue(duration);
            dataRow2.createCell(5).setCellValue(os_version);
            dataRow2.createCell(6).setCellValue(browser_version);
            dataRow2.createCell(7).setCellValue(project_name);
            dataRow2.createCell(8).setCellValue(reason);
            dataRow2.createCell(9).setCellValue(status);

        }
        try {
            fileOut = new FileOutputStream("/Users/varunoberoi/Downloads/Book4.xlsx");

            wb.write(fileOut);

            fileOut.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return sessionIdList;

    }

    public static void main(String args[]) {
        sessionAPI();
    }

}