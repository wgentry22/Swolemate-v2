package com.gtrain.swolematev2.CustomAdapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtrain.swolematev2.Database;
import com.gtrain.swolematev2.R;

/**
 * Created by gtrain on 12/3/17.
 */

public class WorkoutListAdapter extends CursorAdapter {

    public WorkoutListAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.workout_list_item, parent, false);
    }


    @Override
    public void bindView(View v, Context context, Cursor cursor) {
        TextView nameOf = (TextView) v.findViewById(R.id.tvNameOfWorkout);
        TextView numExercises = (TextView) v.findViewById(R.id.numOfExercisesInWorkout);

        String[] exercises = cursor.getString(cursor.getColumnIndex(Database.COL_WORKOUT)).split(", ");
        int numExercisesValue = exercises.length;
        String numExercisesText = "Total Exercises: " + numExercisesValue;
        nameOf.setText(cursor.getString(cursor.getColumnIndex(Database.COL_NAMEOF)));
        numExercises.setText(numExercisesText);
    }
}
