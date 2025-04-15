package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.BaseDAO;
import entity.KhachHang;

public class DAO_KhachHang extends BaseDAO {
    
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