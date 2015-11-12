package com.codingdojo.cycleofactivitylife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ActivityLifeCycleActivity extends AppCompatActivity {

    public static String TAG = "ActivityLifeCycleActivity";

    // compiler will check to make sure that we are overriding a method with @Override
    // this is useful because without it we might think we are overriding a method when in reality
    // we created a new method because of some typo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // android.util.Log class has methods for logging messages
        // d stands for debug and refers to the type of log message
        // first argument is the location of the message, and the second is the contents of message
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_activity_life_cycle);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    // BACK BUTTON: will trigger both onStop and onDestroy
    // HOME BUTTON: will trigger onStop, and will trigger onDestory if device needs memory
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
