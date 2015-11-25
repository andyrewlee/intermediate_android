package com.andyrewlee.students;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class StudentDetailActivity extends SingleFragmentActivity {
    private static final String EXTRA_STUDENT_ID = "com.andyrewlee.students.student_id";

    public static Intent newIntent(Context packageContext, UUID studentId) {
        Intent intent = new Intent(packageContext, StudentDetailActivity.class);
        intent.putExtra(EXTRA_STUDENT_ID, studentId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID beastId = (UUID) getIntent().getSerializableExtra(EXTRA_STUDENT_ID);
        return StudentDetailFragment.newInstance(beastId);
    }
}
