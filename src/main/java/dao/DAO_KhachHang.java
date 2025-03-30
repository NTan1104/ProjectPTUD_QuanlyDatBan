package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.BaseDAO;
import entity.KhachHang;

public class DAO_KhachHang extends BaseDAO {
    public void insert(KhachHang khachHang) throws Exception {
        Connection conn = getConnection();
        String sql = "INSERT INTO KhachHang (MaKH, TenKH, SDT, GioiTinh) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, khachHang.getMaKH());
        ps.setString(2, khachHang.getTenKH());
        ps.setString(3, khachHang.getSdt());
        ps.setString(4, khachHang.getGioiTinh());
        ps.executeUpdate();
        conn.close();
    }

    public List<KhachHang> getAll() throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT * FROM KhachHang";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<KhachHang> list = new ArrayList<>();
        while (rs.next()) {
            KhachHang kh = new KhachHang();
            kh.setMaKH(rs.getString("MaKH"));
            kh.setTenKH(rs.getString("TenKH"));
            kh.setSdt(rs.getString("SDT"));
            kh.setGioiTinh(rs.getString("GioiTinh"));
            list.add(kh);
        }
        conn.close();
        return list;
    }

    public void delete(String maKH) throws Exception {
        Connection conn = getConnection();
        String sql = "DELETE FROM KhachHang WHERE MaKH = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maKH);
        ps.executeUpdate();
        conn.close();
    }
}
