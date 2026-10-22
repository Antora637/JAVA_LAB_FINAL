
import java.sql.*;
import java.io.*;
import java.util.*;

public class lab11 {

    public static void main(String[] args) {

        String databasePath = new File("ICE_PUST.accdb").getAbsolutePath();
        String url = "jdbc:ucanaccess://" + databasePath
                + ";newDatabaseVersion=V2010";

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            Connection con = DriverManager.getConnection(url);
            System.out.println("Database connection successful.");

            DatabaseMetaData metaData = con.getMetaData();
            boolean tableExists = false;

            try (ResultSet table = metaData.getTables(
                    null, null, "Student", new String[]{"TABLE"})) {

                if (table.next()) {
                    tableExists = true;
                }
            }

            if (!tableExists) {
                Statement st = con.createStatement();

                st.executeUpdate(
                        "CREATE TABLE STUDENT ("
                        + "[Name] Text(100), "
                        + "[Email] Text(100), "
                        + "[Phone] Text(30))"
                );

                st.close();
                System.out.println("Student table created successfully.");

            } else {
                System.out.println("Student table already exists.");
            }

            Scanner sc = new Scanner(System.in);

            String sql = "INSERT INTO STUDENT ([Name], Email, Phone) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter student name: ");
            ps.setString(1, sc.nextLine());

            System.out.print("Enter student email: ");
            ps.setString(2, sc.nextLine());

            System.out.print("Enter student phone number: ");
            ps.setString(3, sc.nextLine());

            int rows = ps.executeUpdate();

            System.out.println(rows + " student record inserted successfully.");

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT Name, Email, Phone FROM STUDENT");

            System.out.println("\nAll Student Records");
            System.out.println("------------------------------");

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Phone: " + rs.getString("Phone"));
                System.out.println("------------------------------");
            }

            rs.close();
            st.close();
            ps.close();
            sc.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// Compile:
// javac -cp ".;ucanaccess-5.1.8-uber.jar" lab11.java

// Run:
// java -cp ".;ucanaccess-5.1.8-uber.jar" lab11
