package com.gym.models;

/**
 * Represents a workout class offered at the gym.
 */
public class WorkoutClass {
    private int workoutClassId;
    private String workoutClassType;
    private String workoutClassDescription;
    private int trainerId;

    public WorkoutClass(int workoutClassId, String workoutClassType, String workoutClassDescription, int trainerId) {
        this.workoutClassId = workoutClassId;
        this.workoutClassType = workoutClassType;
        this.workoutClassDescription = workoutClassDescription;
        this.trainerId = trainerId;
    }

    public int getWorkoutClassId() {
        return workoutClassId;
    }

    public String getWorkoutClassType() {
        return workoutClassType;
    }

    public String getWorkoutClassDescription() {
        return workoutClassDescription;
    }

    public int getTrainerId() {
        return trainerId;
    }
}
