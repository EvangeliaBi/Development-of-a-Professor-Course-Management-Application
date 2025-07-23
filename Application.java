package exetastiki_earino;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;		
import javax.swing.BoxLayout;		

import java.awt.*;
import java.awt.Component;			  
import java.awt.Dimension;			
import java.awt.event.*;


// import java.sql.Connection;			
// import java.sql.DriverManager;		
// import java.sql.SQLException;		
import java.sql.*;				

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static Connection conn;		

	 
	// public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
		//	public void run() {
			//	try {
				//	Application frame = new Application();
				//	frame.setVisible(true);
			//	} catch (Exception e) {
				//	e.printStackTrace();
			//	}
			// }
		// });
	// }

	 
	public Application() {									
		addWindowListener(new WindowAdapter() {			
			@Override			
			public void windowClosing(WindowEvent e) {		  
				Main_Application.introapp.setEnabled(true);	  
			}		
			
			@Override
			public void windowOpened(WindowEvent ev) {		
				
				String url = "jdbc:mysql://localhost:3306/applicationdb";		
				String username = "appuser";			
				String password = "Evangelia!";			
				try {						
					conn = DriverManager.getConnection(url, username, password);			
				} catch(SQLException e) {		
					JOptionPane.showMessageDialog(contentPane, "Connection to Database failed.", "Error", JOptionPane.ERROR_MESSAGE);		 
					e.printStackTrace();		
				}
			}	
		});		
		
		setTitle("Control Panel");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);			
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));			
		
		JPanel pnlNorth = new JPanel();						
		pnlNorth.setBackground(new Color(255, 250, 240));
		contentPane.add(pnlNorth, BorderLayout.NORTH);			
		
		JLabel lblArxiko = new JLabel("Control Panel");
		lblArxiko.setBackground(new Color(44, 62, 80));
		lblArxiko.setHorizontalAlignment(SwingConstants.CENTER);		
		lblArxiko.setForeground(new Color(65, 105, 225));
		lblArxiko.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlNorth.add(lblArxiko);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(255, 250, 240));
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));		
		pnlCenter.add(Box.createVerticalGlue());								
		
		JButton btnteachers = new JButton("Teachers");
		btnteachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Application.insert.setVisible(true);				
				Main_Application.startwin.setEnabled(false);			
			}
		});
		
		btnteachers.setAlignmentX(Component.CENTER_ALIGNMENT);			
		btnteachers.setIcon(new ImageIcon(Application.class.getResource("/exetastiki_earino/Educators/classroom.png")));		
		btnteachers.setBackground(new Color(175, 238, 238));
		btnteachers.setForeground(new Color(70, 130, 180));
		btnteachers.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlCenter.add(btnteachers);										
		pnlCenter.add(Box.createRigidArea(new Dimension(0, 20)));		
		
		JButton btnversion = new JButton("Version Info");
		btnversion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				JOptionPane.showMessageDialog(contentPane, "Version: 2025-03 (4.35.0)\r\nAuthor: Evangelia\n" + "Build id: 20250306-0812\n" + "This product includes software developed by other open source projects including the Apache Software Foundation.", "About", JOptionPane.PLAIN_MESSAGE);			
			}
		});
		
		btnversion.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnversion.setIcon(new ImageIcon(Application.class.getResource("/exetastiki_earino/Version/upgrade.png")));
		btnversion.setBackground(new Color(175, 238, 238));
		btnversion.setForeground(new Color(70, 130, 180));
		btnversion.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlCenter.add(btnversion);
		pnlCenter.add(Box.createVerticalGlue());
		
	}		
}
