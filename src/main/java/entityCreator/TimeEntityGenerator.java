package entityCreator;

/*
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.eclipse.rdf4j.model.impl.TreeModel;
import main.MainClass;
*/
import java.util.concurrent.ThreadLocalRandom;


public class TimeEntityGenerator extends MatchNameAndDescriptionEntityGenerator {
	public TimeEntityGenerator(String type) {
		super(type);
	}
		
	@Override
	protected String genTempID(int nameListIndex, int IDIndex) {
		String temp = new String(nameList.get(nameListIndex) + "-" +
								String.valueOf(
								ThreadLocalRandom.current().nextInt(2017, 2018 + 1)));
		return temp;
	}
}
