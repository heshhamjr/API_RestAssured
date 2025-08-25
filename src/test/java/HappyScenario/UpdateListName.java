package HappyScenario;
import Utils.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class UpdateListName extends Environment {
    @Test
    public void updateListName() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/lists/{list_id}")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "Update List Name")
                .header("Content-Type", "application/json")
                .pathParam("list_id", listId)
                .when().put()
                .then().statusCode(200).time(lessThan(3000L))
                .extract().response();
        response.prettyPrint();
        String listName = response.path("name");
        Assert.assertEquals(listName, "Update List Name");
    }
}
