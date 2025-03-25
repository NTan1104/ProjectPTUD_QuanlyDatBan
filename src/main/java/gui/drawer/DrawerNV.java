package gui.drawer;

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

public class DrawerNV extends SimpleDrawerBuilder {

	@Override
	public SimpleFooterData getSimpleFooterData() {
		// TODO Auto-generated method stub
		return new SimpleFooterData()
				.setTitle("Chào mừng");
	}

	@Override
	public SimpleHeaderData getSimpleHeaderData() {
		String hoTen = "Nguyễn Tân";
		String chucVu = "Nhân viên lễ tân";

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
				{ "Quản lý", "Quản lý bàn", "Quản lý khách hàng" },
				{ "~TÌM KIẾM~" }, 
				{ "Tìm kiếm", "Tìm kiếm khách hàng", "Tìm kiếm hóa đơn" }, 
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
							WindowsTabbed.getInstance().addTab("Đăt bàn", new panelDatBan());
						}
						//Tài khoản
						if( index==5 && subIndex==3) {
							System.exit(0);
						}
						if( index==5 && subIndex==2) {
							
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
