package model;

public class TaskRequest {
    public String content;
    public String due_string;
    public String due_lang;
    public int priority;

    public TaskRequest(String content, String due_string, String due_lang, int priority) {
        this.content = content;
        this.due_string = due_string;
        this.due_lang = due_lang;
        this.priority = priority;
    }
}
