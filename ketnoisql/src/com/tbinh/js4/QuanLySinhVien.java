package com.tbinh.js4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuanLySinhVien {

    private static Connection getConnection() throws SQLException {
        String connectionUrl = 
                "jdbc:sqlserver://localhost:1433;"
                + "database=CSDL_SinhVien;"
                + "user=sa;"
                + "password=123;" 
                + "encrypt=true;trustServerCertificate=true;loginTimeout=30;";
        
        Connection connection = DriverManager.getConnection(connectionUrl);
        return connection;
    }

    // Viết hàm docDanhSachSinhVien() trả về List<SinhVien>
    public static List<SinhVien> docDanhSachSinhVien() {
        List<SinhVien> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien"; 

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Đã kết nối đến CSDL thành công!");

            while (rs.next()) {
                String msSinhVien = rs.getString("MsSinhVien");
                String hoDem = rs.getString("HoDem");
                String ten = rs.getString("Ten");
                boolean gioiTinhNam = rs.getBoolean("GioiTinhNam");
                double diemTrungBinh = rs.getDouble("DiemTrungBinh");
                int maNganh = rs.getInt("MaNganh");
                
                // Tạo đối tượng và thêm vào list
                SinhVien sv = new SinhVien(msSinhVien, hoDem, ten, gioiTinhNam, diemTrungBinh, maNganh);
                danhSach.add(sv);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi tương tác với cơ sở dữ liệu: " + e.getMessage());
        }
        
        return danhSach;
    }

    // Hàm main để in ra màn hình
    public static void main(String[] args) {
        System.out.println("Đang lấy danh sách sinh viên...\n");
        
        List<SinhVien> listSV = docDanhSachSinhVien();

        System.out.println("                                DANH SÁCH SINH VIÊN                                   ");
        System.out.println("--------------------------------------------------------------------------------------");
        
        if (listSV.isEmpty()) {
            System.out.println("Không có dữ liệu sinh viên.");
        } else {
            for (SinhVien sv : listSV) {
                System.out.println(sv.toString());
            }
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }
}