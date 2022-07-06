package sort;

import java.util.Comparator;

import ATM.KhachHang;

public class sxtenza implements Comparator<KhachHang>{

	@Override
	public int compare(KhachHang o1, KhachHang o2) {
		return o2.getHoTen().compareTo(o1.getHoTen());
	}

}
