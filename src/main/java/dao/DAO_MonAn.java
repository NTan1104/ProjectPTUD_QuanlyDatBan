package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.BaseDAO;
import entity.MonAn;

public class DAO_MonAn extends BaseDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	CallableStatement cs = null;
	List<MonAn> list = new ArrayList<MonAn>();
	public MonAn getMonAnByMa(String maMonAn) throws SQLException {
	    String query = "SELECT * FROM MonAn WHERE MaMonAn = ?";
	    try (Connection conn = new BaseDAO().getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, maMonAn);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	        	return new MonAn(rs.getString(1),
	        			rs.getString(2),
	        			rs.getDouble(3),
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
    public List<MonAn> getAllMonAns() throws SQLException {
        list.clear();
        String sql = "SELECT * FROM MonAn ";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new MonAn(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return list;
    }
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
}
