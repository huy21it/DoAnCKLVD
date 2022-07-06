package ATM;

import java.util.ArrayList;
import java.util.List;

public class quanLy {
	
	private ArrayList<QLDN> list ;
	
	public quanLy() throws ClassNotFoundException {
		list = new ArrayList<QLDN>();
		new MayAtm(this.list).setVisible(true);
	}
}
