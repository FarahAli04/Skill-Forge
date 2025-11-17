package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class Course {

    private String courseId;
    private String title;
    private String description;
    private String instructorId;
    private List<Lesson> lessons;
    private List<Student> Students;
    private final JsonDatabaseManager dbManager = new JsonDatabaseManager();
    private Random random = new Random();

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
    public Course(String courseId, String title, String description, String instructorId) {
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

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
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
// Methods to manage students
// adding and removing students from the course

    public boolean isStudentEnrolled(Student student) {
        return Students.contains(student);
    }

    public void enrollStudent(Student student) {
        Students.add(student);
        student.addEnrolledCourse(this.courseId);
        dbManager.updateStudent(student);
        dbManager.updateCourse(this);
        JOptionPane.showMessageDialog(null, "Student enrolled successfully.");
    }

    public void unenrollStudent(Student student) {
        Students.remove(student);
        student.removeEnrolledCourse(this.courseId);
        dbManager.updateStudent(student);
        dbManager.updateCourse(this);
        JOptionPane.showMessageDialog(null, "Student unenrolled successfully.");
    }

    public void createLesson(String title, String content) {
        String lessonId = "L" + String.format("%04d", random.nextInt(10000));
        Lesson lesson = new Lesson(lessonId, title, content, this.courseId);
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

    public void DeleteCourse() {
        dbManager.deleteCourse(this.courseId);
        JOptionPane.showMessageDialog(null, "Course deleted successfully.");
    }

    public static Course fromJsonObject(JSONObject jsonObject) {
        String courseId = jsonObject.getString("courseId");
        String title = jsonObject.getString("title");
        String description = jsonObject.getString("description");
        String instructorId = jsonObject.getString("instructorId");

        List<Lesson> lessons = new ArrayList<>();
        if (jsonObject.has("lessons")) {
            JSONArray lessonsArray = jsonObject.getJSONArray("lessons");
            for (int i = 0; i < lessonsArray.length(); i++) {
                JSONObject lessonObject = lessonsArray.getJSONObject(i);
                Lesson lesson = Lesson.fromJsonObject(lessonObject);
                lessons.add(lesson);
            }
        }

        List<Student> students = new ArrayList<>();
        if (jsonObject.has("Students")) {
            JSONArray studentsArray = jsonObject.getJSONArray("Students");
            for (int i = 0; i < studentsArray.length(); i++) {
                JSONObject studentObject = studentsArray.getJSONObject(i);
                Student student = Student.fromJsonObject(studentObject);
                students.add(student);
            }
        }
        Course course = new Course(courseId, title, description, instructorId);
        course.setLessons(lessons);
        course.setStudents(students);
        return course;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", this.courseId);
        jsonObject.put("title", this.title);
        jsonObject.put("description", this.description);
        jsonObject.put("instructorId", this.instructorId);
        JSONArray lessonsArray = new JSONArray();
        for (Lesson lesson : this.lessons) {
            lessonsArray.put(lesson.toJsonObject());
        }
        jsonObject.put("lessons", lessonsArray);
        JSONArray studentsArray = new JSONArray();
        for (Student student : this.Students) {
            studentsArray.put(student.toJsonObject());
        }
        jsonObject.put("Students", studentsArray);
        return jsonObject;
    }

}
