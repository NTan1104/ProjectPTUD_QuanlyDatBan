package gui.drawer;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import gui.Main;	
import gui.homeNV;
import gui.panelForm.PanelQLyHD;
import gui.panelForm.panelDatBan;
import gui.panelForm.panelQLyTK;
//import gui.panelForm.panelQLyTK;
import gui.panelForm.panelQlyKhachhang;
import gui.panelForm.panelQlyMonAn;
import gui.panelForm.panelQlyban;
import gui.panelForm.panelSearchKH;
import gui.panelForm.panelThongke;
import gui.panelForm.panelTimKiemHD;
import gui.panelForm.panelTrangChu;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.swing.AvatarIcon;

public class DrawerNV extends SimpleDrawerBuilder {

	private homeNV trangChinh;
	private Main trangDangNhap;
	public DrawerNV(homeNV trangChinh, Main trangDangNhap) {
		this.trangChinh = trangChinh;
		this.trangDangNhap = trangDangNhap;
        // Color theme
        FlatRobotoFont.install();
//      UIManager.put("Panel.background", new Color(247, 248, 252)); // Trắng xám nhạt nhẹ nhàng
//      UIManager.put("Button.background", new Color(52, 102, 255)); // Xanh dương nhẹ cho nút
//      UIManager.put("Button.foreground", Color.WHITE);
//      UIManager.put("Button.disabledBackground", new Color(209, 213, 219)); // Xám nhạt khi vô hiệu
//      UIManager.put("Label.foreground", new Color(17, 24, 39)); // Xám đen đậm cho chữ
//      UIManager.put("Component.borderColor", new Color(229, 231, 235)); // Viền xám nhạt
        
	}

	@Override
	public SimpleFooterData getSimpleFooterData() {
		return new SimpleFooterData().setTitle("Chào mừng");
	}

	@Override
	public SimpleHeaderData getSimpleHeaderData() {
		String hoTen = "Nguyễn Tân";
		String chucVu = "Nhân viên lễ tân";
		return new SimpleHeaderData().setIcon(new AvatarIcon(getClass().getResource("avatar.jpg"), 120, 120, 1200))
				.setTitle(hoTen).setDescription(chucVu);
	}

	@Override
	public SimpleMenuOption getSimpleMenuOption() {
		String[][] menus = { 
		        { "~TRANG CHỦ~" },
		        { "Trang chủ" },
		        { "~BÀN~" },
		        { "Bàn", "Đặt bàn", "Quản lý bàn" },
		        { "~KHÁCH HÀNG~" },
		        { "Khách hàng", "Quản lý khách hàng", "Tìm kiếm khách hàng" },
		        { "~HÓA ĐƠN~" },
		        { "Hóa đơn", "Quản lý hóa đơn", "Tìm kiếm hóa đơn" },
		        { "~Thống kê~" },
		        { "Thống kê", "Thống kê", "Món ăn"},
		        { "~TÀI KHOẢN~" },
		        { "Tài khoản", "Quản lý tài khoản", "Đăng xuất", "Thoát" },
		        { "~CÔNG CỤ~" },
		        { "Công cụ", "Trợ giúp", "Cài đặt" }

		};
		String[] icons = { "home-svgrepo-com.svg", "table-svgrepo-com (1).svg", "customers-svgrepo-com.svg",
				"bill-invoice-ui-svgrepo-com.svg", "tool-01-svgrepo-com.svg", "account-svgrepo-com.svg" };

		return new SimpleMenuOption().setMenus(menus).setIcons(icons).setBaseIconPath("img").setIconScale(0.03f)
				.addMenuEvent(new MenuEvent() {
					@Override
					public void selected(MenuAction action, int index, int subIndex) {
						System.out.println("Menu selected: " + index + " " + subIndex);
						// Trang chủ index=0	
						if (index == 0 && subIndex == 0) {
							
							panelTrangChu home = new panelTrangChu();
							trangChinh.setPanelBody(home);
							home.playVideo("video/2424767-uhd_3840_2160_24fps.mp4");
						}
						// Bàn idex=1
						if (index == 1) {
							if(subIndex==1) {
								panelDatBan DatBan = new panelDatBan();
								trangChinh.setPanelBody(DatBan);
								DatBan.playVideo("video/tableFloor.mp4");
							}
							else if(subIndex==2) {
								
							}
						}
						// Khách hàng index=2
						if(index==2) {
							if(subIndex == 1) {
								trangChinh.setPanelBody(new panelQlyKhachhang());
							}else if(subIndex == 2) {
								trangChinh.setPanelBody(new panelSearchKH());
							}
						}
						// Hóa đơn index=3
						if( index==3) {
							if(subIndex==1) {
								trangChinh.setPanelBody(new PanelQLyHD());
							}else if(subIndex == 2) {
								trangChinh.setPanelBody(new panelTimKiemHD());
							}
						}
						// Công cụ index=4
						if(index==4) {
							if(subIndex == 1) {
								trangChinh.setPanelBody(new panelThongke());
							}else if(subIndex == 2) {
								try {
									trangChinh.setPanelBody(new panelQlyMonAn());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						// Tài khoản index=5
						if (index == 5) {
							if (subIndex == 1) {
								trangChinh.setPanelBody(new panelQLyTK());
							} else if (subIndex == 2) {
								trangChinh.setVisible(false); // Ẩn homeNV thay vì đóng
                                trangDangNhap.switchToLogin(); // Chuyển về Main
                                trangDangNhap.setVisible(true);
								
							} else if(subIndex==3){
								System.exit(0);
							}
						}
					}
				}).setMenuValidation(new MenuValidation() {
					@Override
					public boolean menuValidation(int index, int subIndex) {
						return true;
					}
				});
	}
}