package com.mycompany.skillforge;

import java.util.ArrayList;

public class Student {
private ArrayList<Course> EnrolledCourses;
public Student() {
    //super(userId, username, email, passwordHash);
    this.EnrolledCourses = new ArrayList<>();
}
public ArrayList<Course> getEnrolledCourses() {
    return EnrolledCourses;
}

}
