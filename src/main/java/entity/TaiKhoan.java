package entity;

import java.time.LocalDateTime;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private boolean trangThai;
    private LocalDateTime lockIn;
    private LocalDateTime lockOut;
    private NhanVien nhanVien;
    
    string test = nhanVien.getMaNV();
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public LocalDateTime getLockIn() {
		return lockIn;
	}
	public void setLockIn(LocalDateTime lockIn) {
		this.lockIn = lockIn;
	}
	public LocalDateTime getLockOut() {
		return lockOut;
	}
	public void setLockOut(LocalDateTime lockOut) {
		this.lockOut = lockOut;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}


}
