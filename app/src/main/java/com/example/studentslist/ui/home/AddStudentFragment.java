package com.example.studentslist.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.studentslist.R;
//import com.example.studentslist.databinding.FragmentSlideshowBinding;
import com.example.studentslist.model.Student;
import  com.example.studentslist.model.Model;
import java.util.List;

public class AddStudentFragment extends Fragment {
        EditText nameEt;
        EditText idEt;
        CheckBox cb;
        ProgressBar progressBar;



    public static AddStudentFragment newInstance() {
        AddStudentFragment fragment = new AddStudentFragment();
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
        nameEt = view.findViewById(R.id.new_studentName_et);
        idEt = view.findViewById(R.id.new_studentId_et);
        cb = view.findViewById(R.id.new_checkBox);

        progressBar=view.findViewById(R.id.main_progrees_bar);
        progressBar.setVisibility(View.GONE);

        Button saveBtn = (Button) view.findViewById(R.id.new_save_button);
        saveBtn.setOnClickListener((v)->{
            progressBar.setVisibility(view.VISIBLE);
            Student student = new Student(nameEt.getText().toString(), idEt.getText().toString(), cb.isChecked());
            Model.instance.addStudent(student,()->{
               Navigation.findNavController(nameEt).navigateUp();
            });
            return;
        });

        Button cancelBtn = (Button) view.findViewById(R.id.new_cancel_button);
        cancelBtn.setOnClickListener((v)->{
            Navigation.findNavController(v).navigateUp();
        });

        return view;
    }

}