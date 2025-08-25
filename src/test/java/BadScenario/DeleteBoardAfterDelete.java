package BadScenario;
import Utils.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.hamcrest.Matchers.lessThan;

public class DeleteBoardAfterDelete extends Environment {
    @Test
    public void deleteBoardAfterDelete() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/boards/{board_id}")
                .queryParam("key", key)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .pathParam("board_id", boardId)
                .when().delete()
                .then().statusCode(404).time(lessThan(3000L))
                .extract().response();
        response.prettyPrint();
    }
}
