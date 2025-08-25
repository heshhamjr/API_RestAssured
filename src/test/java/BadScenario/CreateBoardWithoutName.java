package BadScenario;
import Utils.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.hamcrest.Matchers.lessThan;

public class CreateBoardWithoutName extends Environment {
    @Test
    public void createBoardWithoutName() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/boards/")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("defaultLists", "false")
                .header("Content-Type", "application/json")
                .when().post()
                .then().statusCode(400).time(lessThan(3000L))
                .extract().response();
        response.prettyPrint();


    }
}
