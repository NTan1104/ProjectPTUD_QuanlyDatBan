package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private String trangThai;
    private LocalDateTime gioVaoLam;
    private LocalDateTime gioNghi;
    private double soGioLam;
    private NhanVien nhanVien;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String trangThai, LocalDateTime gioVaoLam, LocalDateTime gioNghi, double soGioLam, NhanVien nhanVien) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.gioVaoLam = gioVaoLam;
        this.gioNghi = gioNghi;
        this.soGioLam = soGioLam;
        this.nhanVien = nhanVien;
    }

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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getGioVaoLam() {
        return gioVaoLam;
    }

    public void setGioVaoLam(LocalDateTime gioVaoLam) {
        this.gioVaoLam = gioVaoLam;
    }

    public LocalDateTime getGioNghi() {
        return gioNghi;
    }

    public void setGioNghi(LocalDateTime gioNghi) {
        this.gioNghi = gioNghi;
    }

    public double getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(double soGioLam) {
        this.soGioLam = soGioLam;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenDangNhap);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TaiKhoan other = (TaiKhoan) obj;
        return Objects.equals(tenDangNhap, other.tenDangNhap);
    }

    @Override
    public String toString() {
        return "TaiKhoan [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", trangThai=" + trangThai +
               ", gioVaoLam=" + gioVaoLam + ", gioNghi=" + gioNghi + ", soGioLam=" + soGioLam + ", nhanVien=" + nhanVien + "]";
    }
}