package com.mycompany.skillforge;

import java.util.ArrayList;

public class Instructor {
private ArrayList<Course> CreatedCourses;
public Instructor() {
    //super(userId, username, email, passwordHash);
    this.CreatedCourses = new ArrayList<>();
}
public ArrayList<Course> getCreatedCourses() {
    return CreatedCourses;
}
}
