package test;
import io.restassured.response.Response;
import model.TaskRequest;
import model.TaskResponse;
import org.junit.jupiter.api.Test;
import util.ApiClient;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateTaskTest {

    @Test
    void updateTask_withValidData_shouldSucceed() {
        TaskRequest createRequest = new TaskRequest("Buy Milk", "tomorrow at 12:00", "en", 4);
        TaskResponse createdTask = ApiClient.getRequestSpec()
                .body(createRequest)
                .post("/tasks")
                .as(TaskResponse.class);

        assertThat(createdTask.id).isNotNull();

        String updatedContent = "Buy Coffee";
        Response updateResponse = ApiClient.getRequestSpec()
                .body("{\"content\": \"" + updatedContent + "\"}")
                .post("/tasks/" + createdTask.id);

        updateResponse.then().statusCode(200);

        TaskResponse updatedTask = ApiClient.getRequestSpec()
                .get("/tasks/" + createdTask.id)
                .as(TaskResponse.class);

        assertThat(updatedTask.content).isEqualTo(updatedContent);
    }
}
