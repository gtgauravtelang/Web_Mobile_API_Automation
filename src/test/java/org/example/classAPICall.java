package org.example;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.constants;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

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
    public void wrapperClassTests() {
        int i, j, k;
        i = 10;
        String str1 = "786";
        j = Integer.parseInt(str1);
        k = i + j;
//        str1=String.valueOf(k);
//        System.out.println(str1.charAt(1));
        assertEquals(k, 796);
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
        assertEquals(respa.path("data[1].last_name"), "Ferguson", "Not Matched");
    }
}
