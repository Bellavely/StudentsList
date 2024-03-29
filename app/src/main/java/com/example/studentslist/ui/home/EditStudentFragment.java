package com.example.studentslist.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.studentslist.model.Student;
import com.example.studentslist.model.Model;
import com.example.studentslist.R;

import java.util.List;

public class EditStudentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_STUDENT_ID = "student_id";

    // TODO: Rename and change types of parameters
    private String studentId;
    Button cancelBtn;
    Button deleteBtn;
    Button saveBtn;
    List<Student> data;
    EditText nameEt;
    EditText idEt;
    CheckBox cb;

    public EditStudentFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EditStudentFragment newInstance(String studentId) {
        EditStudentFragment fragment = new EditStudentFragment();
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

        View view = inflater.inflate(R.layout.fragment_edit_student, container, false);
        nameEt = view.findViewById(R.id.edit_studentName_et);
        idEt = view.findViewById(R.id.edit_studentId_et);
        cb = view.findViewById(R.id.edit_checkBox);

        String stId = EditStudentFragmentArgs.fromBundle(getArguments()).getStudentId();
       Model.instance.getStudentById(studentId, new Model.GetStudentById() {
           @Override
           public void onComplete(Student student) {
               nameEt.setText(student.getName());
               idEt.setText(student.getId());
               cb.setChecked(student.isFlag());
           }
       });


        cancelBtn = (Button) view.findViewById(R.id.edit_cancel_button);
        cancelBtn.setOnClickListener((v)->{
            Navigation.findNavController(v).navigateUp();
        });

        deleteBtn = (Button) view.findViewById(R.id.edit_delete_button);
        deleteBtn.setOnClickListener((v)->{
            Model.instance.deleteStudentById(stId, new Model.DeleteStudentListenner() {
                @Override
                public void onComplete() {

                }
            });
            Navigation.findNavController(v).navigate(EditStudentFragmentDirections.actionEditStudentFragmentToNavHome());
        });
        saveBtn = (Button) view.findViewById(R.id.edit_save_button);
        saveBtn.setOnClickListener((v)->{
                    Student editedStudent = new Student(nameEt.getText().toString(), idEt.getText().toString(), cb.isChecked());
                    Model.instance.editStudent(stId, editedStudent, new Model.EditStudentListenner() {
                        @Override
                        public void onComplete() { }
                    });
                    Navigation.findNavController(v).navigate(EditStudentFragmentDirections.actionEditStudentFragmentToNavHome());
                    return;
        });

        return view;
    }
}