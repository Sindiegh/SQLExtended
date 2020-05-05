import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class main {
    private static  PreparedStatement pst;
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/umuzi";
        String user = "user";
        String password = "pass";
        try{
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT* FROM customers");
            while (rs.next()) {
                System.out.println(
                        rs.getString("customerid") + "\t"
                                +rs.getString("firstname") + "\t"
                                +rs.getString("lastname") + "\t"
                                + rs.getString("gender") + "\t"
                                + rs.getString("address") + "\t"
                                + rs.getString("phone") + "\t"
                                + rs.getString("email") + "\t"
                                + rs.getString("city") + "\t"
                                + rs.getString("country")
                );
            }
            rs=st.executeQuery("SELECT FirstName FROM Customers");
            while (rs.next()) {
                System.out.println(rs.getString("firstname"));
            }
            rs=st.executeQuery("SELECT * FROM Customers WHERE CustomerID=1");
            while (rs.next()) {
                System.out.println(rs.getString("firstname"));
            }
            String query1="UPDATE Customers\n" +
                    "SET FirstName=?, LastName=?\n" +
                    "WHERE CustomerID=?";
            pst=con.prepareStatement(query1);
            pst.setString(1,"Lerato");
            pst.setString(2,"Mabitso");
            pst.setInt(3,1);
            pst.executeUpdate();
            String query2 = "DELETE FROM customers WHERE customerid = ?";
            pst = con.prepareStatement(query2);
            pst.setInt(1, 2);
            pst.execute();
            String query3 = "SELECT COUNT(DISTINCT status) FROM orders";
            rs = st.executeQuery(query3);
            rs.next();
            int queryCount = rs.getInt(1);
            System.out.println("Count: " + queryCount);
            String query4 = "SELECT MAX (amount) FROM payments";
            rs = st.executeQuery(query4);
            rs.next();
            double max = rs.getDouble(1);
            System.out.println("Maximum amount: " + max);



            con.close();
        }   catch (SQLException ex) {
            Logger lgr = Logger.getLogger(main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}