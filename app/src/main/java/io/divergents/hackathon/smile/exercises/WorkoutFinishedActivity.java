package io.divergents.hackathon.smile.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.divergents.hackathon.smile.MainActivity;
import io.divergents.hackathon.smile.R;

public class WorkoutFinishedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_finished);
    }
    public void return_to_main(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
