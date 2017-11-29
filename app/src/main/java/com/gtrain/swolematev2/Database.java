package com.gtrain.swolematev2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrain on 11/28/17.
 */

public class Database extends SQLiteOpenHelper {

    private Context mContext;
    private static Database mInstance = null;


    //Logcat tag
    private static final String LOG = Database.class.getName();


    //Name of Database
    private static final String DATABASE_NAME = "swolemateDatabase";

    //Database current version
    private static final int DATABASE_VERSION = 1;

    //Table Names
    public static final String TABLE_EXERCISES = "exercises";
    public static final String TABLE_WORKOUTS = "workouts";

    //Common Columns
    public static final String COL_ID = "_id";
    public static final String COL_NAMEOF = "nameOf";

    //Exercise Table Specific Columns
    public static final String COL_IS_COMPOUND = "isCompound";
    public static final String COL_MUSCLES = "musclesTargeted";
    public static final String COL_GRIP = "grip";

    //Workout Table Specific Columns
    public static final String COL_WORKOUT = "workout";



    public static final String[] allExerciseColumns = {COL_ID, COL_NAMEOF, COL_IS_COMPOUND, COL_MUSCLES, COL_GRIP};
    public static final String[] allWorkoutColumns = {COL_ID, COL_WORKOUT, COL_NAMEOF};

    //Table Create Statements
    //Create Statement for 'exercises' table
    private static final String createExercisesTable = "CREATE TABLE " + TABLE_EXERCISES +
            " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_NAMEOF + " TEXT, " +
            COL_IS_COMPOUND + " NUMERIC, " + COL_MUSCLES + " TEXT, " + COL_GRIP + " TEXT)";

    //Create Statement for 'workouts' table
    private static final String createWorkoutsTable = "CREATE TABLE " + TABLE_WORKOUTS +
            " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_NAMEOF + " TEXT, "+ COL_WORKOUT + " TEXT)";


    //Return a Singleton
    public static Database getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Database(context.getApplicationContext());
        }
        return mInstance;
    }


    //Constructor for Database object
    private Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }








    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, DATABASE_VERSION);
        loadData();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 0:
                db.execSQL(createExercisesTable);
                db.execSQL(createWorkoutsTable);
                break;
            default:
                throw new SQLException("Cannot upgrade from oldVersion (" + oldVersion + ") to newVersion (" + newVersion + ")");
        }
    }




    public void loadData() {
        deleteAll();
        loadChestExercises();
        loadBackExercises();
        loadShouldersExercises();
        loadBicepsExercises();
        loadTricepsExercises();
        loadQuadsExercises();
        loadHamstringsExercises();
        loadGlutesExercises();
        loadCalvesExercises();
        loadTrapsExercises();
        loadCoreExercises();

        createWorkout("The Big Four", "Bench Press, Standing Overhead Barbell Press, Back Squats, Deadlifts");
        createWorkout("Epic Chest", "Bench Press, Incline Press, Decline Press, Incline Cable Flye, Single Arm DB Bench Press");
        createWorkout("Leg Day From Hell", "Back Squats, Front Squats, Deadlifts, Leg Press (Narrow Feet), Pistol Squat, Hamstring Curls, Romanian Deadlifts");

    }

    public void loadChestExercises() {
        createExercise("Bench Press", true, "Chest, Shoulders, Triceps", "Overhand");
        createExercise("Incline Press", true, "Chest, Shoulders, Triceps", "Overhand");
        createExercise("Decline Press", true, "Chest, Shoulders, Triceps", "Overhand");
        createExercise("DB Bench Press", true, "Chest, Shoulders, Triceps", "Neutral/Overhand");
        createExercise("Incline DB Press", true, "Chest, Shoulders, Triceps", "Overhand");
        createExercise("Incline Cable Flye", false, "Chest", "Neutral");
        createExercise("Single Arm DB Bench Press", false, "Chest", "Overhand");

    }

    public void loadBackExercises() {
        createExercise("Bent Over Row", true, "Back, Shoulders, Biceps, Traps", "Overhand");
        createExercise("DB Row", true, "Back, Shoulders, Biceps", "Neutral");
        createExercise("Unilateral Lat Pulldown", true, "Back, Biceps", "Underhand");
        createExercise("Cable T-Bar Pulldown", false, "Back", "Neutral");
        createExercise("DB Rear Flye", true, "Back, Shoulders, Traps", "Neutral");
        createExercise("Wide Grip Pullup", true, "Back, Traps, Shoulders, Biceps", "Overhand");
        createExercise("Seated Cable Row", true, "Back, Biceps, Traps", "Neutral");
        createExercise("Back Extensions", false, "Back", "N/A");

    }

    public void loadShouldersExercises() {
        createExercise("Standing Overhead Barbell Press", true, "Shoulders, Triceps, Core", "Overhand");
        createExercise("Alternating DB Overhead Press", false, "Shoulders", "Overhand");
        createExercise("Lateral Raises", false, "Shoulders", "Neutral");
        createExercise("Front Raises", false, "Shoulders", "Neutral");
        createExercise("Landmine Shoulder Press", true, "Shoulders, Core", "Neutral");
        createExercise("Upright Rows", true, "Shoulders, Traps, Biceps", "Overhand");
        createExercise("Car Drivers", false, "Shoulders", "Neutral");

    }

    public void loadBicepsExercises() {
        createExercise("Hammer Curls", false, "Biceps", "Neutral");
        createExercise("Incline DB Curls", false, "Biceps", "Overhand");
        createExercise("Preacher Curls", false, "Biceps", "Underhand");
        createExercise("Chinups", true, "Biceps, Back, Traps", "Underhand");
        createExercise("Underhand Bent Over Row", true, "Biceps, Back", "Underhand");
        createExercise("Barbell Curls", false, "Biceps", "Underhand");

    }

    public void loadTricepsExercises() {
        createExercise("Close Grip Bench Press", true, "Triceps, Chest", "Overhand");
        createExercise("Skullcrushers", true, "Triceps, Chest", "Overhand");
        createExercise("Dips", true, "Triceps, Shoulders, Chest", "Neutral");
        createExercise("Seated DB Overhead Extension", true, "Triceps, Core", "Neutral");
        createExercise("Cable Rope Triceps Press", false, "Triceps", "Neutral");
        createExercise("DB Skullcrushers", false, "Triceps", "Neutral");
        createExercise("Underhand Cable Triceps Press", false, "Triceps", "Underhand");
        createExercise("Cable V-Bar Press", false, "Triceps", "Pronated");
        createExercise("Machine Triceps Press", false, "Triceps", "Neutral");

    }

    public void loadQuadsExercises() {
        createExercise("Front Squats", true, "Quads, Hamstrings, Glutes, Back", "Overhand");
        createExercise("Leg Press (Narrow Feet)", false, "Quads", "N/A");
        createExercise("Leg Extension", false, "Quads", "N/A");
        createExercise("Wall Sits", true, "Quads, Hamstrings, Glutes", "N/A");
        createExercise("Hack Squat", false, "Quads", "N/A");
        createExercise("Barbell Walking Squat", true, "Quads, Hamstrings, Core", "Overhand");
        createExercise("Pistol Squat", false, "Quads", "N/A");

    }

    public void loadHamstringsExercises() {
        createExercise("Back Squats", true, "Hamstrings, Quads, Glutes, Back", "Overhand");
        createExercise("Deadlifts", true, "Hamstrings, Back, Glutes, Quads, Calves", "Overhand or Mixed");
        createExercise("Leg Press (Wide Feet)", true, "Hamstrings, Glutes", "N/A");
        createExercise("Hamstring Curls", false, "Hamstrings", "N/A");
        createExercise("Romanian Deadlift", true, "Hamstrings, Back, Core", "Overhand");
        createExercise("One Leg Deadlift", true, "Hamstrings, Glutes, Core", "Overhand");
        createExercise("Kettlebell Swings", true, "Hamstrings, Core", "Neutral");

    }

    public void loadGlutesExercises() {
        createExercise("Bulgarian Split Squats", true, "Glutes, Quads, Hamstrings", "N/A");
        createExercise("Cable Kickbacks", false, "Glutes", "N/A");
        createExercise("Hip Abductor Machine", false, "Glutes", "N/A");
        createExercise("Hip Adductor Machine", false, "Glutes", "N/A");
        createExercise("Assisted Dip Machine Pressdown", false, "Glutes", "N/A");
        createExercise("Donkey Kicks", false, "Glutes", "N/A");

    }

    public void loadCalvesExercises() {
        createExercise("Standing Barbell Calf Press", false, "Calves", "Overhand");
        createExercise("Sitting Calf Press", false, "Calves", "N/A");
        createExercise("Horizontal Calf Press Machine", false, "Calves", "N/A");

    }

    public void loadTrapsExercises() {
        createExercise("Barbell Shrugs", true, "Traps, Shoulders", "Overhand");
        createExercise("DB Shrugs", true, "Traps, Shoulders", "Neutral");

    }

    public void loadCoreExercises() {
        createExercise("Cable Crunch", false, "Core", "N/A");
        createExercise("Bosu Plank", false, "Core", "N/A");
        createExercise("Cable Woodchop", false, "Core", "Neutral");
        createExercise("Hanging Leg Lifts", false, "Core", "Overhand");
        createExercise("Deadbugs", false, "Core", "N/A");
        createExercise("Russian Twist", false, "Core", "N/A");
        createExercise("Ab Wheel Rollouts", false, "Core", "Overhand");
        createExercise("Spiderman Crunch", false, "Core", "N/A");

    }


    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EXERCISES);
        db.execSQL("DELETE FROM " + TABLE_WORKOUTS);
    }




    //CRUD Functions for Exercise

    //Create
    public void createExercise(String nameOf, boolean isCompound, String musclesTargeted, String grip) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nameOf", nameOf);
        values.put("isCompound", isCompound);
        values.put("musclesTargeted", musclesTargeted);
        values.put("grip", grip);

        db.insert(TABLE_EXERCISES, null, values);

    }


    //Read
    public Exercise getExerciseByName(String nameOf) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_EXERCISES, allExerciseColumns, COL_NAMEOF + " = ?", new String[] {nameOf}, null, null, null);

        Exercise e = null;
        try {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getInt(cursor.getColumnIndex(COL_IS_COMPOUND)) != 0) {
                        e = new Exercise.Builder().isCompoundExercise()
                                .name(cursor.getString(cursor.getColumnIndex(COL_NAMEOF)))
                                .muscles(cursor.getString(cursor.getColumnIndex(COL_MUSCLES)))
                                .grip(cursor.getString(cursor.getColumnIndex(COL_GRIP)))
                                .createExercise();

                    } else {
                        e = new Exercise.Builder().isIsolationExercise()
                                .name(cursor.getString(cursor.getColumnIndex(COL_NAMEOF)))
                                .muscles(cursor.getString(cursor.getColumnIndex(COL_MUSCLES)))
                                .grip(cursor.getString(cursor.getColumnIndex(COL_GRIP)))
                                .createExercise();
                    }
                } while (cursor.moveToNext());
            }
            return e;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cursor.close();
        }
        return e;
    }



    //Read all exercises
    public List<Exercise> getAllExercises() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_EXERCISES, allExerciseColumns, null, null, null, null, null);
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        Exercise e = null;
        try {
            if (cursor.moveToFirst()) {
                do {
                    e = getExerciseByName(cursor.getString(cursor.getColumnIndex(COL_NAMEOF)));
                    exerciseList.add(e);
                } while (cursor.moveToNext());
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cursor.close();
        }
        return exerciseList;
    }










    //CRUD Functions for Workout

    //Create
    public void createWorkout(String nameOf, String workout) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nameOf", nameOf);
        values.put("workout", workout);

        db.insert(TABLE_WORKOUTS, null, values);
    }



    //Read
    public Workout getWorkoutByName(String nameOf) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_WORKOUTS, allWorkoutColumns, COL_NAMEOF + " = ?", new String[] {nameOf}, null, null, null, null);
        Workout w = null;
        Exercise e = null;
        try {
            if (cursor.moveToFirst()) {
                w = new Workout(cursor.getString(cursor.getColumnIndex(COL_NAMEOF)));
                String[] exerciseNames = cursor.getString(cursor.getColumnIndex(COL_WORKOUT)).split(", ");
                for (int i = 0; i < exerciseNames.length; i++) {
                    e = getExerciseByName(exerciseNames[i]);
                    w.addExerciseToWorkout(e);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cursor.close();
        }
        return w;
    }




    //Read all workouts
    public List<Workout> getAllWorkouts() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_WORKOUTS, allWorkoutColumns, null, null, null, null, null, null);
        List<Workout> workoutList = new ArrayList<Workout>();
        Workout w;
        Exercise e;
        try {
            if (cursor.moveToFirst()) {
                do {
                    w = new Workout();
                    w.setNameOf(cursor.getString(cursor.getColumnIndex(COL_NAMEOF)));
                    String[] exercises = cursor.getString(cursor.getColumnIndex(COL_WORKOUT)).split(", ");
                    for (int i = 0; i < exercises.length; i++) {
                        Cursor cursor2 = db.query(TABLE_EXERCISES, allExerciseColumns, COL_NAMEOF + " = ?", new String[] {exercises[i]}, null, null, null);
                        try {
                            if (cursor2.moveToFirst()) {
                                do {
                                    if (cursor2.getInt(cursor2.getColumnIndex(COL_IS_COMPOUND)) != 0) {
                                        e = new Exercise.Builder().isCompoundExercise()
                                                .name(cursor2.getString(cursor2.getColumnIndex(COL_NAMEOF)))
                                                .muscles(cursor2.getString(cursor2.getColumnIndex(COL_MUSCLES)))
                                                .grip(cursor2.getString(cursor2.getColumnIndex(COL_GRIP)))
                                                .createExercise();
                                        w.addExerciseToWorkout(e);

                                    } else {
                                        e = new Exercise.Builder().isIsolationExercise()
                                                .name(cursor2.getString(cursor2.getColumnIndex(COL_NAMEOF)))
                                                .muscles(cursor2.getString(cursor2.getColumnIndex(COL_MUSCLES)))
                                                .grip(cursor2.getString(cursor2.getColumnIndex(COL_GRIP)))
                                                .createExercise();
                                        w.addExerciseToWorkout(e);
                                    }
                                } while (cursor2.moveToNext());
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            cursor2.close();
                        }
                    }
                    workoutList.add(w);

                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cursor.close();
        }
        return workoutList;
    }





}
