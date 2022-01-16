package com.example.studentslist.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ModelFirebase
{
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    public void getAllStudents(Model.GetAllStudentsListenner listenner) {
        db.collection(Student.CollectionName).get()
                .addOnCompleteListener(task -> {
                    List<Student> list =new LinkedList<Student>();
                    if (task.isSuccessful()) {
                       QuerySnapshot querySnapshot=task.getResult();
                    for (QueryDocumentSnapshot doc :querySnapshot){
                        Student student= Student.create(doc.getData());
                        if (student!=null) {
                            list.add(student);
                        }
                      }
                    }
                    listenner.onComplete(list);
                });
    }

    public void addStudent(Student student, Model.AddStudentListenner listenner) {
        // Create a new user with a first and last name
        Map<String, Object> json = student.toJson();
        // Add a new document with a generated ID
        db.collection(Student.CollectionName)
                .document(student.getId())
                .set(json).addOnSuccessListener(unused -> listenner.onComplete())
                .addOnFailureListener(e -> listenner.onComplete());

    }

    public void getStudentById(String studentId, Model.GetStudentById listenner) {
        db.collection(Student.CollectionName)
                .document(studentId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Student student= null;
                       if (task.isSuccessful() & task.getResult()!=null){
                           student= Student.create(task.getResult().getData());
                       }
                       listenner.onComplete(student);
                    }
                });
    }

    public void editStudent(String studentId, Student s, Model.EditStudentListenner listener)
    {
        DocumentReference student = db.collection(Student.CollectionName).document(studentId);
        student
                .update("name", s.getName(),
                          "flag",s.isFlag());

    }

    public void deleteStudentById(String studentId, Model.DeleteStudentListenner listenner)
    {
        db.collection(Student.CollectionName).document(studentId)
                .delete()
                .addOnSuccessListener(unused->listenner.onComplete())
                .addOnFailureListener(e->listenner.onComplete());
    }
}

