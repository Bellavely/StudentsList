package com.example.studentslist.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentslist.R;
import com.example.studentslist.model.Student;
import com.example.studentslist.model.Model;

import java.util.List;

public class StudentsListRvFragment extends Fragment {

    List<Student> data;
    Button newBtn;
    MyAdapter adapter;
    ProgressBar progressBar;

    public StudentsListRvFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students_list_rv,container,false);

        progressBar=view.findViewById(R.id.progressBar);
        progressBar.setVisibility(view.GONE);
        RecyclerView list = view.findViewById(R.id.studentslist_rv);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyAdapter();
        list.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String stId = data.get(position).getId();
              Navigation.findNavController(view).navigate((NavDirections)StudentsListRvFragmentDirections.actionNavHomeToStudentDetailsFragment2(stId));
            }
        });

        newBtn = view.findViewById(R.id.new_button_rv);
        newBtn.setOnClickListener((v)->
           Navigation.findNavController(v).navigate(StudentsListRvFragmentDirections.actionNavHomeToCreateStudentFragment()));
        setHasOptionsMenu(true);
        refresh();
        return view;
    }

    private void refresh() {
        progressBar.setVisibility(View.VISIBLE);
        Model.instance.getAllStudents((list)->{
            data=list;
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        });
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.row_name_tv);
            idTv = itemView.findViewById(R.id.row_id_tv);
            cb = itemView.findViewById(R.id.row_checkbox);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    data.get(pos).setFlag(cb.isChecked());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(view,pos);
                }
            });
        }
    }

    interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        OnItemClickListener listener;
        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.students_list_row,parent,false);
            MyViewHolder holder = new MyViewHolder(view,listener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Student student = data.get(position);
            holder.nameTv.setText(student.getName());
            holder.idTv.setText(student.getId());
            holder.cb.setChecked(student.isFlag());
        }

        @Override
        public int getItemCount() {
            if (data==null)
                return 0;
            return data.size();
        }
    }

}