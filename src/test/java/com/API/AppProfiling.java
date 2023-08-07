package com.API;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.json.JSONArray;

import org.json.JSONObject;

import io.restassured.RestAssured;

import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;

public class AppProfiling extends Session {

    public static void workbook(JSONArray sessionsArray1) {
        try {

            org.apache.poi.ss.usermodel.Sheet sheet = Session.wb.getSheet("AppProfiling");
            for (int i = 0; i < sessionsArray1.length(); i++) {
                Row row1;
                row1 = sheet.getRow(0);

                if (row1 == null) {
                    row1 = sheet.createRow(i + 1);
                }

                row1.createCell(10).setCellValue("ts");
                row1.createCell(11).setCellValue("CPU");
                row1.createCell(12).setCellValue("MEM");
                row1.createCell(13).setCellValue("mema");
                row1.createCell(14).setCellValue("batt");
                row1.createCell(15).setCellValue("temp");

            }
            for (int i = 0; i < sessionsArray1.length(); i++) {

                JSONObject sessionObject1 = sessionsArray1.getJSONObject(i);
                System.out.println("Jsonobject: " + sessionObject1);
                int ts = sessionObject1.getInt("ts");
                int cpu = sessionObject1.getInt("cpu");
                double mem = sessionObject1.getDouble("mem");
                double mema = sessionObject1.getInt("mema");
                int batt = sessionObject1.getInt("batt");
                double temp = sessionObject1.getDouble("temp");
                Row dataRow1;

                dataRow1 = sheet.getRow(i + 1);

                if (dataRow1 == null) {
                    dataRow1 = sheet.createRow(i + 1);
                }

                dataRow1.createCell(10).setCellValue(ts);
                dataRow1.createCell(11).setCellValue(cpu);
                dataRow1.createCell(12).setCellValue(mem);
                dataRow1.createCell(13).setCellValue(mema);
                dataRow1.createCell(14).setCellValue(batt);
                dataRow1.createCell(15).setCellValue(temp);

            }
            FileOutputStream fileOut1 = new FileOutputStream("/Users/varunoberoi/Downloads/Book4.xlsx");

            wb.write(fileOut1);

            fileOut.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static void workbook1(JSONArray sessionsArray1) {
        try {

            org.apache.poi.ss.usermodel.Sheet sheet = Session.wb.getSheet("AppProfiling");

            for (int i = 0; i < sessionsArray1.length(); i++) {
                Row row1;
                row1 = sheet.getRow(0);

                if (row1 == null) {
                    row1 = sheet.createRow(i + 1);
                }
                row1.createCell(10).setCellValue("ts");
                row1.createCell(11).setCellValue("CPU");
                row1.createCell(12).setCellValue("MEM");
                row1.createCell(13).setCellValue("mema");
                row1.createCell(14).setCellValue("batt");
                row1.createCell(15).setCellValue("temp");

            }
            for (int i = 0; i < sessionsArray1.length(); i++) {

                JSONObject sessionObject1 = sessionsArray1.getJSONObject(i);
                System.out.println("Jsonobject: " + sessionObject1);
                int ts = sessionObject1.getInt("ts");
                int cpu = sessionObject1.getInt("cpu");
                double mem = sessionObject1.getDouble("mem");
                double mema = sessionObject1.getInt("mema");
                int batt = sessionObject1.getInt("batt");
                double temp = sessionObject1.getDouble("temp");
                Row currentRow;

                currentRow = sheet.getRow(i + 1);

                int lastRow = sheet.getLastRowNum();
                int nextRow = lastRow + 1;

                currentRow = sheet.getRow(nextRow);
                if (currentRow == null) {

                    currentRow = sheet.createRow(nextRow);
                    lastRow++;
                }

                currentRow.createCell(10).setCellValue(ts);
                currentRow.createCell(11).setCellValue(cpu);
                currentRow.createCell(12).setCellValue(mem);
                currentRow.createCell(13).setCellValue(mema);
                currentRow.createCell(14).setCellValue(batt);
                currentRow.createCell(15).setCellValue(temp);

            }

            FileOutputStream fileOut1 = new FileOutputStream("/Users/varunoberoi/Downloads/Book4.xlsx");

            wb.write(fileOut1);

            fileOut.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static void appprofiling() {
        List<String> buildIds1List = sessionAPI();

        for (int i = 0; i < buildIds1List.size(); i++) {

            if (i == 0) {
                String firstBuildId1 = buildIds1List.get(i);
                RestAssured.baseURI = "https://app-automate.browserstack.com";
                RequestSpecification request = RestAssured.given();
                String path1 = "/api/v1/sessions/" + firstBuildId1 + "/app_profiling";
                request.header("Authorization", "Basic cHVzaHBlbmRyYWt1bWFyY196U1ZxNWI6aUFVeVhDYkVkTmNZcm5NeDY2Z3E=");
                Response response = request.get(path1);
                String responseBody = response.getBody().asString();
                JSONArray sessionsArray1 = new JSONArray(responseBody);
                workbook(sessionsArray1);
            } else {
                String firstBuildId1 = buildIds1List.get(i);
                RestAssured.baseURI = "https://app-automate.browserstack.com";
                RequestSpecification request = RestAssured.given();
                String path1 = "/api/v1/sessions/" + firstBuildId1 + "/app_profiling";
                request.header("Authorization", "Basic cHVzaHBlbmRyYWt1bWFyY196U1ZxNWI6aUFVeVhDYkVkTmNZcm5NeDY2Z3E=");
                Response response = request.get(path1);
                String responseBody = response.getBody().asString();
                JSONArray sessionsArray1 = new JSONArray(responseBody);
                workbook1(sessionsArray1);
            }
        }

    }

    public static void main(String[] args) {

        appprofiling();

    }

}