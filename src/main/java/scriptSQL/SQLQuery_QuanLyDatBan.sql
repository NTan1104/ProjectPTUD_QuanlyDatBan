CREATE DATABASE QuanLyDatBan;
USE QuanLyDatBan;

-- Tạo các bảng không có khóa ngoại trước
CREATE TABLE KhachHang (
    MaKH VARCHAR(10) PRIMARY KEY,
    TenKH NVARCHAR(50) NOT NULL,
    SDT VARCHAR(11),
    GioiTinh NVARCHAR(10)
);

CREATE TABLE ThueVAT (
    MaVAT VARCHAR(10) PRIMARY KEY,
    PhanTramThue DECIMAL(5, 2) NOT NULL,
    CONSTRAINT CHK_PhanTramThue CHECK (PhanTramThue >= 0 AND PhanTramThue <= 100)
);

CREATE TABLE PhuongThucThanhToan (
    MaPTTT VARCHAR(10) PRIMARY KEY,
    PhuongThuc NVARCHAR(100) NOT NULL
);

CREATE TABLE KhuyenMai (
    MaKM VARCHAR(10) PRIMARY KEY,
    DieuKien NVARCHAR(255) NOT NULL,
    Hansudung DATETIME,
    Ngaybatdau DATETIME,
    PhanTramGiamGia DECIMAL(5, 2) NOT NULL,
    CONSTRAINT PhanTramGiamGia CHECK (PhanTramGiamGia >= 0 AND PhanTramGiamGia <= 100)
);
ALTER TABLE NhanVien
ADD Email VARCHAR(50);
CREATE TABLE NhanVien (
    MaNV VARCHAR(10) PRIMARY KEY,
    TenNV NVARCHAR(50) NOT NULL,
    SDT VARCHAR(11),
    GioiTinh NVARCHAR(10),
    ChucVu NVARCHAR(50),
    Tuoi INT,
    Hesoluong FLOAT,
    LuongNV FLOAT,
	LinkIMG VARCHAR(100)
);

CREATE TABLE Ban (
    MaBan VARCHAR(10) PRIMARY KEY,
    TrangThai NVARCHAR(50) NOT NULL,
    Tang INT,
    SoCho INT,
    GhiChu NVARCHAR(MAX),
	CONSTRAINT CHK_TrangThai CHECK (TrangThai IN (N'Còn Trống', N'Đã Đặt', N'Đang Sử Dụng', N'Bảo Trì'))
);
ALTER TABLE Ban
DROP CONSTRAINT CHK_TrangThai;
ALTER TABLE Ban
ADD TenBan NVARCHAR(20);
ALTER TABLE Ban
ADD CONSTRAINT CHK_TrangThai CHECK (TrangThai IN (N'CÒN TRỐNG', N'ĐÃ ĐẶT', N'ĐANG SỬ DỤNG', N'BẢO TRÌ'));
CREATE TABLE MonAn (
    MaMonAn VARCHAR(10) PRIMARY KEY,
    TenMonAn NVARCHAR(50) NOT NULL,
    Gia FLOAT,
    GhiChu NVARCHAR(MAX),
    LoaiMonAn NVARCHAR(20)
);

CREATE TABLE ChiTietPhieuDatBan (
    MaCTPDB VARCHAR(10) PRIMARY KEY,
    TimeNhanBan DATETIME,
    TimeTraBan DATETIME,
    SoNguoi INT
);

-- Tạo các bảng có khóa ngoại sau
CREATE TABLE TaiKhoan (
    MaNV VARCHAR(10) NOT NULL,
    TenDangNhap VARCHAR(10) PRIMARY KEY,
    MatKhau VARCHAR(20) NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL,
	CONSTRAINT TK_TrangThai CHECK (TrangThai IN (N'Online', N'Offline')),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

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

CREATE TABLE ChiTietHoaDon (
    MaCTHD VARCHAR(10) PRIMARY KEY,
    MaHD VARCHAR(10) NOT NULL,
    MaMonAn VARCHAR(10) NOT NULL,
    SoLuong INT NOT NULL,
    DonGia FLOAT NOT NULL,
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
    FOREIGN KEY (MaMonAn) REFERENCES MonAn(MaMonAn)
);
-- THÊM DỮ LIỆU
INSERT INTO ThueVAT (MaVAT, PhanTramThue) VALUES
('VAT01', 10.00),
('VAT02', 8.00)
INSERT INTO PhuongThucThanhToan (MaPTTT, PhuongThuc) VALUES
('PTTT01', N'Tiền mặt'),
('PTTT03', N'Chuyển khoản');
INSERT INTO KhuyenMai (MaKM, DieuKien, Hansudung, Ngaybatdau, PhanTramGiamGia) VALUES
('KM01', N'Giảm giá cho hóa đơn trên 500,000', '2025-12-31 23:59:59', '2025-04-01 00:00:00', 10.00),
('KM02', N'Giảm giá cho khách hàng 1,000,000', '2025-06-30 23:59:59', '2025-04-01 00:00:00', 15.00)
INSERT INTO KhachHang (MaKH, TenKH, SDT, GioiTinh) VALUES
('KH001', N'Nguyễn Văn An', '0912345678', N'Nam'),
('KH002', N'Trần Thị Bình', '0987654321', N'Nữ'),
('KH003', N'Lê Minh Châu', '0901234567', N'Nữ');
INSERT INTO NhanVien (MaNV, TenNV, SDT, GioiTinh, ChucVu, Tuoi, Hesoluong, LuongNV, LinkIMG) VALUES
('NV001', N'Nguyễn Tân', '0935123456', N'Nam', N'Nhân viên quản lý', 21, 30000, 10000000, null),
('NV002', N'Đỗ Đông Giang', '0978123456', N'Nam', N'Nhân viên quản lý', 21, 30000, 8000000, null),
('NV003', N'Trần Minh Tín', '0967123456', N'Nam', N'Nhân viên quản lý', 21, 30000, 10000000, null);
UPDATE NhanVien
SET Email = 
    CASE MaNV
        WHEN 'NV001' THEN 'nguyen.tan@gmail.com'
        WHEN 'NV002' THEN 'do.giang@gmail.com'
        WHEN 'NV003' THEN 'tran.tin@gmail.com'
        WHEN 'NV006' THEN 'nguyen.hung@gmail.com'
        WHEN 'NV007' THEN 'tran.lan@gmail.com'
        WHEN 'NV008' THEN 'le.tuan@gmail.com'
        WHEN 'NV009' THEN 'pham.mai@gmail.com'
        WHEN 'NV010' THEN 'hoang.nam@gmail.com'
    END
WHERE MaNV IN ('NV001', 'NV002', 'NV003', 'NV006', 'NV007', 'NV008', 'NV009', 'NV010');
INSERT INTO NhanVien (MaNV, TenNV, SDT, GioiTinh, ChucVu, Tuoi, Hesoluong, LuongNV, LinkIMG) VALUES
('NV006', N'Nguyễn Văn Hùng', '0986123456', N'Nam', N'Nhân viên lễ tân', 35, 20000, 8000000, null),
('NV007', N'Trần Thị Lan', '0986123457', N'Nữ', N'Nhân viên lễ tân', 19, 20000, 8000000, null),
('NV008', N'Lê Minh Tuấn', '0986123458', N'Nam', N'Nhân viên lễ tân', 22, 20000, 8000000, null),
('NV009', N'Phạm Thị Mai', '0986123459', N'Nữ', N'Nhân viên lễ tân', 29, 20000, 8000000, null),
('NV010', N'Hoàng Văn Nam', '0986123460', N'Nam', N'Nhân viên lễ tân', 24, 20000, 8000000, null);

INSERT INTO TaiKhoan (MaNV, TenDangNhap, MatKhau, TrangThai) VALUES
('NV001', 'NV001', 'password', N'Offline'),
('NV002', 'NV002', 'password', N'Offline'),
('NV003', 'NV003', 'password', N'Offline');

INSERT INTO TaiKhoan (MaNV, TenDangNhap, MatKhau, TrangThai) VALUES
('NV006', 'NV006', 'password', N'Offline'),
('NV007', 'NV007', 'password', N'Offline'),
('NV008', 'NV008', 'password', N'Offline'),
('NV009', 'NV009', 'password', N'Offline'),
('NV010', 'NV010', 'password', N'Offline');
UPDATE TaiKhoan
SET 
    lockIn = 
        CASE MaNV
            WHEN 'NV001' THEN '2025-04-14 08:00:00'
            WHEN 'NV002' THEN '2025-04-14 08:30:00'
            WHEN 'NV003' THEN '2025-04-14 09:00:00'
            WHEN 'NV006' THEN '2025-04-14 07:30:00'
            WHEN 'NV007' THEN '2025-04-14 08:00:00'
            WHEN 'NV008' THEN '2025-04-14 08:15:00'
            WHEN 'NV009' THEN '2025-04-14 09:00:00'
            WHEN 'NV010' THEN '2025-04-14 08:45:00'
        END,
    lockOut = 
        CASE MaNV
            WHEN 'NV001' THEN '2025-04-14 17:00:00'
            WHEN 'NV002' THEN '2025-04-14 16:30:00'
            WHEN 'NV003' THEN NULL -- Chưa ra ca
            WHEN 'NV006' THEN '2025-04-14 16:00:00'
            WHEN 'NV007' THEN '2025-04-14 17:00:00'
            WHEN 'NV008' THEN '2025-04-14 16:45:00'
            WHEN 'NV009' THEN NULL -- Chưa ra ca
            WHEN 'NV010' THEN '2025-04-14 17:30:00'
        END,
    tongGioLam = 
        CASE MaNV
            WHEN 'NV001' THEN 9  -- 8:00 -> 17:00
            WHEN 'NV002' THEN 8   -- 8:30 -> 16:30
            WHEN 'NV003' THEN NULL  -- Chưa ra ca
            WHEN 'NV006' THEN 8   -- 7:30 -> 16:00
            WHEN 'NV007' THEN 9  -- 8:00 -> 17:00
            WHEN 'NV008' THEN 8   -- 8:15 -> 16:45
            WHEN 'NV009' THEN NULL  -- Chưa ra ca
            WHEN 'NV010' THEN 8.75  -- 8:45 -> 17:30
        END
WHERE MaNV IN ('NV001', 'NV002', 'NV003', 'NV006', 'NV007', 'NV008', 'NV009', 'NV010');
UPDATE Ban
SET TrangThai = UPPER(TrangThai);
UPDATE Ban
SET TENBAN = 
    CASE MaBan
        WHEN 'B001' THEN N'BÀN 1'
        WHEN 'B002' THEN N'BÀN 2'
        WHEN 'B003' THEN N'BÀN 3'
        WHEN 'B004' THEN N'BÀN 4'
        WHEN 'B005' THEN N'BÀN 5'
        WHEN 'B006' THEN N'BÀN 6'
    END;
INSERT INTO Ban (MaBan, TrangThai, Tang, SoCho, GhiChu) VALUES
('B001', N'Còn Trống', 1, 4, N'Không có'),
('B002', N'Còn Trống', 1, 4, N'Không có'),
('B003', N'Còn Trống', 1, 4, N'Không có'),
('B004', N'Còn Trống', 1, 4, N'Không có'),
('B005', N'Còn Trống', 1, 4, N'Không có'),
('B006', N'Còn Trống', 1, 4, N'Không có');

SELECT * FROM TaiKhoan
select * from NhanVien