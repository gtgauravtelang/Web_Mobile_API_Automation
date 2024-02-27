package org.example;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utilities.constants;
import utilities.jsonReaderCode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class classAPICall {
    @Test
    public String fetchTokenBookStore() {
        Response responseT = given()
                .header("Content-Type", constants.CONTENT_TYPE)
                .header("accept", constants.ACCEPT)
                .body("{\"userName\":\"gtadmin2\",\"password\":\"Emov@123\"}")
                .when()
                .post("https://bookstore.toolsqa.com/Account/v1/GenerateToken");
        assertEquals(responseT.statusCode(),200);
        String newtoken = responseT.path("token");
        System.out.println("Token at fetchTokenBookStore: "+newtoken);
        return newtoken;
    }

    @Test
    public void getreqresdata1() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200);
        System.out.println("TC 1 Passed");
    }

    @Test
    public void getreqresdata2() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200);
        System.out.println("TC 2 Passed");
    }

    @Test
    public void postBooksWithToken()
    {
        String fetchedToken=fetchTokenBookStore();
        System.out.println("Token at postBooksWithToken : "+fetchedToken);
        Response resp=given()
                .header("Content-Type", constants.CONTENT_TYPE)
                .header("Authorization","Bearer "+fetchedToken)
                .body("{\"userId\":\"gtadmin2\",\"collectionOfIsbns\":[{\"isbn\":" +
                        "\"9781593277574\"}]}")
                .when()
                .post("https://bookstore.toolsqa.com/BookStore/v1/Books");
        assertEquals(resp.statusCode(), 401);
        System.out.println("Message is:"+resp.path("message"));
        System.out.println("Code is : "+resp.path("code"));
    }

    @Test
    public void JsonpathResponseCheck()
    {
        Response resp2=given()
                .when().get("https://reqres.in/api/users?page=2");
        String str1 = resp2.body().path("data[4].first_name");
        System.out.println(resp2.body().path("data[4]").toString());
        System.out.println(str1);
    }

    @Test
    public void checkAnotherJSON() {
        Response respa = given()
                .when().get("https://reqres.in/api/users?delay=3");
        assertEquals(respa.statusCode(), 200);
        assertEquals(respa.path("data[1].last_name"), "Weaver", "Not Matched");
    }

    @Test(priority = -1)
    public void jsonSchemaValidator()
    {
        File jsonschema = new File("resources/jsonExpectedSchema.json");
        given()
                .when().get("https://reqres.in/api/users")
                .then().assertThat().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(jsonschema));
    }

    @Test(priority = -2)
    public void keyPairTestData() throws IOException, ParseException {
        int i=0;
        ArrayList<String> strArr = new ArrayList<String>();
        for(i=0; i<6; i++) {
            strArr.add(jsonReaderCode.getTestData("url["+i+"]"));
        }
        i=0;
        for (String x: strArr)
        {
            Response resT = given().when().get(x);
            //Intentionally failing 6th URL
            assertEquals(resT.statusCode(), 200, "Not Matched");
            System.out.println("Url."+(i+1)+" "+x);
            i++;
        }
    }
}
