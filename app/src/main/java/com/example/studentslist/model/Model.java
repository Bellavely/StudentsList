package com.example.studentslist.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    List<Student> data = new ArrayList<Student>();
    public static final Model instance = new Model();

    private Model(){
        for(int i=0; i<10; i++){
            Student s = new Student("name",""+i,false);
            data.add(s);
        }
    }

    public List<Student> getAllStudents() {
        return data;
    }

    public void addStudent(Student student) {
        data.add(student);
    }

    public Student getStudentById(String studentId) {
        for (Student s:data) {
            if(s.getId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }

    public void editStudent(String studentId,Student s){
        for (int i=0; i<data.size(); i++) {
            if(data.get(i).getId().equals(studentId)) {
                data.get(i).setName(s.getName());
                data.get(i).setId(s.getId());
                data.get(i).setFlag(s.isFlag());
                return;
            }
        }
    }

    public void deleteStudentById(String studentId) {
        for (int i=0; i<data.size(); i++) {
            if(data.get(i).getId().equals(studentId)) {
                data.remove(i);
                return;
            }
        }
    }
}
