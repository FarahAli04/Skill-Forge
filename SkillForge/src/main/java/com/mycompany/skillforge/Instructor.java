package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class Instructor extends User {
private ArrayList<Course> CreatedCourses;
private final JsonDatabaseManager dbManager = new JsonDatabaseManager();
private Random random = new Random();
public Instructor(String userId, String role, String username, String email, String passwordHash) {
    super(userId, role, username, email, passwordHash);
    this.CreatedCourses = new ArrayList<>();
}
public ArrayList<Course> getCreatedCourses() {
    return CreatedCourses;
}
public void setCreatedCourses(ArrayList<Course> createdCourses) {
    CreatedCourses = createdCourses;
}

public void createCourse( String title, String description) {
    String courseId = "C" + String.format("%04d", random.nextInt(10000));
    Course course = new Course(courseId, title, description, this.getUserId());
    try {
        dbManager.addCourse(course);
         CreatedCourses.add(course);
        dbManager.updateInstructor(this);
    } catch (IllegalArgumentException e) {
       JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


public static Instructor fromJsonObject(JSONObject jsonObject) {
    String userId = jsonObject.getString("userId");
    String role = jsonObject.getString("role");
    String username = jsonObject.getString("username");
    String email = jsonObject.getString("email");
    String passwordHash = jsonObject.getString("passwordHash");
    ArrayList<Course> courses = new ArrayList<>();
    if ( jsonObject.has("CreatedCourses") ) {
        JSONArray coursesArray = jsonObject.getJSONArray("CreatedCourses");
        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject courseJson = coursesArray.getJSONObject(i);
            Course course = Course.fromJsonObject(courseJson);
            courses.add(course);
        }

    }
    Instructor i = new Instructor(userId, role, username, email, passwordHash);
    i.CreatedCourses= courses;
    return i;

}
@Override
public JSONObject toJsonObject() {
    JSONObject jsonObject = super.toJsonObject();
    JSONArray coursesArray = new JSONArray();
    for (Course course : this.CreatedCourses) {
        coursesArray.put(course.toJsonObject());
    }
    jsonObject.put("CreatedCourses", coursesArray);
    return jsonObject;
}
}
