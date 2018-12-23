package query;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.query.TupleQuery;

import main.MainClass;
import utility.FileReaderAndWriter;


// this class is used to query to the database
public class QueryInfo {
	
	protected String source = "data\\";
	BufferedWriter writer = null;
	BufferedReader reader = null;
	
	//constructor, create instance for writer and reader
	public QueryInfo() {
		writer = FileReaderAndWriter.writer(source + "query//output.txt", false);
		reader = FileReaderAndWriter.reader(source + "query//input.txt");
	}
	
	/*
	*	this will read the data/query/input.txt, query that file and store result in data/query/output.txt
	*	and return query time
	**/
	public void query() {
		long start;
		long end;
		String queryString = new String();
		String line = null;
		try {
			while((line = reader.readLine()) != null) {
				queryString += line;
			}
			start = System.currentTimeMillis();

			TupleQuery query = MainClass.connection.prepareTupleQuery(queryString);
			List<BindingSet> result = QueryResults.asList(query.evaluate());
			
			end = System.currentTimeMillis();
			int count = 1;
			for(BindingSet t : result) {
				writer.write("No." + count + "\n");
				count += 1;
				writer.write(OutputFormat.outputFormat(t.toString()));
				//writer.write(t.toString());

			}
			writer.append("\nThời gian truy vấn: " + (end - start) + " ms");
			writer.close();
			reader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	//this will use the queryString to query and store result in data/query/output.txt
	public void query(String queryString) {
		try {
			long start;
			long end;
			start = System.currentTimeMillis();
			TupleQuery query = MainClass.connection.prepareTupleQuery(queryString);
			List<BindingSet> result = QueryResults.asList(query.evaluate());
			end = System.currentTimeMillis();
			int count = 1;
			for(BindingSet t : result) {
				writer.write("No." + count + "\n");
				count += 1;
				writer.write(OutputFormat.outputFormat(t.toString()));
			}
			writer.append("\nThời gian truy vấn: " + (end - start) + " ms");
			writer.close();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
