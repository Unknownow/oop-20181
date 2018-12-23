package graphdb;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;

//this class is used to create connection to database.
public class CreateConnection {
	private static final String GRAPHDB_SERVER = "http://localhost:7200/";
	private static final String REPOSITORY_ID = "OOP_Project";
	
	
	
	public static RepositoryConnection getRepositoryConnection() {
		Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
		repository.initialize();
		RepositoryConnection repositoryConnection = repository.getConnection();
		return repositoryConnection;
	}
	
	public static RepositoryConnection getRepositoryConnection(String repoID) {
		Repository repository = new HTTPRepository(GRAPHDB_SERVER, repoID);
		repository.initialize();
		RepositoryConnection repositoryConnection = repository.getConnection();
		return repositoryConnection;
	}
}
