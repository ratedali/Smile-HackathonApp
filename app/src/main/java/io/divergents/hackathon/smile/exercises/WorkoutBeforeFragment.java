package io.divergents.hackathon.smile.exercises;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.divergents.hackathon.smile.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutBeforeFragment extends Fragment {


    public WorkoutBeforeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_before, container, false);
        Button button = view.findViewById(R.id.exersice_button);
        button.setOnClickListener(v -> {
            AccelWorkoutFragment Thefrag = new AccelWorkoutFragment();
            FragmentTransaction ft =
                    WorkoutBeforeFragment.this.getFragmentManager().beginTransaction();
            ft.replace(R.id.workout_container, Thefrag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        });
        return view;
    }

    public void workout_button(View view) {


    }


}
