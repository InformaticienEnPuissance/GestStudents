package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class StudentUI extends JFrame {
    private JTextField idField, firstNameField, lastNameField, emailField;
    private JTextArea resultArea;
    private StudentDAO studentDAO;

    public StudentUI() {
        studentDAO = new StudentDAO();

        // Configuration de la fenêtre principale
        setTitle("Gestion des Étudiants");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Création des labels et champs de texte
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(20);

        JLabel firstNameLabel = new JLabel("Prénom:");
        firstNameField = new JTextField(20);

        JLabel lastNameLabel = new JLabel("Nom:");
        lastNameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        // Zone de résultats
        resultArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Boutons CRUD
        JButton addButton = new JButton("Ajouter");
        JButton updateButton = new JButton("Mettre à jour");
        JButton deleteButton = new JButton("Supprimer");
        JButton viewButton = new JButton("Afficher tous");

        // Ajout des composants à la fenêtre
        add(idLabel); add(idField);
        add(firstNameLabel); add(firstNameField);
        add(lastNameLabel); add(lastNameField);
        add(emailLabel); add(emailField);
        add(addButton); add(updateButton);
        add(deleteButton); add(viewButton);
        add(scrollPane);

        // Actions pour les boutons CRUD
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

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAllStudents();
            }
        });

        setVisible(true);
    }

    // Méthode pour ajouter un étudiant
    private void addStudent() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();

        Student student = new Student(0, firstName, lastName, email);

        try {
            studentDAO.addStudent(student);
            resultArea.setText("Étudiant ajouté avec succès !");
            clearFields();
        } catch (SQLException e) {
            resultArea.setText("Erreur lors de l'ajout : " + e.getMessage());
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
            resultArea.setText("Étudiant mis à jour avec succès !");
            clearFields();
        } catch (SQLException e) {
            resultArea.setText("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    // Méthode pour supprimer un étudiant
    private void deleteStudent() {
        int id = Integer.parseInt(idField.getText());

        try {
            studentDAO.deleteStudent(id);
            resultArea.setText("Étudiant supprimé avec succès !");
            clearFields();
        } catch (SQLException e) {
            resultArea.setText("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    // Méthode pour afficher tous les étudiants
    private void viewAllStudents() {
        try {
            List<Student> students = studentDAO.getAllStudents();
            StringBuilder builder = new StringBuilder();
            for (Student student : students) {
                builder.append(student).append("\n");
            }
            resultArea.setText(builder.toString());
        } catch (SQLException e) {
            resultArea.setText("Erreur lors de l'affichage : " + e.getMessage());
        }
    }

    // Méthode pour vider les champs de saisie
    private void clearFields() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
    }
}
