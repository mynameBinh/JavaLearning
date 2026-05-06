package com.tbinh.js4;

public class SinhVien {
    private String msSinhVien;
    private String hoDem;
    private String ten;
    private boolean gioiTinhNam;
    private double diemTrungBinh;
    private int maNganh;

    public SinhVien() {
    }

    // Hàm khởi tạo
    public SinhVien(String msSinhVien, String hoDem, String ten, boolean gioiTinhNam, double diemTrungBinh, int maNganh) {
        this.msSinhVien = msSinhVien;
        this.hoDem = hoDem;
        this.ten = ten;
        this.gioiTinhNam = gioiTinhNam;
        this.diemTrungBinh = diemTrungBinh;
        this.maNganh = maNganh;
    }

    // Getter Setter
    public String getMsSinhVien() { return msSinhVien; }
    public void setMsSinhVien(String msSinhVien) { this.msSinhVien = msSinhVien; }

    public String getHoDem() { return hoDem; }
    public void setHoDem(String hoDem) { this.hoDem = hoDem; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public boolean isGioiTinhNam() { return gioiTinhNam; }
    public void setGioiTinhNam(boolean gioiTinhNam) { this.gioiTinhNam = gioiTinhNam; }

    public double getDiemTrungBinh() { return diemTrungBinh; }
    public void setDiemTrungBinh(double diemTrungBinh) { this.diemTrungBinh = diemTrungBinh; }

    public int getMaNganh() { return maNganh; }
    public void setMaNganh(int maNganh) { this.maNganh = maNganh; }

    // Hàm toString để in thông tin ra màn hình
    @Override
    public String toString() {
        String gioiTinh = gioiTinhNam ? "Nam" : "Nữ";
        String hoTen = hoDem + " " + ten;
        return String.format("Mã SV: %-10s  Họ tên: %-20s  Giới tính: %-3s  Điểm TB: %-5.2f  Mã Ngành: %d", 
                             msSinhVien, hoTen, gioiTinh, diemTrungBinh, maNganh);
    }
}