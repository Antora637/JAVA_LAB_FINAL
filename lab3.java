import javax.swing.*;
import java.awt.*;

public class lab3 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student form");
        frame.setSize(550, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();

        JLabel idLabel = new JLabel("Student ID: ");
        JTextField idField = new JTextField();

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();

        JLabel addressLabel = new JLabel("Address: ");
        JTextField addressField = new JTextField();

        JLabel deptLabel = new JLabel("Department: ");
        String[] departments = { "ICE", "CSE", "EEE", "EECE" };
        JComboBox<String> deptBox = new JComboBox<>(departments);

        JLabel genderLabel = new JLabel("Gender: ");
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JPanel genderPanel = new JPanel();
        genderPanel.add(male);
        genderPanel.add(female);

        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(deptLabel);
        panel.add(deptBox);
        panel.add(genderLabel);
        panel.add(genderPanel);
        panel.add(submitButton);
        panel.add(clearButton);
        frame.add(panel);

        submitButton.addActionListener(e -> {
            String gender = "";
            if (male.isSelected()) {
                gender = "Male";
            } else if (female.isSelected()) {
                gender = "Female";
            }

            String information = "     Student Information\n" +
                    "Name: " + nameField.getText() + "\n" +
                    "Student ID: " + idField.getText() + "\n" +
                    "Department: " + deptBox.getSelectedItem() + "\n" +
                    "Gender: " + gender + "\n" +
                    "Email: " + emailField.getText() + "\n" +
                    "Address: " + addressField.getText() + "\n\n" +
                    "Student Form Submitted Successfully!";

            JOptionPane.showMessageDialog(
                    frame, information, "Student Information", JOptionPane.INFORMATION_MESSAGE);

        });

        clearButton.addActionListener(e -> {
            nameField.setText("");
            idField.setText("");
            emailField.setText("");
            addressField.setText("");

            deptBox.setSelectedIndex(0);
            genderGroup.clearSelection();
        });
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }

}
