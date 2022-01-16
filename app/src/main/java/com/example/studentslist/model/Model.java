package com.example.studentslist.model;

import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Model {
    public static final Model instance = new Model();
    Executor executor=Executors.newFixedThreadPool(1);
    Handler mainThread = HandlerCompat.createAsync(Looper.getMainLooper());

    ModelFirebase modelFirebase=new ModelFirebase();
    private Model(){

    }

    public interface GetAllStudentsListenner{
        void onComplete(List<Student> list);
    }
    public interface  GetStudentById{
        void onComplete(Student student);
    }
    public interface AddStudentListenner{
        void onComplete();
    }
    public interface DeleteStudentListenner{
        void onComplete();
    }
    public interface EditStudentListenner{
        void onComplete();
    }


    public void getAllStudents(GetAllStudentsListenner listenner) {
        modelFirebase.getAllStudents(listenner);
    }


    public void addStudent(Student student,AddStudentListenner listener) {
        modelFirebase.addStudent(student,listener);
    }

    public void getStudentById(String studentId, GetStudentById listener) {
        modelFirebase.getStudentById( studentId ,listener);
    }

    public void editStudent(String studentId,Student s,EditStudentListenner listener){
    modelFirebase.editStudent( studentId, s,listener);
    }

    public void deleteStudentById(String studentId,DeleteStudentListenner listener) {
    modelFirebase.deleteStudentById(studentId,listener);
    }
}
