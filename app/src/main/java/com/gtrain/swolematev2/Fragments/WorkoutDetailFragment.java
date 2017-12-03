package com.gtrain.swolematev2.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtrain.swolematev2.Database;
import com.gtrain.swolematev2.R;
import com.gtrain.swolematev2.Workout;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {


    private String workoutId;
    Database db;



    public WorkoutDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        db = Database.getInstance(getContext());
        if (view != null) {
            TextView nameOfWorkout = (TextView) view.findViewById(R.id.nameOfWorkout);
            TextView exercisesInWorkout = (TextView) view.findViewById(R.id.exercisesInWorkout);
            Workout w = db.getWorkoutByName(workoutId);
            nameOfWorkout.setText(w.getNameOf());
            for (int i = 0; i < w.getWorkout().size(); i++) {
                exercisesInWorkout.append(w.getWorkout().get(i).getNameOf()+"\n");
            }
        }
    }


    public void setWorkoutId(String id) {
        this.workoutId = id;
    }
}
