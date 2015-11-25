package com.andyrewlee.students;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by dev1 on 11/24/15.
 */
public class StudentDetailFragment extends Fragment {

    private static final String ARG_STUDENT_ID = "student_id";

    private Student student;
    private EditText studentNameEditText;
    private Button deleteStudentButton;

    public static StudentDetailFragment newInstance(UUID studentId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_STUDENT_ID, studentId);

        StudentDetailFragment fragment = new StudentDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StudentsFactory studentsFactory = StudentsFactory.get(getActivity());
        UUID studentId = (UUID) getArguments().getSerializable(ARG_STUDENT_ID);

        student = studentsFactory.getStudent(studentId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_detail, container, false);
        studentNameEditText = (EditText) view.findViewById(R.id.student_name);
        deleteStudentButton = (Button) view.findViewById(R.id.delete_student);

        studentNameEditText.setText(student.getName());

        studentNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                student.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        deleteStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentsFactory studentsFactory = StudentsFactory.get(getActivity());
                studentsFactory.deleteStudent(student.getUuid());
                getActivity().finish();
            }
        });

        return view;
    }
}
