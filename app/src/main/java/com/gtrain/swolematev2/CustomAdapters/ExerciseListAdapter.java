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

public class ExerciseListAdapter extends CursorAdapter {

    public ExerciseListAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.exercise_list_item, parent, false);
    }

    @Override
    public void bindView(View v, Context context, Cursor cursor) {
        TextView nameOfExercise = (TextView) v.findViewById(R.id.tvNameOfExercise);
        TextView detailsOfExercise = (TextView) v.findViewById(R.id.tvExerciseDetails);
        nameOfExercise.setText(cursor.getString(cursor.getColumnIndex(Database.COL_NAMEOF)));

        String compound;
        if (cursor.getInt(cursor.getColumnIndex(Database.COL_IS_COMPOUND)) != 0) {
            compound = "Compound";
        } else {
            compound = "Isolation";
        }

        String[] musclesTargeted = cursor.getString(cursor.getColumnIndex(Database.COL_MUSCLES)).split(", ");
        String primaryMuscle = musclesTargeted[0];
        detailsOfExercise.setText(primaryMuscle + " (" + compound + ")");

    }
}
