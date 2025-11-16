package com.mycompany.skillforge;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Instructor {
private ArrayList<Course> CreatedCourses;
public Instructor() {
    //super(userId, username, email, passwordHash);
    this.CreatedCourses = new ArrayList<>();
}
public ArrayList<Course> getCreatedCourses() {
    return CreatedCourses;
}


public static Instructor fromJsonObject(JSONObject jsonObject) {
     //super.fromJsonObject(jsonObject);
    /*instructor.setUserId(jsonObject.getString("userId"));
    instructor.setRole(jsonObject.getString("role"));
    instructor.setUsername(jsonObject.getString("username"));
    instructor.setEmail(jsonObject.getString("email"));
    instructor.setPasswordHash(jsonObject.getString("passwordHash"));*/
    ArrayList<Course> courses = new ArrayList<>();
    if ( jsonObject.has("CreatedCourses") ) {
        JSONArray coursesArray = jsonObject.getJSONArray("CreatedCourses");
        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject courseJson = coursesArray.getJSONObject(i);
            Course course = Course.fromJsonObject(courseJson);
            courses.add(course);
        }

    }
    return new Instructor(userId, role, username, email, passwordHash, courses);

}

public JSONObject toJsonObject() {
    //JSONObject jsonObject = super.toJsonObject();

    /*jsonObject.put("userId", this.getUserId());
    jsonObject.put("role", this.getRole());
    jsonObject.put("username", this.getUsername());
    jsonObject.put("email", this.getEmail());
    jsonObject.put("passwordHash", this.getPasswordHash());*/
    JSONArray coursesArray = new JSONArray();
    for (Course course : this.CreatedCourses) {
        coursesArray.put(course.toJsonObject());
    }
    jsonObject.put("CreatedCourses", coursesArray);
    return jsonObject;


}
}
