package exetastiki_earino;		
import java.awt.EventQueue;		

import javax.swing.JFrame;		
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;			

import java.awt.Font;	
import java.awt.Color;		

import javax.swing.SwingConstants;		
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Box;			

import java.awt.event.*;	  

import java.awt.GridBagLayout;			
import java.awt.GridBagConstraints;			
import java.awt.Insets;			

public class IntroApplicationPlatform extends JFrame {		 

    private static final long serialVersionUID = 1L;
    
    private JPanel contentPane;			
    private JTextField txtUsername;       
    private JPasswordField txtPassword;     

    public IntroApplicationPlatform() {  						
        
        setTitle("Digital Learning Ecosystem");					
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
        setBounds(100, 100, 450, 300);		  					
        
        
        contentPane = new JPanel();								
        contentPane.setBackground(new Color(245, 245, 220));	
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
        contentPane.setLayout(new BorderLayout());				
        setContentPane(contentPane);							
        
        
        JPanel pnlNorth = new JPanel();							
        pnlNorth.setBackground(new Color(245, 245, 220));		
        JLabel lblWelcome = new JLabel("Welcome to Digital Learning Ecosystem");		
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);					
        lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));			 
        lblWelcome.setForeground(new Color(100, 149, 237));				
        pnlNorth.add(lblWelcome);										
        contentPane.add(pnlNorth, BorderLayout.NORTH);					
        
        
        JPanel pnlCenter = new JPanel(new GridBagLayout());			 
        pnlCenter.setBackground(new Color(245, 245, 220));			
        contentPane.add(pnlCenter, BorderLayout.CENTER);			
        
        
        JLabel lblUsername = new JLabel("Username:");						 
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);			
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));				
        lblUsername.setForeground(new Color(100, 149, 237));				
        
        txtUsername = new JTextField(15);									
        txtUsername.setHorizontalAlignment(SwingConstants.CENTER);			
        txtUsername.setBackground(new Color(176, 196, 222));				
        
        JLabel lblPassword = new JLabel("Password:");						
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);			
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));				
        lblPassword.setForeground(new Color(100, 149, 237));				
        
        txtPassword = new JPasswordField(15);							
        txtPassword.setHorizontalAlignment(SwingConstants.CENTER);		
        txtPassword.setBackground(new Color(176, 196, 222));			
        
        JButton btnLogin = new JButton("Log in");				
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));	
        btnLogin.setForeground(Color.WHITE);					
        btnLogin.setBackground(new Color(135, 206, 235));		
        
        JLabel lblForgot = new JLabel("<html><u>Forgot my password</u></html>");		
        lblForgot.setForeground(new Color(100, 149, 237));			
        
        GridBagConstraints gbc = new GridBagConstraints();			
        gbc.insets = new Insets(10, 10, 10, 10);				  
        gbc.fill = GridBagConstraints.HORIZONTAL;				
        
        
        GridBagConstraints gbcTop = (GridBagConstraints) gbc.clone();		 
        gbcTop.gridx = 0;							 
        gbcTop.gridy = 0;							
        gbcTop.gridwidth = 2;						 
        gbcTop.weighty = 1;							
        gbcTop.fill = GridBagConstraints.VERTICAL;			
        pnlCenter.add(Box.createVerticalGlue(), gbcTop);		
        
        
        GridBagConstraints gbcUsernameLabel = (GridBagConstraints) gbc.clone();		
        gbcUsernameLabel.gridx = 0;								
        gbcUsernameLabel.gridy = 1;								
        gbcUsernameLabel.anchor = GridBagConstraints.EAST;		
        pnlCenter.add(lblUsername, gbcUsernameLabel);			
        
        
        GridBagConstraints gbcUsernameField = (GridBagConstraints) gbc.clone();		
        gbcUsernameField.gridx = 1;								
        gbcUsernameField.gridy = 1;								
        gbcUsernameField.anchor = GridBagConstraints.WEST;		
        pnlCenter.add(txtUsername, gbcUsernameField);			
        
        GridBagConstraints gbcPasswordLabel = (GridBagConstraints) gbc.clone();		
        gbcPasswordLabel.gridx = 0;								
        gbcPasswordLabel.gridy = 2;								
        gbcPasswordLabel.anchor = GridBagConstraints.EAST;		
        pnlCenter.add(lblPassword, gbcPasswordLabel);			
        
        GridBagConstraints gbcPasswordField = (GridBagConstraints) gbc.clone();			
        gbcPasswordField.gridx = 1;
        gbcPasswordField.gridy = 2;
        gbcPasswordField.anchor = GridBagConstraints.WEST;
        pnlCenter.add(txtPassword, gbcPasswordField);
        
        
        GridBagConstraints gbcLogin = (GridBagConstraints) gbc.clone();			
        gbcLogin.gridx = 0;									
        gbcLogin.gridy = 3;									
        gbcLogin.gridwidth = 2;								
        gbcLogin.anchor = GridBagConstraints.CENTER;		
        pnlCenter.add(btnLogin, gbcLogin);					
        
        GridBagConstraints gbcForgot = (GridBagConstraints) gbc.clone();	 
        gbcForgot.gridx = 0;			
        gbcForgot.gridy = 4;			
        gbcForgot.gridwidth = 2;		
        gbcForgot.anchor = GridBagConstraints.CENTER;
        pnlCenter.add(lblForgot, gbcForgot);
        
        GridBagConstraints gbcBottom = (GridBagConstraints) gbc.clone();		
        gbcBottom.gridx = 0;
        gbcBottom.gridy = 5;
        gbcBottom.gridwidth = 2;
        gbcBottom.weighty = 1;
        gbcBottom.fill = GridBagConstraints.VERTICAL;			
        pnlCenter.add(Box.createVerticalGlue(), gbcBottom);
        
        
        btnLogin.addActionListener(new ActionListener() {			
            public void actionPerformed(ActionEvent e) {			
            	
                String username = txtUsername.getText();			
                String password = new String(txtPassword.getPassword());	 
                if(username.equals("teachersadmin!") && password.equals("college7845")) {		
            	Main_Application.startwin.setVisible(true);				
                Main_Application.introapp.setEnabled(false);			
                JOptionPane.showMessageDialog(Main_Application.startwin, "Successful Login!", "Login", 1);		
                } else {
                	JOptionPane.showMessageDialog(Main_Application.introapp, "Invalid Username or Password! Please try again.", "Invalid Login", JOptionPane.ERROR_MESSAGE);	
                }
               }	
        });		
        
        lblForgot.addMouseListener(new MouseAdapter() {			
            @Override
            public void mouseClicked(MouseEvent e) {			 
            JOptionPane.showConfirmDialog(Main_Application.introapp, "Reset your password?", "Reset", JOptionPane.YES_NO_OPTION);	 
            }	
        });		
    }			
    
   // public static void main(String[] args) {						 
       // EventQueue.invokeLater(new Runnable() {					 
          //  public void run() {									
             //   try {								
                //    IntroApplicationPlatform frame = new IntroApplicationPlatform();		
                //    frame.setVisible(true);			
              //  } catch (Exception e) {				
               //     e.printStackTrace();			
              //  }
         //   }		
      //  });			
    // }				

	}
