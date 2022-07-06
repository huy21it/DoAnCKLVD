package ATM;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KiemTraSoDu extends JFrame {
	
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
	
	ArrayList<QLDN> list ;

	private JLabel lblNewLabel_1;
	private int soDu;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					KiemTraSoDu frame = new KiemTraSoDu();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	public KiemTraSoDu(ArrayList<QLDN> list) {
		int soDu = 0;
		init(list);
		this.setLocationRelativeTo(null);
		KiemTraSoDu(list);
		
	}

	public void init(ArrayList<QLDN> list) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Thoát");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(170, 211, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Số dư tài khoản của bạn");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 23, 436, 48);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 131, 436, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Đơn vị: VNĐ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 82, 436, 23);
		contentPane.add(lblNewLabel_2);
	}
	
	public void KiemTraSoDu(ArrayList<QLDN> list) {
		SQLServerDataSource dataSource = new SQLServerDataSource();
		for (QLDN qldn : list) {
			System.out.println(qldn.getStk());
		}
		
		String stk = null;
		for (QLDN qldn : list) {
			stk = qldn.getStk();
		}
	
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
			while (resultSet.next()) {
			    soDu = resultSet.getInt(4);	
			}
			lblNewLabel_1.setText(soDu+"");
			
			//System.out.println(soDu);
		} catch (SQLServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
