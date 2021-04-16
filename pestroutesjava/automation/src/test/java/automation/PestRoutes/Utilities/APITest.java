package automation.PestRoutes.Utilities;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class APITest {
    public static void main(String[] args) {
        //Add Place
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String responseAdd = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(body()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
                .body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        JsonPath js = new JsonPath(responseAdd);
        String placeID = js.getString("place_id");
        System.out.println(placeID);

        //Update Place
        String currentAddress = "2314 Farrington Dr, USA";
        given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\""+currentAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").when().put("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));
        System.out.println(placeID);
        //Get Place
        String responseGet = given().queryParam("key", "qaclick123").queryParam("place_id", placeID)
                .when().get("/maps/api/place/get/json").then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = new JsonPath(responseGet);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);

        Assert.assertEquals(actualAddress, currentAddress);

    }

    public static String body() {
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Pest\",\n" +
                "  \"phone_number\": \"(+1) 469 274 8946\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"QA\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://rahulshettyacademy.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }

}
