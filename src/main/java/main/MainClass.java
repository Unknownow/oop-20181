package main;

import java.awt.EventQueue;
import gUI.UserInterface;
import graphdb.Connection;
public class MainClass {
	//use this function to change repoID
	public static void main(String[] args)
	{			
		Connection.conn = Connection.getRepositoryConnection();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


