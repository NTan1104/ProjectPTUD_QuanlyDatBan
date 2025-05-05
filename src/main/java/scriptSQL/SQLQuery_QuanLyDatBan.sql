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
    Ngayketthuc DATETIME,
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
    MaPDB VARCHAR(10) NOT NULL,
    TimeNhanBan DATETIME,
    TimeTraBan DATETIME,
    SoNguoi INT,
    FOREIGN KEY (MaPDB) REFERENCES PhieuDatBan(MaPDB)
);
-- Tạo các bảng có khóa ngoại sau
CREATE TABLE TaiKhoan_Fake (
    MaNV VARCHAR(10) NOT NULL,
    TenDangNhap VARCHAR(10) PRIMARY KEY,
    MatKhau VARCHAR(20) NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL,
	CONSTRAINT TK_TrangThai CHECK (TrangThai IN (N'Online', N'Offline')),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

CREATE TABLE TaiKhoan (
    MaNV VARCHAR(10) NOT NULL,
    TenDangNhap VARCHAR(10) PRIMARY KEY,
    MatKhau VARCHAR(20) NOT NULL,
    GioVaoLam DATETIME NOT NULL,
    GioNghi DATETIME NOT NULL,
    SoGioLam FLOAT NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL,
    CONSTRAINT TK_TrangThai CHECK (TrangThai IN (N'Online', N'Offline')),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

CREATE TABLE PhieuDatBan (
    MaPDB VARCHAR(10) PRIMARY KEY,
    MaKhachHang VARCHAR(10) NOT NULL,
    MaBan VARCHAR(10) NOT NULL,
    MaNV VARCHAR(10) NOT NULL,
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKH),
    FOREIGN KEY (MaBan) REFERENCES Ban(MaBan),
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
	TrangThai NVARCHAR(50) NOT NULL,
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
    FOREIGN KEY (MaPTTT) REFERENCES PhuongThucThanhToan(MaPTTT),
    FOREIGN KEY (MaVAT) REFERENCES ThueVAT(MaVAT),
    FOREIGN KEY (MaKM) REFERENCES KhuyenMai(MaKM)
);

CREATE TABLE ChiTietHoaDon (
    MaHD VARCHAR(10) NOT NULL,
    MaMonAn VARCHAR(10) NOT NULL,
    SoLuong INT NOT NULL,
    DonGia FLOAT NOT NULL,
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
    FOREIGN KEY (MaMonAn) REFERENCES MonAn(MaMonAn)
);

-- Stored Procedure SQL
CREATE PROCEDURE ThemHoaDon
    @MaNV VARCHAR(10),
    @MaKH VARCHAR(10),
    @MaPTTT VARCHAR(10),
    @MaVAT VARCHAR(10),
    @MaKM VARCHAR(10),
    @NgayLap DATETIME,
    @NgayXuat DATETIME,
    @TrangThai NVARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @NewMaHD NVARCHAR(10);
    DECLARE @MaxMaHD NVARCHAR(10);
    DECLARE @NextNumber INT;

    -- Lấy mã hóa đơn lớn nhất hiện tại
    SELECT @MaxMaHD = MAX(MaHD) FROM HoaDon;

    IF @MaxMaHD IS NULL
    BEGIN
        -- Nếu chưa có hóa đơn nào, bắt đầu với HD000001
        SET @NewMaHD = 'HD000001';
    END
    ELSE
    BEGIN
        -- Trích xuất phần số, tăng thêm 1 và tạo mã hóa đơn mới
        SET @NextNumber = CAST(SUBSTRING(@MaxMaHD, 3, LEN(@MaxMaHD) - 2) AS INT) + 1;
        SET @NewMaHD = 'HD' + RIGHT('000000' + CAST(@NextNumber AS NVARCHAR(6)), 6);
    END

    -- Thêm dữ liệu mới vào bảng HoaDon
    INSERT INTO HoaDon (
        MaHD, MaNV, MaKH, MaPTTT, MaVAT, MaKM, NgayLap, NgayXuat, TrangThai
    )
    VALUES (
        @NewMaHD, @MaNV, @MaKH, @MaPTTT, @MaVAT, @MaKM, @NgayLap, @NgayXuat, @TrangThai
    );
END;


--KHuyến Mãi
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

--Nhân Viên

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

---HÓA ĐƠN TÍN

CREATE PROCEDURE sp_GetAllHoaDon
AS
BEGIN
    SELECT 
        hd.MaHD, 
        hd.MaNV, 
        hd.MaKH, 
        hd.MaPTTT, 
        hd.MaVAT, 
        hd.MaKM, 
        hd.NgayLap, 
        hd.NgayXuat, 
        hd.TrangThai,
        b.MaBan,
        kh.SDT,
        COALESCE(SUM(ct.SoLuong * ct.DonGia), 0) AS TongTien
    FROM HoaDon hd
    LEFT JOIN KhachHang kh ON hd.MaKH = kh.MaKH
    LEFT JOIN ChiTietHoaDon ct ON hd.MaHD = ct.MaHD
    LEFT JOIN ChiTietBan cb ON hd.MaHD = cb.MaHD
    LEFT JOIN Ban b ON cb.MaBan = b.MaBan
    GROUP BY 
        hd.MaHD, hd.MaNV, hd.MaKH, hd.MaPTTT, hd.MaVAT, hd.MaKM, 
        hd.NgayLap, hd.NgayXuat, hd.TrangThai, b.MaBan, kh.SDT
    ORDER BY 
        hd.NgayLap DESC;
END;

CREATE PROCEDURE sp_SearchHoaDon
    @MaHD VARCHAR(10) = NULL,
    @MaKH VARCHAR(10) = NULL,
    @MaBan VARCHAR(10) = NULL,
    @SDT VARCHAR(15) = NULL,
    @TrangThai NVARCHAR(50) = NULL,
    @StartDate DATETIME = NULL,
    @EndDate DATETIME = NULL,
    @SortByGia VARCHAR(10) = NULL
AS
BEGIN
    SELECT 
        hd.MaHD, 
        hd.MaNV, 
        hd.MaKH, 
        hd.MaPTTT, 
        hd.MaVAT, 
        hd.MaKM, 
        hd.NgayLap, 
        hd.NgayXuat, 
        hd.TrangThai,
        b.MaBan,
        kh.SDT,
        SUM(ct.SoLuong * ct.DonGia) AS TongTien
    FROM HoaDon hd
    LEFT JOIN KhachHang kh ON hd.MaKH = kh.MaKH
    LEFT JOIN ChiTietHoaDon ct ON hd.MaHD = ct.MaHD
    LEFT JOIN ChiTietBan cb ON hd.MaHD = cb.MaHD
    LEFT JOIN Ban b ON cb.MaBan = b.MaBan
    WHERE 
        (@MaHD IS NULL OR hd.MaHD LIKE '%' + @MaHD + '%')
        AND (@MaKH IS NULL OR hd.MaKH LIKE '%' + @MaKH + '%')
        AND (@MaBan IS NULL OR b.MaBan LIKE '%' + @MaBan + '%')
        AND (@SDT IS NULL OR kh.SDT LIKE '%' + @SDT + '%')
        AND (@TrangThai IS NULL OR @TrangThai = 'Tất cả' OR hd.TrangThai = @TrangThai)
        AND (@StartDate IS NULL OR hd.NgayLap >= @StartDate)
        AND (@EndDate IS NULL OR hd.NgayLap <= @EndDate)
    GROUP BY 
        hd.MaHD, hd.MaNV, hd.MaKH, hd.MaPTTT, hd.MaVAT, hd.MaKM, 
        hd.NgayLap, hd.NgayXuat, hd.TrangThai, b.MaBan, kh.SDT
    ORDER BY 
        CASE 
            WHEN @SortByGia = 'DESC' THEN SUM(ct.SoLuong * ct.DonGia)
        END DESC,
        CASE 
            WHEN @SortByGia = 'ASC' THEN SUM(ct.SoLuong * ct.DonGia)
        END ASC,
        hd.NgayLap DESC; -- Default sort by NgayLap if SortByGia is not specified
END;

---Mon An
CREATE PROCEDURE GetAllMonAn
AS
BEGIN
    SELECT MaMonAn, TenMonAn, Gia, GhiChu, LoaiMonAn, DuongDanHinhAnh
    FROM MonAn;
END;

CREATE PROCEDURE GetMonAnByMa
    @MaMonAn VARCHAR(10)
AS
BEGIN
    SELECT MaMonAn, TenMonAn, Gia, GhiChu, LoaiMonAn, DuongDanHinhAnh
    FROM MonAn
    WHERE MaMonAn = @MaMonAn;
END;

CREATE PROCEDURE AddMonAn
    @MaMonAn VARCHAR(10),
    @TenMonAn NVARCHAR(50),
    @Gia FLOAT,
    @GhiChu NVARCHAR(MAX),
    @LoaiMonAn NVARCHAR(20),
    @DuongDanHinhAnh VARCHAR(255)
AS
BEGIN
    INSERT INTO MonAn (MaMonAn, TenMonAn, Gia, GhiChu, LoaiMonAn, DuongDanHinhAnh)
    VALUES (@MaMonAn, @TenMonAn, @Gia, @GhiChu, @LoaiMonAn, @DuongDanHinhAnh);
END;

CREATE PROCEDURE UpdateMonAn
    @MaMonAn VARCHAR(10),
    @TenMonAn NVARCHAR(50),
    @Gia FLOAT,
    @GhiChu NVARCHAR(MAX),
    @LoaiMonAn NVARCHAR(20),
    @DuongDanHinhAnh VARCHAR(255)
AS
BEGIN
    UPDATE MonAn
    SET TenMonAn = @TenMonAn,
        Gia = @Gia,
        GhiChu = @GhiChu,
        LoaiMonAn = @LoaiMonAn,
        DuongDanHinhAnh = @DuongDanHinhAnh
    WHERE MaMonAn = @MaMonAn;
END;

CREATE PROCEDURE DeleteMonAn
    @MaMonAn VARCHAR(10)
AS
BEGIN
    DELETE FROM MonAn
    WHERE MaMonAn = @MaMonAn;
END;

CREATE PROCEDURE SearchMonAn
    @SearchValue NVARCHAR(50),
    @SearchBy NVARCHAR(20) -- 'MaMonAn' ho?c 'TenMonAn'
AS
BEGIN
    IF @SearchBy = 'MaMonAn'
    BEGIN
        SELECT MaMonAn, TenMonAn, Gia, GhiChu, LoaiMonAn, DuongDanHinhAnh
        FROM MonAn
        WHERE MaMonAn LIKE '%' + @SearchValue + '%';
    END
    ELSE IF @SearchBy = 'TenMonAn'
    BEGIN
        SELECT MaMonAn, TenMonAn, Gia, GhiChu, LoaiMonAn, DuongDanHinhAnh
        FROM MonAn
        WHERE TenMonAn LIKE '%' + @SearchValue + '%';
    END
    ELSE
    BEGIN
        SELECT MaMonAn, TenMonAn, Gia, GhiChu, LoaiMonAn, DuongDanHinhAnh
        FROM MonAn;
    END
END;

CREATE PROCEDURE FilterMonAnByLoai
    @LoaiMonAn NVARCHAR(20)
AS
BEGIN
    IF @LoaiMonAn = 'Tất cả'
    BEGIN
        SELECT MaMonAn, TenMonAn, Gia, GhiChu, LoaiMonAn, DuongDanHinhAnh
        FROM MonAn;
    END
    ELSE
    BEGIN
        SELECT MaMonAn, TenMonAn, Gia, GhiChu, LoaiMonAn, DuongDanHinhAnh
        FROM MonAn
        WHERE LoaiMonAn = @LoaiMonAn;
    END
END;


--Khach Hang
CREATE PROCEDURE sp_GetAllKhachHang
AS
BEGIN
    SELECT * FROM KhachHang;
END;
GO

-- 2. Stored Procedure để thêm khách hàng
CREATE PROCEDURE sp_AddKhachHang
    @MaKH VARCHAR(10),
    @TenKH NVARCHAR(50),
    @SDT VARCHAR(11),
    @GioiTinh NVARCHAR(10)
AS
BEGIN
    INSERT INTO KhachHang (MaKH, TenKH, SDT, GioiTinh)
    VALUES (@MaKH, @TenKH, @SDT, @GioiTinh);
END;
GO

-- 3. Stored Procedure để cập nhật khách hàng
CREATE PROCEDURE sp_UpdateKhachHang
    @MaKH VARCHAR(10),
    @TenKH NVARCHAR(50),
    @SDT VARCHAR(11),
    @GioiTinh NVARCHAR(10)
AS
BEGIN
    UPDATE KhachHang
    SET TenKH = @TenKH, SDT = @SDT, GioiTinh = @GioiTinh
    WHERE MaKH = @MaKH;
END;
GO

-- 4. Stored Procedure để xóa khách hàng
CREATE PROCEDURE sp_DeleteKhachHang
    @MaKH VARCHAR(10)
AS
BEGIN
    DELETE FROM KhachHang
    WHERE MaKH = @MaKH;
END;
GO

-- 5. Stored Procedure để lấy khách hàng theo mã
CREATE PROCEDURE sp_GetKhachHangByMa
    @MaKH VARCHAR(10)
AS
BEGIN
    SELECT * FROM KhachHang
    WHERE MaKH = @MaKH;
END;
GO

CREATE PROCEDURE sp_SearchKhachHang
    @Query NVARCHAR(50),
    @Criteria NVARCHAR(20)
AS
BEGIN
    IF @Criteria = 'Mã khách hàng'
        SELECT * FROM KhachHang WHERE MaKH = @Query;
    ELSE IF @Criteria = 'Tên khách hàng'
        SELECT * FROM KhachHang WHERE TenKH LIKE '%' + @Query + '%';
    ELSE IF @Criteria = 'Số điện thoại'
        SELECT * FROM KhachHang WHERE SDT LIKE '%' + @Query + '%';
END;
GO


CREATE PROCEDURE sp_GetAllTaiKhoan
AS
BEGIN
    SELECT tk.MaNV, tk.TenDangNhap, tk.MatKhau, tk.TrangThai, tk.GioVaoLam, tk.GioNghi, tk.SoGioLam,
           nv.TenNV, nv.SDT, nv.GioiTinh, nv.ChucVu, nv.Tuoi, nv.Hesoluong, nv.LuongNV, nv.LinkIMG, nv.Email
    FROM TaiKhoan tk
    JOIN NhanVien nv ON tk.MaNV = nv.MaNV;
END;


SELECT ROUTINE_NAME, ROUTINE_TYPE, CREATED, LAST_ALTERED
FROM information_schema.ROUTINES
DROP PROCEDURE IF EXISTS themKH;
SELECT * FROM NhanVien
select * from TaiKhoan
select * from Ban	
select * from ThueVAT
select * from KhuyenMai
select * from KhachHang
select * from PhuongThucThanhToan
select * from ThueVAT
select * from KhuyenMai
select * from MonAn
select * from HoaDon
select * from ChiTietHoaDon
select * from PhieuDatBan
select * from ChiTietPhieuDatBan
SELECT * FROM Ban WHERE TenBan = 'BÀN 1'
SELECT * FROM PhieuDatBan
SELECT MaPDB FROM PhieuDatBan ORDER BY MaPDB DESC

SELECT TOP 1 P.MaPDB 
FROM PhieuDatBan P
JOIN ChiTietPhieuDatBan C ON P.MaPDB = C.MaPDB
WHERE MaBan = 'B001'
ORDER BY C.TimeNhanBan DESC;

SELECT * FROM PhieuDatBan where MaBan = 'B001'
SELECT TOP 1 * FROM PhieuDatBan WHERE MaBan = 'B001' ORDER BY MaPDB DESC
SELECT MaMonAn, SoLuong, DonGia FROM ChiTietHoaDon WHERE MaHD = 'HD000005'
ALTER TABLE ChiTietPhieuDatBan
ADD CONSTRAINT FK_PhieuDatBan
FOREIGN KEY (MaPDB) REFERENCES PhieuDatBan(MaPDB)
ON DELETE CASCADE;

SELECT *
FROM sys.database_permissions
WHERE class_desc = 'OBJECT_OR_COLUMN' AND major_id = OBJECT_ID('HoaDon');

DELETE FROM ChiTietHoaDon WHERE MaHD = 'HD000001';
DELETE FROM HoaDon WHERE MaHD = 'HD000001';

delete from KhuyenMai where MaKM = 'KM01'

SELECT * FROM MonAn WHERE MaMonAn = 'MA001'
SELECT TOP 1 * FROM PhieuDatBan WHERE MaBan = 'B001' ORDER BY MaPDB DESC
SELECT MaHD FROM HoaDon WHERE MaKH = 'KH003' AND TrangThai = N'Chưa thanh toán'
SELECT * FROM ChiTietHoaDon WHERE MaHD = 'HD000001'
SELECT MaKM FROM KhuyenMai WHERE MaKM = 'KM01'


--CTHD
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


--



create PROCEDURE sp_XoaPhieuDatTheoBan
    @MaBan NVARCHAR(10)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @LatestMaPDB NVARCHAR(10);

    -- Lấy mã phiếu đặt mới nhất theo MaPDB
    SELECT TOP 1 @LatestMaPDB = MaPDB
    FROM PhieuDatBan
    WHERE MaBan = @MaBan
    ORDER BY MaPDB DESC;

    -- Nếu tìm thấy mã phiếu đặt mới nhất, thì tiến hành xóa
    IF @LatestMaPDB IS NOT NULL
    BEGIN
        DELETE FROM ChiTietPhieuDatBan WHERE MaPDB = @LatestMaPDB;
        DELETE FROM PhieuDatBan WHERE MaPDB = @LatestMaPDB;
    END
END;

