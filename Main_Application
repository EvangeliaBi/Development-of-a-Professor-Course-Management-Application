package exetastiki_earino;			

import java.awt.EventQueue;			

	
public class Main_Application {		
	
	
	public static IntroApplicationPlatform introapp;		
	public static Application startwin;						
	public static Window_Search_Insert insert;				
	public static New_Register register;					
	public static Results results;							

	public static void main(String[] args) {	
		EventQueue.invokeLater(new Runnable() {
		@Override
		public void run() {
			try {		 				
				introapp = new IntroApplicationPlatform();	
				introapp.setLocationByPlatform(true);		
				introapp.setVisible(true);					
				
				startwin = new Application();
				startwin.setLocationByPlatform(true);			
				startwin.setVisible(false);						
				
				insert = new Window_Search_Insert();
				insert.setLocationByPlatform(true);
				insert.setVisible(false);					
				
				register = new New_Register();
				register.setLocationByPlatform(true);
				register.setVisible(false);					
				
				results = new Results();
				results.setLocationByPlatform(true);
				results.setVisible(false);					
				
			} catch(Exception e) {
				e.printStackTrace();
				}
			}
		});
	}
}
