package com.nguyenthanhbinh.j5;

//câu 1:
public class NhanVien {
	private int maNhanVien;
	private String hoTen;
	private boolean gioiTinh;
	private double heSoLuong;
	
	//hamKhoiTao
	public NhanVien() {
		this.maNhanVien = 0;
		this.hoTen = "";
		this.gioiTinh = false;
		this.heSoLuong = 0.0;
	}
	public NhanVien(int maNhanVien, String hoTen, boolean gioiTinh, double heSoLuong) {
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.heSoLuong = heSoLuong;
	}
	//setters
	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public void setHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	}
	//getters
	public int getMaNhanVien() {
		return maNhanVien;
	}
	public String getHoTen() {
		return hoTen;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public double getHeSoLuong() {
		return heSoLuong;
	}
	
    @Override
    public String toString() {
        return String.format("Mã NV: %d | Họ tên: %s | Giới tính: %s | Hệ số lương: %.2f", 
                            maNhanVien, hoTen, gioiTinh, heSoLuong);
    }
}

