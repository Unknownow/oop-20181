package relationshipCreator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.impl.TreeModel;

import main.MainClass;
import utility.FileReaderAndWriter;


//this class is used to generate *num relationship between 2 type of entity.
public class RelationshipGenerator {
	
	protected final int MAX_MODEL = 3000000;
	protected String source = "data\\";
	protected ArrayList<String> actionList = new ArrayList<String>();
	protected ArrayList<String> linkList = new ArrayList<String>();
	protected String actionNamespace;
	protected String subjectType;
	protected String objectType;
	
	protected BufferedWriter writer;
	// constructor of this class. It'll automatically add data to actionList and linkList.
	public RelationshipGenerator(String subjectType, String objectType) {
		this.subjectType = subjectType = subjectType.toLowerCase();
		this.objectType = objectType = objectType.toLowerCase();
		actionNamespace = new String(subjectType.substring(0, 1) + objectType.substring(0, 1));
		
		
		
		//read file and store data in 2 lists up there
		try {
			BufferedReader link;
			BufferedReader action;
			link = FileReaderAndWriter.reader(source + "link.txt");
			action = FileReaderAndWriter.reader(source + subjectType + "\\" + "action\\" + objectType + ".txt");
			String line;
			while((line = link.readLine()) != null)
			{
				line = line.replaceAll(" ", "_");
				linkList.add(line);
			}
			link.close();
			while((line = action.readLine()) != null)
			{
				line = line.replaceAll(" ", "_");
				actionList.add(line);
			}
			action.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	public void relationshipGenerator(int num) 
	{
		Model model = new TreeModel();
		String source;
		String subj = new String();
		String obj = new String();
		String link = new String();
		String dayExtracted = new String();
		String act = new String();
		IRI s, p, o, has_source;

		try {
			MainClass.connection.begin();
			while(num > 0) {
				link = linkList.get(randomInt(0, linkList.size() - 1));
				dayExtracted = createRandomDate(2016, 2018);
				act = actionList.get(randomInt(0, actionList.size() - 1));
				subj = RelationshipGeneratorForDatabase.IDGen.genRandomID(subjectType);
				obj = RelationshipGeneratorForDatabase.IDGen.genRandomID(objectType);
				source = dayExtracted + " | " + link;
				
				s = SimpleValueFactory.getInstance().createIRI(subjectType + ":", subj);
				o = SimpleValueFactory.getInstance().createIRI(objectType + ":", obj);
				p = SimpleValueFactory.getInstance().createIRI(actionNamespace + ":", act);
				has_source = SimpleValueFactory.getInstance().createIRI("object:has_source");
				model.add(s, p, o);
				model.add(s, has_source, MainClass.connection.getValueFactory().createLiteral(source));
				model.add(o, has_source, MainClass.connection.getValueFactory().createLiteral(source));
				
				if(model.size() >= MAX_MODEL)
				{
					MainClass.connection.add(model);
					model.clear();
				}
				
				num -= 1;		
			}	
			MainClass.connection.add(model);
			MainClass.connection.commit();
		}
		catch(Throwable e) {
			MainClass.connection.rollback();
			e.printStackTrace();
		}
		
	}
	
	protected int randomInt(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	protected String createRandomDate(int startYear, int endYear) 
	{
		int day = randomInt(1,28);
		int month = randomInt(1,12);
		int year = randomInt(startYear,endYear);
		return String.format("%02d", day) + "-" + String.format("%02d", month) + "-" + String.valueOf(year);
	}
}
