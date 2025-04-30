package dao;

import connectDatabase.BaseDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DAO_HoaDon extends BaseDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	CallableStatement cs = null;

	public boolean themHD(String maNV, String maKH, String maPTTT, String maVAT, String maKM, LocalDateTime ngayLap,
			LocalDateTime ngayXuat, String trangThai) {
		boolean isSuccess = false;

		try {
			conn = new BaseDAO().getConnection();
			String sql = "{CALL ThemHoaDon(?, ?, ?, ?, ?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);

			// Thiết lập các tham số đầu vào cho thủ tục
			cs.setString(1, maNV);
			cs.setString(2, maKH);
			cs.setString(3, maPTTT);
			cs.setString(4, maVAT);
			cs.setString(5, maKM);
			cs.setTimestamp(6, Timestamp.valueOf(ngayLap)); 
			cs.setTimestamp(7, Timestamp.valueOf(ngayXuat));
			cs.setString(8, trangThai);

			// Thực thi thủ tục
			int rowsAffected = cs.executeUpdate();
			// Kiểm tra kết quả
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}
	public String getMaHDByMaKHAndStatus(String maKH, String trangThai)  {
	    String maHD = null;
	    String query = "SELECT MaHD FROM HoaDon WHERE MaKH = ? AND TrangThai = ?";
	    try (Connection conn = new BaseDAO().getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, maKH);
	        ps.setString(2, trangThai);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            maHD = rs.getString("MaHD");
	        }
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return maHD;
	}
	public boolean capNhatTrangThaiHoaDon(String maHD, String trangThai) throws SQLException {
	    String query = "UPDATE HoaDon SET TrangThai = ? WHERE MaHD = ?";
	    try (Connection conn = new BaseDAO().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, trangThai);
	        ps.setString(2, maHD);
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}