package ui;

import dao.StudentDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import model.Student;

public class StudentUI extends JFrame {

    private final JTextField idField;
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField emailField;
    private final JTextArea resultArea;
    private final StudentDAO studentDAO;

    public StudentUI() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu EtudiantMenu = new JMenu("Etudiant");
        menuBar.add(fileMenu);
        menuBar.add(EtudiantMenu);
        setJMenuBar(menuBar);

        // Init DAO
        studentDAO = new StudentDAO();

        // Panel d'entrée des données (input panel)
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Détails de l'étudiant"));

        idField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Prénom:"));
        inputPanel.add(firstNameField);
        inputPanel.add(new JLabel("Nom:"));
        inputPanel.add(lastNameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        // Panel des boutons (action panel)
        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton addButton = new JButton("Ajouter");
        JButton updateButton = new JButton("Mettre à jour");
        JButton deleteButton = new JButton("Supprimer");
        JButton viewButton = new JButton("Afficher tout");

        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.BLUE);
        addButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        actionPanel.add(addButton);
        actionPanel.add(updateButton);
        actionPanel.add(deleteButton);
        actionPanel.add(viewButton);

        // Panel pour l'affichage des résultats (output panel)
        resultArea = new JTextArea();
        resultArea.setBorder(BorderFactory.createTitledBorder("Résultats"));
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Ajouter les panels au frame principal
        add(inputPanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Actions des boutons
        addButton.addActionListener((ActionEvent e) -> {
            addStudent();
        });

        updateButton.addActionListener((ActionEvent e) -> {
            updateStudent();
        });

        deleteButton.addActionListener((ActionEvent e) -> {
            deleteStudent();
        });

        viewButton.addActionListener((ActionEvent e) -> {
            viewAllStudents();
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
    public void viewAllStudents() {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentUI ui = new StudentUI();
            ui.setVisible(true);
        });
    }
}
