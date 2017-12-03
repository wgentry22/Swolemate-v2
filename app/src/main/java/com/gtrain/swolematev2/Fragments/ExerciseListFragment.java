package com.gtrain.swolematev2.Fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtrain.swolematev2.CustomAdapters.ExerciseListAdapter;
import com.gtrain.swolematev2.Database;
import com.gtrain.swolematev2.R;

public class ExerciseListFragment extends ListFragment {


    public ExerciseListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Database db = Database.getInstance(getContext());
        SQLiteDatabase sqlDb = db.getReadableDatabase();
        Cursor cursor = sqlDb.query(Database.TABLE_EXERCISES, Database.allExerciseColumns, null, null, null, null, null);
        ExerciseListAdapter adapter = new ExerciseListAdapter(getContext(), cursor);
        setListAdapter(adapter);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
