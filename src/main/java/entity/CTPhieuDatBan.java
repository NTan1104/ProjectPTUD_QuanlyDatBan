package entity;

import java.time.LocalDateTime;

public class CTPhieuDatBan {
    private String maCTPDB;
    private LocalDateTime timeNhanBan;
    private LocalDateTime timeTraBan;
    private int soNguoi;
	public String getMaCTPDB() {
		return maCTPDB;
	}
	public void setMaCTPDB(String maCTPDB) {
		this.maCTPDB = maCTPDB;
	}
	public LocalDateTime getTimeNhanBan() {
		return timeNhanBan;
	}
	public void setTimeNhanBan(LocalDateTime timeNhanBan) {
		this.timeNhanBan = timeNhanBan;
	}
	public LocalDateTime getTimeTraBan() {
		return timeTraBan;
	}
	public void setTimeTraBan(LocalDateTime timeTraBan) {
		this.timeTraBan = timeTraBan;
	}
	public int getSoNguoi() {
		return soNguoi;
	}
	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}
    
}
