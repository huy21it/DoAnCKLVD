package ATM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ChucNang extends JFrame {

	private MayAtm mayAtm;
	private JPanel contentPane;
	private JButton btnRutTien;
	private JButton btnKiemTraSoDu;
	private JButton btnChuyenKhoan;
	private JButton btnNapTien;
	private JButton btnThoat;
	
	ArrayList<QLDN> list = new ArrayList<QLDN>();
	
    
	
	


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChucNang frame = new ChucNang();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public ChucNang(ArrayList<QLDN> list) {
		this.list = list;
		init();
		this.setLocationRelativeTo(null);
	}
	
	public void init() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 473);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(176, 224, 230));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(ChucNang.class.getResource("/images/card.png")));
		lblNewLabel.setBounds(0, 0, 458, 176);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("KÍNH CHÀO QUÝ KHÁCH!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 187, 205, 27);
		contentPane.add(lblNewLabel_1);
		
		btnRutTien = new JButton("Rút tiền");
		btnRutTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRutTienActionPerformed(e);
			}
		});
		btnRutTien.setIcon(new ImageIcon(ChucNang.class.getResource("/images/ruttien.png")));
		btnRutTien.setBounds(48, 249, 145, 23);
		contentPane.add(btnRutTien);
		
		btnKiemTraSoDu = new JButton("Kiểm tra số dư");
		btnKiemTraSoDu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnKiemTraSoDuActionPerformed(e);
			}
		});
		btnKiemTraSoDu.setHorizontalAlignment(SwingConstants.LEFT);
		btnKiemTraSoDu.setIcon(new ImageIcon(ChucNang.class.getResource("/images/kiemtrasodu.png")));
		btnKiemTraSoDu.setBounds(48, 318, 145, 23);
		contentPane.add(btnKiemTraSoDu);
		
		btnChuyenKhoan = new JButton("Chuyển khoản");
		btnChuyenKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnChuyenKhoanActionPerformed(e);
			}
		});
		btnChuyenKhoan.setIcon(new ImageIcon(ChucNang.class.getResource("/images/chuyen.png")));
		btnChuyenKhoan.setBounds(257, 249, 145, 23);
		contentPane.add(btnChuyenKhoan);
		
		btnNapTien = new JButton("Nạp tiền");
		btnNapTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNapTienActionPerformed(e);
			}
		});
		btnNapTien.setIcon(new ImageIcon(ChucNang.class.getResource("/images/nap.png")));
		btnNapTien.setBounds(257, 318, 145, 23);
		contentPane.add(btnNapTien);
		
		btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnThoatActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThoat.setIcon(new ImageIcon(ChucNang.class.getResource("/images/thoat.png")));
		btnThoat.setBounds(163, 378, 110, 23);
		contentPane.add(btnThoat);
	}
	
	private void btnRutTienActionPerformed(ActionEvent e) {
		rutTien rutTien = new rutTien(this.list);
		rutTien.setVisible(true);
		
	}
	
	private void btnChuyenKhoanActionPerformed(ActionEvent e) {
		new ChuyenKhoan(this.list).setVisible(true);
		
	}
	
	private void btnKiemTraSoDuActionPerformed(ActionEvent e) {
		new KiemTraSoDu(this.list).setVisible(true);
	}
	
	private void btnNapTienActionPerformed(ActionEvent e) {
		new NapTien(this.list).setVisible(true);
		
	}
	
	private void btnThoatActionPerformed(ActionEvent e) throws ClassNotFoundException {
		new MayAtm(this.list).setVisible(true);
		this.dispose();	
		
	}
	
}
