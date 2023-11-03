/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ultilities;

/**
 *
 * @author ADMIN
 */
import java.sql.*;

public class DBConnection1 {

    private static String hostName = "localhost";
    private static String acc = "sa";
    private static String pass = "123456";
    private static String dbName = "COFFEESHOP_DA11";
    private static String connectionSql
            = "jdbc:sqlserver://" + hostName + ":1433;databaseName=" + dbName + ";user=" + acc + ";password=" + pass + ";encrypt=false;sendTimeAsDateTime=false";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection conn;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error At Driver");
        }
    }

    public static void main(String[] args) {
        openDbConnection();
    }

    public static Connection openDbConnection() {
        try {

//            System.out.println(connectionSql);
            System.out.println("Loading...");

            return DriverManager.getConnection(connectionSql, acc, pass);
        } catch (Exception e) {
            System.out.println("Trace error at OpenDbConnection function: ");
            e.printStackTrace();
            return null;
        }
    }

    public static int ExcuteSQL(String sql, Object... args) {
        PreparedStatement pstm = getStmt(sql, args);
        try {
            try {
                return pstm.executeUpdate();
            } finally {
                pstm.close();
            }
        } catch (Exception e) {
            System.out.println("Trace error at ExcuteSQL function: ");
            e.printStackTrace();
            return 0;
        }
    }

    public static ResultSet getDataFromQuery(String sql, Object... args) throws SQLException {
        PreparedStatement pstm = getStmt(sql, args);
        return pstm.executeQuery();
    }

    public static PreparedStatement getStmt(String sql, Object... args) {
        try {
            conn = openDbConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps;
        } catch (Exception ex) {
            System.out.println("Trace error at getStmt function: ");
            ex.printStackTrace();
            return null;
        }
    }

}
