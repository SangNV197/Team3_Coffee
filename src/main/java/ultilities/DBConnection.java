/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ultilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 84374
 */
public class DBConnection {//Lớp này giải quyết kết nối xử lý truy vấn

    private static String hostName = "localhost";
    private static String acc = "sa";
    private static String pass = "123456";
    private static String dbName = "COFFEESHOP_DA11";
    private static String connectionSql
            = "jdbc:sqlserver://" + hostName + ":1433;databaseName=" + dbName + ";user=" + acc + ";password=" + pass + ";encrypt=false";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection conn;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("Lỗi Driver");
        }
    }

    //1. Mở kết nối
    public static Connection openDbConnection() {
        try {
            return DriverManager.getConnection(connectionSql, acc, pass);
        } catch (SQLException ex) {
            return null;
        }
    }

    //2. Thực thi truy vấn Thêm, Sửa , Xoá
    public static int ExcuteDungna(String sql, Object... args) {
        PreparedStatement pstm = getStmt(sql, args);
        try {
            try {
                int rs = pstm.executeUpdate();
                System.out.println(rs);
                return pstm.executeUpdate();
            } finally {
                pstm.close();
            }
        } catch (Exception ex) {
            System.out.println("Lỗi tại ExcuteDungna");
            ex.printStackTrace();
            return 0;
        }
    }

    //3. Trả lại 1 tập đối tượng
    public static ResultSet getDataFromQuery(String sql, Object... args) throws SQLException {
        PreparedStatement pstm = getStmt(sql, args);
        return pstm.executeQuery();
    }

    //4. Chuẩn bị câu truy vấn trước khi thực thi - Các varargs sử dụng dấu ba chấm (...) sau kiểu dữ liệu.
    public static PreparedStatement getStmt(String sql, Object... args) {
        try {
            conn = openDbConnection();
            PreparedStatement ps;
            //ps = conn.prepareCall(sql) Gọi store procedure
            ps = conn.prepareStatement(sql);//Dùng để triển khai các câu lệnh truy vấn thường
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);//Cộng các value sau câu truy vấn
            }
            return ps;
        } catch (SQLException ex) {
            return null;
        }
    }

    public synchronized static Connection getConnection() throws Exception {
        return DriverManager.getConnection(connectionSql);
    }

    public static void main(String[] args) throws Exception {
        String version = DBConnection.getConnection().getMetaData().getDatabaseProductVersion();
//        System.out.println(version);
    }

    public void ExcuteSQL(String INSERT_SQL, String id, String tenLoaiDoUong, String tenDoUong, int i, double giaBan) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void ExcuteSQL(String INSERT_SQL, String ma, String tenLoaiDoUong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
