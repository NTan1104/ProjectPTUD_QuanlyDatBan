package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.BaseDAO;
import entity.TaiKhoan;

public class DAO_TaiKhoan extends BaseDAO{
	private DAO_NhanVien nhanVienDAO = new DAO_NhanVien();
	
	// Lấy tất cả tài khoản
    public List<TaiKhoan> getAllTaiKhoan() throws Exception {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "{CALL GetAllTaiKhoan}";
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {
            while (rs.next()) {
                TaiKhoan taiKhoan = mapResultSetToTaiKhoan(rs);
                list.add(taiKhoan);
            }
        }
        return list;
    }
    
 // Hàm ánh xạ ResultSet sang TaiKhoan
    private TaiKhoan mapResultSetToTaiKhoan(ResultSet rs) throws Exception {
        String maNV = rs.getString("MaNV");
        NhanVien nhanVien = nhanVienDAO.(maNV); // Load NhanVien nếu cần
        return new TaiKhoan(
            maNV,
            rs.getString("TenDangNhap"),
            rs.getString("MatKhau"),
            rs.getTimestamp("GioVaoLam").toLocalDateTime(),
            rs.getTimestamp("GioNghi").toLocalDateTime(),
            rs.getFloat("SoGioLam"),
            rs.getString("TrangThai"),
            nhanVien
        );
    }
}
