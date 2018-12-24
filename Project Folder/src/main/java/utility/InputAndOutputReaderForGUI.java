package utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


//this class is used to 
public class InputAndOutputReaderForGUI {
	final String SOURCE = "data\\query\\";
	
	//those lists is used to store name and query string for 
	public ArrayList<String> basicQueryName = new ArrayList<String>();
	public ArrayList<String> advancedQueryName = new ArrayList<String>();
	public ArrayList<String> basicQueryString = new ArrayList<String>();
	public ArrayList<String> advancedQueryString = new ArrayList<String>();
	
	
	//constructor
	public InputAndOutputReaderForGUI() {
		BufferedReader reader;
		String line = new String();
		String temp = new String();
		try {
			reader = FileReaderAndWriter.reader(SOURCE + "basicQuery.txt");
			while((line = reader.readLine()) != null) {
				if(line.charAt(0) == '[') {
					basicQueryName.add(line.substring(1));
					continue;
				}
				else if(line.charAt(0) == ']') {
					temp = new String();
					temp += line.substring(1);
					continue;
				}
				else if(line.charAt(line.length() - 1) == '[') {
					temp += line.substring(0, line.length() - 1);
					basicQueryString.add(temp);
					continue;
				}
				temp += line;
			}
			reader.close();

			reader = FileReaderAndWriter.reader(SOURCE + "advancedQuery.txt");
			while((line = reader.readLine()) != null) {
				if(line.charAt(0) == '[') {
					advancedQueryName.add(line.substring(1));
					continue;
				}
				else if(line.charAt(0) == ']') {
					temp = new String();
					temp += line.substring(1);
					continue;
				}
				else if(line.charAt(line.length() - 1) == '[') {
					temp += line.substring(0, line.length() - 1);
					advancedQueryString.add(temp);
					continue;
				}
				temp += line;
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
	
	
	// This method will return a string contain the output file.
	public StringBuffer outputReader() {
		BufferedReader reader;
		StringBuffer output = new StringBuffer();
		String line = new String();

		reader = FileReaderAndWriter.reader(SOURCE + "output.txt");
		try {
			while((line = reader.readLine()) != null) 
				output.append(line + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
}
