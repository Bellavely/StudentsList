package com.example.studentslist.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.studentslist.R;
//import com.example.studentslist.databinding.FragmentSlideshowBinding;
import com.example.studentslist.model.Student;
import  com.example.studentslist.model.Model;
import java.util.List;

public class CreateStudentFragment extends Fragment {
    List<Student> data;

    public CreateStudentFragment() {
        // Required empty public constructor
    }

    public static CreateStudentFragment newInstance() {
        CreateStudentFragment fragment = new CreateStudentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_student, container, false);
        EditText nameEt = view.findViewById(R.id.new_studentName_et);
        EditText idEt = view.findViewById(R.id.new_studentId_et);
        CheckBox cb = view.findViewById(R.id.new_checkBox);

        Button saveBtn = (Button) view.findViewById(R.id.new_save_button);
        saveBtn.setOnClickListener((v)->{
            Student newStudent = new Student(nameEt.getText().toString(), idEt.getText().toString(), cb.isChecked());
            Model.instance.addStudent(newStudent);
            Navigation.findNavController(v).navigate(CreateStudentFragmentDirections.actionCreateStudentFragmentToNavHome());
            return;
        });

        Button cancelBtn = (Button) view.findViewById(R.id.new_cancel_button);
        cancelBtn.setOnClickListener((v)->{
            Navigation.findNavController(v).navigateUp();
        });

        return view;
    }
}