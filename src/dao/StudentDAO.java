package dao;


public class StudentDAO {


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Méthode pour ajouter un étudiant
    public void addStudent(Student student) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, student.getFirstName());
        ps.setString(2, student.getLastName());
        ps.setString(3, student.getEmail());
        ps.executeUpdate();
        ps.close();
        connection.close();
    }

    // Méthode pour lire tous les étudiants
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM students";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setEmail(rs.getString("email"));
            students.add(student);
        }
        rs.close();
        stmt.close();
        connection.close();
        return students;
    }

    // Méthode pour mettre à jour un étudiant
    public void updateStudent(Student student) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE students SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, student.getFirstName());
        ps.setString(2, student.getLastName());
        ps.setString(3, student.getEmail());
        ps.setInt(4, student.getId());
        ps.executeUpdate();
        ps.close();
        connection.close();
    }

    // Méthode pour supprimer un étudiant
    public void deleteStudent(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "DELETE FROM students WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }

}
