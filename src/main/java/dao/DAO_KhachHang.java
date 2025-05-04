package dao;

import java.sql.CallableStatement;
import java.sql.Connection;	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.BaseDAO;
import entity.KhachHang;
public class DAO_KhachHang extends BaseDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<KhachHang> list = new ArrayList<KhachHang>();
	public List<KhachHang> getAllKHs() {
		list.clear();
		String sql = "select * from KhachHang";
		try {
			conn = new BaseDAO().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next()) {
				list.add(new KhachHang(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	public KhachHang getKhachHangbyMa(String maKH) {
	    String sql = "select * from KhachHang where MaKH =  ?";
	    try (Connection conn = new BaseDAO().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, maKH);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public boolean themKhachHang(KhachHang kh) {
        String sqlCheck = "SELECT COUNT(*) FROM KhachHang WHERE MaKH = ?";
        String sqlInsert = "INSERT INTO KhachHang (MaKH, TenKH, SDT, GioiTinh) VALUES (?, ?, ?, ?)";
        boolean result = false;

        try {
            conn = new BaseDAO().getConnection();
            // Kiểm tra MaKH đã tồn tại chưa
            ps = conn.prepareStatement(sqlCheck);
            ps.setString(1, kh.getMaKH());
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Mã khách hàng đã tồn tại!");
                return false;
            }

            // Kiểm tra TenKH không được để trống
            if (kh.getTenKH() == null || kh.getTenKH().trim().isEmpty()) {
                System.out.println("Tên khách hàng không được để trống!");
                return false;
            }
            // Thêm khách hàng mới
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getTenKH());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getGioiTinh());

            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0;
            if (result) {
                System.out.println("Thêm khách hàng thành công!");
            } else {
                System.out.println("Thêm khách hàng thất bại!");
            }

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm khách hàng: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
        return result;
    }
	public String getNextMaKH() {
	    String sql = "SELECT MAX(MaKH) AS MaxMaKH FROM KhachHang";
	    String newMaKH = "KH001"; // Mặc định nếu bảng rỗng
	    try {
	        conn = new BaseDAO().getConnection();
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            String maxMaKH = rs.getString("MaxMaKH");
	            if (maxMaKH != null) {
	                // Lấy số từ mã lớn nhất (bỏ 'KH')
	                int number = Integer.parseInt(maxMaKH.substring(2));
	                // Tăng lên 1
	                number++;
	                // Tạo mã mới với định dạng KH + 3 chữ số
	                newMaKH = String.format("KH%03d", number);
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("Lỗi khi lấy mã khách hàng mới: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            System.out.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
	        }
	    }
	    
	    return newMaKH;
	}
	public List<KhachHang> getAllKhachHang() throws Exception {
        List<KhachHang> list = new ArrayList<>();
        String sql = "{CALL sp_GetAllKhachHang}";
        
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {
            
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setSdt(rs.getString("SDT"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                list.add(kh);
            }
        } catch (Exception e) {
            throw new Exception("Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
        }
        return list;
    }
    
    public void addKhachHang(KhachHang kh) throws Exception {
        String sql = "{CALL sp_AddKhachHang(?, ?, ?, ?)}";
        
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            
            cstmt.setString(1, kh.getMaKH());
            cstmt.setString(2, kh.getTenKH());
            cstmt.setString(3, kh.getSdt());
            cstmt.setString(4, kh.getGioiTinh());
            
            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Thêm khách hàng thất bại!");
            }
        } catch (Exception e) {
            throw new Exception("Lỗi khi thêm khách hàng: " + e.getMessage());
        }
    }
    
    public void updateKhachHang(KhachHang kh) throws Exception {
        String sql = "{CALL sp_UpdateKhachHang(?, ?, ?, ?)}";
        
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            
            cstmt.setString(1, kh.getMaKH());
            cstmt.setString(2, kh.getTenKH());
            cstmt.setString(3, kh.getSdt());
            cstmt.setString(4, kh.getGioiTinh());
            
            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Cập nhật khách hàng thất bại! Không tìm thấy mã " + kh.getMaKH());
            }
        } catch (Exception e) {
            throw new Exception("Lỗi khi cập nhật khách hàng: " + e.getMessage());
        }
    }
    
    public void deleteKhachHang(String maKH) throws Exception {
        String sql = "{CALL sp_DeleteKhachHang(?)}";
        
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            
            cstmt.setString(1, maKH);
            
            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Xóa khách hàng thất bại! Không tìm thấy mã " + maKH);
            }
        } catch (Exception e) {
            throw new Exception("Lỗi khi xóa khách hàng: " + e.getMessage());
        }
    }
    
    public KhachHang getKhachHangByMa(String maKH) throws Exception {
        String sql = "{CALL sp_GetKhachHangByMa(?)}";
        KhachHang kh = null;
        
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            
            cstmt.setString(1, maKH);
            ResultSet rs = cstmt.executeQuery();
            
            if (rs.next()) {
                kh = new KhachHang();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setSdt(rs.getString("SDT"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
            }
        } catch (Exception e) {
            throw new Exception("Lỗi khi tìm khách hàng: " + e.getMessage());
        }
        return kh;
    }
    
    public List<KhachHang> searchKhachHang(String query, String criteria) throws Exception {
        List<KhachHang> list = new ArrayList<>();
        String sql = "{CALL sp_SearchKhachHang(?, ?)}";
        
        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            
            cstmt.setString(1, query);
            cstmt.setString(2, criteria);
            ResultSet rs = cstmt.executeQuery();
            
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setSdt(rs.getString("SDT"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                list.add(kh);
            }
        } catch (Exception e) {
            throw new Exception("Lỗi khi tìm kiếm khách hàng: " + e.getMessage());
        }
        return list;
    }
}
