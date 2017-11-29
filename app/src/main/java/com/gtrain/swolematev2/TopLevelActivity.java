package com.gtrain.swolematev2;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        Database db = Database.getInstance(this);
        db.loadData();


        TextView test1 = (TextView) findViewById(R.id.test1);
        TextView test2 = (TextView) findViewById(R.id.test2);
        TextView test3 = (TextView) findViewById(R.id.test3);

        Exercise e1 = db.getExerciseByName("Bench Press");
        //test1.setText(e1.toString());

        Workout w1 = db.getWorkoutByName("The Big Four");
        //test2.setText(w1.toString());

        List<Workout> workoutList = db.getAllWorkouts();
        for (int i = 0; i < workoutList.size(); i++) {
            test3.append("Workout " + (i + 1) + ": " + workoutList.get(i).getNameOf() + "\n");
            for (int j = 0; j < workoutList.get(i).getWorkout().size(); j++) {
                test3.append("\t"+workoutList.get(i).getWorkout().get(j).getNameOf() + "\n");
            }
        }



    }
}
