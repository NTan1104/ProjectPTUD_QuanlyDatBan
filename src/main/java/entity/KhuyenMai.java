package entity;

import java.time.LocalDateTime;


public class KhuyenMai {
    private String maKM;
    private String dieuKien;
    private LocalDateTime hanSuDung;
    private LocalDateTime ngayBatDau;
    private double phanTramGiamGia;
	public String getMaKM() {
		return maKM;
	}
	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}
	public String getDieuKien() {
		return dieuKien;
	}
	public void setDieuKien(String dieuKien) {
		this.dieuKien = dieuKien;
	}
	public LocalDateTime getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(LocalDateTime hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public LocalDateTime getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDateTime ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public double getPhanTramGiamGia() {
		return phanTramGiamGia;
	}
	public void setPhanTramGiamGia(double phanTramGiamGia) {
		this.phanTramGiamGia = phanTramGiamGia;
	}


}
