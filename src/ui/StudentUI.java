package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.StudentDAO;
import model.Student;

public class StudentUI extends JFrame {

    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextArea resultArea;
    private StudentDAO studentDAO;

    public StudentUI() {
        studentDAO = new StudentDAO();  // Initialisation du DAO

        // Configuration de la fenêtre principale
        setTitle("Student Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Création des composants d'interface
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton viewAllButton = new JButton("View All Students");

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Ajout des composants à la fenêtre
        add(idLabel);
        add(idField);
        add(firstNameLabel);
        add(firstNameField);
        add(lastNameLabel);
        add(lastNameField);
        add(emailLabel);
        add(emailField);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewAllButton);
        add(new JScrollPane(resultArea));

        // Définir les actions pour les boutons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        viewAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAllStudents();
            }
        });
    }

    // Méthode pour ajouter un étudiant
    private void addStudent() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();

        Student student = new Student(0, firstName, lastName, email);

        try {
            studentDAO.addStudent(student);
            resultArea.setText("Student added successfully!");
            clearFields();
        } catch (SQLException e) {
            resultArea.setText("Error adding student: " + e.getMessage());
        }
    }

    // Méthode pour mettre à jour un étudiant
    private void updateStudent() {
        int id = Integer.parseInt(idField.getText());
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();

        Student student = new Student(id, firstName, lastName, email);

        try {
            studentDAO.updateStudent(student);
            resultArea.setText("Student updated successfully!");
            clearFields();
        } catch (SQLException e) {
            resultArea.setText("Error updating student: " + e.getMessage());
        }
    }

    // Méthode pour supprimer un étudiant
    private void deleteStudent() {
        int id = Integer.parseInt(idField.getText());

        try {
            studentDAO.deleteStudent(id);
            resultArea.setText("Student deleted successfully!");
            clearFields();
        } catch (SQLException e) {
            resultArea.setText("Error deleting student: " + e.getMessage());
        }
    }

    // Méthode pour afficher tous les étudiants
    public void viewAllStudents() {
        try {
            List<Student> students = studentDAO.getAllStudents();
            StringBuilder builder = new StringBuilder();
            for (Student student : students) {
                builder.append(student).append("\n");
            }
            resultArea.setText(builder.toString());
        } catch (SQLException e) {
            resultArea.setText("Error displaying students: " + e.getMessage());
        }
    }

    // Méthode pour vider les champs de saisie
    private void clearFields() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
    }

    // Lancer l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentUI().setVisible(true);
            }
        });
    }
}
