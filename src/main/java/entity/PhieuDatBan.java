package entity;

public class PhieuDatBan {
    private String maPDB;
    private KhachHang khachHang;
    private Ban Ban;
    private CTPhieuDatBan CTPDB;
    private NhanVien nhanVien;
	public String getMaPDB() {
		return maPDB;
	}
	public void setMaPDB(String maPDB) {
		this.maPDB = maPDB;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public Ban getBan() {
		return Ban;
	}
	public void setBan(Ban ban) {
		Ban = ban;
	}
	public CTPhieuDatBan getCTPDB() {
		return CTPDB;
	}
	public void setCTPDB(CTPhieuDatBan cTPDB) {
		CTPDB = cTPDB;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}


}
