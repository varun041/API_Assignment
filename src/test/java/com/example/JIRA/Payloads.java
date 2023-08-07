package com.example.JIRA;

public class Payloads {
    
public static String addcomment(){
    return "{\n" + //
            "    \"body\": \"Hi this is my first comment on a JIRA defect\",\n" + //
            "    \"visibility\": {\n" + //
            "        \"type\": \"role\",\n" + //
            "        \"value\": \"Administrators\"\n" + //
            "    }\n" + //
            "}";
}

public static String loginapi(){
    return "{ \"username\": \"varun041\", \"password\": \"Ilanam1234@\" }";
}

public static String createapi(){
    return "{\n" + //
            "    \"fields\": {\n" + //
            "        \"project\": {\n" + //
            "            \"key\": \"MYT\"\n" + //
            "        },\n" + //
            "        \"summary\": \"Login Not working\",\n" + //
            "        \"assignee\": {\n" + //
            "            \"name\": \"varun041\"\n" + //
            "        },\n" + //
            "        \"reporter\": {\n" + //
            "            \"name\": \"varun041\"\n" + //
            "        },\n" + //
            "    \n" + //
            "        \"environment\": \"Test\",\n" + //
            "        \"description\": \"Creating issue number 2\",\n" + //
            "        \"issuetype\":  {\n" + //
            "            \"name\": \"Bug\"\n" + //
            "        }\n" + //
            "    }\n" + //
            "}";
}

}
