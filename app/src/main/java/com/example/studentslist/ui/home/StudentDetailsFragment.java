package com.example.studentslist.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.studentslist.model.Student;
import com.example.studentslist.model.Model;
import com.example.studentslist.R;


import java.util.List;

public class StudentDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_STUDENT_ID = "student_id";

    // TODO: Rename and change types of parameters
    private String studentId;

    public StudentDetailsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static StudentDetailsFragment newInstance(String studentId) {
        StudentDetailsFragment fragment = new StudentDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STUDENT_ID, studentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            studentId = getArguments().getString(ARG_STUDENT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //data = Model.instance.getAllStudents();
        container.removeAllViews();

        View view = inflater.inflate(R.layout.fragment_student_details, container, false);
        String stId=StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentId();

        Button editBtn = view.findViewById(R.id.details_edit_button);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditStudentFragment frag = EditStudentFragment.newInstance(studentId);
                FragmentTransaction tran = getParentFragmentManager().beginTransaction();
                //tran.replace(R.id.base_frag_container,frag);
                tran.addToBackStack("");
                tran.commit();
            }
        });

        Student student = Model.instance.getStudentById(stId);
        if(student != null) {
            TextView nameTv = view.findViewById(R.id.details_studentName_tv);
            TextView idTv = view.findViewById(R.id.details_studentId_tv);
            CheckBox cb = view.findViewById(R.id.details_checkBox);

            nameTv.setText(student.getName());
            idTv.setText(student.getId());
            cb.setChecked(student.isFlag());
        }
        return view;
    }
}