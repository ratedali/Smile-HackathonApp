package io.divergents.hackathon.smile.exercises;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import io.divergents.hackathon.smile.R;

public class LayoutContainerActivity extends AppCompatActivity {
    public FragmentTransaction ft;
    public WorkoutBeforeFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_container);
        frag = new WorkoutBeforeFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.workout_container, frag);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void settran() {
        AccelWorkoutFragment Thefrag = new AccelWorkoutFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.workout_container, Thefrag);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();


    }
}
