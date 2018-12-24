package entityCreator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.util.ModelBuilder;

import graphdb.Connection;
import utility.FileReaderAndWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class EntityGenerator {
	//this is the source to get input files:
	protected String source = "data\\";
	
	//max enetity for 1 model
	protected final int MAX_ENTITY = 3000000;
	
	// these 2 lists is used to store name and description of object
	protected ArrayList<String> nameList = new ArrayList<String>();
	protected ArrayList<String> descriptionList = new ArrayList<String>();

	// prefix of object
	protected String prefix;
	
	protected ModelBuilder builder = new ModelBuilder();
	/* 
	 * constructor of this class. type is the type of entity.
	 * this will read all the data needed to the list above
	 */
	public EntityGenerator (String type)
	{	
		// reformat type:
		type = type.toLowerCase();
		this.prefix = new String(type + ":");
		source += type + "\\";
		
		//read file and store data in 2 lists up there
		try {
			BufferedReader name;
			BufferedReader description;
			name = FileReaderAndWriter.reader(source + "name.txt");
			description = FileReaderAndWriter.reader(source + "description.txt");
			String line;
			while((line = name.readLine()) != null)
				nameList.add(line);

			while((line = description.readLine()) != null)
				descriptionList.add(line);
			name.close();
			description.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	// this method is used to create entites and add it to database. num is the number of IDs you want to created
	public void entityCreator(int num) {
		
		try {
			//setting up for idWriter. Use this idWriter to store ID of generated Entites
			BufferedWriter idWriter = FileReaderAndWriter.writer(source + "id.txt", false);
			//this model is used to store triple
			Model model;
			//those code lines below to generate entities to database
			String tempID;
			int i = 0;
			Connection.conn.begin();
			while(i < nameList.size() && num > 0) {
				//IDCounter is used to store number of ID 1 name can produce
				int IDCounter = -1;
				//random generate IDCounter
				if(i == nameList.size() - 1) {
					IDCounter = num;
				}
				else {
					int max = num/(nameList.size() - i);
					if(max == 0) 
						max = 1;
					IDCounter = ThreadLocalRandom.current().nextInt(0, max + 1);
				}
				num -= IDCounter;
				//generate ID 
				
				for(int j = 0 ; j < IDCounter; j++) {
					// add newly created id to the id file in source
					tempID = new String(genTempID(i, j));	
					idWriter.write(tempID + "\n");
					model = addNewEntityToModel(tempID, i);
					Connection.conn.add(model);
					model.clear();
					if(model.size() >= MAX_ENTITY) {
						Connection.conn.add(model);
						builder = new ModelBuilder();
						model.clear();
					}
				}
				i += 1;
			}
			Connection.conn.add(builder.build());
			Connection.conn.commit();
			idWriter.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(Throwable e1) {
			Connection.conn.rollback();
			e1.printStackTrace();
		}
	}

	
	// Override this method if you want to add more field to the object with indexOfName is the index of 
	// this object's name in the nameList.
	protected Model addNewEntityToModel(String tempID, int indexOfName)
	{	
		String tempDescription;
		// randomly pick a description
		int tempDescriptionIndex = ThreadLocalRandom.current().nextInt(0, descriptionList.size());
		tempDescription = new String(descriptionList.get(tempDescriptionIndex));
		return builder.subject(prefix + tempID)
		              .add("object:"+"has_name", nameList.get(indexOfName))
		              .add("object:"+"has_description", tempDescription)
		              .build();
	}
	
	
	// this method is used to generate ID for Entity.
	// Override this method if you want to modify the tempID of the object
	protected String genTempID(int nameListIndex, int IDIndex) 
	{
		String temp = new String(nameList.get(nameListIndex) + " " + String.valueOf(IDIndex));
		temp = temp.replaceAll("\\s", "_");
		return temp;
	}

}
