
import javax.swing.*;
import java.awt.*;

public class lab2new {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Restaurant Bill");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel itemLabel = new JLabel("Items: ");
        JTextField itemField = new JTextField(10);

        JLabel quantityLabel = new JLabel("Quantity: ");
        JTextField quantityField = new JTextField(5);

        JLabel priceLabel = new JLabel("Price: ");
        JTextField priceField = new JTextField(7);

        JButton addButton = new JButton("Add Item");
        JButton billButton = new JButton("Generate Bill");

        JTextArea billArea = new JTextArea(18, 45);
        billArea.setEditable(false);

        frame.add(itemLabel);
        frame.add(itemField);
        frame.add(quantityLabel);
        frame.add(quantityField);
        frame.add(priceLabel);
        frame.add(priceField);
        frame.add(addButton);
        frame.add(billButton);
        frame.add(new JScrollPane(billArea));

        StringBuilder items = new StringBuilder();
        double[] subtotal = {0};
        int[] itemCount = {0};

        // Add Item button
        addButton.addActionListener(e -> {

            String item = itemField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            double total = quantity * price;
            subtotal[0] += total;

            items.append(String.format(
                    "%-15s %-8d %-10.2f %-10.2f\n",
                    item, quantity, price, total));

            itemCount[0]++;

            itemField.setText("");
            quantityField.setText("");
            priceField.setText("");
        });

        // Generate Bill button
        billButton.addActionListener(e -> {

            if (itemCount[0] < 2) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Please order at least 2 items!"
                );
                return;
            }

            double vat = subtotal[0] * 0.05;
            double totalBill = subtotal[0] + vat;

            billArea.setText(
                    "          RESTAURANT BILL\n" +
                    "---------------------------------------------\n" +
                    String.format(
                            "%-15s %-8s %-10s %-10s\n",
                            "Item", "Qty", "Price", "Total") +
                    "---------------------------------------------\n" +
                    items.toString() +
                    "\nSubtotal: " +
                    String.format("%.2f", subtotal[0]) +
                    "\nVAT (5%): " +
                    String.format("%.2f", vat) +
                    "\nTotal Bill: " +
                    String.format("%.2f", totalBill)
            );
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
