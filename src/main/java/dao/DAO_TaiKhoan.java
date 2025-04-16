package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.BaseDAO;
import entity.TaiKhoan;

public class DAO_TaiKhoan extends BaseDAO{
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<TaiKhoan> list = new ArrayList<TaiKhoan>();
	public boolean checkLogins(String username, String password) {
	   String sql = "select * from TaiKhoan where MaNV = ?";
	   try {
		    conn = new BaseDAO().getConnection();
		    ps = conn.prepareStatement(sql);
		    ps.setString(1, username);
		    rs = ps.executeQuery();
		    while(rs.next()) {
		    	return password.equals(rs.getString(3).trim());
		    }
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		} finally {
		    try {
		        if (rs != null) rs.close();
		        if (ps != null) ps.close();
		        if (conn != null) conn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	   return false;
	}
}
