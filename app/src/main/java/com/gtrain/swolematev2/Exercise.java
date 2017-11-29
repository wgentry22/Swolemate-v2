package com.gtrain.swolematev2;

/**
 * Created by gtrain on 11/12/17.
 */

public class Exercise {

    private String nameOf;
    private boolean isCompound;
    private String musclesTargeted;
    private String grip;

    public Exercise(Builder builder) {
        this.nameOf = builder.nameOf;
        this.isCompound = builder.isCompound;
        this.musclesTargeted = builder.musclesTargeted;
        this.grip = builder.grip;
    }

    public String getNameOf() { return this.nameOf; }
    public void setNameOf(String nameOf) { this.nameOf = nameOf; }
    public boolean isCompound() { return this.isCompound; }
    public void setCompound(boolean isCompound) { this.isCompound = isCompound;}
    public String getMusclesTargeted() { return this.musclesTargeted; }
    public void setMusclesTargeted(String muscles) { this.musclesTargeted = muscles; }
    public String getGrip() { return this.grip; }
    public void setGrip(String grip) { this.grip = grip; }


    @Override
    public String toString() {
        return this.getNameOf() +" (Compound:" + this.isCompound() + ")\n" + this.getMusclesTargeted() + "\n";
    }

    static class Builder {
        private String nameOf;
        private boolean isCompound;
        private String musclesTargeted;
        private String grip;

        public Builder name(String name) {
            this.nameOf = name;
            return this;
        }

        public Builder isCompoundExercise() {
            this.isCompound = true;
            return this;
        }

        public Builder isIsolationExercise() {
            this.isCompound = false;
            return this;
        }

        public Builder muscles(String muscles) {
            this.musclesTargeted = muscles;
            return this;
        }

        public Builder grip(String grip) {
            this.grip = grip;
            return this;
        }

        public Exercise createExercise() {
            return new Exercise(this);
        }

    }
}
