package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.BaseDAO;
import entity.Ban;

public class DAO_Ban extends BaseDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Ban> list = new ArrayList<Ban>();

    public List<Ban> getAllBansinTang1() {
        String sql = "select * from Ban where Tang = 1";
        try {
            conn = new BaseDAO().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ban(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // Phương thức cập nhật trạng thái bàn
    public boolean capNhatTrangThaiBan(String tenBan, String trangThai) {
        // Kiểm tra trạng thái hợp lệ
        if (!isValidTrangThai(trangThai)) {
            System.out.println("Trạng thái không hợp lệ!");
            return false;
        }

        // Lấy maBan từ tenBan
        String maBan = getMaBanByTenBan(tenBan);
        if (maBan == null) {
            System.out.println("Số bàn không tồn tại!");
            return false;
        }

        String sqlUpdate = "UPDATE Ban SET TrangThai = ? WHERE MaBan = ?";
        boolean result = false;

        try {
            conn = new BaseDAO().getConnection();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1, trangThai);
            ps.setString(2, maBan);

            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0;
            if (result) {
                System.out.println("Cập nhật trạng thái bàn thành công!");
            } else {
                System.out.println("Cập nhật trạng thái bàn thất bại!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật trạng thái bàn: " + e.getMessage());
        } finally {
            closeResources();
        }

        return result;
    }

    // Phương thức kiểm tra trạng thái hợp lệ
    private boolean isValidTrangThai(String trangThai) {
        return trangThai != null && (
            trangThai.equals("CÒN TRỐNG") ||
            trangThai.equals("ĐÃ ĐẶT") ||
            trangThai.equals("ĐANG SỬ DỤNG") ||
            trangThai.equals("BẢO TRÌ")
        );
    }
    public String getMaBanByTenBan(String tenBan) {
        String sql = "SELECT MaBan FROM Ban WHERE TenBan = ?";
        String maBan = null;
        try {
            conn = new BaseDAO().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenBan);
            rs = ps.executeQuery();
            if (rs.next()) {
                maBan = rs.getString("MaBan");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy maBan từ tenBan: " + e.getMessage());
        } finally {
            closeResources();
        }
        return maBan;
    }
    public Ban getBanbyTenBan(String tenBan) {
        String sql = "SELECT * FROM Ban WHERE TenBan = ?";
        try {
            conn = new BaseDAO().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenBan);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Ban(rs.getString(1),
                		rs.getString(2),
                		rs.getInt(3),
                		rs.getInt(4),
                		rs.getString(5),
                		rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy maBan từ tenBan: " + e.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }
    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
        }
    }
    
}