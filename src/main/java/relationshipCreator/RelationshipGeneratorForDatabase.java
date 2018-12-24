package relationshipCreator;



//this class is used to generate *num relationship for database
public class RelationshipGeneratorForDatabase {
	
	RelationshipForOneEntityType p;
	RelationshipForOneEntityType o;
	RelationshipForOneEntityType l;
	RelationshipForOneEntityType c;
	RelationshipForOneEntityType e;
	RelationshipForOneEntityType t;
	public void generateRelationshipForDatabase(int num)
	{
		int numOfEachRelationship = num / 6;
		
		p = new RelationshipForOneEntityType("person");
		p.relationshipGenerator(numOfEachRelationship);
		
		
		o = new RelationshipForOneEntityType("organization");
		o.relationshipGenerator(numOfEachRelationship);
		
		
		l = new RelationshipForOneEntityType("location");
		l.relationshipGenerator(numOfEachRelationship);
		
		
		c = new RelationshipForOneEntityType("country");
		c.relationshipGenerator(numOfEachRelationship);
		
		
		e = new RelationshipForOneEntityType("event");
		e.relationshipGenerator(numOfEachRelationship);
		
		
		t = new RelationshipForOneEntityType("time");
		if(num - (num / 6) * 5 > 0)
			t.relationshipGenerator(num - (num / 6) * 5);
	}
}
