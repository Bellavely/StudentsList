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
import androidx.navigation.Navigation;

import com.example.studentslist.model.Student;
import com.example.studentslist.model.Model;
import com.example.studentslist.R;


import java.util.List;

public class StudentDetailsFragment extends Fragment {

    TextView nameTv;
    TextView idTv;
    CheckBox cb;

    private static final String ARG_STUDENT_ID = "student_id";

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

        Model.instance.getStudentById(stId, new Model.GetStudentById() {
            @Override
            public void onComplete(Student student) {
                nameTv.setText(student.getName());
                idTv.setText(student.getId());
                cb.setChecked(student.isFlag());
            }
        });
             nameTv = view.findViewById(R.id.details_studentName_tv);
             idTv = view.findViewById(R.id.details_studentId_tv);
             cb = view.findViewById(R.id.details_checkBox);

        Button editBtn = view.findViewById(R.id.details_edit_button);
        editBtn.setOnClickListener((v)->{
            Navigation.findNavController(v).navigate(StudentDetailsFragmentDirections.actionStudentDetailsFragment2ToEditStudentFragment(stId));
        });


        return view;
    }
}