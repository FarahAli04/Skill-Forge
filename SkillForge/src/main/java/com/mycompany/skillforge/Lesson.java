package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Lesson {

    private String lessonId;
    private String title;
    private String content;
    private List<String> OpResources;
    private boolean isCompleted;

    public Lesson() {
        this.OpResources = new ArrayList<>();
        this.isCompleted = false;

    }

    public Lesson(String lessonId, String title, String content) {
        this();
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getResources() {
        return OpResources;
    }

    public void addResource(String resource) {
        this.OpResources.add(resource);
    }

    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lessonId", this.lessonId);
        jsonObject.put("title", this.title);
        jsonObject.put("content", this.content);
        jsonObject.put("OpResources", this.OpResources);
        jsonObject.put("isCompleted", this.isCompleted);
        return jsonObject;
    }

    public static Lesson fromJsonObject(JSONObject jsonObject) {
        Lesson lesson = new Lesson();
        lesson.setLessonId(jsonObject.getString("lessonId"));
        lesson.setTitle(jsonObject.getString("title"));
        lesson.setContent(jsonObject.getString("content"));
        List<String> resources = new ArrayList<>();
        if (jsonObject.has("OpResources")) {
            for (Object resource : jsonObject.getJSONArray("OpResources")) {
                resources.add((String) resource);
            }
        }
        lesson.OpResources = resources;
        lesson.setCompleted(jsonObject.getBoolean("isCompleted"));
        return lesson;
    }

    
}
