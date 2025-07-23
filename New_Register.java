package exetastiki_earino;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;

public class New_Register extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textName;
    private JTextField textLName;

     
   // public static void main(String[] args) {
    //    EventQueue.invokeLater(new Runnable() {
        //	public void run() {
         //   try {
           //     New_Register frame = new New_Register();
             //   frame.setMinimumSize(new Dimension(450, 300));		
             //   frame.setLocationRelativeTo(null);	   
             //   frame.setVisible(true);				
          //  } catch (Exception e) {
             //   e.printStackTrace();
         //   }
        //  }
      //  });
   // }

     
    public New_Register() {										
        setBackground(new Color(240, 248, 255));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {			
                Main_Application.insert.setEnabled(true);		
            }
        });
        setTitle("New Registration");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);			
        setBounds(100, 100, 500, 350);							

        
        contentPane = new JPanel();								
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));			
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);							

        
        JPanel pnlNorth = new JPanel();							
        pnlNorth.setBackground(new Color(255, 229, 204));
        JLabel lblNewRegister = new JLabel("Registration Form");
        lblNewRegister.setForeground(new Color(0, 51, 102));
        lblNewRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pnlNorth.add(lblNewRegister);							
        contentPane.add(pnlNorth, BorderLayout.NORTH);			

        
        JPanel pnlCenter = new JPanel(new GridBagLayout());			
        pnlCenter.setBackground(new Color(255, 229, 204));
        
        GridBagConstraints baseGbc = new GridBagConstraints();		
        baseGbc.insets = new Insets(10, 10, 10, 10);				
        baseGbc.weightx = 1.0;										
        baseGbc.anchor = GridBagConstraints.CENTER;					

        GridBagConstraints gbcTop = (GridBagConstraints) baseGbc.clone();			
        gbcTop.gridx = 0;											
        gbcTop.gridy = 0;											
        gbcTop.gridwidth = 3;										
        gbcTop.weighty = 1.0;  										
        gbcTop.fill = GridBagConstraints.VERTICAL;					
        pnlCenter.add(Box.createVerticalGlue(), gbcTop);			

        
        GridBagConstraints gbcIDLabel = (GridBagConstraints) baseGbc.clone();		
        gbcIDLabel.gridx = 0;										
        gbcIDLabel.gridy = 1;										
        gbcIDLabel.anchor = GridBagConstraints.EAST;				
        JLabel lblID = new JLabel("ID:");
        lblID.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblID.setForeground(new Color(102, 153, 255));
        pnlCenter.add(lblID, gbcIDLabel);							

        GridBagConstraints gbcIDField = (GridBagConstraints) baseGbc.clone();
        gbcIDField.gridx = 1;			
        gbcIDField.gridy = 1;			
        gbcIDField.anchor = GridBagConstraints.WEST;	
        JLabel lblIDAuto = new JLabel("Auto-Complete");
        lblIDAuto.setForeground(new Color(102, 153, 255));
        lblIDAuto.setBackground(new Color(230, 230, 230));
        lblIDAuto.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIDAuto.setPreferredSize(new Dimension(120, 25));		
        pnlCenter.add(lblIDAuto, gbcIDField);				

        
        GridBagConstraints gbcPhoto = (GridBagConstraints) baseGbc.clone();
        gbcPhoto.gridx = 2;				
        gbcPhoto.gridy = 1;				
        gbcPhoto.gridheight = 3;  		
        gbcPhoto.anchor = GridBagConstraints.CENTER;		
        JLabel lblPhoto = new JLabel();					
        lblPhoto.setIcon(new ImageIcon(New_Register.class.getResource("/exetastiki_earino/RegisterImage/digital-identity.png")));		
        pnlCenter.add(lblPhoto, gbcPhoto);			

        
        GridBagConstraints gbcNameLabel = (GridBagConstraints) baseGbc.clone();
        gbcNameLabel.gridx = 0;			
        gbcNameLabel.gridy = 2;			
        gbcNameLabel.anchor = GridBagConstraints.EAST;			
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblName.setForeground(new Color(102, 153, 255));
        pnlCenter.add(lblName, gbcNameLabel);			

        GridBagConstraints gbcNameField = (GridBagConstraints) baseGbc.clone();
        gbcNameField.gridx = 1;		
        gbcNameField.gridy = 2;		
        gbcNameField.anchor = GridBagConstraints.WEST;			
        textName = new JTextField(10);
        textName.setBackground(new Color(230, 230, 230));
        textName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textName.setPreferredSize(new Dimension(120, 25));			
        pnlCenter.add(textName, gbcNameField);			

        
        GridBagConstraints gbcLNameLabel = (GridBagConstraints) baseGbc.clone();
        gbcLNameLabel.gridx = 0;		
        gbcLNameLabel.gridy = 3;		
        gbcLNameLabel.anchor = GridBagConstraints.EAST;			
        JLabel lblLName = new JLabel("Last Name:");
        lblLName.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblLName.setForeground(new Color(102, 153, 255));
        pnlCenter.add(lblLName, gbcLNameLabel);				

        GridBagConstraints gbcLNameField = (GridBagConstraints) baseGbc.clone();
        gbcLNameField.gridx = 1;		
        gbcLNameField.gridy = 3;		
        gbcLNameField.anchor = GridBagConstraints.WEST;			
        textLName = new JTextField(10);						
        textLName.setBackground(new Color(230, 230, 230));
        textLName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textLName.setPreferredSize(new Dimension(120, 25));			
        pnlCenter.add(textLName, gbcLNameField);					

        
        GridBagConstraints gbcCourseLabel = (GridBagConstraints) baseGbc.clone();
        gbcCourseLabel.gridx = 0;		
        gbcCourseLabel.gridy = 4;		
        gbcCourseLabel.anchor = GridBagConstraints.EAST;		
        JLabel lblCourse = new JLabel("Course:");
        lblCourse.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblCourse.setForeground(new Color(102, 153, 255));
        pnlCenter.add(lblCourse, gbcCourseLabel);		

        
        JComboBox<String> courseComboBox = new JComboBox<>();		
        courseComboBox.setModel(new DefaultComboBoxModel(new String[] {"Java", "OOP with C#", "Python", "C++", "Databases SQL", "Introduction to C"}));		 
        courseComboBox.setToolTipText("Courses");			
        courseComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));		
        courseComboBox.setPreferredSize(new Dimension(120, 25));		

        GridBagConstraints gbcComboBox = (GridBagConstraints) baseGbc.clone();		
        gbcComboBox.gridx = 1;		
        gbcComboBox.gridy = 4;		
        gbcComboBox.anchor = GridBagConstraints.WEST;		
        pnlCenter.add(courseComboBox, gbcComboBox);			
        
        
        GridBagConstraints gbcButton = (GridBagConstraints) baseGbc.clone();
        gbcButton.gridx = 0;			
        gbcButton.gridy = 5;			
        gbcButton.gridwidth = 3;		
        gbcButton.anchor = GridBagConstraints.CENTER;			
        
        JButton btnInsert = new JButton("Insert");				
        btnInsert.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
                if (textName.getText().isEmpty() || textLName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Please, insert all the required fields: Name and Last Name!", "Missing Data", JOptionPane.WARNING_MESSAGE);
                    return;  
                }
        		
             
                String firstname = textName.getText();			
                String lastname  = textLName.getText();
                if (Digits(firstname) || Digits(lastname)) {	 
                    JOptionPane.showMessageDialog(Main_Application.register, "Unsuccessful registration! Please insert only characters and not numbers!", "Unsuccessful registration", JOptionPane.WARNING_MESSAGE);
                    return;		 
                }
                
                try {
        		Connection myconn = Application.conn;		
        		String checkSql = "SELECT COUNT(*) FROM teachers WHERE firstname = ? AND lastname = ?";		
        		PreparedStatement check = myconn.prepareStatement(checkSql);		
        		check.setString(1, textName.getText());			
        		check.setString(2, textLName.getText());		
        		ResultSet rs = check.executeQuery();			
        		
        		if(rs.next() && rs.getInt(1) > 0){		 
        		    JOptionPane.showMessageDialog(contentPane, "This teacher already exists in the database.", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
        		    check.close();		
        		    return;				 
        		}
        		check.close();
        		
        		String sql = "INSERT INTO teachers (firstname, lastname) VALUES (?, ?)";	 
        		PreparedStatement ps = myconn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);		
        		ps.setString(1, textName.getText());			
        		ps.setString(2, textLName.getText());
        		int data = ps.executeUpdate();			
        		if(data > 0) {			
        	        ResultSet keys = ps.getGeneratedKeys();		
        			if(keys.next()) {					
        	            int regId = keys.getInt(1);		 
        	
                        String selectedCourse = (String) courseComboBox.getSelectedItem();		
                        if(selectedCourse != null && !selectedCourse.isEmpty()) {			 
                            String courseSql = "INSERT INTO teacher_courses (teacher_id, course_id) " + "VALUES (?, (SELECT id FROM courses WHERE course_name = ?))";		 
                            PreparedStatement courseps = myconn.prepareStatement(courseSql);		
                            courseps.setInt(1, regId);				
                            courseps.setString(2, selectedCourse);		
                            courseps.executeUpdate();		 
                            courseps.close();			
                        }
        	            JOptionPane.showMessageDialog(Main_Application.register, "The registration was successful with ID: " +regId+ "!", "Registration", JOptionPane.INFORMATION_MESSAGE);		
        	         
        	
        	            textName.setText("");
        	            textLName.setText("");
        			}
        			 keys.close();  
        	    }
        	    ps.close();  	
        	} catch (SQLException ex) {
        			JOptionPane.showMessageDialog(contentPane, "Connection to Database failed.", "Error", JOptionPane.ERROR_MESSAGE);		
        		}
        	}
        });
        
        btnInsert.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnInsert.setBackground(new Color(255, 153, 204));
        btnInsert.setForeground(new Color(0, 51, 102));
        pnlCenter.add(btnInsert, gbcButton);			

        GridBagConstraints gbcBottom = (GridBagConstraints) baseGbc.clone();		
        gbcBottom.gridx = 0;			
        gbcBottom.gridy = 5;			
        gbcBottom.gridwidth = 3;		
        gbcBottom.weighty = 1.0;		
        gbcBottom.fill = GridBagConstraints.VERTICAL;			
        pnlCenter.add(Box.createVerticalGlue(), gbcBottom);		

        
        JPanel centerContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));		  
        centerContainer.setBackground(new Color(255, 229, 204));
     
        centerContainer.add(pnlCenter);						
        contentPane.add(centerContainer, BorderLayout.CENTER);			
    
    }	
    
    public boolean Digits(String str) {			
        for (char ch : str.toCharArray()) {		
            if (Character.isDigit(ch)) {		
                return true;		
            }					
        }
        return false;			 
    }
    
}
