package ATM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;


//import ghidlvao.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;

import sort.sxsodutangdan;
import sort.sxsodugiamdan;
import sort.sxtenaz;
import sort.sxtenza;

public class QuanLyKhachHang extends JFrame {
	
	private static final String USER = "sa"; 
    private static final String PASSWORD = "sa"; 
    private static final String SERVER_NAME = "LAPTOP-V9UUUO1D\\SQLEXPRESS"; 
    private static final String DB_NAME = "quanLyKhachHang"; 
    private static final int PORT = 1433; 

	//List<KhachHang> khachHangs = new ArrayList<KhachHang>();
	private enum ActionStane {
		ADD,EDIT
	}
	private ActionStane actionStane;
	
	private MayAtm mayAtm;
	private JPanel contentPane;
	private JTextField txtTen;
	private JTextField txtStk;
	private JTextField txtSodu;
	private JTextField txtSDT;
	private JTextField txtPin;
	private JTable table;
	
	private DefaultTableModel model;
	private JTextField txtMa;
	private int chonHang;

	private JButton btnThem;
    
	
	private Connection con;
	private java.sql.Statement stmt;
	private ArrayList<QLDN> list;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JRadioButton sxtenaz;
	private JRadioButton sxtenza;
	private JRadioButton sxsodulondan;
	private JRadioButton sxsodugiamdan;
	private JRadioButton rdbtnNewRadioButton_4;
	private JRadioButton rdbtnNewRadioButton_5;
	private ButtonGroup buttonGroupSort;
	private ButtonGroup buttonGroupSearch;
	
	private sxsodutangdan sxsdtd;
	private sxsodugiamdan sxsdgd;
	private sxtenaz sxtaz;
	private sxtenza sxtza;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					QuanLyKhachHang frame = new QuanLyKhachHang();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	


	
	 public void readStudentsUsingPrepareStatement() {
			SQLServerDataSource ds = confiDataSource();
			try(var conn = ds.getConnection()) {
				var sql = "SELECT * FROM dbo.khachHang";
				var ps = conn.prepareStatement(sql);
				// lay du lieu ra
				ResultSet resultSet = ps.executeQuery();
				// neu no co dong du lieu ke tiep thi chung ta moi thuc hien
				while (resultSet.next()) {
					var maKh = resultSet.getString(1);
					var hoTen = resultSet.getString(2);
					var stk = resultSet.getString(3);
					var soDu = resultSet.getInt(4);
					var sdt = resultSet.getString(5);
					var pin = resultSet.getString(6);
					var khachHang = new KhachHang(maKh, hoTen, stk,soDu, sdt, pin);
					MayAtm.setKachHang(khachHang);
					var row = new Object[] {
							khachHang.getMaKh(), khachHang.getHoTen(), khachHang.getStk(), khachHang.getSoDu(), khachHang.getSdt(), khachHang.getPin()
					};
					try {
						model.addRow(row);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			} catch (SQLServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    }
	
	private void insertDataUsingPrepareStatement(KhachHang khachHang) {
    	SQLServerDataSource dataSource = confiDataSource();
		try(var conn = dataSource.getConnection()) {
			var sql = "INSERT INTO dbo.khachHang(maKh,hoTen,stk,soDu, sdt,pin)"+"VALUES(?,?,?,?,?,?)";
			var ps = conn.prepareStatement(sql);
			ps.setString(1,khachHang.getMaKh());
			ps.setString(2,khachHang.getHoTen());
			ps.setString(3, khachHang.getStk());
			ps.setInt(4,khachHang.getSoDu());
			ps.setString(5,khachHang.getSdt());
			ps.setString(6,khachHang.getPin());
			// làm tahy đổi thì phải sd update
			ps.executeUpdate();
			MayAtm.setKachHang(khachHang);
			var row = new Object[] {
					khachHang.getMaKh(), khachHang.getHoTen(), khachHang.getStk(), khachHang.getSoDu(), khachHang.getSdt(), khachHang.getPin()
			};
				model.addRow(row);
		} catch (SQLServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//	public QuanLyKhachHang() throws ClassNotFoundException {
//	public QuanLyKhachHang(MayAtm mayAtm) throws ClassNotFoundException {
//		this();
//		this.mayAtm = mayAtm;
//		model = (DefaultTableModel)table.getModel();
//		chonHang = -1;
//		actionStane = ActionStane.ADD;
//	}

//	public QuanLyKhachHang() throws ClassNotFoundException {
//		init();
//		setLocationRelativeTo(null);
////		model = (DefaultTableModel)table.getModel();
////		chonHang = -1;
////		actionStane = ActionStane.ADD;
//	}
	
	public QuanLyKhachHang(ArrayList<QLDN> list) throws ClassNotFoundException {
		init();
		setLocationRelativeTo(null);
		addButtonGroup();
		this.list = list;
		model = (DefaultTableModel)table.getModel();
		chonHang = -1;
		actionStane = ActionStane.ADD;
		
	}
	
	private void addButtonGroup() {
		buttonGroupSort.add(sxtenaz);
		buttonGroupSort.add(sxtenza);
		buttonGroupSort.add(sxsodulondan);
		buttonGroupSort.add(sxsodugiamdan);
		
		buttonGroupSearch.add(rdbtnNewRadioButton_4);
		buttonGroupSearch.add(rdbtnNewRadioButton_5);
    }

	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1085, 716);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 1071, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ tên:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(22, 102, 68, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("STK:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(22, 147, 49, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Số dư:");
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(22, 190, 59, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("SĐT:");
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(22, 235, 59, 22);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("PIN:");
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(22, 275, 49, 22);
		contentPane.add(lblNewLabel_5);
		
		txtTen = new JTextField();
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTen.setBounds(155, 108, 235, 20);
		contentPane.add(txtTen);
		txtTen.setColumns(10);
		
		txtStk = new JTextField();
		txtStk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtStk.setBounds(155, 149, 235, 20);
		contentPane.add(txtStk);
		txtStk.setColumns(10);
		
		txtSodu = new JTextField();
		txtSodu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSodu.setBounds(155, 192, 235, 20);
		contentPane.add(txtSodu);
		txtSodu.setColumns(10);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSDT.setBounds(155, 237, 235, 20);
		contentPane.add(txtSDT);
		txtSDT.setColumns(10);
		
		txtPin = new JTextField();
		txtPin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPin.setBounds(155, 277, 235, 20);
		contentPane.add(txtPin);
		txtPin.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 323, 1071, 295);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel();
		Object[] column = {"Mã KHÁCH HÀNG","TÊN KHÁCH HÀNG","SỐ TK", "SỐ DƯ", "SỐ ĐIỆN THOẠI", "PIN"};
		model.setColumnIdentifiers(column);
		table.setModel(model);		
		scrollPane.setViewportView(table);
		
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnThemActionPerformed(e);
			}
		});
		btnThem.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnThem.setBounds(248, 639, 105, 29);
		contentPane.add(btnThem);
		
		JButton btnSua = new JButton("Sửa ");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnSuaActionPerformed(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSua.setBounds(468, 639, 105, 29);
		contentPane.add(btnSua);
		
		JButton btnXoa = new JButton("Xoá");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXoaActionPerformed(e);
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoa.setBounds(678, 639, 105, 29);
		contentPane.add(btnXoa);
		
		JButton btnThoat = new JButton("Thoát");
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
		
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(884, 639, 104, 29);
		contentPane.add(btnThoat);
		
		JLabel lblNewLabel_6 = new JLabel("Mã khách hàng:");
		lblNewLabel_6.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(22, 69, 122, 20);
		contentPane.add(lblNewLabel_6);
		
		txtMa = new JTextField();
		txtMa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMa.setBounds(154, 69, 235, 20);
		contentPane.add(txtMa);
		txtMa.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "s\u1EAFp x\u1EBFp kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(428, 67, 261, 230);
		contentPane.add(panel);
		panel.setLayout(null);
		
		sxtenaz = new JRadioButton("Theo tên từ a-z");
		sxtenaz.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sxtenaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sxtenazActionPerformed(e);
			}
		});
		sxtenaz.setBounds(46, 36, 155, 23);
		panel.add(sxtenaz);
		
		sxtenza = new JRadioButton("Theo tên từ z-a");
		sxtenza.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sxtenza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sxtenzaActionPerformed(e);
			}
		});
		sxtenza.setBounds(46, 84, 155, 23);
		panel.add(sxtenza);
		
		sxsodulondan = new JRadioButton("Theo số dư lớn dần");
		sxsodulondan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sxsodulondan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sxsodulondanActionPerformed(e);
				
			}
		});
		sxsodulondan.setBounds(46, 131, 155, 23);
		panel.add(sxsodulondan);
		
		sxsodugiamdan = new JRadioButton("Theo số dư giảm dần");
		sxsodugiamdan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sxsodugiamdan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sxsodugiamdanActionPerformed(e);
			}
		});
		sxsodugiamdan.setBounds(46, 178, 173, 23);
		panel.add(sxsodugiamdan);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(699, 69, 362, 228);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		rdbtnNewRadioButton_4 = new JRadioButton("Theo tên gần đúng");
		rdbtnNewRadioButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNewRadioButton_4.setBounds(19, 51, 145, 23);
		panel_1.add(rdbtnNewRadioButton_4);
		
		rdbtnNewRadioButton_5 = new JRadioButton("Theo số dư");
		rdbtnNewRadioButton_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNewRadioButton_5.setBounds(19, 125, 111, 23);
		panel_1.add(rdbtnNewRadioButton_5);
		
		buttonGroupSort = new ButtonGroup();
		buttonGroupSearch = new ButtonGroup();
		
		
		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButtonActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(147, 180, 89, 23);
		panel_1.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(170, 52, 142, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Từ:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(136, 129, 29, 14);
		panel_1.add(lblNewLabel_7);
		
		textField_1 = new JTextField();
		textField_1.setBounds(169, 128, 47, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("đến:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(226, 129, 29, 14);
		panel_1.add(lblNewLabel_8);
		
		textField_2 = new JTextField();
		textField_2.setBounds(265, 128, 47, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Làm mới");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1ActionPerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(55, 639, 89, 28);
		contentPane.add(btnNewButton_1);
			
	}
	
	//Thêm
	private void btnThemActionPerformed(ActionEvent e) {
//		themTenKhoan();
		
//		var Ma = txtMa.getText();
//		var hoTen = txtTen.getText();
//		var stk = txtStk.getText();
//		int soDu = Integer.parseInt(txtSodu.getText());
//		var sdt = txtSDT.getText();
//		var pin = txtPin.getText();
//		if(!Ma.isEmpty() && !hoTen.isEmpty() && !stk.isEmpty()  && !sdt.isEmpty() ) {
//			var khachHang = new KhachHang(Ma,hoTen,stk,soDu,sdt,pin);
//			if(actionStane == ActionStane.ADD) {
//				if(mayAtm.khachHangs.contains(khachHang)) {
//					JOptionPane.showMessageDialog(rootPane,
//				    		"Khách hàng mã \""	 + Ma + "\" đã tồn tại!");
//				} else {
//					themTenKhoan();
//					mayAtm.setKachHang(khachHang);
//					
//					//JOptionPane.showMessageDialog(rootPane,
//				    //          "Thêm khách hàng thành công!");
//					var row = new Object[] {
//						khachHang.getMaKh(), khachHang.getHoTen(), khachHang.getStk(), khachHang.getSoDu(), khachHang.getSdt(), khachHang.getPin()
//					};
//					model.addRow(row);
//				}
//			} else if(actionStane == ActionStane.EDIT) {
//				model.removeRow(chonHang);
//				var row = new Object[] {
//						khachHang.getMaKh(), khachHang.getHoTen(), khachHang.getStk(), khachHang.getSoDu(), khachHang.getSdt(), khachHang.getPin()
//				};
//				JOptionPane.showMessageDialog(rootPane,"Cập nhật dữ liệu thành công!");
//				model.insertRow(chonHang, row);
//				actionStane = ActionStane.ADD;
//				btnThem.setText("Thêm");
//				
//			}
//			txtMa.setText("");
//			txtTen.setText("");
//			txtStk.setText("");
//			txtSodu.setText("");
//			txtSDT.setText("");
//			txtPin.setText("");
//		} else {
//			JOptionPane.showMessageDialog(rootPane,"Các ô nhập liệu không được để trống!");
//		}
		
		var Ma = txtMa.getText();
		var hoTen = txtTen.getText();
		var stk = txtStk.getText();
		int soDu = Integer.parseInt(txtSodu.getText());
		var sdt = txtSDT.getText();
		var pin = txtPin.getText();
		if(!Ma.isEmpty() && !hoTen.isEmpty() && !stk.isEmpty()  && !sdt.isEmpty() ) {
			var khachHang = new KhachHang(Ma,hoTen,stk,soDu,sdt,pin);
			if (actionStane == ActionStane.ADD) {
					if(mayAtm.khachHangs.contains(khachHang)) {
					JOptionPane.showMessageDialog(rootPane,
				    		"Khách hàng mã \""	 + Ma + "\" đã tồn tại!");
				} else {
//					var khachHang = new KhachHang(Ma,hoTen,stk,soDu,sdt,pin);
					//mayAtm.setKachHang(khachHang);
					insertDataUsingPrepareStatement(khachHang);
//					var row = new Object[] {
//							khachHang.getMaKh(), khachHang.getHoTen(), khachHang.getStk(), khachHang.getSoDu(), khachHang.getSdt(), khachHang.getPin()
//					};
//					model.addRow(row);
					JOptionPane.showMessageDialog(rootPane,
						              "Thêm khách hàng thành công!");
//					model.addRow(row);
//					txtMa.setText("");
//					txtTen.setText("");
//					txtStk.setText("");
//				    txtSodu.setText("");
//					txtSDT.setText("");
//					txtPin.setText("");
				}
	     		
		
			} else if(actionStane == ActionStane.EDIT) {
				var khachHang1 = mayAtm.khachHangs.get(chonHang);
				mayAtm.khachHangs.set(chonHang, khachHang);
//				mayAtm.khachHangs.remove(khachHang1);
				model.removeRow(chonHang);
				
				var row = new Object[] {
						khachHang.getMaKh(), khachHang.getHoTen(), khachHang.getStk(), khachHang.getSoDu(), khachHang.getSdt(), khachHang.getPin()
				};
				model.insertRow(chonHang, row);
				JOptionPane.showMessageDialog(rootPane,
			              "Cập nhật khách hàng thành công!");
				mayAtm.setKachHang(khachHang);
				SQLServerDataSource ds = confiDataSource();
				try(var conn = ds.getConnection()) {
					var sql = "UPDATE dbo.khachHang SET maKh = '" + Ma  + "',"
				         + "hoTen = " + "'"+ hoTen + "'"+ ","
				         + "stk = " + "'"+ stk + "'"+ ","
				         + "soDu = " + "'"+ soDu + "'"+ ","
				         + "sdt = " + "'"+ sdt + "'"+ ","
				         + "pin = " + "'"+ pin + "'"
				         + "Where maKh = " + "'" + khachHang1.getMaKh() + "'";
					
					var statement = conn.createStatement();
					statement.executeUpdate(sql);
					
					btnThem.setText("Thêm");
					actionStane = ActionStane.ADD;
				} catch (SQLServerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			txtMa.setText("");
			txtTen.setText("");
			txtStk.setText("");
		    txtSodu.setText("");
			txtSDT.setText("");
			txtPin.setText("");
		} else { 
			JOptionPane.showMessageDialog(rootPane,"Các ô nhập liệu không được để trống!");			
		}
	}

	// Sửa
	private void btnSuaActionPerformed(ActionEvent e) throws SQLServerException, SQLException {
//		if(mayAtm.khachHangs.size() > 0) {
//			chonHang = table.getSelectedRow();
//			if(chonHang != -1) {
//				var khachHang = mayAtm.khachHangs.get(chonHang);
//				txtMa.setText(khachHang.getMaKh());
//				txtTen.setText(khachHang.getHoTen());
//				txtStk.setText(khachHang.getStk());
//				txtSodu.setText(String.valueOf(khachHang.getSoDu()));
//				txtSDT.setText(khachHang.getSdt());
//				txtPin.setText(khachHang.getPin());
//				
//				btnThem.setText("Cập nhật");
//				
//				actionStane = ActionStane.EDIT;
//			}
//		} else {
//			JOptionPane.showMessageDialog(rootPane,"Chức năng này hiện chưa khả dụng!");
//		}
		
		chonHang = table.getSelectedRow();
		if (chonHang == -1) {
			JOptionPane.showMessageDialog(rootPane,"Vui lòng chọn một bản ghi để sửa!");
		} else {
			var khachHang1 = mayAtm.khachHangs.get(chonHang);
		txtMa.setText(khachHang1.getMaKh());
		txtTen.setText(khachHang1.getHoTen());
		txtStk.setText(khachHang1.getStk());
		txtSodu.setText(String.valueOf(khachHang1.getSoDu()));
		txtSDT.setText(khachHang1.getSdt());
		txtPin.setText(khachHang1.getPin());
		
		btnThem.setText("Cập nhật");
		
		actionStane = ActionStane.EDIT;
		}
		
		
//		var Ma = txtMa.getText();
//		var hoTen = txtTen.getText();
//		var stk = txtStk.getText();
//		int soDu = Integer.parseInt(txtSodu.getText());
//		var sdt = txtSDT.getText();
//		var pin = txtPin.getText();
//		
//		SQLServerDataSource ds = confiDataSource();
//		try(var conn = ds.getConnection()) {
//			var sql = "UPDATE dbo.khachHang SET maKh = "+ "'"+ Ma + "'"+ "," 
//		         + "hoTen = " + "'"+ hoTen + "'"+ ","
//		         + "stk = " + "'"+ stk + "'"+ ","
//		         + "soDu = " + "'"+ soDu + "'"+ ","
//		         + "sdt = " + "'"+ sdt + "'"+ ","
//		         + "pin = " + "'"+ pin + "'"
//		         + "Where maKh = " + "'" + khachHang.getMaKh() + "'";
//			
//			var statement = conn.createStatement();
//			statement.executeUpdate(sql);
//
//			// neu no co dong du lieu ke tiep thi chung ta moi thuc hien
//			
//			}
			
		
	}
	
	private void btnNewButton_1ActionPerformed(ActionEvent e) {
		buttonGroupSearch.clearSelection();
		buttonGroupSort.clearSelection();
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		txtMa.setText("");
		txtPin.setText("");
		txtSDT.setText("");
		txtSodu.setText("");
		txtStk.setText("");
		txtTen.setText("");
		model.setRowCount(0);
		mayAtm.khachHangs.clear();
		readStudentsUsingPrepareStatement();
	}

	// xoá
	private void btnXoaActionPerformed(ActionEvent e) {
		if(mayAtm.khachHangs.size() > 0) {
			chonHang = table.getSelectedRow();
			System.out.println(chonHang);
			var khachHang2 = mayAtm.khachHangs.get(chonHang);
			if(chonHang != -1) {	
				int choice = JOptionPane.showConfirmDialog(rootPane,
	                    "Bạn có chắc chắn muốn xoá bản ghi không");
				if(choice == JOptionPane.YES_OPTION) {
						SQLServerDataSource ds = confiDataSource();
						try(var conn = ds.getConnection()) {
							var sql = "DELETE dbo.khachHang "
						         + "Where maKh = " + "'" + khachHang2.getMaKh() + "'";
		
							var statement = conn.createStatement();
							statement.executeUpdate(sql);
							mayAtm.khachHangs.remove(chonHang);
							model.removeRow(chonHang);
							JOptionPane.showMessageDialog(rootPane, "Xoá bản ghi thành công");
						} catch (SQLServerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				} 
			} else {
				JOptionPane.showMessageDialog(rootPane,"Vui lòng chọn một hàng để xoá");
			}
		} else {
			JOptionPane.showMessageDialog(rootPane,"Chức năng này hiện chưa khả dụng!");
		}
		
	}
	
	// thoát
	private void btnThoatActionPerformed(ActionEvent e) throws ClassNotFoundException {
		new MayAtm(this.list).setVisible(true);
		this.dispose();	
	}
	
	private void sxtenazActionPerformed(ActionEvent e) {
		sxtenazActionPerformed(mayAtm.khachHangs);
		showKhachHang();
	}
	private void sxtenzaActionPerformed(ActionEvent e) {
		sxtenzaActionPerformed(mayAtm.khachHangs);
		showKhachHang();
	}
	
	private void sxsodulondanActionPerformed(ActionEvent e) {
		sxsodulondanActionPerformed(mayAtm.khachHangs);
	}
	
	private void sxsodugiamdanActionPerformed(ActionEvent e) {
		sxsodugiamdanActionPerformed(mayAtm.khachHangs);	
	}
	
	public void sxtenazActionPerformed(List<KhachHang> khachHangs) {
		Collections.sort(khachHangs, new sxtenaz());
		showKhachHang();
	}
	
	
	public void sxtenzaActionPerformed(List<KhachHang> khachHangs) {
		Collections.sort(khachHangs, new sxtenza());
	}
	
	public void sxsodulondanActionPerformed(List<KhachHang> khachHangs) {
		Collections.sort(khachHangs, new sxsodutangdan());
		showKhachHang();
	}
	
	public void sxsodugiamdanActionPerformed(List<KhachHang> khachHangs) {
		Collections.sort(khachHangs, new sxsodugiamdan());
		showKhachHang();
	}
	
	public void showKhachHang() {
		model.setRowCount(0); 
		for (KhachHang khachHang : mayAtm.khachHangs) {
			var row = new Object[] {
					khachHang.getMaKh(), khachHang.getHoTen(), khachHang.getStk(), khachHang.getSoDu(), khachHang.getSdt(), khachHang.getPin()
			};
			model.addRow(row);
		}
	}
	
	
	public void btnNewButtonActionPerformed(ActionEvent e) {	
		
		if (rdbtnNewRadioButton_4.isSelected()) {
			String key = textField.getText();
			if (key.isEmpty()) {
				JOptionPane.showMessageDialog(rootPane, "Nhập tên cần tìm kiếm!");
			} else {
				List<KhachHang> result = new ArrayList<>();
				var regex = ".*" + key + ".*";
				// khong phan biet chu hoa chua thuong
				Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE );
				Matcher matcher;
				for (KhachHang khachHang : mayAtm.khachHangs) {
					matcher = pattern.matcher(khachHang.getHoTen());
					if (matcher.matches()) {
						result.add(khachHang);
					}
				}
//				for (KhachHang khachHang : mayAtm.khachHangs) {
//					if(khachHang.getHoTen().contains(key)) {
//						result.add(khachHang);
//					}
//				}
				mayAtm.khachHangs.clear();
				mayAtm.khachHangs.addAll(result);
				model.setRowCount(0);
				for (KhachHang khachHang : mayAtm.khachHangs) {
					Object[] row = new Object[] {
							khachHang.getMaKh(), khachHang.getHoTen(),
							khachHang.getStk(),khachHang.getSoDu(),khachHang.getSdt(),khachHang.getPin()
						};
					model.addRow(row);
				}
				JOptionPane.showMessageDialog(rootPane, "Tìm thấy " + mayAtm.khachHangs.size() + " Kết quả !");
			}
		} else if (rdbtnNewRadioButton_5.isSelected()) {
			if(!textField_1.getText().isEmpty() && !textField_2.getText().isEmpty()) {
				int a = Integer.parseInt(textField_1.getText());
				int b = Integer.parseInt(textField_2.getText());
				List<KhachHang> result = new ArrayList<KhachHang>();
				for (KhachHang khachHang : mayAtm.khachHangs) {
					if(khachHang.getSoDu() >= a && khachHang.getSoDu() <= b) {
						result.add(khachHang);
					}
				}
				mayAtm.khachHangs.clear();
				mayAtm.khachHangs.addAll(result);
				model.setRowCount(0);
				for (KhachHang khachHang : mayAtm.khachHangs) {
					Object[] row = new Object[] {
							khachHang.getMaKh(), khachHang.getHoTen(),
							khachHang.getStk(),khachHang.getSoDu(),khachHang.getSdt(),khachHang.getPin()
						};
					model.addRow(row);
				}
				JOptionPane.showMessageDialog(rootPane, "Tìm thấy " + mayAtm.khachHangs.size() + " Kết quả !");
			} else { 
				JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập đoạn giá trị sô dư cần tìm kiếm !");
			}
			
		} else {
			JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn tiêu chí tìm kiếm trước !");
		}
		
	}
	
	private static SQLServerDataSource confiDataSource() {
    	SQLServerDataSource dataSource = new SQLServerDataSource();
    	dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setPortNumber(PORT);
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DB_NAME);
        return dataSource;
    }
}
