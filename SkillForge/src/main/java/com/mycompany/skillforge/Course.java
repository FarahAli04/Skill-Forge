package com.mycompany.skillforge;


import java.util.List;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class Course {
private String courseId;
private String title;
private String description;
private int instructorId;
private List<Lesson> lessons;
private List<Student> Students;
private JsonDatabaseManager dbManager = new JsonDatabaseManager();

/*public Course(String courseId, String title, String description, int instructorId, List<Lesson> lessons, List<Student> Students) {
    this.courseId = courseId;
    this.title = title;
    this.description = description;
    this.instructorId = instructorId;
    this.lessons = lessons;
    this.Students = Students;
    // Save to database (not needed because saved when creating lessons and enrolling students)
}
public Course(String courseId, String title, String description, int instructorId , List<Lesson> lessons) {
    this.courseId = courseId;
    this.title = title;
    this.description = description;
    this.instructorId = instructorId;
    this.lessons = lessons;
     // Save to database (not needed because saved when creating lessons and enrolling students)
}*/
// because we will create lessons and enroll students later

public Course(String courseId, String title, String description, int instructorId) {
    this.courseId = courseId;
    this.title = title;
    this.description = description;
    this.instructorId = instructorId;
     // Save to database (not needed because saved when creating lessons and enrolling students)
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
    dbManager.updateCourse(this);
    dbManager.updateStudent(student);
}
public void removeStudent(Student student) {
    Students.remove(student);
    dbManager.updateCourse(this);
    dbManager.updateStudent(student);
}
public void enrollStudent(Student student) {
    addStudent(student);
    JOptionPane.showMessageDialog(null, "Student enrolled successfully.");
}
public void unenrollStudent(Student student) {
    removeStudent(student);
    JOptionPane.showMessageDialog(null, "Student unenrolled successfully.");
}
public void createLesson(String title, String content) {
    String lessonId = "L" + (lessons.size() + 1);
    Lesson lesson = new Lesson(lessonId, title, content);
    lessons.add(lesson);
    dbManager.updateCourse(this);
}
public void removeLesson(Lesson lesson) {
    lessons.remove(lesson);
    dbManager.updateCourse(this);
}
public void updateLesson(Lesson lesson) {
    for (int i = 0; i < lessons.size(); i++) {
        if (lessons.get(i).getLessonId().equals(lesson.getLessonId())) {
            lessons.set(i, lesson);
            break;
        }
    }
    dbManager.updateCourse(this);
}
// Instructor can delete the course

/*private void dbManagerupdateCourse(Course course) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'dbManagerupdateCourse'");
}*/ // what is this for?

public void DeleteCourse() {
    dbManager.deleteCourse(this.courseId);
    JOptionPane.showMessageDialog(null, "Course deleted successfully.");
}

public static Course fromJsonObject(JSONObject jsonObject) {
    String courseId = jsonObject.getString("courseId");
    String title = jsonObject.getString("title");
    String description = jsonObject.getString("description");
    int instructorId = jsonObject.getInt("instructorId");
    return new Course(courseId, title, description, instructorId);

}

public JSONObject toJsonObject() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("courseId", this.courseId);
    jsonObject.put("title", this.title);
    jsonObject.put("description", this.description);
    jsonObject.put("instructorId", this.instructorId);
    return jsonObject;
}

}
}