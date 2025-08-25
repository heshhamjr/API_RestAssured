package BadScenario;
import Utils.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.hamcrest.Matchers.lessThan;

public class DeleteCardAfterDelete extends Environment {
    @Test
    public void deleteCardAfterDelete() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/cards/{card_id}")
                .queryParam("key", key)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .pathParam("card_id", cardId)
                .when().delete()
                .then().statusCode(404).time(lessThan(3000L))
                .extract().response();
        response.prettyPrint();
    }
}
