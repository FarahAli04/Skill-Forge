package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {
    private List<String> createdCourses; // List of course IDs
    
    public Instructor(String userId, String username, String email, String passwordHash) {
        super(userId, "instructor", username, email, passwordHash);
        this.createdCourses = new ArrayList<>();
    }
    public Instructor(String userId, String username, String email, String passwordHash) {
        super(userId, "instructor", username, email, passwordHash);
        this.createdCourses = new ArrayList<>();
    }
    
    // Getters and setters
    public List<String> getCreatedCourses() {
        return createdCourses;
    }
    
    public void addCreatedCourse(String courseId) {
        if (!createdCourses.contains(courseId)) {
            createdCourses.add(courseId);
        }
    }
    
    public void removeCreatedCourse(String courseId) {
        createdCourses.remove(courseId);
    }
}