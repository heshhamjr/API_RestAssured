package HappyScenario;
import Utils.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.hamcrest.Matchers.lessThan;

public class GetBoard extends Environment {
    @Test
    public void getBoard() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/boards/{board_id}")
                .queryParam("key", key)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .pathParam("board_id", boardId)
                .when().get()
                .then().statusCode(200).time(lessThan(3000L))
                .extract().response();
        response.prettyPrint();
    }
}
