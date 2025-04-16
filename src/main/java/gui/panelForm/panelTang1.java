package gui.panelForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import connectDatabase.BaseDAO;
import dao.DAO_Ban;
import dao.DAO_CTHoaDon;
import dao.DAO_CTPhieuDatBan;
import dao.DAO_HoaDon;
import dao.DAO_KhachHang;
import dao.DAO_KhuyenMai;
import dao.DAO_MonAn;
import dao.DAO_PhieuDatBan;
import entity.Ban;
import entity.CTHoaDon;
import entity.CTPhieuDatBan;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.MonAn;
import entity.PhieuDatBan;

import javax.swing.table.DefaultTableModel;

public class panelTang1 extends JPanel {
	private static final long serialVersionUID = 1L;
	private RoundedPanelWithShadow panel_ChiTietBan;
	private DAO_Ban Ban_DAO;
	private JTextField txt_tongCong;
	private JTextField txt_VAT;
	private JTextField txt_tienCanTra;
	private JPanel panel_Ban;
	private JPanel panelMenu;
	private JScrollPane scrollPaneMenu;
	private JTable table;
	private JComboBox<String> comboKM;
	private DAO_KhuyenMai khuyenMai_DAO;
	private DAO_HoaDon hoaDon_DAO;
	private DAO_CTHoaDon CTHD_DAO;
	private JButton btnHuyBan;
	private DAO_MonAn monAn_DAO;
	private JComboBox<String> comboLoaiMon;
	private DAO_PhieuDatBan PDB_DAO;
	private DAO_KhachHang KH_DAO;
	private DAO_CTPhieuDatBan CTPDB_DAO;

	public panelTang1() {
		setSize(new Dimension(1535, 850));
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("themes");
		FlatMacLightLaf.setup();

		UIManager.put("Panel.background", new Color(247, 248, 252));
		UIManager.put("Button.background", new Color(52, 102, 255));
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Button.disabledBackground", new Color(209, 213, 219));
		UIManager.put("Label.foreground", new Color(17, 24, 39));
		UIManager.put("Component.borderColor", new Color(229, 231, 235));

		setLayout(null);
		setBackground(UIManager.getColor("Panel.background"));

		panel_ChiTietBan = new RoundedPanelWithShadow(30, new Color(255, 255, 255), 10, 5, new Color(0, 0, 0, 50));
		panel_ChiTietBan.setBounds(1055, 10, 470, 755);
		panel_ChiTietBan.setLayout(null);
		panel_ChiTietBan.setVisible(false);
		add(panel_ChiTietBan);

		panel_Ban = new RoundedPanelWithShadow(30, new Color(250, 251, 255), 5, 3, new Color(0, 0, 0, 30));
		panel_Ban.setBounds(10, 10, 1032, 755);
		panel_Ban.setLayout(null);

		panel_Ban.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_ChiTietBan.setVisible(false);
			}
		});

		add(panel_Ban);
		refreshBanList();
	}

	private void refreshBanList() {
		panel_Ban.removeAll();
		Ban_DAO = new DAO_Ban();
		java.util.List<Ban> dsBan = Ban_DAO.getAllBansinTang1();
		int x = 85, y = 110;
		int count = 0;
		for (Ban ban : dsBan) {
			String trangThai = ban.getTrangThai().trim();
			RoundedPanelWithShadow panelBan = createBanPanel(ban.getTenBan().trim(), ban.getMaBan().trim(), trangThai,
					x, y);
			panel_Ban.add(panelBan);
			count++;
			if (count % 3 == 0) {
				x = 85;
				y += 394;
			} else {
				x += 319;
			}
		}
		panel_Ban.revalidate();
		panel_Ban.repaint();
	}

	private RoundedPanelWithShadow createBanPanel(String tenBan, String maBan, String status, int x, int y) {
		RoundedPanelWithShadow panel = new RoundedPanelWithShadow(30, new Color(255, 255, 255), 8, 4,
				new Color(0, 0, 0, 40));
		panel.setBounds(x, y, 201, 222);
		panel.setLayout(null);

		ImageIcon originalIcon = new ImageIcon(panelTang1.class.getResource("/img/dining-table.png"));
		Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		JLabel lblImage = new JLabel(scaledIcon);
		lblImage.setBounds(60, 40, 80, 80);
		panel.add(lblImage);

		JLabel lblBanTitle = new JLabel(tenBan);
		lblBanTitle.setForeground(UIManager.getColor("Label.foreground"));
		lblBanTitle.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 18));
		lblBanTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanTitle.setBounds(0, 10, 201, 30);
		panel.add(lblBanTitle);

		JLabel lblStatus = new JLabel(status);
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStatus.setForeground(status.equals("CÒN TRỐNG") ? new Color(16, 185, 129) : new Color(239, 68, 68));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(0, 130, 201, 30);
		panel.add(lblStatus);

		String buttonText = status.equals("CÒN TRỐNG") ? "ĐẶT BÀN" : "XEM CHI TIẾT";
		JButton btnDatBan = new JButton(buttonText);
		btnDatBan.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 12));
		btnDatBan.setForeground(UIManager.getColor("Button.foreground"));
		btnDatBan.setBackground(status.equals("BẢO TRÌ") ? UIManager.getColor("Button.disabledBackground")
				: UIManager.getColor("Button.background"));
		btnDatBan.setFocusPainted(false);
		btnDatBan.setBounds(40, 170, 120, 35);
		btnDatBan.setBorderPainted(false);
		btnDatBan.setCursor(
				Cursor.getPredefinedCursor(status.equals("BẢO TRÌ") ? Cursor.DEFAULT_CURSOR : Cursor.HAND_CURSOR));
		btnDatBan.setEnabled(!status.equals("BẢO TRÌ"));

		if (!status.equals("BẢO TRÌ")) {
			btnDatBan.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent evt) {
					btnDatBan.setBackground(new Color(37, 99, 235));
				}

				@Override
				public void mouseExited(MouseEvent evt) {
					btnDatBan.setBackground(UIManager.getColor("Button.background"));
				}
			});
		}

		btnDatBan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (status.equals("ĐANG SỬ DỤNG") || status.equals("ĐÃ ĐẶT")) {
					try {
						PDB_DAO = new DAO_PhieuDatBan();
						PhieuDatBan pdb = PDB_DAO.getLatestPDBByMaBan(maBan);
						if (pdb != null) {
							KH_DAO = new DAO_KhachHang();
							KhachHang kh = KH_DAO.getKhachHangbyMa(pdb.getMaKH());
							CTPDB_DAO = new DAO_CTPhieuDatBan();
							CTPhieuDatBan ctpdb = CTPDB_DAO.getCTPDBByMa(pdb.getMaPDB());

							if (kh != null && ctpdb != null) {
								displayBanDetails(tenBan, status, kh.getMaKH(), kh.getTenKH(), kh.getSdt(),
										kh.getGioiTinh(), String.valueOf(ctpdb.getSoNguoi()));
								panel_ChiTietBan.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(panelTang1.this, "Không tìm thấy thông tin khách hàng!");
								displayBanDetails(tenBan, status, null, null, null, null, null);
								panel_ChiTietBan.setVisible(true);
							}
						} else {
							JOptionPane.showMessageDialog(panelTang1.this, "Không tìm thấy phiếu đặt bàn!");
							displayBanDetails(tenBan, status, null, null, null, null, null);
							panel_ChiTietBan.setVisible(true);
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(panelTang1.this, "Lỗi khi lấy thông tin: " + ex.getMessage());
					}
				} else {
					panel_ChiTietBan.setVisible(false);
					JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(panelTang1.this), "ĐẶT BÀN",
							Dialog.ModalityType.APPLICATION_MODAL);
					panelPhieuDatBan panelPDB = new panelPhieuDatBan();
					panelPDB.setTableNumber(tenBan);
					panelPDB.setRefreshCallback(() -> refreshBanList());
					panelPDB.setCustomerInfoCallback((maPDB) -> {
						PDB_DAO = new DAO_PhieuDatBan();
						PhieuDatBan PDB = PDB_DAO.getPDBbyMa(maPDB);
						KH_DAO = new DAO_KhachHang();
						KhachHang KH = KH_DAO.getKhachHangbyMa(PDB.getMaKH());
						CTPDB_DAO = new DAO_CTPhieuDatBan();
						CTPhieuDatBan CTPDB = CTPDB_DAO.getCTPDBByMa(maPDB);

						displayBanDetails(tenBan, "ĐÃ ĐẶT", KH.getMaKH(), KH.getTenKH(), KH.getSdt(), KH.getGioiTinh(),
								String.valueOf(CTPDB.getSoNguoi()));
						panel_ChiTietBan.setVisible(true);
					});
					dialog.setContentPane(panelPDB);
					dialog.setUndecorated(false);
					dialog.setSize(460, 455);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
			}
		});

		panel.add(btnDatBan);
		return panel;
	}

	private void displayBanDetails(String tenBan, String status, String maKH, String tenKH, String sdt, String gioiTinh,
			String soLuong) {
		panel_ChiTietBan.removeAll();
		panel_ChiTietBan.setBackground(new Color(255, 255, 255));

		btnHuyBan = new JButton("HỦY BÀN");
		btnHuyBan.setFont(new Font("Dialog", Font.BOLD, 20));
		btnHuyBan.setBounds(10, 684, 180, 43);
		btnHuyBan.setBackground(
				status.equals("ĐÃ ĐẶT") ? new Color(239, 68, 68) : UIManager.getColor("Button.disabledBackground"));
		btnHuyBan.setForeground(Color.WHITE);
		btnHuyBan.setFocusPainted(false);
		btnHuyBan.setBorderPainted(false);
		btnHuyBan.setEnabled(status.equals("ĐÃ ĐẶT"));

		if (status.equals("ĐÃ ĐẶT")) {
			btnHuyBan.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnHuyBan.setBackground(new Color(220, 38, 38));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnHuyBan.setBackground(new Color(239, 68, 68));
				}
			});
		}

		btnHuyBan.addActionListener(e -> {
			if (status.equals("ĐÃ ĐẶT")) {
				int confirm = JOptionPane.showConfirmDialog(panelTang1.this,
						"Bạn có muốn hủy bàn " + tenBan + " không?", "Xác Nhận Hủy Bàn", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (confirm == JOptionPane.YES_OPTION) {
					try {
						Ban_DAO.capNhatTrangThaiBan(tenBan, "CÒN TRỐNG");
						PDB_DAO.deletePhieuDatBanByMaBan(Ban_DAO.getMaBanByTenBan(tenBan));
						panel_ChiTietBan.setVisible(false);
						refreshBanList();
						JOptionPane.showMessageDialog(panelTang1.this, "Hủy bàn thành công");
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(panelTang1.this, "Lỗi khi hủy bàn: " + ex.getMessage());
					}
				}
			}
		});
		panel_ChiTietBan.add(btnHuyBan);

		JButton btnChuyenBan = new JButton("CHUYỂN BÀN");
		btnChuyenBan.setFont(new Font("Dialog", Font.BOLD, 20));
		btnChuyenBan.setBounds(269, 684, 180, 43);
		btnChuyenBan.setBackground(new Color(59, 130, 246));
		btnChuyenBan.setForeground(Color.WHITE);
		btnChuyenBan.setFocusPainted(false);
		btnChuyenBan.setBorderPainted(false);
		btnChuyenBan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnChuyenBan.setBackground(new Color(37, 99, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnChuyenBan.setBackground(new Color(59, 130, 246));
			}
		});
		panel_ChiTietBan.add(btnChuyenBan);

		JPanel panel = new RoundedPanel(30);
		panel.setBounds(10, 20, 439, 654);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel_ChiTietBan.add(panel);

		JLabel lblChiTiet = new JLabel("CHI TIẾT BÀN");
		lblChiTiet.setBounds(10, 10, 419, 50);
		lblChiTiet.setFont(new Font("Roboto", Font.BOLD, 26));
		lblChiTiet.setForeground(new Color(17, 24, 39));
		lblChiTiet.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblChiTiet);

		JLabel lblSoBan = new JLabel("SỐ BÀN: " + tenBan);
		lblSoBan.setBounds(10, 51, 419, 20);
		lblSoBan.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoBan.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblSoBan.setForeground(new Color(55, 65, 81));
		panel.add(lblSoBan);

		JLabel lblTenKhachHang = new JLabel("TÊN KHÁCH HÀNG: " + (tenKH != null ? tenKH : "Chưa có"));
		lblTenKhachHang.setBounds(10, 73, 419, 20);
		lblTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKhachHang.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblTenKhachHang.setForeground(new Color(55, 65, 81));
		panel.add(lblTenKhachHang);

		JLabel lblSoLuong = new JLabel("SỐ LƯỢNG: " + (soLuong != null ? soLuong : "0"));
		lblSoLuong.setBounds(10, 92, 419, 20);
		lblSoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoLuong.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblSoLuong.setForeground(new Color(55, 65, 81));
		panel.add(lblSoLuong);

		JLabel lblDanhSachMon = new JLabel("DANH SÁCH MÓN ĂN ĐÃ GỌI");
		lblDanhSachMon.setBounds(10, 110, 419, 50);
		lblDanhSachMon.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSachMon.setForeground(new Color(17, 24, 39));
		lblDanhSachMon.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblDanhSachMon);

		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "Tên món ăn", "Số lượng", "Giá", "Thành tiền" });
		table = new JTable(model);
		table.setFont(new Font("Roboto", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setGridColor(new Color(229, 231, 235));
		table.setShowGrid(true);
		table.setBackground(Color.WHITE);
		table.setSelectionBackground(new Color(191, 219, 254));
		table.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 14));
		table.getTableHeader().setBackground(new Color(243, 244, 246));
		table.getTableHeader().setForeground(new Color(17, 24, 39));
		
		System.out.println(maKH);
		if (maKH != null) {
			try {
				hoaDon_DAO = new DAO_HoaDon();
				String maHD = hoaDon_DAO.getMaHDByMaKHAndStatus(maKH, "Chưa thanh toán");
				System.out.println(maHD);
				if (maHD != null) {
					CTHD_DAO = new DAO_CTHoaDon();
					monAn_DAO = new DAO_MonAn();
					List<CTHoaDon> dsachCTHD = CTHD_DAO.getCTHDByMaHD(maHD);
					for (CTHoaDon row : dsachCTHD) {
						String maMonAn = row.getMaMonAn(); 
						int soLuongMon = row.getSoLuong();
						MonAn monAn = monAn_DAO.getMonAnByMa(maMonAn);
						if (monAn != null) {
							double gia = monAn.getGia();
							model.addRow(new Object[] { monAn.getTenMonAn(), soLuongMon, gia, soLuongMon * gia });
						}
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(panelTang1.this, "Lỗi khi lấy chi tiết hóa đơn: " + ex.getMessage());
			}
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 170, 419, 308);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		panel.add(scrollPane);

		JButton btnGoiMon = new JButton("GỌI MÓN");
		btnGoiMon.setBounds(10, 611, 180, 43);
		btnGoiMon.setFont(new Font("Roboto", Font.BOLD, 15));
		btnGoiMon.setBackground(new Color(249, 115, 22));
		btnGoiMon.setForeground(Color.WHITE);
		btnGoiMon.setFocusPainted(false);
		btnGoiMon.setBorderPainted(false);
		btnGoiMon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGoiMon.setBackground(new Color(234, 88, 12));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnGoiMon.setBackground(new Color(249, 115, 22));
			}
		});
		btnGoiMon.addActionListener(e -> {
			try {
				showMenuPanel(tenBan);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(panelTang1.this, "Lỗi khi hiển thị menu: " + ex.getMessage());
			}
		});
		panel.add(btnGoiMon);

		JButton btnThanhToan = new JButton("THANH TOÁN");
		btnThanhToan.setBounds(249, 611, 180, 43);
		btnThanhToan.setFont(new Font("Roboto", Font.BOLD, 15));
		btnThanhToan.setBackground(new Color(16, 185, 129));
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFocusPainted(false);
		btnThanhToan.setBorderPainted(false);
		btnThanhToan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnThanhToan.setBackground(new Color(5, 150, 105));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnThanhToan.setBackground(new Color(16, 185, 129));
			}
		});
		panel.add(btnThanhToan);

		JLabel lblTongCong = new JLabel("TỔNG CỘNG");
		lblTongCong.setBounds(50, 488, 114, 20);
		lblTongCong.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongCong.setFont(new Font("Roboto", Font.BOLD, 15));
		lblTongCong.setForeground(new Color(55, 65, 81));
		panel.add(lblTongCong);

		JLabel lblTongTienCanTra = new JLabel("TỔNG TIỀN CẦN TRẢ:");
		lblTongTienCanTra.setBounds(50, 581, 161, 20);
		lblTongTienCanTra.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongTienCanTra.setFont(new Font("Roboto", Font.BOLD, 15));
		lblTongTienCanTra.setForeground(new Color(55, 65, 81));
		panel.add(lblTongTienCanTra);

		txt_tongCong = new JTextField("0");
		txt_tongCong.setBounds(250, 488, 136, 19);
		txt_tongCong.setFont(new Font("Roboto", Font.PLAIN, 14));
		txt_tongCong.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));
		txt_tongCong.setEditable(false);
		panel.add(txt_tongCong);

		txt_tienCanTra = new JTextField("0");
		txt_tienCanTra.setBounds(250, 580, 136, 19);
		txt_tienCanTra.setFont(new Font("Roboto", Font.PLAIN, 14));
		txt_tienCanTra.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));
		txt_tienCanTra.setEditable(false);
		panel.add(txt_tienCanTra);

		JLabel lblVat = new JLabel("VAT");
		lblVat.setBounds(50, 507, 114, 20);
		lblVat.setHorizontalAlignment(SwingConstants.LEFT);
		lblVat.setFont(new Font("Roboto", Font.BOLD, 15));
		lblVat.setForeground(new Color(55, 65, 81));
		panel.add(lblVat);

		JLabel lblKhuyenMai = new JLabel("KHUYẾN MÃI");
		lblKhuyenMai.setBounds(50, 530, 114, 20);
		lblKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
		lblKhuyenMai.setFont(new Font("Roboto", Font.BOLD, 15));
		lblKhuyenMai.setForeground(new Color(55, 65, 81));
		panel.add(lblKhuyenMai);

		khuyenMai_DAO = new DAO_KhuyenMai();
		try {
			java.util.List<KhuyenMai> danhsachKM = khuyenMai_DAO.getAllKMs();
			java.util.List<String> kmOptions = new ArrayList<>();
			kmOptions.add("Không có");
			for (KhuyenMai km : danhsachKM) {
				kmOptions.add(String.format("%.0f%%", km.getPhanTramGiamGia()));
			}
			comboKM = new JComboBox<>(kmOptions.toArray(new String[0]));
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(panelTang1.this, "Lỗi khi lấy khuyến mãi: " + e1.getMessage());
		}

		comboKM.setBounds(250, 532, 136, 21);
		comboKM.setFont(new Font("Roboto", Font.PLAIN, 14));
		comboKM.setBackground(Color.WHITE);
		comboKM.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));
		comboKM.addActionListener(e -> updateTotal());
		panel.add(comboKM);

		txt_VAT = new JTextField("0");
		txt_VAT.setBounds(250, 510, 136, 19);
		txt_VAT.setFont(new Font("Roboto", Font.PLAIN, 14));
		txt_VAT.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));
		txt_VAT.setEditable(false);
		panel.add(txt_VAT);

		updateTotal();

		panel_ChiTietBan.revalidate();
		panel_ChiTietBan.repaint();
	}

	private void showMenuPanel(String tenBan) throws Exception {
		if (panelMenu == null) {
			panelMenu = new JPanel();
			panelMenu.setLayout(null);
			panelMenu.setBackground(new Color(250, 251, 255));

			scrollPaneMenu = new JScrollPane(panelMenu);
			scrollPaneMenu.setBounds(10, 10, 1032, 755);
			scrollPaneMenu.setBorder(BorderFactory.createEmptyBorder());
			scrollPaneMenu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPaneMenu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneMenu.setVisible(false);
			scrollPaneMenu.getVerticalScrollBar().setUnitIncrement(16);
			scrollPaneMenu.getVerticalScrollBar().setBlockIncrement(50);
			add(scrollPaneMenu);

			JLabel lblTitle = new JLabel("DANH SÁCH MÓN ĂN");
			lblTitle.setBounds(0, 10, 1032, 40);
			lblTitle.setFont(new Font("Roboto", Font.BOLD, 24));
			lblTitle.setForeground(new Color(17, 24, 39));
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			panelMenu.add(lblTitle);

			JLabel lblLoaiMon = new JLabel("LOẠI MÓN:");
			lblLoaiMon.setBounds(50, 60, 100, 30);
			lblLoaiMon.setFont(new Font("Roboto", Font.BOLD, 16));
			lblLoaiMon.setForeground(new Color(17, 24, 39));
			panelMenu.add(lblLoaiMon);

			monAn_DAO = new DAO_MonAn();
			List<MonAn> danhSachMonAn = monAn_DAO.getAllMonAns();

			comboLoaiMon = new JComboBox<String>();
			comboLoaiMon.setBounds(150, 60, 200, 30);
			comboLoaiMon.setFont(new Font("Roboto", Font.PLAIN, 14));
			comboLoaiMon.setBackground(Color.WHITE);
			comboLoaiMon.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));
			comboLoaiMon.addActionListener(e -> updateMenuDisplay(tenBan, danhSachMonAn));
			List<String> danhsachloaiMonAn = monAn_DAO.getLoaiMonAn();
			comboLoaiMon.addItem("Tất cả");
			for (String loaimonAn : danhsachloaiMonAn) {
				comboLoaiMon.addItem(loaimonAn);
			}
			panelMenu.add(comboLoaiMon);

			JButton btnBack = new JButton("QUAY LẠI");
			btnBack.setFont(new Font("Roboto", Font.BOLD, 15));
			btnBack.setBackground(new Color(239, 68, 68));
			btnBack.setForeground(Color.WHITE);
			btnBack.setFocusPainted(false);
			btnBack.setBorderPainted(false);
			panelMenu.add(btnBack);
			btnBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnBack.setBackground(new Color(220, 38, 38));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnBack.setBackground(new Color(239, 68, 68));
				}
			});
			btnBack.addActionListener(e -> {
				panel_ChiTietBan.setVisible(true);
				scrollPaneMenu.setVisible(false);
				panel_Ban.setVisible(true);
			});

			updateMenuDisplay(tenBan, danhSachMonAn);
		} else {
			updateMenuDisplay(tenBan, monAn_DAO.getAllMonAns());
		}

		panel_Ban.setVisible(false);
		scrollPaneMenu.setVisible(true);
	}

	private void updateMenuDisplay(String tenBan, List<MonAn> danhSachMonAn) {
		Component[] components = panelMenu.getComponents();
		for (Component comp : components) {
			if (!(comp instanceof JLabel && ((JLabel) comp).getText().equals("DANH SÁCH MÓN ĂN"))
					&& !(comp instanceof JComboBox)
					&& !(comp instanceof JLabel && ((JLabel) comp).getText().equals("LOẠI MÓN:"))
					&& !(comp instanceof JButton)) {
				panelMenu.remove(comp);
			}
		}

		String selectedLoaiMon = (String) comboLoaiMon.getSelectedItem();
		List<MonAn> filteredMonAn = selectedLoaiMon == null || selectedLoaiMon.equals("Tất cả") ? danhSachMonAn
				: danhSachMonAn.stream()
						.filter(mon -> mon.getLoaiMonAn() != null && mon.getLoaiMonAn().equals(selectedLoaiMon))
						.collect(Collectors.toList());

		int x = 50, y = 100;
		int itemsPerRow = 4;
		int itemHeight = 260;
		int panelWidth = 1032;

		for (MonAn monAn : filteredMonAn) {
			RoundedPanelWithShadow dishPanel = new RoundedPanelWithShadow(30, Color.WHITE, 5, 3,
					new Color(0, 0, 0, 30));
			dishPanel.setBounds(x, y, 180, 250);
			dishPanel.setLayout(null);

			JLabel lblImage;
			try {
				String linkIMG = monAn.getLinkIMG();
				if (linkIMG != null && !linkIMG.isEmpty()) {
					ImageIcon originalIcon = new ImageIcon(getClass().getResource(linkIMG));
					Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
					lblImage = new JLabel(new ImageIcon(scaledImage));
				} else {
					throw new Exception("Hình ảnh không có");
				}
			} catch (Exception e) {
				lblImage = new JLabel("No Image", SwingConstants.CENTER);
				lblImage.setFont(new Font("Roboto", Font.PLAIN, 12));
			}
			lblImage.setBounds(40, 10, 100, 100);
			dishPanel.add(lblImage);

			JLabel lblName = new JLabel(monAn.getTenMonAn());
			lblName.setBounds(0, 120, 180, 30);
			lblName.setFont(new Font("Roboto", Font.BOLD, 16));
			lblName.setForeground(new Color(17, 24, 39));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			dishPanel.add(lblName);

			JLabel lblPrice = new JLabel(String.format("%.0f VNĐ", monAn.getGia()));
			lblPrice.setBounds(0, 150, 180, 20);
			lblPrice.setFont(new Font("Roboto", Font.PLAIN, 14));
			lblPrice.setForeground(new Color(55, 65, 81));
			lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
			dishPanel.add(lblPrice);

			JTextField txtQuantity = new JTextField("1");
			txtQuantity.setBounds(40, 180, 100, 25);
			txtQuantity.setFont(new Font("Roboto", Font.PLAIN, 14));
			txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
			dishPanel.add(txtQuantity);

			JButton btnSelect = new JButton("CHỌN");
			btnSelect.setBounds(40, 210, 100, 30);
			btnSelect.setFont(new Font("Roboto", Font.BOLD, 12));
			btnSelect.setBackground(new Color(16, 185, 129));
			btnSelect.setForeground(Color.WHITE);
			btnSelect.setFocusPainted(false);
			btnSelect.setBorderPainted(false);
			btnSelect.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnSelect.setBackground(new Color(5, 150, 105));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnSelect.setBackground(new Color(16, 185, 129));
				}
			});
			btnSelect.addActionListener(e -> {
				try {
					int quantity = Integer.parseInt(txtQuantity.getText());
					if (quantity <= 0) {
						JOptionPane.showMessageDialog(panelTang1.this, "Số lượng phải lớn hơn 0!");
						return;
					}
					double price = monAn.getGia();
					double total = quantity * price;

					Ban_DAO = new DAO_Ban();
					String maBan = Ban_DAO.getMaBanByTenBan(tenBan);
					if (maBan == null) {
						JOptionPane.showMessageDialog(panelTang1.this, "Không tìm thấy mã bàn!");
						return;
					}

					String maKH = getMaKHFromPhieuDatBan(maBan);
					if (maKH == null) {
						JOptionPane.showMessageDialog(panelTang1.this, "Không tìm thấy khách hàng cho bàn này!");
						return;
					}

					hoaDon_DAO = new DAO_HoaDon();
					String maHD = hoaDon_DAO.getMaHDByMaKHAndStatus(maKH, "Chưa thanh toán");
					if (maHD == null) {
						LocalDateTime ngayLap = LocalDateTime.now();
						LocalDateTime ngayXuat = LocalDateTime.now();
						boolean isAdded = hoaDon_DAO.themHD("NV001", maKH, "PTTT01", "VAT01", "KM01", ngayLap, ngayXuat,
								"Chưa thanh toán");
						maHD = hoaDon_DAO.getMaHDByMaKHAndStatus(maKH, "Chưa thanh toán");
					}

					CTHD_DAO = new DAO_CTHoaDon();
					boolean isAddedCTHD = CTHD_DAO.themCTHD(maHD, monAn.getMaMonAn(), quantity, price);
					if (!isAddedCTHD) {
						JOptionPane.showMessageDialog(panelTang1.this, "Không thể thêm món vào hóa đơn!");
						return;
					}

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					boolean found = false;
					for (int i = 0; i < model.getRowCount(); i++) {
						if (model.getValueAt(i, 0).equals(monAn.getTenMonAn())) {
							int oldQuantity = (int) model.getValueAt(i, 1);
							model.setValueAt(oldQuantity + quantity, i, 1);
							model.setValueAt((oldQuantity + quantity) * price, i, 3);
							found = true;
							break;
						}
					}
					if (!found) {
						model.addRow(new Object[] { monAn.getTenMonAn(), quantity, price, total });
					}

					Ban_DAO.capNhatTrangThaiBan(tenBan, "ĐANG SỬ DỤNG");
					refreshBanList();
					btnHuyBan.setEnabled(false);
					btnHuyBan.setFocusPainted(false);
					btnHuyBan.setBorderPainted(false);

					updateTotal();
					JOptionPane.showMessageDialog(panelTang1.this,
							"Đã thêm " + quantity + " " + monAn.getTenMonAn() + " vào danh sách!");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(panelTang1.this, "Vui lòng nhập số lượng hợp lệ!");
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(panelTang1.this, "Lỗi cơ sở dữ liệu: " + ex.getMessage());
				}
			});
			dishPanel.add(btnSelect);

			panelMenu.add(dishPanel);
			x += 200;
			if (x > 850) {
				x = 50;
				y += itemHeight;
			}
		}

		int rows = (int) Math.ceil((double) filteredMonAn.size() / itemsPerRow);
		int contentHeight = 100 + rows * itemHeight;
		int btnHeight = 40;
		int padding = 20;
		int panelHeight = contentHeight + btnHeight + padding * 2;
		int btnX = (panelWidth - 150) / 2;
		for (Component comp : panelMenu.getComponents()) {
			if (comp instanceof JButton) {
				comp.setBounds(btnX, contentHeight + padding, 150, btnHeight);
				break;
			}
		}

		panelMenu.setPreferredSize(new Dimension(panelWidth, panelHeight));
		panelMenu.revalidate();
		panelMenu.repaint();
	}

	private String getMaKHFromPhieuDatBan(String maBan) throws SQLException {
		String maKH = null;
		String query = "SELECT TOP 1 MaKhachHang FROM PhieuDatBan WHERE MaBan = ? ORDER BY MaPDB DESC";
		try (Connection conn = new BaseDAO().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, maBan);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				maKH = rs.getString("MaKhachHang");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maKH;
	}

	private void updateTotal() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		double tongCong = 0;
		for (int i = 0; i < model.getRowCount(); i++) {
			tongCong += (double) model.getValueAt(i, 3);
		}
		txt_tongCong.setText(String.format("%.0f", tongCong));

		double vatPercentage = 0.08; // Fixed 8% VAT
		double vat = tongCong * vatPercentage;
		txt_VAT.setText(String.format("%.0f", vat));

		double khuyenMai = 0;
		String khuyenMaiText = (String) comboKM.getSelectedItem();
		if (!khuyenMaiText.equals("Không có")) {
			khuyenMai = Double.parseDouble(khuyenMaiText.replace("%", "")) / 100;
		}

		double tienCanTra = (tongCong + vat) * (1 - khuyenMai);
		txt_tienCanTra.setText(String.format("%.0f", tienCanTra));
	}

	private static class RoundedPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private final int arc;

		public RoundedPanel(int arc) {
			this.arc = arc;
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(getBackground());
			g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc));
			g2.dispose();
			super.paintComponent(g);
		}
	}

	private static class RoundedPanelWithShadow extends JPanel {
		private static final long serialVersionUID = 1L;
		private final int arc;
		private final int shadowSize;
		private final int shadowOffset;
		private final Color shadowColor;

		public RoundedPanelWithShadow(int arc, Color bgColor, int shadowSize, int shadowOffset, Color shadowColor) {
			this.arc = arc;
			this.shadowSize = shadowSize;
			this.shadowOffset = shadowOffset;
			this.shadowColor = shadowColor;
			setOpaque(false);
			setBackground(bgColor);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(shadowColor);
			g2.fill(new RoundRectangle2D.Double(shadowOffset, shadowOffset, getWidth() - shadowSize,
					getHeight() - shadowSize, arc, arc));
			g2.setColor(getBackground());
			g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - shadowSize, getHeight() - shadowSize, arc, arc));
			g2.dispose();
		}
	}
}