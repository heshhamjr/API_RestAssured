package HappyScenario;
import Utils.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.hamcrest.Matchers.lessThan;

public class CreateBoard extends Environment {
    @Test
    public void createBoard() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/boards/")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "new_board")
                .queryParam("defaultLists", "false")
                .header("Content-Type", "application/json")
                .when().post()
                .then().statusCode(200).time(lessThan(3000L))
                .extract().response();
        response.prettyPrint();

        // Get Board_ID and save it in environment variables
        boardId = response.path("id");

        // To validate board name
        String boardName = response.path("name");
        Assert.assertEquals(boardName, "new_board");
}
}
