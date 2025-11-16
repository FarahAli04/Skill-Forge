package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Student extends User {
private List<Course> EnrolledCourses;
private List <Lesson> CompletedLessons;
private final JsonDatabaseManager dbManager = new JsonDatabaseManager();
public Student(String userId, String role, String username, String email, String passwordHash) {
    super(userId, role, username, email, passwordHash);
    this.EnrolledCourses = new ArrayList<>();
    this.CompletedLessons = new ArrayList<>();
}
public List<Lesson> getCompletedLessons() {
    return CompletedLessons;
}
public void addEnrolledCourse(Course course) {
    EnrolledCourses.add(course);

}
public void removeEnrolledCourse(Course course) {
    EnrolledCourses.remove(course);
}
public List<Course> getEnrolledCourses() {
    return EnrolledCourses;
}

public void addCompletedLesson(Lesson lesson) {
    CompletedLessons.add(lesson);
    dbManager.updateStudent(this);
}

public static Student fromJsonObject(JSONObject jsonObject) {
    String userId = jsonObject.getString("userId");
    String role = jsonObject.getString("role");
    String username = jsonObject.getString("username");
    String email = jsonObject.getString("email");
    String passwordHash = jsonObject.getString("passwordHash");
    List<Course> courses = new ArrayList<>();
    if ( jsonObject.has("EnrolledCourses") ) {
        JSONArray coursesArray = jsonObject.getJSONArray("EnrolledCourses");
        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject courseJson = coursesArray.getJSONObject(i);
            Course course = Course.fromJsonObject(courseJson);
            courses.add(course);
        }
    }
    Student student = new Student(userId, role, username, email, passwordHash);
    student.EnrolledCourses = courses;
    return student;
}

@Override
public JSONObject toJsonObject() {
    JSONObject jsonObject = super.toJsonObject();
    JSONArray coursesArray = new JSONArray();
    for (Course course : this.EnrolledCourses) {
        coursesArray.put(course.toJsonObject());
    }
    jsonObject.put("EnrolledCourses", coursesArray);
    return jsonObject;
}
}
