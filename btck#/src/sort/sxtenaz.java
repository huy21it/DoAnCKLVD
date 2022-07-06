package sort;

import java.util.Comparator;

import ATM.KhachHang;

public class sxtenaz implements Comparator<KhachHang> {

	@Override
	public int compare(KhachHang o1, KhachHang o2) {
		return o1.getHoTen().compareTo(o2.getHoTen());
	}

}
