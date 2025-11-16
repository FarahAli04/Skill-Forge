package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Student {
private List<Course> EnrolledCourses;
public Student() {
    //super(userId, username, email, passwordHash);
    this.EnrolledCourses = new ArrayList<>();
}
public List<Course> getEnrolledCourses() {
    return EnrolledCourses;
}

public static Student fromJsonObject(JSONObject jsonObject) {
    //super.fromJsonObject(jsonObject);
    String userId = jsonObject.getString("userId");
    String role = jsonObject.getString("role");
    String username = jsonObject.getString("username");
    String email = jsonObject.getString("email");
    String passwordHash = jsonObject.getString("passwordHash");

    /*student.setUserId(jsonObject.getString("userId"));
    student.setRole(jsonObject.getString("role"));
    student.setUsername(jsonObject.getString("username"));
    student.setEmail(jsonObject.getString("email"));
    student.setPasswordHash(jsonObject.getString("passwordHash"));*/

    List<Course> courses = new ArrayList<>();
    if ( jsonObject.has("EnrolledCourses") ) {
        JSONArray coursesArray = jsonObject.getJSONArray("EnrolledCourses");
        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject courseJson = coursesArray.getJSONObject(i);
            Course course = Course.fromJsonObject(courseJson);
            courses.add(course);
        }
    }
    return new Student(userId, role, username, email, passwordHash, courses);
}

public JSONObject toJsonObject() {
    //JSONObject jsonObject = super.toJsonObject();
    
    /*jsonObject.put("userId", this.getUserId());
    jsonObject.put("role", this.getRole());
    jsonObject.put("username", this.getUsername());
    jsonObject.put("email", this.getEmail());
    jsonObject.put("passwordHash", this.getPasswordHash());*/
    JSONArray coursesArray = new JSONArray();
    for (Course course : this.EnrolledCourses) {
        coursesArray.put(course.toJsonObject());
    }
    jsonObject.put("EnrolledCourses", coursesArray);
    return jsonObject;
}
}
