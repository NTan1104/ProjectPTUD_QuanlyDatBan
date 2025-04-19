package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.BaseDAO;
import entity.NhanVien;
import entity.TaiKhoan;

public class DAO_TaiKhoan extends BaseDAO {
    private Connection conn = null;
    private CallableStatement cs = null;
    private ResultSet rs = null;

    // Kiểm tra đăng nhập
    public boolean checkLogins(String username, String password) throws Exception {
        try {
            conn = getConnection();
            cs = conn.prepareCall("{CALL sp_CheckLogin(?, ?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            rs = cs.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

 // Tải tất cả tài khoản
    public List<TaiKhoan> loadTaiKhoanData() throws Exception {
        List<TaiKhoan> taiKhoanList = new ArrayList<>();
        try {
            conn = getConnection();
            cs = conn.prepareCall("{CALL sp_GetAllTaiKhoan}");
            rs = cs.executeQuery();
            while (rs.next()) {
                taiKhoanList.add(createTaiKhoanFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return taiKhoanList;
    }

    

    // Tạo đối tượng TaiKhoan từ ResultSet
    private TaiKhoan createTaiKhoanFromResultSet(ResultSet rs) throws SQLException {
        NhanVien nhanVien = new NhanVien(
            rs.getString("MaNV"),
            rs.getString("TenNV"),
            rs.getString("SDT"),
            rs.getString("GioiTinh"),
            rs.getString("ChucVu"),
            rs.getInt("Tuoi"),
            rs.getDouble("Hesoluong"),
            rs.getDouble("LuongNV"),
            rs.getString("LinkIMG"),
            rs.getString("Email")
        );

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenDangNhap(rs.getString("TenDangNhap"));
        taiKhoan.setMatKhau(rs.getString("MatKhau"));
        taiKhoan.setTrangThai(rs.getString("TrangThai"));
        if (rs.getTimestamp("GioVaoLam") != null) {
            taiKhoan.setGioVaoLam(rs.getTimestamp("GioVaoLam").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }
        if (rs.getTimestamp("GioNghi") != null) {
            taiKhoan.setGioNghi(rs.getTimestamp("GioNghi").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }
        taiKhoan.setSoGioLam(rs.getDouble("SoGioLam"));
        taiKhoan.setNhanVien(nhanVien);
        return taiKhoan;
    }

    // Đóng tài nguyên
    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (cs != null) cs.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}