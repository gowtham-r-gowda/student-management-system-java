package service;

import model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private List<Student> students = new ArrayList<>();
    private final String filePath;

    public StudentManager(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    public void addStudent(Student s) {
        if (getStudentById(s.getId()) != null) {
        System.out.println("Student with this ID already exists!");
        return;
    }
        students.add(s);
        saveToFile();
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean deleteStudent(int id) {
        Student s = getStudentById(id);
        if (s != null) {
            students.remove(s);
            saveToFile();
            return true;
        }
        return false;
    }

    public boolean updateStudent(Student updated) {
        for (Student s : students) {
            if (s.getId() == updated.getId()) {
                s.setName(updated.getName());
                s.setAge(updated.getAge());
                s.setCourse(updated.getCourse());
                saveToFile();
                return true;
            }
        }
        return false;
    }

    private void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(students);
            oos.close();
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        try {
            File f = new File(filePath);
            if (!f.exists()) return;

            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            students = (List<Student>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("No saved data found yet.");
        }
    }
}
