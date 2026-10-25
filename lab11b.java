import java.sql.*;
import java.io.*;

public class lab11b {

    public static void main(String[] args) {

        String databasePath = new File("ICE_PUST.accdb").getAbsolutePath();
        String url = "jdbc:ucanaccess://" + databasePath
                + ";newDatabaseVersion=V2010";

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            Connection con = DriverManager.getConnection(url);

            System.out.println("Database connection successful.");

            Statement st = con.createStatement();

            String sql = "SELECT Name, Email, Phone FROM Student";

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\nAll Student Records");
            System.out.println("------------------------------");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Phone: " + rs.getString("Phone"));

                System.out.println("------------------------------");
            }

            if (!found) {
                System.out.println("No student records found.");
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}