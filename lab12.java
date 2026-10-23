
import java.sql.*;
import java.util.Scanner;

public class lab12 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/ICE_PUST3";
        String user = "root";
        String password = ""; // Enter your MySQL password here, if required.

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Database connected successfully.");

            Statement st = con.createStatement();

            int choice;

            do {
                System.out.println("\n===== Student Management System =====");
                System.out.println("1. Insert Data");
                System.out.println("2. Delete Data");
                System.out.println("3. Edit Data");
                System.out.println("4. Show All Data");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1: {
                        System.out.print("Enter student name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter student email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter student phone number: ");
                        String phone = sc.nextLine();

                        String sql =
                            "INSERT INTO Student (Name, Email, Phone) " +
                            "VALUES (?, ?, ?)";

                        PreparedStatement ps = con.prepareStatement(sql);

                        ps.setString(1, name);
                        ps.setString(2, email);
                        ps.setString(3, phone);

                        int rows = ps.executeUpdate();

                        System.out.println(
                            rows + " student record(s) inserted successfully."
                        );

                        ps.close();
                        break;
                    }

                    case 2: {
                        System.out.print("Enter the student ID to delete: ");
                        int id = Integer.parseInt(sc.nextLine());

                        String sql = "DELETE FROM Student WHERE id = ?";

                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1, id);

                        int rows = ps.executeUpdate();

                        if (rows > 0) {
                            System.out.println("Student record deleted successfully.");
                        } else {
                            System.out.println("Student ID not found.");
                        }

                        ps.close();
                        break;
                    }

                    case 3: {
                        System.out.print("Enter the student ID to edit: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter the new name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter the new email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter the new phone number: ");
                        String phone = sc.nextLine();

                        String sql =
                            "UPDATE Student SET Name = ?, Email = ?, " +
                            "Phone = ? WHERE id = ?";

                        PreparedStatement ps = con.prepareStatement(sql);

                        ps.setString(1, name);
                        ps.setString(2, email);
                        ps.setString(3, phone);
                        ps.setInt(4, id);

                        int rows = ps.executeUpdate();

                        if (rows > 0) {
                            System.out.println("Student record updated successfully.");
                        } else {
                            System.out.println("Student ID not found.");
                        }

                        ps.close();
                        break;
                    }

                    case 4: {
                        String sql = "SELECT * FROM Student";

                        ResultSet rs = st.executeQuery(sql);

                        System.out.println("\nAll Student Records:");
                        System.out.println(
                            "--------------------------------------------------"
                        );

                        System.out.printf(
                            "%-5s %-15s %-25s %-15s%n",
                            "ID", "Name", "Email", "Phone"
                        );

                        while (rs.next()) {
                            System.out.printf(
                                "%-5d %-15s %-25s %-15s%n",
                                rs.getInt("id"),
                                rs.getString("Name"),
                                rs.getString("Email"),
                                rs.getString("Phone")
                            );
                        }

                        rs.close();
                        break;
                    }

                    case 5:
                        System.out.println("Program terminated successfully.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } while (choice != 5);

            st.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// Compile:
// javac -cp ".;mysql-connector-j-26.7.0.jar" lab12.java

// Run:
// java -cp ".;mysql-connector-j-26.7.0.jar" lab12

//sql code:

// CREATE DATABASE IF NOT EXISTS ICE_PUST3;
// USE ICE_PUST3;

// CREATE TABLE IF NOT EXISTS Student (
//     id INT PRIMARY KEY AUTO_INCREMENT,
//     Name VARCHAR(100),
//     Email VARCHAR(100),
//     Phone VARCHAR(30)
// );