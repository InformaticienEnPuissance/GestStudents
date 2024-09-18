package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.jgoodies.forms.layout.FormLayout;

public class FormLayoutExample extends JFrame {

    public FormLayoutExample() {
        setTitle("FormLayout Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Form layout
        FormLayout layout = new FormLayout(
            "left:pref, 4dlu, 50dlu",  // Column specs
            "pref, 4dlu, pref, 4dlu, pref");  // Row specs
        setLayout(layout);

        // Components
        add(new JLabel("Name:"), "1, 1");
        add(new JTextField(20), "3, 1");
        add(new JLabel("Email:"), "1, 3");
        add(new JTextField(20), "3, 3");
        add(new JButton("Submit"), "3, 5");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormLayoutExample().setVisible(true));
    }
}

