package quanlyphutung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/quanlyphutung";
    private static final String USER = "root";   // sửa lại theo máy bạn
    private static final String PASSWORD = "Namanh0908";   // mật khẩu MySQL

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Khong tim thay driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Loi ket noi CSDL: " + e.getMessage());
        }
        return conn;
    }
}
