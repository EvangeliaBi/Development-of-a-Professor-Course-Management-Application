package exetastiki_earino;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Results extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel contentPane;
    private JTextField txt_ID;
    private JTextField txt_Name;
    private JTextField txt_LName;   
    private ResultSet rsData; 	     

   // public static void main(String[] args) {
    //  EventQueue.invokeLater(new Runnable() {
       //   public void run() {
         //     try {
              //    Results frame = new Results();
                //  frame.setMinimumSize(new Dimension(450, 300));		
                //  frame.setLocationRelativeTo(null);					// 
               //   frame.setVisible(true);								
            //  } catch (Exception e) {
               //   e.printStackTrace();
            //  }
        //  }
    //  });
  //  }

    
    public Results() {										
      setBackground(new Color(245, 245, 250));
      addWindowListener(new WindowAdapter() {				
          @Override
          public void windowClosing(WindowEvent e) {
              Main_Application.insert.setEnabled(true);		
          }
      });
      setTitle("Results");
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		
      setBounds(100, 100, 450, 300);						
      
      contentPane = new JPanel();							
      contentPane.setBackground(new Color(245, 245, 250));
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
      contentPane.setLayout(new BorderLayout());				
      setContentPane(contentPane);							
      
      
      JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));		
      pnlNorth.setBackground(new Color(245, 245, 250));
      JLabel lblResults = new JLabel("Results");				
      lblResults.setBackground(new Color(153, 0, 102));
      lblResults.setForeground(new Color(0, 51, 102));
      lblResults.setFont(new Font("Tahoma", Font.PLAIN, 20));
      pnlNorth.add(lblResults);						
      contentPane.add(pnlNorth, BorderLayout.NORTH);		
      
      
      JPanel fieldsPanel = new JPanel();						
      fieldsPanel.setOpaque(false);								
      fieldsPanel.setLayout(new GridLayout(3, 2, 10, 10));		
      
      Dimension labelSize = new Dimension(80, 25);			 
      
      JLabel lblID = new JLabel("ID:", SwingConstants.RIGHT);		
      lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblID.setForeground(new Color(0,102,153));
      lblID.setPreferredSize(labelSize);					
      fieldsPanel.add(lblID);								
      
      txt_ID = new JTextField(10);							
      txt_ID.setEditable(false);							
      txt_ID.setFont(new Font("Tahoma", Font.PLAIN, 14));
      txt_ID.setBackground(new Color(230,230,240));
      Dimension fieldSize = new Dimension(120, 25);			
      txt_ID.setPreferredSize(fieldSize);					
      txt_ID.setMaximumSize(fieldSize);
      fieldsPanel.add(txt_ID);							
      
      JLabel lblName = new JLabel("Name:", SwingConstants.RIGHT);		
      lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblName.setForeground(new Color(0,102,153));
      lblName.setPreferredSize(labelSize);				
      fieldsPanel.add(lblName);							
      
      txt_Name = new JTextField(10);
      txt_Name.setFont(new Font("Tahoma", Font.PLAIN, 14));
      txt_Name.setBackground(new Color(230,230,240));
      txt_Name.setPreferredSize(fieldSize);			
      txt_Name.setMaximumSize(fieldSize);
      fieldsPanel.add(txt_Name);					
      
      JLabel lblLName = new JLabel("Last Name:", SwingConstants.RIGHT);			
      lblLName.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblLName.setForeground(new Color(0,102,153));
      lblLName.setPreferredSize(labelSize);				
      fieldsPanel.add(lblLName);						
      
      txt_LName = new JTextField(10);
      txt_LName.setFont(new Font("Tahoma", Font.PLAIN, 14));
      txt_LName.setBackground(new Color(230,230,240));
      txt_LName.setPreferredSize(fieldSize);			
      txt_LName.setMaximumSize(fieldSize);
      fieldsPanel.add(txt_LName);						
      
      
      JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));		
      navigationPanel.setOpaque(false);			
      
      
      JButton btnFirst = new JButton("|<");					
      btnFirst.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		try {			 
                if (Main_Application.results.rsData != null) {		
                    Main_Application.results.rsData.first();			
                    Main_Application.results.Records();			
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
      	}
      });
      btnFirst.setBackground(new Color(200, 220, 240));
      btnFirst.setForeground(new Color(0, 102, 153));
      btnFirst.setFont(new Font("Tahoma", Font.BOLD, 17));
      
      JButton btnPrev = new JButton("<");						
      btnPrev.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		try {
                if (Main_Application.results.rsData != null && !Main_Application.results.rsData.isFirst()) {		
                    Main_Application.results.rsData.previous();			
                    Main_Application.results.Records();					
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
      	}
      });
      btnPrev.setBackground(new Color(200, 220, 240));
      btnPrev.setForeground(new Color(0, 102, 153));
      btnPrev.setFont(new Font("Tahoma", Font.BOLD, 17));
      
      JButton btnNext = new JButton(">");				
      btnNext.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		try {
                if (Main_Application.results.rsData != null && !Main_Application.results.rsData.isLast()) {			
                    Main_Application.results.rsData.next();			
                    Main_Application.results.Records();				
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
      	}
      });
      btnNext.setBackground(new Color(200, 220, 240));
      btnNext.setForeground(new Color(0, 102, 153));
      btnNext.setFont(new Font("Tahoma", Font.BOLD, 17));
      
      JButton btnLast = new JButton(">|");					
      btnLast.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		try {
                if (Main_Application.results.rsData != null) {		
                    Main_Application.results.rsData.last();			
                    Main_Application.results.Records();				
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
      	}
      });
      btnLast.setBackground(new Color(200, 220, 240));
      btnLast.setForeground(new Color(0, 102, 153));
      btnLast.setFont(new Font("Tahoma", Font.BOLD, 17));
      
      navigationPanel.add(btnFirst);
      navigationPanel.add(btnPrev);
      navigationPanel.add(btnNext);
      navigationPanel.add(btnLast);
      
      
      JPanel mainPanel = new JPanel();		
      mainPanel.setOpaque(false);			
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));		
      
      Component verticalStrut = Box.createVerticalStrut(20);		
      mainPanel.add(verticalStrut);				
      mainPanel.add(fieldsPanel);				
      mainPanel.add(Box.createVerticalStrut(20));		
      mainPanel.add(navigationPanel);				
      
      JPanel centerContainer = new JPanel(new GridBagLayout());		
      centerContainer.setOpaque(false);						
      GridBagConstraints gbc = new GridBagConstraints();		
      gbc.gridx = 0;		
      gbc.gridy = 0;		
      centerContainer.add(mainPanel, gbc);			
      contentPane.add(centerContainer, BorderLayout.CENTER);		

      
      JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));	
      pnlSouth.setBackground(new Color(245,245,250));
      
      JButton btnUpdate = new JButton("Update");
      btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
      btnUpdate.setBackground(new Color(255,128,102));
      btnUpdate.setForeground(Color.WHITE);
      btnUpdate.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		if (txt_ID.getText().isEmpty() || txt_Name.getText().isEmpty() || txt_LName.getText().isEmpty()) {  	
      	            JOptionPane.showMessageDialog(contentPane, "You cannot proceed to update the record with empty fields (ID, Name, Last Name)!", "Missing Data", JOptionPane.ERROR_MESSAGE);
      	            return;		
      	       }
           
      		try {
      			Connection myconn = Application.conn;	 
      			int id = Integer.parseInt(txt_ID.getText());		
      			String sql = "UPDATE teachers SET firstname = ?, lastname = ? " + "WHERE id = ? AND (firstname <> ? OR lastname <> ?)";		 
                PreparedStatement ps = myconn.prepareStatement(sql);		
                ps.setString(1, txt_Name.getText());			
                ps.setString(2, txt_LName.getText());
                ps.setInt(3, id);			
                ps.setString(4, txt_Name.getText());  
                ps.setString(5, txt_LName.getText());	
                int updated = ps.executeUpdate();		
                ps.close();			
                if (updated == 0) { 	
                	JOptionPane.showMessageDialog(Main_Application.results, "There have been no changes to this registration!", "Unsuccessfull Update", JOptionPane.WARNING_MESSAGE);
               } else {				
                    JOptionPane.showMessageDialog(Main_Application.results, "The selected registration was updated successfully!", " Successfull Update", JOptionPane.INFORMATION_MESSAGE);
                    updatedRecords();			
                    shownextResult();			 
               }
            } catch (SQLException ex) {
            	JOptionPane.showMessageDialog(contentPane, "Connection to Database failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
      	}
      });  
      
      JButton btnDelete = new JButton("Delete");
      btnDelete.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		if (txt_ID.getText().isEmpty() || txt_Name.getText().isEmpty() || txt_LName.getText().isEmpty()) {		
  	            JOptionPane.showMessageDialog(contentPane, "You cannot delete a record with empty fields (ID, First Name, Last Name)!", "Missing Data", JOptionPane.ERROR_MESSAGE);
  	            return;		
  	       }
      		
      	int delete = JOptionPane.showConfirmDialog(Main_Application.results, "Delete registration?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);	
      	if (delete == JOptionPane.YES_OPTION) {		
      		try {		
      		Connection myconn = Application.conn;			
      		String del = "DELETE FROM teachers WHERE id = ?";			
      		PreparedStatement ps = myconn.prepareStatement(del);		
      		int id = Integer.parseInt(txt_ID.getText());		
            ps.setInt(1, id);			
      		int confirmdel = ps.executeUpdate();		
      		if (confirmdel > 0) {						
      			JOptionPane.showMessageDialog(Main_Application.results, "The selected registration was deleted successfully!", "Delete", JOptionPane.INFORMATION_MESSAGE);		
      			rsData.deleteRow();			
      			updatedRecords();		
      			shownextResult();		 
      		} 
      		ps.close();		
      		clearForm(); 		
      		} catch (SQLException ex) {
        	JOptionPane.showMessageDialog(contentPane, "Connection to Database failed.", "Error", JOptionPane.ERROR_MESSAGE);
        	}
      	  }
      	}
      });
      
      btnDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
      btnDelete.setBackground(new Color(255,128,102));
      btnDelete.setForeground(Color.WHITE);
      
      pnlSouth.add(btnUpdate);		 
      pnlSouth.add(btnDelete);
      contentPane.add(pnlSouth, BorderLayout.SOUTH);	 
      
    }	
    
    public void setResultSet(ResultSet rs) {		
        this.rsData = rs;							
    }

    public void Records() {			
        try {					
            if (rsData != null) {		 
                int teacherID = rsData.getInt("id");		
                String teacherFirstName = rsData.getString("firstname");	
                String teacherLastName = rsData.getString("lastname");

                displayTeacher(teacherID, teacherFirstName, teacherLastName);	
            }
        } catch (SQLException ex) {				
        	JOptionPane.showMessageDialog(contentPane, "No more registrations to display!", "Error", JOptionPane.ERROR_MESSAGE);		
        }
    }

    public void displayTeacher(int teacherID, String teacherFName, String teacherLName) {		
    	txt_ID.setText(String.valueOf(teacherID));								
        txt_Name.setText(teacherFName);
        txt_LName.setText(teacherLName);
    }
    
    private void clearForm() {		
        txt_ID.setText("");
        txt_Name.setText("");
        txt_LName.setText("");
    }
    
    private void shownextResult() {			
    	try {						
    		boolean moreResults = rsData.next();	 
    		if(!moreResults) {			 
    			rsData.first();
    			Records();
    		}
    	} catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    }
    
    private void updatedRecords() {		 
        try {						
            if (rsData != null) {		 
                rsData.close();		
            }
            Statement stquery = Application.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);	 
            rsData = stquery.executeQuery("SELECT * FROM teachers");		
            if (rsData.next()) { 	 
                rsData.first();		
                Records();			
            } else {				 
                clearForm();
                JOptionPane.showMessageDialog(contentPane, "There are no records available!", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {		 
            ex.printStackTrace();
        }
    }

}
