package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import util.DatabaseConnection;

public class StudentDAO {

    // Méthode pour ajouter un étudiant
    public void addStudent(Student student) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, student.getFirstName());
                ps.setString(2, student.getLastName());
                ps.setString(3, student.getEmail());
                ps.executeUpdate();
            }
        }
    }

    // Méthode pour lire tous les étudiants
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM students";
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setEmail(rs.getString("email"));
                    students.add(student);
                }
            }
        }
        return students;
    }

    // Méthode pour mettre à jour un étudiant
    public void updateStudent(Student student) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE students SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, student.getFirstName());
                ps.setString(2, student.getLastName());
                ps.setString(3, student.getEmail());
                ps.setInt(4, student.getId());
                ps.executeUpdate();
            }
        }
    }

    // Méthode pour supprimer un étudiant
    public void deleteStudent(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }

}
