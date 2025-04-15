package dao;

import connectDatabase.BaseDAO;
import entity.MonAn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
            cstmt.setFloat(3, monAn.getGia());
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
            cstmt.setFloat(3, monAn.getGia());
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

    // Lấy món ăn theo mã (nếu cần)
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
}