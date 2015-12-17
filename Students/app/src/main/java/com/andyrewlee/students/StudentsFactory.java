package com.andyrewlee.students;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dev1 on 11/24/15.
 */
public class StudentsFactory {
    private static StudentsFactory studentsFactory;
    private List<Student> students;

    public static StudentsFactory get(Context context) {
        if(studentsFactory == null) {
            studentsFactory = new StudentsFactory(context);
        }
        return studentsFactory;
    }

    public StudentsFactory(Context context) {
        students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudent(UUID id) {
        for(Student student : students) {
            if(student.getUuid().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void deleteStudent(UUID id) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getUuid().equals(id)) {
                students.remove(i);
            }
        }
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}
