package entityCreator;

import java.util.concurrent.ThreadLocalRandom;



public class EntitiesGeneratorForDatabase {
	EntityGenerator person;
	EntityGenerator location;
	EntityGenerator organization;
	EntityGenerator country;
	EntityGenerator event;
	EntityGenerator time;
	
	/*	This method is used to create entities for database!
	 * @param: num == number of entities
	 */
	public void generateEntitiesForDatabase(int num)
	{
		int numOfEntity = ThreadLocalRandom.current().nextInt(num / 3, num / 2 + 1);
		num -= numOfEntity;
		person = new EntityGenerator("person");
		person.entityCreator(numOfEntity);
		
		
		numOfEntity = ThreadLocalRandom.current().nextInt(num / 3, num / 2 + 1);
		if(numOfEntity <= 0)
			return;
		num -= numOfEntity;
		location = new EntityGenerator("location");
		location.entityCreator(numOfEntity);
		
		
		numOfEntity = ThreadLocalRandom.current().nextInt(num / 3, num / 2 + 1);
		if(numOfEntity <= 0)
			return;
		num -= numOfEntity;
		organization = new EntityGenerator("organization");
		organization.entityCreator(numOfEntity);
		
		
		numOfEntity = ThreadLocalRandom.current().nextInt(num / 3, num / 2 + 1);
		if(numOfEntity <= 0)
			return;
		num -= numOfEntity;
		country = new MatchNameAndDescriptionEntityGenerator("country");
		country.entityCreator(numOfEntity);
		
		
		numOfEntity = ThreadLocalRandom.current().nextInt(num / 3, num / 2 + 1);
		if(numOfEntity <= 0)
			return;
		num -= numOfEntity;
		event = new MatchNameAndDescriptionEntityGenerator("event");
		event.entityCreator(numOfEntity);
		
		if(num <= 0)
			return;
		time = new TimeEntityGenerator("time");
		time.entityCreator(num);

	}
}
