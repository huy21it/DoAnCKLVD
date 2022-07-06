package ATM;

import java.io.Serializable;
import java.util.Objects;

public class KhachHang {
	private String maKh;
	private String hoTen;
	private String stk;
	private int soDu;
	private String sdt;
	private String pin;
	
//	public KhachHang() {
//		
//	}
//	
	
	
	

	public KhachHang(String stk, String pin) {
		this.stk = stk;
		this.pin = pin;
	}

	




	public KhachHang(String maKh, String hoTen, String stk, int soDu, String sdt, String pin) {
		this.maKh = maKh;
		this.hoTen = hoTen;
		this.stk = stk;
		this.soDu = soDu;
		this.sdt = sdt;
		this.pin = pin;
	}

	public String getMaKh() {
		return maKh;
	}

	public void setMaKh(String maKh) {
		this.maKh = maKh;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getStk() {
		return stk;
	}

	public void setStk(String stk) {
		this.stk = stk;
	}

	public int getSoDu() {
		return soDu;
	}

	public void setSoDu(int soDu) {
		this.soDu = soDu;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}





	@Override
	public int hashCode() {
		return Objects.hash(pin, stk);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(pin, other.pin) && Objects.equals(stk, other.stk);
	}





	@Override
	public String toString() {
		return "KhachHang [maKh=" + maKh + ", hoTen=" + hoTen + ", stk=" + stk + ", soDu=" + soDu + ", sdt=" + sdt
				+ ", pin=" + pin + "]";
	}
	
	


}
	