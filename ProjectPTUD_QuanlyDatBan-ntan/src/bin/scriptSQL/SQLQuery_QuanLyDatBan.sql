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
ALTER TABLE NhanVien
ADD Email VARCHAR(50);


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
ALTER TABLE TaiKhoan
ADD 
    lockIn DATETIME NULL,
    lockOut DATETIME NULL,
    tongGioLam FLOAT NULL;

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
('KM001', N'Giảm giá cho hóa đơn trên 500,000', '2025-12-31 23:59:59', '2025-04-01 00:00:00', 10.00),
('KM002', N'Giảm giá cho khách hàng 1,000,000', '2025-06-30 23:59:59', '2025-04-01 00:00:00', 15.00)
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



--NHÂN VIÊN
go

CREATE PROCEDURE GetAllNhanVien

AS
BEGIN	

    SELECT MaNV, TenNV, SDT, GioiTinh, ChucVu, Tuoi, Hesoluong , LuongNV , LinkIMG , Email FROM NhanVien;
END;
CREATE PROCEDURE InsertNhanVien (
    @MaNV NVARCHAR(10),
    @TenNV NVARCHAR(50),
    @SDT NVARCHAR(15),
    @GioiTinh NVARCHAR(10),
    @ChucVu NVARCHAR(50),
    @Tuoi INT,	
    @Hesoluong FLOAT,
    @LuongNV FLOAT,
    @LinkIMG NVARCHAR(255),
    @Email NVARCHAR(50)
)
AS
BEGIN
    INSERT INTO NhanVien(MaNV, TenNV, SDT, GioiTinh, ChucVu, Tuoi, Hesoluong, LuongNV, LinkIMG, Email)
    VALUES(@MaNV, @TenNV, @SDT, @GioiTinh, @ChucVu, @Tuoi, @Hesoluong, @LuongNV, @LinkIMG, @Email);
END;
GO

CREATE PROCEDURE UpdateNhanVien (
    @TenNV NVARCHAR(50),
    @SDT NVARCHAR(15),
    @GioiTinh NVARCHAR(10),
    @ChucVu NVARCHAR(50),
    @Tuoi INT,
    @Hesoluong FLOAT,
    @LuongNV FLOAT,
    @LinkIMG NVARCHAR(255),
    @Email NVARCHAR(50),
    @MaNV NVARCHAR(10)
)
AS
BEGIN
    UPDATE NhanVien
    SET TenNV = @TenNV,
        SDT = @SDT,
        GioiTinh = @GioiTinh,
        ChucVu = @ChucVu,
        Tuoi = @Tuoi,
        Hesoluong = @Hesoluong,
        LuongNV = @LuongNV,
        LinkIMG = @LinkIMG,
        Email = @Email
    WHERE MaNV = @MaNV;
END;
GO

GO

-- Stored procedure để xóa một nhân viên theo mã NV
CREATE PROCEDURE DeleteNhanVien (
    @MaNV NVARCHAR(10)
)
AS
BEGIN
		DELETE FROM TaiKhoan WHERE MaNV = @MaNV;
    DELETE FROM NhanVien WHERE MaNV = @MaNV;


END;
GO


CREATE PROCEDURE SearchNhanVien
    @Keyword NVARCHAR(50)
AS
BEGIN
    SELECT MaNV, TenNV, SDT, GioiTinh, ChucVu, Tuoi, Hesoluong, LuongNV, LinkIMG, Email
    FROM NhanVien
    WHERE MaNV LIKE  @Keyword 
       OR TenNV LIKE  @Keyword 
       OR SDT LIKE  @Keyword 
       OR Email LIKE  @Keyword ;
END;
GO



--KHUYẾN MÃI
CREATE PROCEDURE sp_AddPromotion
    @PromotionId VARCHAR(10),
    @Condition NVARCHAR(255),
    @StartDate DATETIME,
    @EndDate DATETIME,
    @DiscountPercentage Decimal(5,2)
AS
BEGIN
    INSERT INTO KhuyenMai (MaKM, DieuKien, NgayBatDau, NgayKetThuc, PhanTramGiamGia)
    VALUES (@PromotionId, @Condition, @StartDate, @EndDate, @DiscountPercentage);
END;

-- 2. Update promotion
CREATE PROCEDURE sp_UpdatePromotion
    @PromotionId VARCHAR(10),
    @Condition NVARCHAR(255),
    @StartDate DATETIME,
    @EndDate DATETIME,
    @DiscountPercentage Decimal(5,2)
AS
BEGIN
    UPDATE KhuyenMai
    SET DieuKien = @Condition,
        NgayBatDau = @StartDate,
        NgayKetThuc = @EndDate,
        PhanTramGiamGia = @DiscountPercentage
    WHERE MaKM = @PromotionId;
END;

-- 3. Delete promotion
CREATE PROCEDURE sp_DeletePromotion
    @PromotionId VARCHAR(10)
AS
BEGIN
    DELETE FROM KhuyenMai
    WHERE MaKM = @PromotionId;
END;

-- 4. Get list of promotions
CREATE PROCEDURE sp_GetAllPromotions
AS
BEGIN
    SELECT * FROM KhuyenMai;
END;

-- 5. Get promotion by ID

CREATE PROCEDURE sp_GetPromotionById
    @PromotionId VARCHAR(10)
AS
BEGIN
    SELECT MaKM ,DieuKien ,Ngayketthuc ,Ngaybatdau ,PhanTramGiamGia
	FROM KhuyenMai
    WHERE MaKM LIKE  @PromotionId;
END;





--HÓA ĐƠN
CREATE PROCEDURE sp_GetAllHoaDon
AS
BEGIN
    SET NOCOUNT ON;
    SELECT 
        hd.MaHD,
        nv.MaNV,
        kh.MaKH,
        pttt.MaPTTT,
        tvat.MaVAT,
        km.MaKM,
        hd.NgayLap,
        hd.NgayXuat,
        hd.TrangThai,
        b.MaBan
    FROM HoaDon hd
    LEFT JOIN NhanVien nv ON hd.MaNV = nv.MaNV
    LEFT JOIN KhachHang kh ON hd.MaKH = kh.MaKH
    LEFT JOIN PhuongThucThanhToan pttt ON hd.MaPTTT = pttt.MaPTTT
    LEFT JOIN ThueVAT tvat ON hd.MaVAT = tvat.MaVAT
    LEFT JOIN KhuyenMai km ON hd.MaKM = km.MaKM
    LEFT JOIN PhieuDatBan pdb ON hd.MaKH = pdb.MaKhachHang
    LEFT JOIN Ban b ON pdb.MaBan = b.MaBan
    GROUP BY 
        hd.MaHD, nv.MaNV, kh.MaKH, pttt.MaPTTT, tvat.MaVAT, km.MaKM,
        hd.NgayLap, hd.NgayXuat, hd.TrangThai, b.MaBan;
END


CREATE PROCEDURE sp_SearchHoaDon
    @MaHD VARCHAR(10) = NULL,
    @MaKH VARCHAR(10) = NULL,
    @MaBan VARCHAR(10) = NULL,
    @SDT VARCHAR(15) = NULL, -- Thêm tham số SDT
    @TrangThai NVARCHAR(50) = NULL,
    @StartDate DATETIME = NULL,
    @EndDate DATETIME = NULL,
    @SortByGia VARCHAR(10) = NULL
AS
BEGIN
    SET NOCOUNT ON;
    SELECT 
        hd.MaHD,
        nv.MaNV,
        kh.MaKH,
        pttt.MaPTTT,
        tvat.MaVAT,
        km.MaKM,
        hd.NgayLap,
        hd.NgayXuat,
        hd.TrangThai,
        b.MaBan,
        SUM(cthd.SoLuong * cthd.DonGia) AS TongTien
    FROM HoaDon hd
    LEFT JOIN NhanVien nv ON hd.MaNV = nv.MaNV
    LEFT JOIN KhachHang kh ON hd.MaKH = kh.MaKH
    LEFT JOIN PhuongThucThanhToan pttt ON hd.MaPTTT = pttt.MaPTTT
    LEFT JOIN ThueVAT tvat ON hd.MaVAT = tvat.MaVAT
    LEFT JOIN KhuyenMai km ON hd.MaKM = km.MaKM
    LEFT JOIN PhieuDatBan pdb ON hd.MaKH = pdb.MaKhachHang
    LEFT JOIN Ban b ON pdb.MaBan = b.MaBan
    LEFT JOIN ChiTietHoaDon cthd ON hd.MaHD = cthd.MaHD
    WHERE 
        (@MaHD IS NULL OR hd.MaHD LIKE  @MaHD )
        AND (@MaKH IS NULL OR kh.MaKH LIKE  @MaKH )
        AND (@MaBan IS NULL OR b.MaBan LIKE  @MaBan )
        AND (@SDT IS NULL OR kh.SDT LIKE  @SDT ) -- Thêm điều kiện tìm kiếm theo SDT
        AND (@TrangThai IS NULL OR hd.TrangThai = @TrangThai OR @TrangThai = N'Tất cả')
        AND (@StartDate IS NULL OR hd.NgayLap >= @StartDate)
        AND (@EndDate IS NULL OR hd.NgayLap <= @EndDate)
    GROUP BY 
        hd.MaHD, nv.MaNV, kh.MaKH, pttt.MaPTTT, tvat.MaVAT, km.MaKM,
        hd.NgayLap, hd.NgayXuat, hd.TrangThai, b.MaBan
    ORDER BY 
        CASE WHEN @SortByGia = 'DESC' THEN SUM(cthd.SoLuong * cthd.DonGia) END DESC,
        CASE WHEN @SortByGia = 'ASC' THEN SUM(cthd.SoLuong * cthd.DonGia) END ASC,
        hd.NgayLap DESC;
END



---CT HÓA ĐƠN
CREATE PROCEDURE sp_GetChiTietHoaDonByMaHD
    @MaHD VARCHAR(10)
AS
BEGIN
    SET NOCOUNT ON;
    -- Lấy chi tiết hóa đơn
    SELECT 
        cthd.MaHD,
        cthd.MaMonAn,
        cthd.SoLuong,
        cthd.DonGia,
        (cthd.SoLuong * cthd.DonGia) AS ThanhTien,
        m.TenMonAn
    FROM ChiTietHoaDon cthd
    LEFT JOIN MonAn m ON cthd.MaMonAn = m.MaMonAn
    WHERE cthd.MaHD = @MaHD;

    -- Lấy tổng tiền (nếu cần)
    SELECT SUM(cthd.SoLuong * cthd.DonGia) AS TongTien
    FROM ChiTietHoaDon cthd
    WHERE cthd.MaHD = @MaHD;
END



select *from HoaDon
select *from ChiTietHoaDon
select *from PhieuDatBan
select *from NhanVien
select *from KhuyenMai
INSERT INTO PhuongThucThanhToan (MaPTTT, PhuongThuc) VALUES
('PT001', 'Tien Mat'),
('PT002', 'Chuyen Khoan');
INSERT INTO ThueVAT (MaVAT, PhanTramThue) VALUES
('VAT001', 10.0),
('VAT002', 5.0);

INSERT INTO MonAn (MaMonAn, TenMonAn, Gia, GhiChu, LoaiMonAn) VALUES
('MA001', 'Com Ga', 50000, 'Mon chinh', 'Com'),
('MA002', 'Nuoc Ngot', 15000, 'Do uong', 'Nuoc');
INSERT INTO ChiTietHoaDon (MaHD, MaMonAn, SoLuong, DonGia) VALUES
('HD001', 'MA001', 2, 50000),
('HD001', 'MA002', 1, 15000),
('HD002', 'MA001', 1, 50000);

INSERT INTO PhieuDatBan(MaPDB, MaKhachHang, MaBan, MaNV) VALUES
('PDB00002', 'KH001', 'B001',' NV001'),
('PDB00003', 'KH002', 'B003',' NV001');


select *from HoaDon
SELECT b.MaBan 
FROM HoaDon hd
LEFT JOIN PhieuDatBan pdb ON hd.MaKH = pdb.MaKhachHang
LEFT JOIN Ban b ON pdb.MaBan = b.MaBan
WHERE hd.MaHD = 'HD003'


SELECT 
    MaBan, 
    TenBan, 
    TrangThai, 
    LEN(TrangThai) AS LengthWithoutTrailingSpaces, 
    DATALENGTH(TrangThai) AS DataLength, 
    CONVERT(VARBINARY(MAX), TrangThai) AS HexValue
FROM Ban 
WHERE Tang = 1;