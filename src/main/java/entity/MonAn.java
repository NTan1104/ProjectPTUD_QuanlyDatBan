package entity;

@SuppressWarnings("serial")
public class MonAn implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String maMonAn;
    private String tenMonAn;
    private float gia; // Đổi từ double sang float để khớp với SQL
    private String ghiChu;
    private String loaiMonAn;
    private String duongDanHinhAnh;

    // Constructor
    public MonAn(String maMonAn, String tenMonAn, float gia, String ghiChu, String loaiMonAn, String duongDanHinhAnh) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.gia = gia;
        this.ghiChu = ghiChu;
        this.loaiMonAn = loaiMonAn;
        this.duongDanHinhAnh = duongDanHinhAnh;
    }

    // Getters and Setters
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

    public float getGia() { // Đổi từ double sang float
        return gia;
    }

    public void setGia(float gia) { // Đổi từ double sang float
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

    public String getDuongDanHinhAnh() {
        return duongDanHinhAnh;
    }

    public void setDuongDanHinhAnh(String duongDanHinhAnh) {
        this.duongDanHinhAnh = duongDanHinhAnh;
    }
}