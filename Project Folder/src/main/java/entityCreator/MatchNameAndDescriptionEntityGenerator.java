package entityCreator;

import org.eclipse.rdf4j.model.Model;

//this class is used to generate entity which have name and description matched
public class MatchNameAndDescriptionEntityGenerator extends EntityGenerator {
	public MatchNameAndDescriptionEntityGenerator(String type) {
		super(type);
	}
	
	@Override
	protected Model addNewEntityToModel(String tempID, int indexOfName)
	{	
		return builder.subject(prefix  + tempID)
		              .add("object:"+"has_name", nameList.get(indexOfName))
		              .add("object:"+"has_description", descriptionList.get(indexOfName))
		              .build();
	}

}
