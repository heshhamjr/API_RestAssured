package HappyScenario;
import Utils.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.hamcrest.Matchers.lessThan;

public class CreateCard extends Environment {
    @Test
    public void createCard() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/cards")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "My Card")
                .queryParam("idList", listId)
                .header("Content-Type", "application/json")
                .when().post()
                .then().statusCode(200).time(lessThan(3000L))
                .extract().response();
        response.prettyPrint();

        // Get LIST_ID and save it in environment variables
        cardId = response.path("id");

        // To validate list name
        String cardName = response.path("name");
        Assert.assertEquals(cardName, "My Card");
    }
}
