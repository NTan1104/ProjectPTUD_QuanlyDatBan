package entity;

public class PhieuDatBan {
    private String maPDB;
    private String maKH;
    private String maBan;
    private String maNV;
    public PhieuDatBan() {
    	
    }
    public PhieuDatBan(String maPDB, String maKH, String maBan, String maNV) {
    	this.maPDB = maPDB;
    	this.maKH = maKH;
    	this.maBan = maBan;
    	this.maNV = maNV;
    }
	public String getMaPDB() {
		return maPDB;
	}
	public void setMaPDB(String maPDB) {
		this.maPDB = maPDB;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getMaBan() {
		return maBan;
	}
	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
    


}
