
import javax.swing.*;
import java.awt.*;

public class lab3new {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Student form");
        frame.setSize(550, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();

        JLabel idLabel = new JLabel("Student ID: ");
        JTextField idField = new JTextField();

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone: ");
        JTextField phoneField = new JTextField();

        JLabel addressLabel = new JLabel("Address: ");
        JTextField addressField = new JTextField();

        JLabel deptLabel = new JLabel("Department: ");
        String[] departments = {"ICE", "CSE", "EEE", "EECE"};
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

        panel.add(phoneLabel);
        panel.add(phoneField);

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

            String email = emailField.getText();
            String phone = phoneField.getText();

            // Email validation
            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(
                        frame, "Invalid Email!");
                return;
            }

            // Phone validation
            if (phone.length() != 11 ||
                    !phone.startsWith("01") ||
                    !phone.matches("[0-9]+")) {

                JOptionPane.showMessageDialog(
                        frame, "Invalid Phone Number!");
                return;
            }

            String gender = "";

            if (male.isSelected()) {
                gender = "Male";
            } else if (female.isSelected()) {
                gender = "Female";
            }

            String information =
                    "Student Information\n" +
                    "Name: " + nameField.getText() + "\n" +
                    "Student ID: " + idField.getText() + "\n" +
                    "Department: " + deptBox.getSelectedItem() + "\n" +
                    "Gender: " + gender + "\n" +
                    "Email: " + email + "\n" +
                    "Phone: " + phone + "\n" +
                    "Address: " + addressField.getText() + "\n\n" +
                    "Student Form Submitted Successfully!";

            JOptionPane.showMessageDialog(
                    frame,
                    information,
                    "Student Information",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        clearButton.addActionListener(e -> {

            nameField.setText("");
            idField.setText("");
            emailField.setText("");
            phoneField.setText("");
            addressField.setText("");

            deptBox.setSelectedIndex(0);
            genderGroup.clearSelection();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
