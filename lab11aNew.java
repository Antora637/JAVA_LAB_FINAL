
import java.sql.*;
import java.io.*;
import java.util.*;

public class lab11aNew {

    public static void main(String[] args) {

        String path = new File("ICE_PUST.accdb").getAbsolutePath();
        String url = "jdbc:ucanaccess://" + path
                   + ";newDatabaseVersion=V2010";

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            Connection con = DriverManager.getConnection(url);
            System.out.println("Database connection successful.");

            DatabaseMetaData meta = con.getMetaData();

            ResultSet rs = meta.getTables(
                null, null, "Student", new String[]{"TABLE"});

            boolean tableExists = rs.next();
            rs.close();

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

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            System.out.print("Enter student email: ");
            String email = sc.nextLine();

            System.out.print("Enter student phone: ");
            String phone = sc.nextLine();

            String sql = "INSERT INTO Student ([Name], Email, Phone) " +
                         "VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);

            int rows = ps.executeUpdate();

            System.out.println(
                rows + " student record inserted successfully.");

            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
