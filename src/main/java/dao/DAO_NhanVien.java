package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.BaseDAO;
import entity.NhanVien;

public class DAO_NhanVien extends BaseDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<NhanVien> list = new ArrayList<NhanVien>();
	public NhanVien getNVbyID(String idNV) {
		String sql = "select * from NhanVien where MaNV = ?";
		try {
			conn = new BaseDAO().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, idNV);
			rs = ps.executeQuery();
			while( rs.next()) {
				return new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getDouble(7),rs.getDouble(8), rs.getString(9), rs.getString(10));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
