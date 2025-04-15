package gui.drawer;

import gui.HomeQL;
import gui.Main;

import gui.panelForm.PanelQLyHD;
import gui.panelForm.panelDatBan;
import gui.panelForm.panelQLNhanVien;
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

public class DrawerQL extends SimpleDrawerBuilder {

	private HomeQL trangChinh;
	private Main trangDangNhap;
	public DrawerQL(HomeQL trangChinh, Main trangDangNhap) {
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
		String chucVu = "Nhân viên quản lý";
		return new SimpleHeaderData().setIcon(new AvatarIcon(getClass().getResource("avatar.jpg"), 120, 120, 1200))
				.setTitle(hoTen).setDescription(chucVu);
	}

	@Override
	public SimpleMenuOption getSimpleMenuOption() {
		String[][] menus = { { "~TRANG CHỦ~" }, { "Trang chủ" }, { "~BÀN~" }, { "Bàn", "Quản lý bàn" },
				{ "~NHÂN VIÊN~" }, { "Nhân viên", "Quản lý nhân viên", "Tìm kiếm nhân viên" }, { "~MÓN ĂN~" },
				{ "Món ăn", "Quản lý món ăn", "Tìm kiếm món ăn" },{"~THỐNG KÊ~"},{"Thống kê","Thống kê doanh thu","Thống kê khách hàng","Thống kê món ăn"}, { "~CÔNG CỤ~" },
				{ "Công cụ", "Trợ giúp", "Cài đặt" }, { "~TÀI KHOẢN~" },
				{ "Tài khoản","Quản lý tài khoản", "Thay đổi mật khẩu", "Đăng xuất", "Thoát" } };
		String[] icons = { "home-svgrepo-com.svg", "table-svgrepo-com.svg", "customers-svgrepo-com.svg",
				"bill-invoice-ui-svgrepo-com.svg", "tool-01-svgrepo-com.svg", "account-svgrepo-com.svg" };

		return new SimpleMenuOption().setMenus(menus).setIcons(icons).setBaseIconPath("img").setIconScale(0.04f)
				.addMenuEvent(new MenuEvent() {
					@Override
					public void selected(MenuAction action, int index, int subIndex) {
						System.out.println("Menu selected: " + index + " " + subIndex);
						// Trang chủ index=0
						if (index == 0 && subIndex == 0) {
							trangChinh.setPanelBody(new panelTrangChu());
						}
						// Bàn idex=1
						if (index == 1) {
							if(subIndex==1) {
								trangChinh.setPanelBody(new panelDatBan());
							}
							else if(subIndex==2) {
								
							}
						}
						// Nhân viên index=2
						if(index==2) {
							if(subIndex==1) {
								trangChinh.setPanelBody(new panelQLNhanVien());
							}
							else if(subIndex==2) {

							}
						
						}
						// Hóa đơn index=3
						if( index==3) {
							if(subIndex==1) {
								trangChinh.setPanelBody(new PanelQLyHD());
							}
							else if(subIndex==2) {
								trangChinh.setPanelBody(new panelTimKiemHD());
							}
						}
						// món ăn index=4
						if(index==4) {
							
						}
						// thống kê index=5
						if(index==5) {
							
						}
						// công cụ index=6
						if(index==6) {
							
						}
						//tài khoản index =7
						if (index == 7) {
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