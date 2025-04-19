//package DAO;
//
//import entity.KhuyenMai;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.JOptionPane; // Import JOptionPane
//
//import ConnectDB.ConnectionDB;
//
//public class DAO_KhuyenMai {
//    private Connection conn;
//
//    public DAO_KhuyenMai() {
//        this.conn = ConnectionDB.getConnection();
//    }
//
//    // Kiểm tra định dạng MaKM: KMXXX (XXX là số)
//    private boolean isValidMaKM(String maKM) {
//        if (maKM == null || !maKM.matches("^KM\\d{3}$")) {
//            JOptionPane.showMessageDialog(null, 
//                "Invalid MaKM format: " + maKM + ". Mã Khuyến mãi phải có cấu trúc KMXXX (X là số).",
//                "Lỗi định dạng", 
//                JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        return true;
//    }
//
//    public boolean addPromotion(KhuyenMai promotion) {
//        if (promotion == null || !isValidMaKM(promotion.getMaKM()) || 
//            promotion.getPhanTramGiamGia() < 0 || promotion.getPhanTramGiamGia() > 100) {
//            return false; // Không cần in lỗi ở đây vì đã xử lý trong isValidMaKM
//        }
//        String sql = "{CALL sp_AddPromotion(?, ?, ?, ?, ?)}";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, promotion.getMaKM());
//            ps.setString(2, promotion.getDieuKien());
//            ps.setObject(3, promotion.getNgayBatDau());
//            ps.setObject(4, promotion.getngayKetThuc());
//            ps.setDouble(5, promotion.getPhanTramGiamGia());
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean updatePromotion(KhuyenMai promotion) {
//        if (promotion == null || !isValidMaKM(promotion.getMaKM()) || 
//            promotion.getPhanTramGiamGia() < 0 || promotion.getPhanTramGiamGia() > 100) {
//            return false;
//        }
//        String sql = "{CALL sp_UpdatePromotion(?, ?, ?, ?, ?)}";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, promotion.getMaKM());
//            ps.setString(2, promotion.getDieuKien());
//            ps.setObject(3, promotion.getNgayBatDau());
//            ps.setObject(4, promotion.getngayKetThuc());
//            ps.setDouble(5, promotion.getPhanTramGiamGia());
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean deletePromotion(String promotionId) {
//        if (!isValidMaKM(promotionId)) {
//            return false;
//        }
//        String sql = "{CALL sp_DeletePromotion(?)}";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, promotionId);
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public List<KhuyenMai> getAllPromotions() {
//        List<KhuyenMai> promotions = new ArrayList<>();
//        String sql = "{CALL sp_GetAllPromotions}";
//        try (PreparedStatement ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                KhuyenMai promotion = new KhuyenMai();
//                promotion.setMaKM(rs.getString("MaKM"));
//                promotion.setDieuKien(rs.getString("DieuKien"));
//                promotion.setNgayBatDau(rs.getObject("NgayBatDau", LocalDateTime.class));
//                promotion.setngayKetThuc(rs.getObject("NgayKetThuc", LocalDateTime.class));
//                promotion.setPhanTramGiamGia(rs.getDouble("PhanTramGiamGia"));
//                promotions.add(promotion);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return promotions;
//    }
//
//    public KhuyenMai getPromotionById(String promotionId) {
//        if (!isValidMaKM(promotionId)) {
//            return null;
//        }
//        String sql = "{CALL sp_GetPromotionById(?)}";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, promotionId);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                KhuyenMai promotion = new KhuyenMai();
//                promotion.setMaKM(rs.getString("MaKM"));
//                promotion.setDieuKien(rs.getString("DieuKien"));
//                promotion.setNgayBatDau(rs.getObject("NgayBatDau", LocalDateTime.class));
//                promotion.setngayKetThuc(rs.getObject("NgayKetThuc", LocalDateTime.class));
//                promotion.setPhanTramGiamGia(rs.getDouble("PhanTramGiamGia"));
//                return promotion;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}