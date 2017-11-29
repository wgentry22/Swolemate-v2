package com.gtrain.swolematev2;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrain on 11/14/17.
 */

public class Workout {

    private String nameOf;
    private List<Exercise> workout;

    public Workout() {
        this.workout = new ArrayList<Exercise>();
    }


    public Workout(String nameOf) {
        this.nameOf = nameOf;
        this.workout = new ArrayList<Exercise>();
    }

    public String getNameOf() { return this.nameOf; }
    public void setNameOf(String nameOf) { this.nameOf = nameOf; }
    public List<Exercise> getWorkout() { return this.workout;}
    public void addExerciseToWorkout(Exercise exercise) {
        this.workout.add(exercise);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getNameOf() + "\n");
        for (int i = 0; i < this.workout.size(); i++) {
            builder.append(this.workout.get(i).getNameOf() +" (Compound: "+ this.workout.get(i).isCompound()+")\n" + this.workout.get(i).getMusclesTargeted() + "\n\n");
        }
        return builder.toString();
    }
}
