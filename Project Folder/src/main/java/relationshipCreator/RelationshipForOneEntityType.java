package relationshipCreator;

//this class is used to create *num relationship of one type
public class RelationshipForOneEntityType {
	private String type;
	
	RelationshipGenerator person;
	RelationshipGenerator location;
	RelationshipGenerator organization;
	RelationshipGenerator country;
	RelationshipGenerator event;
	RelationshipGenerator time;
	
	public RelationshipForOneEntityType(String type) {
		type = type.toLowerCase();
		this.type = new String(type);
	}
	
	public void relationshipGenerator(int num)
	{
		if(type.equals("time")) {
			int numOfRelationship = num/5;
			person = new RelationshipGenerator(type, "person");
			person.relationshipGenerator(numOfRelationship);
			location = new RelationshipGenerator(type, "location");
			location.relationshipGenerator(numOfRelationship);
			organization = new RelationshipGenerator(type, "organization");
			organization.relationshipGenerator(numOfRelationship);
			country = new RelationshipGenerator(type, "country");
			country.relationshipGenerator(numOfRelationship);
			event = new RelationshipGenerator(type, "event");
			event.relationshipGenerator(num - (num / 5) * 4);
			return;
		}
			
		int numOfRelationship = num/6;
		person = new RelationshipGenerator(type, "person");
		person.relationshipGenerator(numOfRelationship);
		location = new RelationshipGenerator(type, "location");
		location.relationshipGenerator(numOfRelationship);
		organization = new RelationshipGenerator(type, "organization");
		organization.relationshipGenerator(numOfRelationship);
		country = new RelationshipGenerator(type, "country");
		country.relationshipGenerator(numOfRelationship);
		event = new RelationshipGenerator(type, "event");
		event.relationshipGenerator(numOfRelationship);
		time = new RelationshipGenerator(type, "time");
		time.relationshipGenerator(num - (num / 6) * 5);
		return;
	}
}
