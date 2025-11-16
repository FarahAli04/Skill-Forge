package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Student extends User {

    private List<String> EnrolledCourses;
    private List<Progress> progress;
    private final JsonDatabaseManager dbManager = new JsonDatabaseManager();

    public Student(String userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
        this.EnrolledCourses = new ArrayList<>();
        this.progress = new ArrayList<>();
    }

   public List <Progress> getProgress() {
        return progress;
    }

    public void addEnrolledCourse(String courseId) {
        EnrolledCourses.add(courseId);
        progress.add( new Progress(courseId));

    }

    public void removeEnrolledCourse(String courseId) {
        EnrolledCourses.remove(courseId);
    }

    public List<String> getEnrolledCourses() {
        return EnrolledCourses;
    }

    public void addCompletedLesson(Lesson lesson) {
        for ( Progress p : progress ) {
            if (p.getCourseId().equals(lesson.getCourseId()))
            {
                for (String Lid : p.getLessonId())
                {
                    if ( Lid.equals(lesson.getLessonId()))
                    return;
                    else 
                    {
                        p.addLesson(Lid);
                    }
                }
            }
        }
            dbManager.updateStudent(this);
        }
    

    public static Student fromJsonObject(JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        String role = jsonObject.getString("role");
        String username = jsonObject.getString("username");
        String email = jsonObject.getString("email");
        String passwordHash = jsonObject.getString("passwordHash");
        List<String> courses = new ArrayList<>();
        if (jsonObject.has("EnrolledCourses")) {
            JSONArray coursesArray = jsonObject.getJSONArray("EnrolledCourses");
            for (int i = 0; i < coursesArray.length(); i++) {
                JSONObject courseJson = coursesArray.getJSONObject(i);
                String course = courseJson.getString("courseId");
                courses.add(course);
            }
        }
        List<Progress> progress = new ArrayList<>();
        if (jsonObject.has("Progress")) {
            JSONArray lessonsArray = jsonObject.getJSONArray("Progress");
            for (int i = 0; i < lessonsArray.length(); i++) {
                JSONObject progressJson = lessonsArray.getJSONObject(i);
                Progress p = Progress.fromJsonObject(progressJson);
                progress.add(p);
            }
        }
        Student student = new Student(userId, role, username, email, passwordHash);
        student.EnrolledCourses = courses;
        student.progress = progress;
        return student;
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = super.toJsonObject();
        JSONArray coursesArray = new JSONArray();
        for (String courseId : this.EnrolledCourses) {
            JSONObject courseJson = new JSONObject();
            courseJson.put("courseId", courseId);
            coursesArray.put(courseJson);
        }
        jsonObject.put("EnrolledCourses", coursesArray);
        JSONArray progressArray = new JSONArray();
        for (Progress p : this.progress) {
            progressArray.put(p.toJsonObject());
        }
        jsonObject.put("Progress", progressArray);

        return jsonObject;
    }
}
