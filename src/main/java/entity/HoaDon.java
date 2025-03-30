package entity;

import java.time.LocalDateTime;

public class HoaDon {
    private String maHD;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private PhuongThucThanhToan PTTT;
    private ThueVAT thueVAT;
    private KhuyenMai khuyenMai;
    private LocalDateTime ngayLap;
    private LocalDateTime ngayXuat;
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public PhuongThucThanhToan getPTTT() {
		return PTTT;
	}
	public void setPTTT(PhuongThucThanhToan pTTT) {
		PTTT = pTTT;
	}
	public ThueVAT getThueVAT() {
		return thueVAT;
	}
	public void setThueVAT(ThueVAT thueVAT) {
		this.thueVAT = thueVAT;
	}
	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	public LocalDateTime getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(LocalDateTime ngayLap) {
		this.ngayLap = ngayLap;
	}
	public LocalDateTime getNgayXuat() {
		return ngayXuat;
	}
	public void setNgayXuat(LocalDateTime ngayXuat) {
		this.ngayXuat = ngayXuat;
	}


}
