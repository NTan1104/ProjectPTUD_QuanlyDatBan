package gui.drawer;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import gui.Main;
import gui.homeAll;
import gui.panelForm.panelDatBan;
import gui.panelForm.panelKhuyenMai;
import gui.panelForm.panelPhieuDatBan;
import gui.panelForm.panelQLNhanVien;
import gui.panelForm.panelTKNhanVien;
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

	private homeAll trangChinh;
	private Main trangDangNhap;

	public DrawerQL(homeAll trangChinh, Main trangDangNhap) {
		this.trangChinh = trangChinh;
		this.trangDangNhap = trangDangNhap;
	}

	public void showWelcomeMessage(String name) {
		JOptionPane.showMessageDialog(trangChinh, "Chào mừng " + name, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public SimpleHeaderData getSimpleHeaderData() {
		String chucVu = "Nhân viên quản lý";
		return new SimpleHeaderData()
				.setIcon(new AvatarIcon(getClass().getResource("/img/staffIcon.png"), 120, 120, 1200))
				.setTitle("Nhà hàng Cerbus BBQ").setDescription(chucVu);
	}

	@Override
	public SimpleFooterData getSimpleFooterData() {
		Date currentTime = new Date();
		// Định dạng ban đầu
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy" + " HH:mm              ");
		String formattedTime = formatter.format(currentTime);
		// Thay thế sang tiếng Việt
		formattedTime = formattedTime.replace("Monday", "Thứ Hai").replace("Tuesday", "Thứ Ba")
				.replace("Wednesday", "Thứ Tư").replace("Thursday", "Thứ Năm").replace("Friday", "Thứ Sáu")
				.replace("Saturday", "Thứ Bảy").replace("Sunday", "Chủ Nhật");
		return new SimpleFooterData().setTitle(formattedTime);
	}

	@Override
	public SimpleMenuOption getSimpleMenuOption() {
		String[][] menus = { { "~TRANG CHỦ~" }, { "Trang chủ" }, { "~BÀN~" }, { "Bàn", "Đặt bàn", "Quản lý bàn" },
				{ "~KHÁCH HÀNG~" }, { "Khách hàng", "Quản lý khách hàng", "Tìm kiếm khách hàng" }, { "~NHÂN VIÊN~" },
				{ "Nhân viên", "Quản lý nhân viên", "Tìm kiếm nhân viên" }, { "~HÓA ĐƠN~" },
				{ "Hóa đơn", "Quản lý hóa đơn", "Tìm kiếm hóa đơn" }, { "~MÓN ĂN~" }, { "Món ăn", "Quản lý món ăn" },
				{ "~THỐNG KÊ~" }, { "Thống kê" }, { "~KHUYẾN MÃI~" }, { "Khuyến mãi", "Quản lý khuyến mãi" },
				{ "~CÔNG CỤ~" }, { "Công cụ", "Trợ giúp", "Cài đặt" }, { "~TÀI KHOẢN~" },
				{ "Tài khoản", "Thông tin cá nhân", "Đăng xuất", "Thoát" } };
		String[] icons = { "home-svgrepo-com.svg", "table-dinner-svgrepo-com.svg", "users-svgrepo-com.svg",
				"staff-symbol-svgrepo-com.svg", "bill-svgrepo-com.svg", "fast-food-outline-svgrepo-com.svg",
				"statistics-graph-stats-analytics-business-data-svgrepo-com.svg", "discount-percentage-svgrepo-com.svg",
				"tools-svgrepo-com.svg", "account-svgrepo-com.svg" };

		return new SimpleMenuOption().setMenus(menus).setIcons(icons).setBaseIconPath("img").setIconScale(0.03f)
				.addMenuEvent(new MenuEvent() {
					@Override
					public void selected(MenuAction action, int index, int subIndex) {
						System.out.println("Menu selected: " + index + " " + subIndex);
						// Trang chủ
						if (index == 0 && subIndex == 0) {

							panelTrangChu home = new panelTrangChu();
							trangChinh.setPanelBody(home);
							home.playVideo("video/2424767-uhd_3840_2160_24fps.mp4");
						}
						// Bàn
						if (index == 1) {
							if (subIndex == 1) {
								panelDatBan DatBan = new panelDatBan();
								trangChinh.setPanelBody(DatBan);
								DatBan.playVideo("video/88189-602915536_small.mp4");
							} else if (subIndex == 2) {

							}
						}
						// Khách hàng
						if (index == 2) {
							if (subIndex == 1) {
							} else if (subIndex == 2) {

							}
						}
						// Nhân viên
						if (index == 3) {
							if (subIndex == 1) {
								panelQLNhanVien panelNV = new panelQLNhanVien();
								trangChinh.setPanelBody(panelNV);
							}	else if (subIndex == 2) {
								panelTKNhanVien panelTKNV = new panelTKNhanVien();
								trangChinh.setPanelBody(panelTKNV);
							}
							
						}
						// Hóa đơn
						if (index == 4) {

						}
						// Món ăn
						if (index == 5) {
						}
						// Thống kê
						if (index == 6) {
							if (subIndex == 0) {

							}
						}
						// Khuyến mãi
						if (index == 7) {
							if (subIndex == 0) {
								panelKhuyenMai panelKM = new panelKhuyenMai();
								trangChinh.setPanelBody(panelKM);
							}
						}
						// Công cụ
						if (index == 8) {

						}
						// Tài khoản
						if (index == 9) {
							if (subIndex == 1) {
							} else if (subIndex == 2) {
								trangChinh.setVisible(false);
								trangDangNhap.switchToLogin();
								trangDangNhap.setVisible(true);

							} else if (subIndex == 3) {
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
