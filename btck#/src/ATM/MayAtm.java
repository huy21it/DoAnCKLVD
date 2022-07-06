package ATM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MayAtm extends JFrame {

	static List<KhachHang> khachHangs = new ArrayList<KhachHang>();
	
	private JPanel contentPane;
	private JPasswordField txtMaPin;
	private JButton btnQuanLy;
	private JButton btnDangNhap;
	private ChucNang chucNang;
	JTextField txtSoTaiKhoan;
	
	public KhachHang khachHang;
	//private RutTien rutTien;
	
	public KhachHang khachHang123;
	
	 ArrayList<QLDN> list;
	
	QLDN dn = new QLDN();
	
	//public int maPin;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
//				try {
//					MayAtm frame = new MayAtm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
		try {
			new quanLy();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
//	public MayAtm() {
//		init();
//		setLocationRelativeTo(null);
//	}
	
	public MayAtm(ArrayList<QLDN> list) throws ClassNotFoundException  {
		this.list = list;
		//this();
//		QuanLyKhachHang quanLyKhachHang = new QuanLyKhachHang(this.list);
//		quanLyKhachHang.readStudentsUsingPrepareStatement();
		init();
		setLocationRelativeTo(null);
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 677);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MayAtm.class.getResource("/images/atm1.png")));
		lblNewLabel.setBounds(364, 289, 256, 256);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MayAtm.class.getResource("/images/app-MB-bank-1.jpg")));
		lblNewLabel_1.setBounds(0, 0, 608, 193);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(0, 299, 405, 233);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("NHẬP SỐ TÀI KHOẢN:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 23, 215, 35);
		panel.add(lblNewLabel_2);
		
		txtSoTaiKhoan = new JTextField();
		txtSoTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoTaiKhoan.setBounds(10, 69, 306, 34);
		panel.add(txtSoTaiKhoan);
		txtSoTaiKhoan.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("MÃ PIN:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 114, 193, 35);
		panel.add(lblNewLabel_3);
		
		txtMaPin = new JPasswordField();
		txtMaPin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaPin.setBounds(10, 160, 306, 35);
		panel.add(txtMaPin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.YELLOW);
		panel_1.setBounds(0, 534, 620, 123);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnDangNhap = new JButton("ĐĂNG NHẬP");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnDangNhapActionPerformed(e, list);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDangNhap.setHorizontalAlignment(SwingConstants.LEFT);
		btnDangNhap.setIcon(new ImageIcon(MayAtm.class.getResource("/images/login.png")));
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDangNhap.setBounds(112, 47, 167, 31);
		panel_1.add(btnDangNhap);
		
		btnQuanLy = new JButton("QUẢN LÝ");
		btnQuanLy.setHorizontalAlignment(SwingConstants.LEFT);
		btnQuanLy.setIcon(new ImageIcon(MayAtm.class.getResource("/images/quanly.png")));
		btnQuanLy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnQuanLyActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnQuanLy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuanLy.setBounds(373, 47, 143, 31);
		panel_1.add(btnQuanLy);
	}
	
	
	private void btnDangNhapActionPerformed(ActionEvent e, ArrayList<QLDN> list) throws ClassNotFoundException {
		QuanLyKhachHang quanLyKhachHang = new QuanLyKhachHang(this.list);
		quanLyKhachHang.readStudentsUsingPrepareStatement();
		var stk = txtSoTaiKhoan.getText();
		var pin = new String(txtMaPin.getPassword());
		khachHang123 = new KhachHang(stk,pin);
//		maPin = Integer.parseInt(khachHang123.getPin());
//		System.out.println(maPin);
		if(khachHangs != null && khachHangs.contains(khachHang123)) {
			AddTaiKhoan();
//			dn.setStk(txtSoTaiKhoan.getText());
//			dn.setPin(new String(txtMaPin.getPassword()));
//			list.add(dn);
//			for (QLDN qldn : list) {
//				System.out.println(qldn.getStk());
//			}
			//setTxtSoTaiKhoan(txtSoTaiKhoan);
			JOptionPane.showMessageDialog(rootPane,"Đăng nhập thành công ");
			new ChucNang(this.list).setVisible(true);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(rootPane,"Số tài khoản hoặc mã pin không chính xác ");
		}	
	}	
	
	public void AddTaiKhoan() {
		dn.setStk(txtSoTaiKhoan.getText());
		dn.setPin(new String(txtMaPin.getPassword()));
		list.add(dn);
	}
	
//	public JTextField getTxtSoTaiKhoan() {
//		return txtSoTaiKhoan;
//	}
//
//	public void setTxtSoTaiKhoan(JTextField txtSoTaiKhoan) {
//		this.txtSoTaiKhoan = txtSoTaiKhoan;
//	}
	

	private void btnQuanLyActionPerformed(ActionEvent e) throws ClassNotFoundException {
		QuanLyKhachHang quanLyKhachHang = new QuanLyKhachHang(this.list);
		quanLyKhachHang.readStudentsUsingPrepareStatement();
		quanLyKhachHang.setVisible(true);
		this.setVisible(false);
	}

	public static void setKachHang(KhachHang khachHang) {
		khachHangs.add(khachHang);
	}	
}
