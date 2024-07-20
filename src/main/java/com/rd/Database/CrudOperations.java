package com.rd.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudOperations {

    public void createPerson(String name, String surname, String job, String department, double salary) {
        String query = "INSERT INTO sena_efe (name, surname, job, department, salary) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, job);
            preparedStatement.setString(4, department);
            preparedStatement.setDouble(5, salary);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Personel> readAllPersons() {
        String query = "SELECT * FROM sena_efe";
        List<Personel> personels = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Personel personel = new Personel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("job"),
                        resultSet.getString("department"),
                        resultSet.getDouble("salary")
                );
                personels.add(personel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personels;
    }

    public Personel readPerson(int id) {
        String query = "SELECT * FROM sena_efe WHERE id = ?";
        Personel personel = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                personel = new Personel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("job"),
                        resultSet.getString("department"),
                        resultSet.getDouble("salary")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personel;
    }

    public void updatePerson(int id, String name, String surname, String job, String department, double salary) {
        String query = "UPDATE sena_efe SET name = ?, surname = ?, job = ?, department = ?, salary = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, job);
            preparedStatement.setString(4, department);
            preparedStatement.setDouble(5, salary);
            preparedStatement.setInt(6, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(name + " has been updated. Updated details: " + readPerson(id));
            } else {
                System.out.println("No rows updated. Check if the ID exists.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson(int id) {
        Personel personel = readPerson(id);
        if (personel != null) {
            String query = "DELETE FROM sena_efe WHERE id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, id);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println(" (" + personel + ") has been removed from the project.");
                } else {
                    System.out.println("No rows deleted. Check if the ID exists.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No person found with ID: " + id);
        }
    }

    public void deleteAllPersons() {
        String deleteQuery = "DELETE FROM sena_efe";
        String resetIncrementQuery = "ALTER TABLE sena_efe AUTO_INCREMENT = 1";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
             PreparedStatement resetIncrementStatement = connection.prepareStatement(resetIncrementQuery)) {

            deleteStatement.executeUpdate();
            resetIncrementStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
