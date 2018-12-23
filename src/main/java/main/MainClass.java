package main;

import java.awt.EventQueue;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import gUI.UserInterface;
import graphdb.CreateConnection;
public class MainClass {
	public static RepositoryConnection connection = CreateConnection.getRepositoryConnection();
	//use this function to change repoID
	public static void main(String[] args)
	{			
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


