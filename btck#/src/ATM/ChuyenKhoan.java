package ATM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ChuyenKhoan extends JFrame {
	
	private static final String USER = "sa"; 
    private static final String PASSWORD = "sa"; 
    private static final String SERVER_NAME = "LAPTOP-V9UUUO1D\\SQLEXPRESS"; 
    private static final String DB_NAME = "quanLyKhachHang"; 
    private static final int PORT = 1433; 

    private static SQLServerDataSource confiDataSource() {
    	SQLServerDataSource dataSource = new SQLServerDataSource();
    	dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setPortNumber(PORT);
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DB_NAME);
        return dataSource;
    }

	private JPanel contentPane;
	private JTextField txtNhapStkChuyen;
	private JTextField txtNhapTienChuyen;
	 ArrayList<QLDN> list;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChuyenKhoan frame = new ChuyenKhoan();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public ChuyenKhoan(ArrayList<QLDN> list) {
		init(list);
		setLocationRelativeTo(null);
	}
	 
	public void init(ArrayList<QLDN> list) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập số tài khoản bạn muốn chuyển tiền");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 26, 416, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nhập số tiền ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(0, 121, 436, 20);
		contentPane.add(lblNewLabel_1);
		
		txtNhapStkChuyen = new JTextField();
		txtNhapStkChuyen.setBounds(124, 69, 178, 27);
		contentPane.add(txtNhapStkChuyen);
		txtNhapStkChuyen.setColumns(10);
		
		txtNhapTienChuyen = new JTextField();
		txtNhapTienChuyen.setBounds(124, 160, 178, 27);
		contentPane.add(txtNhapTienChuyen);
		txtNhapTienChuyen.setColumns(10);
		
		JButton btnHoanTat = new JButton("Hoàn tất");
		btnHoanTat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHoanTatActionPerformed(e, list);
			}

		});
		btnHoanTat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHoanTat.setBounds(58, 226, 100, 23);
		contentPane.add(btnHoanTat);
		
		JButton btnHuyBo = new JButton("Huỷ bỏ");
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHuyBo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuyBo.setBounds(277, 226, 100, 23);
		contentPane.add(btnHuyBo);
	}
	
	private void btnHoanTatActionPerformed(ActionEvent e,ArrayList<QLDN> list) {
		for (QLDN qldn : list) {
			System.out.println(qldn.getStk());
		}
		
		String stk = null;
		for (QLDN qldn : list) {
			stk = qldn.getStk();
		}
		String stkChuyen = txtNhapStkChuyen.getText();
		int soTienChuyen = Integer.parseInt(txtNhapTienChuyen.getText());
		
		SQLServerDataSource ds = confiDataSource();
		try(var conn = ds.getConnection()) {
			var sql = "SELECT * FROM dbo.khachHang Where stk = '" + stk + "'";
//			System.out.println("hello");
//			var sql = "SELECT * FROM dbo.khachHang";
			var ps = conn.prepareStatement(sql);
			//ps.setInt(1, 123);
			// lay du lieu ra
			ResultSet resultSet = ps.executeQuery();
			//System.out.println(resultSet);
			int soDu = 0;
			while (resultSet.next()) {
			    soDu = resultSet.getInt(4);	
			}
			//System.out.println(soDu);
			int soDuConLai = soDu - soTienChuyen;
			var sql1 = "UPDATE dbo.khachHang SET "        
			         + "soDu = '" + soDuConLai + "'"
			         + "Where stk = '" +   stk + "'";	
			var statement = conn.createStatement();
			statement.executeUpdate(sql1);	
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		try(var conn = ds.getConnection()) {
			var sql = "SELECT * FROM dbo.khachHang Where stk = '" + stkChuyen + "'";
//			System.out.println("hello");
//			var sql = "SELECT * FROM dbo.khachHang";
			var ps = conn.prepareStatement(sql);
			//ps.setInt(1, 123);
			// lay du lieu ra
			ResultSet resultSet = ps.executeQuery();
			//System.out.println(resultSet);
			int soDu = 0;
			while (resultSet.next()) {
			    soDu = resultSet.getInt(4);	
			}
			//System.out.println(soDu);
			int soDuSauKhiNhanDuoc = soDu + soTienChuyen;
			var sql1 = "UPDATE dbo.khachHang SET "        
			         + "soDu = '" + soDuSauKhiNhanDuoc + "'"
			         + "Where stk = '" +   stkChuyen + "'";	
			var statement = conn.createStatement();
			statement.executeUpdate(sql1);	
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
		JOptionPane.showMessageDialog(rootPane,"Chuyển khoản thành công !");
		this.dispose();
	}
}
