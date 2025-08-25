package HappyScenario;
import Utils.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class GetList extends Environment {
    @Test
    public void getList() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/lists/{list_id}")
                .queryParam("key", key)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .pathParam("list_id", listId)
                .when().get()
                .then().statusCode(200).time(lessThan(3000L))
                .extract().response();
        response.prettyPrint();
    }
}
