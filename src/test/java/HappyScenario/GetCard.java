package HappyScenario;
import Utils.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.hamcrest.Matchers.lessThan;

public class GetCard extends Environment {
    @Test
    public void getCard() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/cards/{card_id}")
                .queryParam("key", key)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .pathParam("card_id", cardId)
                .when().get()
                .then().statusCode(200).time(lessThan(3000L))
                .extract().response();
        response.prettyPrint();
    }
}
