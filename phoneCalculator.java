import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class phoneCalculator extends JFrame implements ActionListener {

    JTextField display;
    double num1, num2;
    String operator = "";
    boolean newNumber = false;

    phoneCalculator() {

        setTitle("Simple Calculator");
        setLayout(new BorderLayout(5, 5));

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 25));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String buttons[] = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String s : buttons) {
            JButton b = new JButton(s);
            b.setFont(new Font("Arial", Font.BOLD, 20));
            b.addActionListener(this);
            panel.add(b);
        }

        add(panel, BorderLayout.CENTER);

        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();

        if (s.matches("[0-9]")) {

            if (newNumber) {
                display.setText(display.getText() + s);
                newNumber = false;
            } else {
                display.setText(display.getText() + s);
            }
        }

        else if (s.equals("C")) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            operator = "";
            newNumber = false;
        }

        else if (s.equals("+") || s.equals("-")
                || s.equals("*") || s.equals("/")) {

            if (display.getText().isEmpty())
                return;

            String text = display.getText().trim();

            if (text.endsWith("+") || text.endsWith("-")
                    || text.endsWith("*") || text.endsWith("/"))
                return;

            num1 = Double.parseDouble(text);
            operator = s;
            display.setText(text + " " + s + " ");
            newNumber = true;
        }

        else if (s.equals("=")) {

            try {
                String parts[] = display.getText().trim().split(" ");

                if (parts.length < 3)
                    return;

                num1 = Double.parseDouble(parts[0]);
                operator = parts[1];
                num2 = Double.parseDouble(parts[2]);

                double result = 0;

                if (operator.equals("+"))
                    result = num1 + num2;

                else if (operator.equals("-"))
                    result = num1 - num2;

                else if (operator.equals("*"))
                    result = num1 * num2;

                else if (operator.equals("/")) {
                    if (num2 == 0) {
                        display.setText("Cannot divide by zero");
                        return;
                    }
                    result = num1 / num2;
                }

                display.setText(String.valueOf(result));
                operator = "";
                newNumber = true;

            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        new phoneCalculator();
    }
}