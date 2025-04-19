[1mdiff --git a/src/main/java/dao/DAO_KhachHang.java b/src/main/java/dao/DAO_KhachHang.java[m
[1mindex fef3b47..ddb0afa 100644[m
[1m--- a/src/main/java/dao/DAO_KhachHang.java[m
[1m+++ b/src/main/java/dao/DAO_KhachHang.java[m
[36m@@ -2,6 +2,7 @@[m [mpackage dao;[m
 [m
 import java.sql.CallableStatement;[m
 import java.sql.Connection;[m
[32m+[m[32mimport java.sql.PreparedStatement;[m
 import java.sql.ResultSet;[m
 import java.util.ArrayList;[m
 import java.util.List;[m
[36m@@ -137,4 +138,22 @@[m [mpublic class DAO_KhachHang extends BaseDAO {[m
         }[m
         return list;[m
     }[m
[32m+[m[41m    [m
[32m+[m[32m    public boolean checkMaKHExists(String maKH) throws Exception {[m
[32m+[m[32m        String sql = "SELECT COUNT(*) FROM KhachHang WHERE MaKH = ?";[m
[32m+[m[41m        [m
[32m+[m[32m        try (Connection conn = getConnection();[m
[32m+[m[32m             PreparedStatement pstmt = conn.prepareStatement(sql)) {[m
[32m+[m[41m            [m
[32m+[m[32m            pstmt.setString(1, maKH);[m
[32m+[m[32m            ResultSet rs = pstmt.executeQuery();[m
[32m+[m[41m            [m
[32m+[m[32m            if (rs.next()) {[m
[32m+[m[32m                return rs.getInt(1) > 0; // Trả về true nếu có ít nhất 1 bản ghi[m
[32m+[m[32m            }[m
[32m+[m[32m            return false;[m
[32m+[m[32m        } catch (Exception e) {[m
[32m+[m[32m            throw new Exception("Lỗi khi kiểm tra mã khách hàng: " + e.getMessage());[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/src/main/java/dao/DAO_KhuyenMai.java b/src/main/java/dao/DAO_KhuyenMai.java[m
[1mindex 2b71689..0c4f2e0 100644[m
[1m--- a/src/main/java/dao/DAO_KhuyenMai.java[m
[1m+++ b/src/main/java/dao/DAO_KhuyenMai.java[m
[36m@@ -1,7 +1,128 @@[m
[31m-package dao;[m
[31m-[m
[31m-import connectDatabase.BaseDAO;[m
[31m-[m
[31m-public class DAO_KhuyenMai extends BaseDAO {[m
[31m-[m
[31m-}[m
[32m+[m[32m//package DAO;[m
[32m+[m[32m//[m
[32m+[m[32m//import entity.KhuyenMai;[m
[32m+[m[32m//import java.sql.Connection;[m
[32m+[m[32m//import java.sql.PreparedStatement;[m
[32m+[m[32m//import java.sql.ResultSet;[m
[32m+[m[32m//import java.sql.SQLException;[m
[32m+[m[32m//import java.time.LocalDateTime;[m
[32m+[m[32m//import java.util.ArrayList;[m
[32m+[m[32m//import java.util.List;[m
[32m+[m[32m//import javax.swing.JOptionPane; // Import JOptionPane[m
[32m+[m[32m//[m
[32m+[m[32m//import ConnectDB.ConnectionDB;[m
[32m+[m[32m//[m
[32m+[m[32m//public class DAO_KhuyenMai {[m
[32m+[m[32m//    private Connection conn;[m
[32m+[m[32m//[m
[32m+[m[32m//    public DAO_KhuyenMai() {[m
[32m+[m[32m//        this.conn = ConnectionDB.getConnection();[m
[32m+[m[32m//    }[m
[32m+[m[32m//[m
[32m+[m[32m//    // Kiểm tra định dạng MaKM: KMXXX (XXX là số)[m
[32m+[m[32m//    private boolean isValidMaKM(String maKM) {[m
[32m+[m[32m//        if (maKM == null || !maKM.matches("^KM\\d{3}$")) {[m
[32m+[m[32m//            JOptionPane.showMessageDialog(null,[m[41m [m
[32m+[m[32m//                "Invalid MaKM format: " + maKM + ". Mã Khuyến mãi phải có cấu trúc KMXXX (X là số).",[m
[32m+[m[32m//                "Lỗi định dạng",[m[41m [m
[32m+[m[32m//                JOptionPane.ERROR_MESSAGE);[m
[32m+[m[32m//            return false;[m
[32m+[m[32m//        }[m
[32m+[m[32m//        return true;[m
[32m+[m[32m//    }[m
[32m+[m[32m//[m
[32m+[m[32m//    public boolean addPromotion(KhuyenMai promotion) {[m
[32m+[m[32m//        if (promotion == null || !isValidMaKM(promotion.getMaKM()) ||[m[41m [m
[32m+[m[32m//            promotion.getPhanTramGiamGia() < 0 || promotion.getPhanTramGiamGia() > 100) {[m
[32m+[m[32m//            return false; // Không cần in lỗi ở đây vì đã xử lý trong isValidMaKM[m
[32m+[m[32m//        }[m
[32m+[m[32m//        String sql = "{CALL sp_AddPromotion(?, ?, ?, ?, ?)}";[m
[32m+[m[32m//        try (PreparedStatement ps = conn.prepareStatement(sql)) {[m
[32m+[m[32m//            ps.setString(1, promotion.getMaKM());[m
[32m+[m[32m//            ps.setString(2, promotion.getDieuKien());[m
[32m+[m[32m//            ps.setObject(3, promotion.getNgayBatDau());[m
[32m+[m[32m//            ps.setObject(4, promotion.getngayKetThuc());[m
[32m+[m[32m//            ps.setDouble(5, promotion.getPhanTramGiamGia());[m
[32m+[m[32m//            return ps.executeUpdate() > 0;[m
[32m+[m[32m//        } catch (SQLException e) {[m
[32m+[m[32m//            e.printStackTrace();[m
[32m+[m[32m//            return false;[m
[32m+[m[32m//        }[m
[32m+[m[32m//    }[m
[32m+[m[32m//[m
[32m+[m[32m//    public boolean updatePromotion(KhuyenMai promotion) {[m
[32m+[m[32m//        if (promotion == null || !isValidMaKM(promotion.getMaKM()) ||[m[41m [m
[32m+[m[32m//            promotion.getPhanTramGiamGia() < 0 || promotion.getPhanTramGiamGia() > 100) {[m
[32m+[m[32m//            return false;[m
[32m+[m[32m//        }[m
[32m+[m[32m//        String sql = "{CALL sp_UpdatePromotion(?, ?, ?, ?, ?)}";[m
[32m+[m[32m//        try (PreparedStatement ps = conn.prepareStatement(sql)) {[m
[32m+[m[32m//            ps.setString(1, promotion.getMaKM());[m
[32m+[m[32m//            ps.setString(2, promotion.getDieuKien());[m
[32m+[m[32m//            ps.setObject(3, promotion.getNgayBatDau());[m
[32m+[m[32m//            ps.setObject(4, promotion.getngayKetThuc());[m
[32m+[m[32m//            ps.setDouble(5, promotion.getPhanTramGiamGia());[m
[32m+[m[32m//            return ps.executeUpdate() > 0;[m
[32m+[m[32m//        } catch (SQLException e) {[m
[32m+[m[32m//            e.printStackTrace();[m
[32m+[m[32m//            return false;[m
[32m+[m[32m//        }[m
[32m+[m[32m//    }[m
[32m+[m[32m//[m
[32m+[m[32m//    public boolean deletePromotion(String promotionId) {[m
[32m+[m[32m//        if (!isValidMaKM(promotionId)) {[m
[32m+[m[32m//            return false;[m
[32m+[m[32m//        }[m
[32m+[m[32m//        String sql = "{CALL sp_DeletePromotion(?)}";[m
[32m+[m[32m//        try (PreparedStatement ps = conn.prepareStatement(sql)) {[m
[32m+[m[32m//            ps.setString(1, promotionId);[m
[32m+[m[32m//            return ps.executeUpdate() > 0;[m
[32m+[m[32m//        } catch (SQLException e) {[m
[32m+[m[32m//            e.printStackTrace();[m
[32m+[m[32m//            return false;[m
[32m+[m[32m//        }[m
[32m+[m[32m//    }[m
[32m+[m[32m//[m
[32m+[m[32m//    public List<KhuyenMai> getAllPromotions() {[m
[32m+[m[32m//        List<KhuyenMai> promotions = new ArrayList<>();[m
[32m+[m[32m//        String sql = "{CALL sp_GetAllPromotions}";[m
[32m+[m[32m//        try (PreparedStatement ps = conn.prepareStatement(sql);[m
[32m+[m[32m//             ResultSet rs = ps.executeQuery()) {[m
[32m+[m[32m//            while (rs.next()) {[m
[32m+[m[32m//                KhuyenMai promotion = new KhuyenMai();[m
[32m+[m[32m//                promotion.setMaKM(rs.getString("MaKM"));[m
[32m+[m[32m//                promotion.setDieuKien(rs.getString("DieuKien"));[m
[32m+[m[32m//                promotion.setNgayBatDau(rs.getObject("NgayBatDau", LocalDateTime.class));[m
[32m+[m[32m//                promotion.setngayKetThuc(rs.getObject("NgayKetThuc", LocalDateTime.class));[m
[32m+[m[32m//                promotion.setPhanTramGiamGia(rs.getDouble("PhanTramGiamGia"));[m
[32m+[m[32m//                promotions.add(promotion);[m
[32m+[m[32m//            }[m
[32m+[m[32m//        } catch (SQLException e) {[m
[32m+[m[32m//            e.printStackTrace();[m
[32m+[m[32m//        }[m
[32m+[m[32m//        return promotions;[m
[32m+[m[32m//    }[m
[32m+[m[32m//[m
[32m+[m[32m//    public KhuyenMai getPromotionById(String promotionId) {[m
[32m+[m