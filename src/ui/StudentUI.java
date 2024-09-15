package ui;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.StudentDAO;
import model.Student;

public class StudentUI extends JFrame {

    private final JTextField idField;
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField emailField;
    private final JTextArea resultArea;
    private final StudentDAO studentDAO;

    public class StudentUI {
        public void showMenu() {
            // Logique de menu
            System.out.println("Veuillez entrer les informations de l'étudiant :");
            
            // Ajoute un étudiant fictif pour tester
            Student student = new Student(1, "John", "Doe", "john.doe@example.com");
            try {
                StudentDAO studentDAO = new StudentDAO();
                studentDAO.addStudent(student);
                System.out.println("Étudiant ajouté avec succès.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'ajout : " + e.getMessage());
            }
        }
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

    // // Méthode pour mettre à jour un étudiant
    // private void updateStudent() {
    //     int id = Integer.parseInt(idField.getText());
    //     String firstName = firstNameField.getText();
    //     String lastName = lastNameField.getText();
    //     String email = emailField.getText();

    //     Student student = new Student(id, firstName, lastName, email);

    //     try {
    //         studentDAO.updateStudent(student);
    //         resultArea.setText("Étudiant mis à jour avec succès !");
    //         clearFields();
    //     } catch (SQLException e) {
    //         resultArea.setText("Erreur lors de la mise à jour : " + e.getMessage());
    //     }
    // }

    // // Méthode pour supprimer un étudiant
    // private void deleteStudent() {
    //     int id = Integer.parseInt(idField.getText());

    //     try {
    //         studentDAO.deleteStudent(id);
    //         resultArea.setText("Étudiant supprimé avec succès !");
    //         clearFields();
    //     } catch (SQLException e) {
    //         resultArea.setText("Erreur lors de la suppression : " + e.getMessage());
    //     }
    // }

    // // Méthode pour afficher tous les étudiants
    // private void viewAllStudents() {
    //     try {
    //         List<Student> students = studentDAO.getAllStudents();
    //         StringBuilder builder = new StringBuilder();
    //         for (Student student : students) {
    //             builder.append(student).append("\n");
    //         }
    //         resultArea.setText(builder.toString());
    //     } catch (SQLException e) {
    //         resultArea.setText("Erreur lors de l'affichage : " + e.getMessage());
    //     }
    // }

    // // Méthode pour vider les champs de saisie
    // private void clearFields() {
    //     idField.setText("");
    //     firstNameField.setText("");
    //     lastNameField.setText("");
    //     emailField.setText("");
    // }
}
