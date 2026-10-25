import java.sql.*;
import java.io.*;
import java.util.*;

public class lab11a {

    public static void main(String[] args) {

        String path = new File("ICE_PUST.accdb").getAbsolutePath();
        String url = "jdbc:ucanaccess://" + path
                   + ";newDatabaseVersion=V2010";

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            Connection con = DriverManager.getConnection(url);
            System.out.println("Database connection successful.");

            // Check Student table
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getTables(
                null, null, "Student", new String[]{"TABLE"});

            boolean tableExists = rs.next();
            rs.close();

            // Create table if it does not exist
            if (!tableExists) {
                Statement st = con.createStatement();

                st.executeUpdate(
                    "CREATE TABLE Student (" +
                    "[Name] TEXT(100), " +
                    "Email TEXT(100), " +
                    "Phone TEXT(30))"
                );

                st.close();
                System.out.println("Student table created successfully.");
            }

            // Take input
            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            System.out.print("Enter student email: ");
            String email = sc.nextLine();

            System.out.print("Enter student phone: ");
            String phone = sc.nextLine();

            // Validate input
            if (name.trim().isEmpty() ||
                email.trim().isEmpty() ||
                phone.trim().isEmpty()) {

                System.out.println("Error: All fields are required!");

            } else if (!email.contains("@") ||
                       !email.contains(".")) {

                System.out.println("Error: Invalid email address!");

            } else if (!phone.matches("[0-9]+")) {

                System.out.println("Error: Phone must contain digits only!");

            } else {

                String sql =
                    "INSERT INTO Student ([Name], Email, Phone) " +
                    "VALUES (?, ?, ?)";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, phone);

                int rows = ps.executeUpdate();

                System.out.println(
                    rows + " student record inserted successfully.");

                ps.close();
            }

            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        sc.close();
    }
}

// javac -cp ".;ucanaccess-5.1.8-uber.jar" lab11a.java
// java -cp ".;ucanaccess-5.1.8-uber.jar" lab11a
