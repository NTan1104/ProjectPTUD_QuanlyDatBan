package gui.drawer;

import gui.Main;
import gui.homeNV;
import gui.panelForm.panelDatBan;
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
		        { "~CÔNG CỤ~" },
		        { "Công cụ", "Trợ giúp", "Cài đặt" },
		        { "~TÀI KHOẢN~" },
		        { "Tài khoản", "Thay đổi mật khẩu", "Đăng xuất", "Thoát" }

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
							
						}
						// Hóa đơn index=3
						if( index==3) {
							
						}
						// Công cụ index=4
						if(index==4) {
							
						}
						// Tài khoản index=5
						if (index == 5) {
							if (subIndex == 1) {

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