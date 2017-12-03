package com.gtrain.swolematev2.Fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.gtrain.swolematev2.CustomAdapters.WorkoutListAdapter;
import com.gtrain.swolematev2.Database;
import com.gtrain.swolematev2.R;
import com.gtrain.swolematev2.Workout;

import java.lang.reflect.Array;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {


    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Database db = Database.getInstance(getContext());
        SQLiteDatabase sqlDb = db.getReadableDatabase();
        Cursor cursor = sqlDb.query(Database.TABLE_WORKOUTS, Database.allWorkoutColumns, null, null, null, null, null);
        WorkoutListAdapter adapter = new WorkoutListAdapter(getContext(), cursor);
        setListAdapter(adapter);

        //Inflate the layour for this Fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
