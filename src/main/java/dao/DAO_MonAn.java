package dao;

import connectDatabase.BaseDAO;
import entity.MonAn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_MonAn extends BaseDAO {

    // Lấy tất cả món ăn
    public List<MonAn> getAllMonAn() throws Exception {
        List<MonAn> list = new ArrayList<>();
        String sql = "{CALL GetAllMonAn}";
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {
            while (rs.next()) {
                MonAn monAn = new MonAn(
                    rs.getString("MaMonAn"),
                    rs.getString("TenMonAn"),
                    rs.getFloat("Gia"),
                    rs.getString("GhiChu"),
                    rs.getString("LoaiMonAn"),
                    rs.getString("DuongDanHinhAnh")
                );
                list.add(monAn);
            }
        }
        return list;
    }

    // Thêm món ăn
    public void addMonAn(MonAn monAn) throws Exception {
        String sql = "{CALL AddMonAn(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, monAn.getMaMonAn());
            cstmt.setString(2, monAn.getTenMonAn());
            cstmt.setDouble(3, monAn.getGia());
            cstmt.setString(4, monAn.getGhiChu());
            cstmt.setString(5, monAn.getLoaiMonAn());
            cstmt.setString(6, monAn.getDuongDanHinhAnh());
            cstmt.executeUpdate();
        }
    }

    // Sửa món ăn
    public void updateMonAn(MonAn monAn) throws Exception {
        String sql = "{CALL UpdateMonAn(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, monAn.getMaMonAn());
            cstmt.setString(2, monAn.getTenMonAn());
            cstmt.setDouble(3, monAn.getGia());
            cstmt.setString(4, monAn.getGhiChu());
            cstmt.setString(5, monAn.getLoaiMonAn());
            cstmt.setString(6, monAn.getDuongDanHinhAnh());
            cstmt.executeUpdate();
        }
    }

    // Xóa món ăn
    public void deleteMonAn(String maMonAn) throws Exception {
        String sql = "{CALL DeleteMonAn(?)}";
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, maMonAn);
            cstmt.executeUpdate();
        }
    }

    // Tìm kiếm món ăn theo mã hoặc tên
    public List<MonAn> searchMonAn(String searchValue, String searchBy) throws Exception {
        List<MonAn> list = new ArrayList<>();
        String sql = "{CALL SearchMonAn(?, ?)}";
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, searchValue);
            cstmt.setString(2, searchBy.equals("Mã món ăn") ? "MaMonAn" : "TenMonAn");
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    MonAn monAn = new MonAn(
                        rs.getString("MaMonAn"),
                        rs.getString("TenMonAn"),
                        rs.getFloat("Gia"),
                        rs.getString("GhiChu"),
                        rs.getString("LoaiMonAn"),
                        rs.getString("DuongDanHinhAnh")
                    );
                    list.add(monAn);
                }
            }
        }
        return list;
    }

    // Lọc món ăn theo loại
    public List<MonAn> filterMonAnByLoai(String loaiMonAn) throws Exception {
        List<MonAn> list = new ArrayList<>();
        String sql = "{CALL FilterMonAnByLoai(?)}";
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, loaiMonAn);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    MonAn monAn = new MonAn(
                        rs.getString("MaMonAn"),
                        rs.getString("TenMonAn"),
                        rs.getFloat("Gia"),
                        rs.getString("GhiChu"),
                        rs.getString("LoaiMonAn"),
                        rs.getString("DuongDanHinhAnh")
                    );
                    list.add(monAn);
                }
            }
        }
        return list;
    }

    // Lấy món ăn theo mã
    public MonAn getMonAnByMa(String maMonAn) throws Exception {
        String sql = "{CALL GetMonAnByMa(?)}";
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, maMonAn);
            try (ResultSet rs = cstmt.executeQuery()) {
                if (rs.next()) {
                    return new MonAn(
                        rs.getString("MaMonAn"),
                        rs.getString("TenMonAn"),
                        rs.getFloat("Gia"),
                        rs.getString("GhiChu"),
                        rs.getString("LoaiMonAn"),
                        rs.getString("DuongDanHinhAnh")
                    );
                }
            }
        }
        return null;
    }

    // Kiểm tra món ăn tồn tại hay không
    public boolean checkMaMonAnExists(String maMonAn) throws Exception {
        String sql = "SELECT COUNT(*) FROM MonAn WHERE MaMonAn = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maMonAn);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Lấy tất cả loại món ăn
    public List<String> getAllLoaiMonAn() throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "{CALL GetAllLoaiMonAn}";
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {
            while (rs.next()) {
                String loaiMonAn = rs.getString("LoaiMonAn");
                if (loaiMonAn != null && !loaiMonAn.trim().isEmpty()) {
                    list.add(loaiMonAn);
                }
            }
        }
        return list;
    }

    // Lấy các loại món ăn khác nhau từ cơ sở dữ liệu
    public List<String> getLoaiMonAn() throws Exception {
        List<String> loaiMonAnList = new ArrayList<>();
        String sql = "SELECT DISTINCT LoaiMonAn FROM MonAn WHERE LoaiMonAn IS NOT NULL";
        try (Connection conn = new BaseDAO().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                loaiMonAnList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loaiMonAnList;
    }
    
    // Lấy món ăn theo tên
    public MonAn getMonAnByTen(String tenMonAn) throws SQLException {
        String query = "SELECT * FROM MonAn WHERE TenMonAn = ?";
        try (Connection conn = new BaseDAO().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, tenMonAn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new MonAn(rs.getString(1), rs.getString(2), 
                		rs.getFloat(3), 
                		rs.getString(4), 
                		rs.getString(5),
                		rs.getString(6));
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
}
