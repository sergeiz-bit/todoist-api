package test;

import io.restassured.response.Response;
import model.TaskRequest;
import model.TaskResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.ApiClient;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateTaskTest {

    @ParameterizedTest
    @CsvSource({
            "'Buy Milk', tomorrow at 12:00, en, 1",
            "'Read Book', next Monday at 09:00, en, 2",
            "'Wash Car', , , 3",
            "'Complete Report', , , 4"
    })
    void createTask_withVariousValidInputs_shouldSucceed(String content, String dueString, String dueLang, int priority) {
        TaskRequest taskRequest = new TaskRequest(content, dueString, dueLang, priority);

        Response response = ApiClient.getRequestSpec()
                .body(taskRequest)
                .post("/tasks");

        response.then().statusCode(200);

        TaskResponse task = response.as(TaskResponse.class);
        assertThat(task.id).isNotNull();
        assertThat(task.content).isEqualTo(content);
        assertThat(task.priority).isEqualTo(priority);
        assertThat(task.completed).isFalse();
    }
}
