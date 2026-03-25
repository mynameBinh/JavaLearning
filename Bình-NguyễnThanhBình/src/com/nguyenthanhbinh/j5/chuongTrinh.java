package com.nguyenthanhbinh.j5;

import java.io.*;
import java.util.*;

public class chuongTrinh {

    public static void main(String[] args) {
        ArrayList<NhanVien> danhSachNV = new ArrayList<>();
        
        // Nhiệm vụ 2: Đọc danh sách nhân viên từ file
        docDanhSachNhanVien(danhSachNV);

        // Nhiệm vụ 3: Sắp xếp giảm dần theo Lương thực nhận và in
        sapXepGiamDanTheoLuongThucNhan(danhSachNV);
        System.out.println("--- CAU 3: DANH SACH NHAN VIEN THEO LUONG THUC NHAN GIAM DAN ---");
        inDanhSachCau3(danhSachNV);

        System.out.println("\n");

        // Nhiệm vụ 4: Sắp xếp ABC theo Tên, trùng Tên thì theo Họ đệm và in
        sapXepTangDanTheoTen(danhSachNV);
        System.out.println("--- CAU 4: DANH SACH NHAN VIEN SAP XEP THEO TEN (ABC) ---");
        inDanhSachCau4(danhSachNV);
    }

    private static void docDanhSachNhanVien(ArrayList<NhanVien> danhSachNV) {
        String filename = "nhanvien.txt";
        
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            
            while(true) {
                String st = br.readLine();
                if(st == null) break;
                if(st.trim().isEmpty()) continue; // Bỏ qua dòng trống nếu có
                
                String[] tt = st.split(";");
                
                int maNhanVien = Integer.parseInt(tt[0].trim());
                String hoTen = tt[1].trim();
                boolean gioiTinh = tt[2].trim().equals("Nam"); // true = Nam, false = Nu
                double heSoLuong = Double.parseDouble(tt[3].trim());
                
                NhanVien x = new NhanVien(maNhanVien, hoTen, gioiTinh, heSoLuong);
                danhSachNV.add(x);
            }
            br.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =========================================================
    // CÁC HÀM HỖ TRỢ XỬ LÝ NGHIỆP VỤ (Thay vì viết trong NhanVien)
    // =========================================================

    private static double tinhLuongThucNhan(NhanVien nv) {
        double luong = nv.getHeSoLuong() * 2000000;
        if (!nv.isGioiTinh()) { // isGioiTinh() == false tức là Nữ
            luong += luong * 0.10; // Nữ được cộng thêm 10%
        }
        return luong;
    }

    private static String layTen(NhanVien nv) {
        String hoTen = nv.getHoTen().trim();
        int lastSpaceIndex = hoTen.lastIndexOf(" ");
        if (lastSpaceIndex == -1) return hoTen; // Nếu tên chỉ có 1 chữ
        return hoTen.substring(lastSpaceIndex + 1);
    }

    private static String layHoDem(NhanVien nv) {
        String hoTen = nv.getHoTen().trim();
        int lastSpaceIndex = hoTen.lastIndexOf(" ");
        if (lastSpaceIndex == -1) return ""; // Không có họ đệm
        return hoTen.substring(0, lastSpaceIndex).trim();
    }

    private static String layChuoiGioiTinh(NhanVien nv) {
        return nv.isGioiTinh() ? "Nam" : "Nu";
    }

    // =========================================================
    // CÁC HÀM SẮP XẾP VÀ IN
    // =========================================================
    
    // Nhiệm vụ 3: Sắp xếp theo Lương thực nhận
    private static void sapXepGiamDanTheoLuongThucNhan(ArrayList<NhanVien> danhSachNV) {
        for(int i = 0; i < danhSachNV.size() - 1; i++) {
            for(int j = i + 1; j < danhSachNV.size(); j++) {
                NhanVien nv1 = danhSachNV.get(i);
                NhanVien nv2 = danhSachNV.get(j);
                
                if(tinhLuongThucNhan(nv1) < tinhLuongThucNhan(nv2)) {
                    NhanVien temp = danhSachNV.get(i);
                    danhSachNV.set(i, danhSachNV.get(j));
                    danhSachNV.set(j, temp);
                }
            }
        }
    }

    // Hàm in định dạng bảng cho câu 3
    private static void inDanhSachCau3(ArrayList<NhanVien> danhSachNV) {
        System.out.printf("%-5s %-15s %-25s %-15s %-15s %-20s\n", 
            "STT", "Ma nhan vien", "Ho va ten", "Gioi tinh", "He so luong", "Luong thuc nhan");
        for (int i = 0; i < danhSachNV.size(); i++) {
            NhanVien nv = danhSachNV.get(i);
            System.out.printf("%-5d %-15d %-25s %-15s %-15.2f %-20.0f\n", 
                (i + 1), nv.getMaNhanVien(), nv.getHoTen(), layChuoiGioiTinh(nv), nv.getHeSoLuong(), tinhLuongThucNhan(nv));
        }
    }

    // Nhiệm vụ 4: Sắp xếp theo Tên, trùng tên thì theo Họ đệm
    private static void sapXepTangDanTheoTen(ArrayList<NhanVien> danhSachNV) {
        for (int i = 0; i < danhSachNV.size() - 1; i++) {
            for (int j = i + 1; j < danhSachNV.size(); j++) {
                NhanVien nv1 = danhSachNV.get(i);
                NhanVien nv2 = danhSachNV.get(j);
                
                int cmpTen = layTen(nv1).compareToIgnoreCase(layTen(nv2));
                
                // Nếu Tên xếp sau trong ABC, hoặc Trùng tên nhưng Họ đệm xếp sau
                if (cmpTen > 0 || (cmpTen == 0 && layHoDem(nv1).compareToIgnoreCase(layHoDem(nv2)) > 0)) {
                    NhanVien temp = danhSachNV.get(i);
                    danhSachNV.set(i, danhSachNV.get(j));
                    danhSachNV.set(j, temp);
                }
            }
        }
    }

    // Hàm in định dạng bảng có tách Họ đệm và Tên cho câu 4
    private static void inDanhSachCau4(ArrayList<NhanVien> danhSachNV) {
        System.out.printf("%-5s %-15s %-20s %-10s %-15s %-15s %-20s\n", 
            "STT", "Ma nhan vien", "Ho dem", "Ten", "Gioi tinh", "He so luong", "Luong thuc nhan");
        for (int i = 0; i < danhSachNV.size(); i++) {
            NhanVien nv = danhSachNV.get(i);
            System.out.printf("%-5d %-15d %-20s %-10s %-15s %-15.2f %-20.0f\n", 
                (i + 1), nv.getMaNhanVien(), layHoDem(nv), layTen(nv), layChuoiGioiTinh(nv), nv.getHeSoLuong(), tinhLuongThucNhan(nv));
        }
    }
}