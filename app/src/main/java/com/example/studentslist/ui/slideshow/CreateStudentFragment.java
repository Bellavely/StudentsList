package com.example.studentslist.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.studentslist.R;
//import com.example.studentslist.databinding.FragmentSlideshowBinding;
import com.example.studentslist.model.Student;
import java.util.List;

public class CreateStudentFragment extends Fragment {

    //private FragmentSlideshowBinding binding;

    private String studentId;
    Button cancelBtn;
    Button deleteBtn;
    Button saveBtn;
    List<Student> data;
    EditText nameEt;
    EditText idEt;
    CheckBox cb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_student, container, false);
        EditText nameEt = view.findViewById(R.id.new_studentName_et);
        EditText idEt = view.findViewById(R.id.new_studentId_et);
        CheckBox cb = view.findViewById(R.id.new_checkBox);
        Button saveBtn = (Button) view.findViewById(R.id.new_save_button);
//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Student s = new Student(nameEt.getText().toString(), idEt.getText().toString(), cb.isChecked());
//                Model.instance.addStudent(s);
//                StudentsListRvFragment frag = StudentsListRvFragment.newInstance();
//                FragmentTransaction tran = getParentFragmentManager().beginTransaction();
//                tran.replace(R.id.base_frag_container, frag);
//                tran.addToBackStack("");
//                tran.commit();
//            }
//        });
//        Button cancelBtn = (Button) view.findViewById(R.id.new_cancel_button);
//        cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                StudentsListRvFragment frag = StudentsListRvFragment.newInstance();
//                FragmentTransaction tran = getParentFragmentManager().beginTransaction();
//                tran.replace(R.id.base_frag_container,frag);
//                tran.addToBackStack("");
//                tran.commit();
//            }
//        });

        return view;
    }




//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}