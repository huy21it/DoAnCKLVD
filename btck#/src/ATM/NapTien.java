package ATM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class NapTien extends JFrame {
	
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
	private JTextField txtNapTien;
	ArrayList<QLDN> list ;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NapTien frame = new NapTien();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	public NapTien(ArrayList<QLDN> list) {
		init(list);
		this.setLocationRelativeTo(null);
	}
	
	public void init(ArrayList<QLDN> list) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNhpSTin = new JLabel("Nhập số tiền bạn muốn nạp");
		lblNhpSTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhpSTin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhpSTin.setBounds(0, 43, 436, 27);
		contentPane.add(lblNhpSTin);
		
		txtNapTien = new JTextField();
		txtNapTien.setColumns(10);
		txtNapTien.setBounds(67, 93, 291, 34);
		contentPane.add(txtNapTien);
		
		JButton btnHoanTat = new JButton("Hoàn tất");
		btnHoanTat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHoanTatActionPerformed(e, list);
			}
		});
		btnHoanTat.setBounds(77, 187, 89, 23);
		contentPane.add(btnHoanTat);
		
		JButton btnHuyBo = new JButton("Huỷ bỏ");
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHuyBoActionPerformed(e);
			}
		});
		btnHuyBo.setBounds(256, 187, 89, 23);
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
		
		int soTien = Integer.parseInt(txtNapTien.getText());
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
			int tienConLai = soDu + soTien;
	
				var sql1 = "UPDATE dbo.khachHang SET "        
		         + "soDu = '" + tienConLai + "'"
		         + "Where stk = '" +   stk + "'";	
				var statement = conn.createStatement();
				statement.executeUpdate(sql1);		
				JOptionPane.showMessageDialog(rootPane,"Nạp tiền thành công ! ");
				this.dispose();
				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		
	}
	
	private void btnHuyBoActionPerformed(ActionEvent e) {
		this.dispose();
		
	}

}
