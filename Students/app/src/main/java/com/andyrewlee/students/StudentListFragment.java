package com.andyrewlee.students;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev1 on 11/24/15.
 */
public class StudentListFragment extends Fragment {
    private RecyclerView studentListRecyclerView;
    private StudentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_student_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_new_student:
                Student student = new Student();
                StudentsFactory.get(getActivity()).addStudent(student);
                Intent intent = StudentDetailActivity.newIntent(getActivity(), student.getUuid());
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        studentListRecyclerView = (RecyclerView) view.findViewById(R.id.student_list);
        studentListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameTextView;
        private Student student;

        public StudentHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            nameTextView = (TextView) itemView.findViewById(R.id.student_name);
        }

        public void bindStudent(Student student) {
            this.student = student;

            nameTextView.setText(student.getName());
        }

        @Override
        public void onClick(View v) {
            Intent intent = StudentDetailActivity.newIntent(getActivity(), student.getUuid());
            startActivity(intent);
        }
    }

    private class StudentAdapter extends RecyclerView.Adapter<StudentHolder> {
        private List<Student> students;

        public StudentAdapter(List<Student> students) {
            this.students = students;
        }

        @Override
        public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.cell_student, parent, false);
            return new StudentHolder(view);
        }

        @Override
        public int getItemCount() {
            return students.size();
        }

        @Override
        public void onBindViewHolder(StudentHolder holder, int position) {
            Student student = students.get(position);
            holder.bindStudent(student);
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }
    }

    private void updateUI() {
        StudentsFactory studentsFactory = StudentsFactory.get(getActivity());
        List<Student> students = studentsFactory.getStudents();

        if(adapter == null) {
            adapter = new StudentAdapter(students);
            studentListRecyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
