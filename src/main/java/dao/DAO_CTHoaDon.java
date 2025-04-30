package dao;

import connectDatabase.BaseDAO;
import entity.CTHoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_CTHoaDon {
    public boolean themCTHD(String maHD, String maMonAn, int soLuong, double gia) throws SQLException {
        String query = "INSERT INTO ChiTietHoaDon (MaHD, MaMonAn, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
        try (Connection conn = new BaseDAO().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, maHD);
            ps.setString(2, maMonAn);
            ps.setInt(3, soLuong);
            ps.setDouble(4, gia);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }
    public boolean xoaCTHD(String maHD, String maMonAn) throws SQLException {
        String query = "DELETE FROM ChiTietHoaDon WHERE MaHD = ? AND MaMonAn = ?";
        try (Connection conn = new BaseDAO().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, maHD);
            ps.setString(2, maMonAn);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }
    public List<CTHoaDon> getCTHDByMaHD(String maHD) {
        List<CTHoaDon> danhSachCTHD = new ArrayList<>();
        String query = "SELECT \r\n"
        		+ "    MaHD,\r\n"
        		+ "    MaMonAn, \r\n"
        		+ "    SUM(SoLuong) AS TongSoLuong,\r\n"
        		+ "    SUM(DonGia) AS TongDonGia\r\n"
        		+ "FROM \r\n"
        		+ "    ChiTietHoaDon \r\n"
        		+ "WHERE \r\n"
        		+ "    MaHD = ? \r\n"
        		+ "GROUP BY \r\n"
        		+ "    MaHD, MaMonAn;";
        try (Connection conn = new BaseDAO().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTHoaDon cthd = new CTHoaDon();
                cthd.setMaHoaDon(rs.getString(1));
                cthd.setMaMonAn(rs.getString(2));
                cthd.setSoLuong(rs.getInt(3));
                cthd.setDonGia(rs.getInt(4));
                danhSachCTHD.add(cthd);
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return danhSachCTHD;
    }
}