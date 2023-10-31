package PT4;

import db_connector.ConnectionMysql;

public class QueriesPT4 {
	public static void run() {
		//instanciate the class and open connection
		ConnectionMysql mySQL = new ConnectionMysql();
		mySQL.openConnection();
		
		//it deletes database if exists and create if not exist
		System.out.println();//enter
		String databaseName = "PT4";
		mySQL.dropDatabase(databaseName);
		mySQL.createDatabase(databaseName);
		
		//create tables
		System.out.println();//enter
		mySQL.createTable(databaseName, "PELICULAS", "codigo INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100) NOT NULL, calificacionEdad INT");
		mySQL.createTable(databaseName, "SALAS", "codigo INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100) NOT NULL, pelicula INT,"
				+ "FOREIGN KEY (pelicula) REFERENCES PELICULAS (codigo) ON DELETE SET NULL ON UPDATE CASCADE");
		
  		//insert data to tables
		System.out.println();//enter
		mySQL.insertData(databaseName, "PELICULAS", ""
				+ "(\"Citizen Kane\",7),"
				+ "(\"Singin in the Rain\",0),"
				+ "(\"The Wizard of Oz\",0),"
				+ "(\"The Quiet Man\",0),"
				+ "(\"North by Northwest\",0),"
				+ "(\"The Last Tango in Paris\",17),"
				+ "(\"Some Like it Hot\",13),"
				+ "(\"A Night at the Opera\",0),"
				+ "(\"Citizen King\",0)");
		mySQL.insertData(databaseName, "SALAS", ""
				+ "(\"Odeon\",1),"
				+ "(\"Imperial\",4),"
				+ "(\"Majestic\",5),"
				+ "(\"Royale\",6),"
				+ "(\"Paraiso\",7),"
				+ "(\"Nickelodeon\",8)");
		
		//show data
		mySQL.printTableContent(databaseName, "PELICULAS");
		mySQL.printTableContent(databaseName, "SALAS");
		
		//delete data
		System.out.println();//enter
		mySQL.deleteData(databaseName, "PELICULAS", "codigo = 1");
		mySQL.deleteData(databaseName, "PELICULAS", "codigo = 4");
		
		//show data
		mySQL.printTableContent(databaseName, "PELICULAS");
		mySQL.printTableContent(databaseName, "SALAS");
		
		//close connection
		mySQL.closeConnection();
	}
}
