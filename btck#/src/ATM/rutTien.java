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
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class rutTien extends JFrame {

	private JPanel contentPane;
	private JTextField txtNhapTien;
	private JButton btnHoanTat;
	private JButton btnHuyBo;
	private MayAtm mayAtm;
	private QLDN qldn = new QLDN();
	
	ArrayList<QLDN> list ;


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					rutTien frame = new rutTien();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
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
    
    public rutTien(ArrayList<QLDN> list) {
  
//    	for (QLDN qldn : list) {
//			System.out.println(qldn.getStk());
//		}
    	init(list);
    	this.setLocationRelativeTo(null);
    	
  
    	
    }
	/**
	 * Create the frame.
	 */
	public void init(ArrayList<QLDN> list) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập số tiền bạn muốn rút");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(0, 33, 436, 27);
		contentPane.add(lblNewLabel);
		
		txtNhapTien = new JTextField();
		txtNhapTien.setBounds(69, 134, 291, 34);
		contentPane.add(txtNhapTien);
		txtNhapTien.setColumns(10);
		
		btnHoanTat = new JButton("Hoàn tất");
		btnHoanTat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHoanTatActionPerformed(e, list);
			}

			
		});
		btnHoanTat.setBounds(80, 215, 89, 23);
		contentPane.add(btnHoanTat);
		
		btnHuyBo = new JButton("Huỷ bỏ");
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHuyBoActionPerformed(e);
			}
		});
		btnHuyBo.setBounds(259, 215, 89, 23);
		contentPane.add(btnHuyBo);
		
		JLabel lblNewLabel_1 = new JLabel("Là bội của 50000");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(0, 62, 436, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Đơn vị: VNĐ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(0, 96, 436, 14);
		contentPane.add(lblNewLabel_2);
	}
	

	private void btnHuyBoActionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	private void btnHoanTatActionPerformed(ActionEvent e,ArrayList<QLDN> list) {
		for (QLDN qldn : list) {
			System.out.println(qldn.getStk());
		}
		
		String stk = null;
		for (QLDN qldn : list) {
			stk = qldn.getStk();
		}
		
		int soTien = Integer.parseInt(txtNhapTien.getText());
		if(soTien % 50000 == 0 && soTien >= 50000) {
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
				int tienConLai = soDu - soTien;
				if (tienConLai < 0 ) {
					JOptionPane.showMessageDialog(rootPane,"Số tiền vượt quá tài khoản gốc !");
				} else {
					var sql1 = "UPDATE dbo.khachHang SET "        
			         + "soDu = '" + tienConLai + "'"
			         + "Where stk = '" +   stk + "'";	
					var statement = conn.createStatement();
					statement.executeUpdate(sql1);		
					JOptionPane.showMessageDialog(rootPane,"Rút tiền thành công ! ");
					this.dispose();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(rootPane,"Số tiền bạn muốn rút không hợp lệ !");
		}
		
		
				
	}
}
