package test;

import io.restassured.response.Response;
import model.TaskRequest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import util.ApiClient;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateTaskNegativeTest {

    @ParameterizedTest
    @MethodSource("invalidTaskInputs")
    void createTask_withInvalidInputs_shouldFail(TaskRequest request) {
        Response response = ApiClient.getRequestSpec()
                .body(request)
                .post("/tasks");

        assertThat(response.statusCode()).isEqualTo(400);
    }

    static Stream<TaskRequest> invalidTaskInputs() {
        return Stream.of(
                new TaskRequest(null, "tomorrow at 12:00", "en", 4),        // Missing content
                new TaskRequest("", "tomorrow at 12:00", "en", 4)         // Empty content
        );
    }
}
