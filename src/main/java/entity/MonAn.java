package entity;

public class MonAn {
    private String maMonAn;
    private String tenMonAn;
    private double gia;
    private String ghiChu;
    private String loaiMonAn;
    private String linkIMG;
    public MonAn() {
    	
    }
    public MonAn(String maMonAn, String tenMonAn, double gia, String ghiChu, String loaiMonAn, String linkIMG) {
    	this.maMonAn = maMonAn;
    	this.tenMonAn = tenMonAn;
    	this.gia = gia;
    	this.ghiChu = ghiChu;
    	this.loaiMonAn = loaiMonAn;
    	this.linkIMG = linkIMG;
    }
	public String getLinkIMG() {
		return linkIMG;
	}
	public void setLinkIMG(String linkIMG) {
		this.linkIMG = linkIMG;
	}
	public String getMaMonAn() {
		return maMonAn;
	}
	public void setMaMonAn(String maMonAn) {
		this.maMonAn = maMonAn;
	}
	public String getTenMonAn() {
		return tenMonAn;
	}
	public void setTenMonAn(String tenMonAn) {
		this.tenMonAn = tenMonAn;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public String getLoaiMonAn() {
		return loaiMonAn;
	}
	public void setLoaiMonAn(String loaiMonAn) {
		this.loaiMonAn = loaiMonAn;
	}

    
}
