package dao;

import connectDatabase.BaseDAO;
import entity.CTHoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_CTHoaDon extends BaseDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public boolean themCTHD(String maHD, String maMonAn, int soLuong, double donGia) {
		String query = "INSERT INTO ChiTietHoaDon (MaHD, MaMonAn, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
		try (Connection conn = new BaseDAO().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, maHD);
			ps.setString(2, maMonAn);
			ps.setInt(3, soLuong);
			ps.setDouble(4, donGia);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<CTHoaDon> getCTHDByMaHD(String maHD) {
		List<CTHoaDon> result = new ArrayList<CTHoaDon>();
		String query = "SELECT * FROM ChiTietHoaDon WHERE MaHD = ?";
		try (Connection conn = new BaseDAO().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, maHD);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new CTHoaDon(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}