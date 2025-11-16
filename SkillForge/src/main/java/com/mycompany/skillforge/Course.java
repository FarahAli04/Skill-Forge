package com.mycompany.skillforge;


import java.util.List;

import javax.swing.JOptionPane;

public class Course {
private String courseId;
private String title;
private String description;
private int instructorId;
private List<Lesson> lessons;
private List<Student> Students;

public Course(String courseId, String title, String description, int instructorId, List<Lesson> lessons, List<Student> Students) {
    this.courseId = courseId;
    this.title = title;
    this.description = description;
    this.instructorId = instructorId;
    this.lessons = lessons;
    this.Students = Students;
    // Save to database 
}
public Course(String courseId, String title, String description, int instructorId , List<Lesson> lessons) {
    this.courseId = courseId;
    this.title = title;
    this.description = description;
    this.instructorId = instructorId;
    this.lessons = lessons;
     // Save to database 
}
public Course(String courseId, String title, String description, int instructorId) {
    this.courseId = courseId;
    this.title = title;
    this.description = description;
    this.instructorId = instructorId;
     // Save to database 
}
public String getCourseId() {
    return courseId;
}
public void setCourseId(String courseId) {
    this.courseId = courseId;
}
public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public int getInstructorId() {
    return instructorId;
}
public void setInstructorId(int instructorId) {
    this.instructorId = instructorId;
}
public List<Lesson> getLessons() {
    return lessons;
}
public void setLessons(List<Lesson> lessons) {
    this.lessons = lessons;
}
public List<Student> getStudents() {
    return Students;
}
public void setStudents(List<Student> Students) {
    this.Students = Students;
} 
public boolean isStudentEnrolled(Student student) {
    return Students.contains(student);
}
// Methods to manage students
// adding and removing students from the course

public void addStudent(Student student) {
    Students.add(student);
    // update database 
}
public void removeStudent(Student student) {
    Students.remove(student);
    // update database 
}
public void enrollStudent(Student student) {
    addStudent(student);
    JOptionPane.showMessageDialog(null, "Student enrolled successfully.");
}
public void unenrollStudent(Student student) {
    removeStudent(student);
    JOptionPane.showMessageDialog(null, "Student unenrolled successfully.");
}
// Instructor can delete the course

public void DeleteCourse() {
  // delete from database 
    JOptionPane.showMessageDialog(null, "Course deleted successfully.");
}
}