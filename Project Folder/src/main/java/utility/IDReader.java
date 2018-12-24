package utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


//this class is used to read ID from file for create relationship
public class IDReader {
	public ArrayList<String> personID = new ArrayList<String>();
	public ArrayList<String> organizationID = new ArrayList<String>();
	public ArrayList<String> locationID = new ArrayList<String>();
	public ArrayList<String> countryID = new ArrayList<String>();
	public ArrayList<String> eventID = new ArrayList<String>();
	public ArrayList<String> timeID = new ArrayList<String>();
	
	private String source = new String("data\\");
	
	public IDReader() {
		BufferedReader reader;
		String line = null;
		try {
			//read person id to personID
			reader = FileReaderAndWriter.reader(source + "person\\id.txt");
			while((line = reader.readLine()) != null)
			{
				line = line.replaceAll(" ", "_");
				personID.add(line);
			}
			reader.close();
			
			//read organization id to organizationID
			reader = FileReaderAndWriter.reader(source + "organization\\id.txt");
			while((line = reader.readLine()) != null)
			{
				line = line.replaceAll(" ", "_");
				organizationID.add(line);
			}
			reader.close();
			
			//read location id to locationID
			reader = FileReaderAndWriter.reader(source + "location\\id.txt");
			while((line = reader.readLine()) != null)
			{
				line = line.replaceAll(" ", "_");
				locationID.add(line);
			}
			reader.close();
			
			//read country id to countryID
			reader = FileReaderAndWriter.reader(source + "country\\id.txt");
			while((line = reader.readLine()) != null)
			{
				line = line.replaceAll(" ", "_");
				countryID.add(line);
			}
			reader.close();
			
			//read event id to eventID
			reader = FileReaderAndWriter.reader(source + "event\\id.txt");
			while((line = reader.readLine()) != null)
			{
				line = line.replaceAll(" ", "_");
				eventID.add(line);
			}
			reader.close();
			
			//read time id to timeID
			reader = FileReaderAndWriter.reader(source + "time\\id.txt");
			while((line = reader.readLine()) != null)
			{
				line = line.replaceAll(" ", "_");
				timeID.add(line);
			}
			reader.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String genRandomID(String type) {
		type = type.toLowerCase();
		if(type.equals("person")) {
			return personID.get(randomInt(0, personID.size() - 1));
		}
		else if(type.equals("organization")) {
			return organizationID.get(randomInt(0, organizationID.size() - 1));
		}
		else if(type.equals("location")) {
			return locationID.get(randomInt(0, locationID.size() - 1));
		}
		else if(type.equals("country")) {
			return countryID.get(randomInt(0, countryID.size() - 1));
		}
		else if(type.equals("event")) {
			return eventID.get(randomInt(0, eventID.size() - 1));
		}
		else if(type.equals("time")) {
			return timeID.get(randomInt(0, timeID.size() - 1));
		}
		return null;
	}
	
	protected int randomInt(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}
