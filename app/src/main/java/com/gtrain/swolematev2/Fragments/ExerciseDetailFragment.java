package com.gtrain.swolematev2.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtrain.swolematev2.Database;
import com.gtrain.swolematev2.Exercise;
import com.gtrain.swolematev2.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseDetailFragment extends Fragment {


    private String exerciseId;
    Database db;

    public ExerciseDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        db = Database.getInstance(getContext());
        if (view != null) {
            TextView nameOfExercise = (TextView) view.findViewById(R.id.nameOfExercise);
            TextView musclesTargeted = (TextView) view.findViewById(R.id.exerciseMusclesTargeted);
            TextView isCompound = (TextView) view.findViewById(R.id.isCompound);
            TextView grip = (TextView) view.findViewById(R.id.gripOfExercise);
            Exercise e = db.getExerciseByName(exerciseId);
            nameOfExercise.setText(e.getNameOf());
            musclesTargeted.setText(e.getMusclesTargeted());
            if (e.isCompound()) {
                isCompound.setText("Compound");
            } else {
                isCompound.setText("Isolation");
            }
            grip.setText(e.getGrip());
        }
    }


    public void setExerciseId(String id) { this.exerciseId = id; }
}
