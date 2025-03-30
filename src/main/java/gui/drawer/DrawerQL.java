package gui.drawer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.homeNV;
import gui.panelForm.panelDatBan;
import gui.tabbed.WindowsTabbed;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.swing.AvatarIcon;

public class DrawerQL extends SimpleDrawerBuilder {


	@Override
	public SimpleFooterData getSimpleFooterData() {
		// TODO Auto-generated method stub
		return new SimpleFooterData()
				.setTitle("Chào mừng");
	}

	@Override
	public SimpleHeaderData getSimpleHeaderData() {
		String hoTen = "Nguyễn Tân";
		String chucVu = "Nhân viên quản lý";

		return new SimpleHeaderData().setIcon(new AvatarIcon(getClass().getResource("iconAccount.png"), 100, 100, 1000))
				.setTitle(hoTen).setDescription(chucVu);
	}
	@Override
	public SimpleMenuOption getSimpleMenuOption() {
		String menus[][] = { 
				{ "~TRANG CHỦ~" }, 
				{ "Trang chủ",}, 
				{ "~XỬ LÝ~" },
				{ "Xử lý", "Đặt bàn" }, 
				{ "~QUẢN LÝ~" }, 
				{ "Quản lý", "Quản lý nhân viên", "Quản lý tài khoản","Quản lý khách hàng" },
				{ "~TÌM KIẾM~" }, 
				{ "Tìm kiếm", "Tìm kiếm khách hàng", "Tìm kiếm hóa đơn" , "Tìm kiếm nhân viên","Tìm kiếm tài khoản" }, 
				{"~THỐNG KÊ~"},
				{"Thống kê","Thống kê theo doanh thu","Thống kê theo món ăn","Thống kê theo khách hàng"},
				{ "~CÔNG CỤ~" },
				{ "Công cụ", "Trợ giúp", "Cập nhật", }, 
				{ "~TÀI KHOẢN~" },
				{ "Tài khoản", "Thay đổi mật khẩu", "Đăng xuất", "Thoát" } };
		String icons[] = {
				"home-svgrepo-com.svg",
				"table-list-alt-svgrepo-com.svg",
				"manage-dashboard-analytic-svgrepo-com.svg",
				"search-alt-2-svgrepo-com.svg",
				"tool-01-svgrepo-com.svg",
				"account-svgrepo-com.svg",};
		return new SimpleMenuOption()
				.setMenus(menus)
				.setIcons(icons)
				.setBaseIconPath("img")
				.setIconScale(0.02f)
				.addMenuEvent(new MenuEvent() {
					@Override
					public void selected(MenuAction action, int index, int subIndex) {
						System.out.println(index + " " + subIndex);
						// TODO Auto-generated method stub
						if( index==1 && subIndex==1) {
							
						}
						//Tài khoản
						if( index==6 && subIndex==3) {
							System.exit(0);
						}
						if( index==6 && subIndex==2) {
							
						}
						System.out.println(index +" "+subIndex);
						
					}
				})
				.setMenuValidation(new MenuValidation() {
					@Override
					public boolean menuValidation(int index, int subIndex) {
						// TODO Auto-generated method stub
						return true;
					}
				});
	}

	
}
