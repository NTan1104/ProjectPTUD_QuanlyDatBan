CREATE DATABASE QuanLyDatBan;
USE QuanLyDatBan;


-- 1. Tạo bảng KhachHang (Khách hàng)
CREATE TABLE KhachHang (
    MaKH VARCHAR(10) PRIMARY KEY,
    TenKH VARCHAR(255) NOT NULL,
    SDT VARCHAR(15),
    GioiTinh VARCHAR(10)
);

-- 2. Tạo bảng ThueVAT (Thuế VAT)
CREATE TABLE ThueVAT (
    MaVAT VARCHAR(10) PRIMARY KEY,
    PhanTramThue DECIMAL(5, 2) NOT NULL,
    CONSTRAINT CHK_PhanTramThue CHECK (PhanTramThue >= 0 AND PhanTramThue <= 100)
);

-- 3. Tạo bảng PhuongThucThanhToan (Phương thức thanh toán)
CREATE TABLE PhuongThucThanhToan (
    MaPTTT VARCHAR(10) PRIMARY KEY,
    PhuongThuc VARCHAR(100) NOT NULL
);

-- 4. Tạo bảng KhuyenMai (Khuyến mãi)
CREATE TABLE KhuyenMai (
    MaKM VARCHAR(10) PRIMARY KEY,
    DieuKien VARCHAR(255) NOT NULL,
    Hansudung DATETIME,
    Ngaybatdau DATETIME,
    PhanTramGiamGia DECIMAL(5, 2) NOT NULL,
    CONSTRAINT PhanTramGiamGia CHECK (PhanTramGiamGia >= 0 AND PhanTramGiamGia <= 100)
);

-- 5. Tạo bảng NhanVien (Nhân viên)
CREATE TABLE NhanVien (
    MaNV VARCHAR(10) PRIMARY KEY,
    TenNV VARCHAR(255) NOT NULL,
    SDT VARCHAR(11),
    GioiTinh VARCHAR(10),
    ChucVu VARCHAR(20),
    Tuoi INT,
    Hesoluong FLOAT,
    LuongNV FLOAT
);

-- 6. Tạo bảng TaiKhoan (Tài khoản)
CREATE TABLE TaiKhoan (
    TenDangNhap VARCHAR(10) PRIMARY KEY,
    MatKhau VARCHAR(255) NOT NULL,
    TrangThai BIT,
    Lockin DATETIME,
    Lockout DATETIME,
    MaNV VARCHAR(10) NOT NULL,
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

-- 7. Tạo bảng Ban (Bàn)
CREATE TABLE Ban (
    MaBan VARCHAR(10) PRIMARY KEY,
    TrangThai VARCHAR(50) NOT NULL,
    Tang INT,
    SoCho INT,
    GhiChu TEXT
);

-- 8. Tạo bảng ChiTietPhieuDatBan (Chi tiết phiếu đặt bàn)
CREATE TABLE ChiTietPhieuDatBan (
    MaCTPDB VARCHAR(10) PRIMARY KEY,
    TimeNhanBan DATETIME,
    TimeTraBan DATETIME, 
    SoNguoi INT
);

-- 9. Tạo bảng PhieuDatBan (Phiếu đặt bàn)
CREATE TABLE PhieuDatBan (
    MaPDB VARCHAR(10) PRIMARY KEY,
    MaKhachHang VARCHAR(10) NOT NULL,
    MaBan VARCHAR(10) NOT NULL,
    MaCTPDB VARCHAR(10) NOT NULL,
    MaNV VARCHAR(10) NOT NULL,
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKH),
    FOREIGN KEY (MaBan) REFERENCES Ban(MaBan),
    FOREIGN KEY (MaCTPDB) REFERENCES ChiTietPhieuDatBan(MaCTPDB),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

-- 10. Tạo bảng MonAn (Món ăn)
CREATE TABLE MonAn (
    MaMonAn VARCHAR(10) PRIMARY KEY,
    TenMonAn VARCHAR(50) NOT NULL,
    Gia FLOAT,
    GhiChu TEXT
);
ALTER TABLE MonAn
    ADD LoaiMonAn VARCHAR(20);

-- 11. Tạo bảng HoaDon (Hóa đơn)
CREATE TABLE HoaDon (
    MaHD VARCHAR(10) PRIMARY KEY,
    MaNV VARCHAR(10) NOT NULL,
    MaKH VARCHAR(10) NOT NULL,
    MaPTTT VARCHAR(10) NOT NULL,
    MaVAT VARCHAR(10) NOT NULL,
    MaKM VARCHAR(10) NOT NULL,
    NgayLap DATETIME,
    NgayXuat DATETIME,
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
    FOREIGN KEY (MaPTTT) REFERENCES PhuongThucThanhToan(MaPTTT),
    FOREIGN KEY (MaVAT) REFERENCES ThueVAT(MaVAT),
    FOREIGN KEY (MaKM) REFERENCES KhuyenMai(MaKM)
);

-- 12. Tạo bảng ChiTietHoaDon (Chi tiết hóa đơn)
CREATE TABLE ChiTietHoaDon (
    MaCTHD VARCHAR(10) PRIMARY KEY,
    MaHD VARCHAR(10) NOT NULL,
    MaMonAn VARCHAR(10) NOT NULL,
    SoLuong INT NOT NULL,
    DonGia FLOAT NOT NULL,
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
    FOREIGN KEY (MaMonAn) REFERENCES MonAn(MaMonAn)
);
