package com.gtrain.swolematev2;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gtrain.swolematev2.Fragments.ExerciseListFragment;
import com.gtrain.swolematev2.Fragments.WorkoutDetailFragment;
import com.gtrain.swolematev2.Fragments.WorkoutListFragment;

import java.util.List;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

    }


    @Override
    public void onStart() {
        super.onStart();
        ExerciseListFragment list = new ExerciseListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, list);
        ft.commit();
    }


}
