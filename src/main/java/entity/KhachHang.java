package entity;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private String sdt;
    private String gioiTinh;
    public KhachHang() {
    	
    }
    public KhachHang(String maKH, String tenKH, String sdt, String gioiTinh) {
    	this.maKH = maKH;
    	this.tenKH = tenKH;
    	this.sdt = sdt;
    	this.gioiTinh = gioiTinh;
    }
	public String getMaKH() {
		return maKH;
	}
	
	public KhachHang() {
		super();
	}

	public KhachHang(String maKH, String tenKH, String sdt, String gioiTinh) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}


}
