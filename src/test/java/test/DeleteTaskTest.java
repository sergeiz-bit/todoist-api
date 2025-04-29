package test;

import io.restassured.response.Response;
import model.TaskRequest;
import model.TaskResponse;
import org.junit.jupiter.api.Test;
import util.ApiClient;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTaskTest {

    @Test
    void deleteTask_withValidId_shouldSucceed() {
        TaskRequest request = new TaskRequest("Buy Milk", "tomorrow at 12:00", "en", 4);
        TaskResponse createdTask = ApiClient.getRequestSpec()
                .body(request)
                .post("/tasks")
                .as(TaskResponse.class);

        assertThat(createdTask.id).isNotNull();

        Response deleteResponse = ApiClient.getRequestSpec()
                .delete("/tasks/" + createdTask.id);

        deleteResponse.then().statusCode(204);

        Response getDeleted = ApiClient.getRequestSpec()
                .get("/tasks/" + createdTask.id);

        getDeleted.then().statusCode(404);
    }
}
