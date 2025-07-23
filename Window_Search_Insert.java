package exetastiki_earino;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;			 
import java.sql.ResultSet;					
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Window_Search_Insert extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField text_LName;

    	
  //  public static void main(String[] args) {
     //   EventQueue.invokeLater(new Runnable() {
       // 	public void run() {
       //     try {
           //     Window_Search_Insert frame = new Window_Search_Insert();
            //   frame.setVisible(true);
          //  } catch (Exception e) {
           //     e.printStackTrace();
         //   }
       //   }
    //    });
   // }

     
    public Window_Search_Insert() {
        setBackground(new Color(240, 248, 255));					
        addWindowListener(new WindowAdapter() {						
            @Override
            public void windowClosing(WindowEvent e) {				
                Main_Application.startwin.setEnabled(true);			
            }
        });
        setTitle("Search/Insert Window");						
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);				
        setBounds(100, 100, 450, 300);

        
        contentPane = new JPanel();								
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));			
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);							

        
        JPanel pnlNorth = new JPanel();								
        pnlNorth.setBackground(new Color(230, 230, 250));			
        JLabel lblInsert = new JLabel("Search / Insert");			
        lblInsert.setHorizontalAlignment(SwingConstants.CENTER);		
        lblInsert.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblInsert.setForeground(new Color(65, 105, 225));
        pnlNorth.add(lblInsert);									
        contentPane.add(pnlNorth, BorderLayout.NORTH);				

        JPanel pnlCenter = new JPanel(new GridBagLayout());			
        pnlCenter.setBackground(new Color(230, 230, 250));
        contentPane.add(pnlCenter, BorderLayout.CENTER);			

        
        GridBagConstraints baseGbc = new GridBagConstraints();			
        baseGbc.insets = new Insets(10, 10, 10, 10);					

        GridBagConstraints gbcTop = (GridBagConstraints) baseGbc.clone();				
        gbcTop.gridx = 0;						
        gbcTop.gridy = 0;						
        gbcTop.gridwidth = 3;					
        gbcTop.weighty = 1.0;         
        gbcTop.fill = GridBagConstraints.VERTICAL;				
        pnlCenter.add(Box.createVerticalGlue(), gbcTop);		

        GridBagConstraints gbcLabel = (GridBagConstraints) baseGbc.clone();
        gbcLabel.gridx = 0;						
        gbcLabel.gridy = 1;						
        gbcLabel.anchor = GridBagConstraints.EAST;		
        JLabel lblLName = new JLabel("Last Name:");
        lblLName.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblLName.setForeground(new Color(30, 144, 255));
        pnlCenter.add(lblLName, gbcLabel);				

        
        GridBagConstraints gbcTextField = (GridBagConstraints) baseGbc.clone();
        gbcTextField.gridx = 1;			
        gbcTextField.gridy = 1;			
        gbcTextField.anchor = GridBagConstraints.CENTER;		
        text_LName = new JTextField(15);						
        text_LName.setBackground(new Color(230, 230, 240));		
        pnlCenter.add(text_LName, gbcTextField);				

        
        GridBagConstraints gbcSearchButton = (GridBagConstraints) baseGbc.clone();
        gbcSearchButton.gridx = 2;				
        gbcSearchButton.gridy = 1;				
        gbcSearchButton.anchor = GridBagConstraints.WEST;		
        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnSearch.setForeground(new Color(85, 107, 47));
        btnSearch.setBackground(new Color(144, 238, 144));
        btnSearch.setIcon(new ImageIcon(Window_Search_Insert.class.getResource("/exetastiki_earino/images/search (1).png")));		
        
        btnSearch.addActionListener(new ActionListener() {		
            public void actionPerformed(ActionEvent e) {
            	String last = text_LName.getText();		
                if (last.isEmpty() || last.isBlank()) {		
                    JOptionPane.showMessageDialog(contentPane, "Please insert the required field for the last name to proceed!", "Warning", JOptionPane.WARNING_MESSAGE);		
                    return;		
                }
                
             
                String lnamecorrect = last.substring(0, 1).toUpperCase() + last.substring(1).toLowerCase();		
                if (!last.equals(lnamecorrect)) {		 
                    text_LName.setText(lnamecorrect);	 
                    last = lnamecorrect;  				
                    JOptionPane.showMessageDialog(contentPane, "The last name was automatically corrected to: " + lnamecorrect, "Correction", JOptionPane.INFORMATION_MESSAGE);		 
                }
                
                try {		
                	Connection myconn = Application.conn;	  
                    String query = "SELECT * FROM teachers WHERE lastname LIKE ?";		
                    PreparedStatement ps = myconn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);		
                    ps.setString(1, text_LName.getText() + "%");		
                    ResultSet rs = ps.executeQuery();		 
                    if (rs.next()) {						
                    	int teacher_id = rs.getInt("id");
                    	String teacher_firstname = rs.getString("firstname");	
                    	String teacher_lastname = rs.getString("lastname");
                    	
                    	Main_Application.results.displayTeacher(teacher_id, teacher_firstname, teacher_lastname);		
                    	Main_Application.results.setResultSet(rs);			
                        Main_Application.results.Records();					
                    	
                        Main_Application.results.setVisible(true);			
                        Main_Application.insert.setEnabled(false);			
                    
                        Main_Application.results.addWindowListener(new WindowAdapter() {	
                            @Override
                            public void windowClosing(WindowEvent e) {
                                clearForm();  				
                            }
                        });
                    } else {			
                    	JOptionPane.showMessageDialog(Main_Application.insert, "Teacher with the specific lastname was not found", "Wrong input", JOptionPane.WARNING_MESSAGE);		
                    }
                } catch (SQLException ex) {
                	JOptionPane.showMessageDialog(contentPane, "Connection to Database failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        pnlCenter.add(btnSearch, gbcSearchButton);			

        
        GridBagConstraints gbcRegister = (GridBagConstraints) baseGbc.clone();			
        gbcRegister.gridx = 0;			
        gbcRegister.gridy = 2;			
        gbcRegister.gridwidth = 2;   	
        gbcRegister.anchor = GridBagConstraints.CENTER;				
        JButton btnRegister = new JButton("New Register");
        btnRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnRegister.setForeground(new Color(128, 0, 0));
        btnRegister.setBackground(new Color(255, 160, 122));
        btnRegister.addActionListener(new ActionListener() {		
            public void actionPerformed(ActionEvent e) {
                Main_Application.register.setVisible(true);			
                Main_Application.insert.setEnabled(false);			
            }
        });
        pnlCenter.add(btnRegister, gbcRegister);		

        
        GridBagConstraints gbcIcon = (GridBagConstraints) baseGbc.clone();
        gbcIcon.gridx = 2;		
        gbcIcon.gridy = 2;		
        gbcIcon.anchor = GridBagConstraints.CENTER;			
        JLabel lblPhoto = new JLabel();			
        lblPhoto.setIcon(new ImageIcon(Window_Search_Insert.class.getResource("/exetastiki_earino/Educ_image/teacher.png")));		
        pnlCenter.add(lblPhoto, gbcIcon);		

        GridBagConstraints gbcBottom = (GridBagConstraints) baseGbc.clone();
        gbcBottom.gridx = 0;		
        gbcBottom.gridy = 3;		
        gbcBottom.gridwidth = 3;	
        gbcBottom.weighty = 1.0;		
        gbcBottom.fill = GridBagConstraints.VERTICAL;		
        pnlCenter.add(Box.createVerticalGlue(), gbcBottom);		
        
    }		
    
 
    public void clearForm() {				
        text_LName.setText("");  			
    }
}
