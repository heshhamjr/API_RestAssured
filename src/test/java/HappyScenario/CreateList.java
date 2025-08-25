package HappyScenario;
import Utils.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.hamcrest.Matchers.lessThan;

public class CreateList extends Environment {
    @Test
    public void createList() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/lists")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "New List")
                .queryParam("idBoard", boardId)
                .header("Content-Type", "application/json")
                .when().post()
                .then().statusCode(200).time(lessThan(3000L))
                .extract().response();
        response.prettyPrint();

        // Get LIST_ID and save it in environment variables
        listId = response.path("id");

        // To validate list name
        String listName = response.path("name");
        Assert.assertEquals(listName, "New List");
    }
}
