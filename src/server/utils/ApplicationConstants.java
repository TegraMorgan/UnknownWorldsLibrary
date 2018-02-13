package server.utils;

public final class ApplicationConstants {

	public final String DB_NAME = "projectDB";
	public final String DB_DATASOURCE = "DB_DATASOURCE";
	public final String DB_CONTEXT = "java:comp/env/jdbc/ExampleDatasource";
	public final String PROTOCOL = "jdbc:derby:"; 
	public final String OPEN = "Open";
	public final String SHUTDOWN = "Shutdown";
	public final String FILE_FORMAT = ".json";

	private ApplicationConstants() {};
}
