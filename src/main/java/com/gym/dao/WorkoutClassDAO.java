package com.gym.dao;

import com.gym.models.WorkoutClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for handling workout class-related database operations.
 */
public class WorkoutClassDAO {
    private final String url = "jdbc:postgresql://localhost:5432/gymdb";
    private final String user = "gymuser";
    private final String password = "gympass";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void addWorkoutClass(WorkoutClass wc) {
        String sql = "INSERT INTO workout_classes (workout_class_type, workout_class_description, trainer_id) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, wc.getWorkoutClassType());
            pstmt.setString(2, wc.getWorkoutClassDescription());
            pstmt.setInt(3, wc.getTrainerId());
            pstmt.executeUpdate();
            System.out.println("Workout class added.");
        } catch (SQLException e) {
            System.out.println("Error adding class: " + e.getMessage());
        }
    }

    public void deleteWorkoutClass(int classId, int trainerId) {
        String sql = "DELETE FROM workout_classes WHERE workout_class_id = ? AND trainer_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, classId);
            pstmt.setInt(2, trainerId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Workout class deleted.");
            } else {
                System.out.println("Delete failed. Check ID or ownership.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting class: " + e.getMessage());
        }
    }

    /**
     * Updates an existing workout class with new values.
     *
     * @param classId    The ID of the class to update.
     * @param newType    The new class type.
     * @param newDesc    The new class description.
     * @param trainerId  The trainer's user ID (for ownership check).
     */
    public void updateWorkoutClass(int classId, String newType, String newDesc, int trainerId) {
        String sql = "UPDATE workout_classes SET workout_class_type = ?, workout_class_description = ? WHERE workout_class_id = ? AND trainer_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newType);
            pstmt.setString(2, newDesc);
            pstmt.setInt(3, classId);
            pstmt.setInt(4, trainerId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Workout class updated.");
            } else {
                System.out.println("Update failed. Check class ID or ownership.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating workout class: " + e.getMessage());
        }
    }

    public List<WorkoutClass> getTrainerClasses(int trainerId) {
        List<WorkoutClass> list = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes WHERE trainer_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trainerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new WorkoutClass(
                    rs.getInt("workout_class_id"),
                    rs.getString("workout_class_type"),
                    rs.getString("workout_class_description"),
                    rs.getInt("trainer_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching trainer classes: " + e.getMessage());
        }
        return list;
    }

    public List<WorkoutClass> getAllWorkoutClasses() {
        List<WorkoutClass> list = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new WorkoutClass(
                    rs.getInt("workout_class_id"),
                    rs.getString("workout_class_type"),
                    rs.getString("workout_class_description"),
                    rs.getInt("trainer_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all classes: " + e.getMessage());
        }
        return list;
    }
}
