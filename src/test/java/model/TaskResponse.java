package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskResponse {

    @JsonProperty("id")
    public String id;

    @JsonProperty("content")
    public String content;

    @JsonProperty("priority")
    public int priority;

    @JsonProperty("is_completed")
    public boolean completed;
}
