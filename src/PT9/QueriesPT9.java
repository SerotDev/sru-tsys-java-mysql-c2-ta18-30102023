package PT9;

import db_connector.ConnectionMysql;

public class QueriesPT9 {
	public static void run() {
		//instanciate the class and open connection
		ConnectionMysql mySQL = new ConnectionMysql();
		mySQL.openConnection();
		
		//it deletes database if exists and create if not exist
		System.out.println();//enter
		String databaseName = "PT9";
		mySQL.dropDatabase(databaseName);
		mySQL.createDatabase(databaseName);
		
		//create tables
		System.out.println();//enter
		mySQL.createTable(databaseName, "FACULTAD", "codigo INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100)");
		mySQL.createTable(databaseName, "INVESTIGADORES", "dni VARCHAR(8) PRIMARY KEY, nomApels VARCHAR(255), facultad INT,"
				+ "FOREIGN KEY (facultad) REFERENCES FACULTAD (codigo) ON DELETE SET NULL ON UPDATE CASCADE");
		mySQL.createTable(databaseName, "EQUIPOS", "numSerie CHAR(4) PRIMARY KEY, nombre VARCHAR(100), facultad INT,"
				+ "FOREIGN KEY (facultad) REFERENCES FACULTAD (codigo) ON DELETE SET NULL ON UPDATE CASCADE");
		mySQL.createTable(databaseName, "RESERVA", "dni VARCHAR(8), numSerie CHAR(4), comienzo DATETIME, fin DATETIME,"
				+ "PRIMARY KEY (dni,numSerie),"
				+ "FOREIGN KEY (dni) REFERENCES INVESTIGADORES (dni) ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "FOREIGN KEY (numSerie) REFERENCES EQUIPOS (numSerie) ON DELETE CASCADE ON UPDATE CASCADE");
		
  		//insert data to tables
		System.out.println();//enter
		mySQL.insertData(databaseName, "FACULTAD", ""
				+ "(\"Facultad Pepito\"),"
				+ "(\"Facultad Antoniete Vila\"),"
				+ "(\"Nostrum Pupum\"),"
				+ "(\"Amigos de la Tierra\"),"
				+ "(\"Alegria Facutativa\"),"
				+ "(\"Facultad Josete\"),"
				+ "(\"Facultad de derecho\")");
		mySQL.insertData(databaseName, "INVESTIGADORES", ""
				+ "(\"11111111\",\"Antonio Fernandez Moreno\",1),"
				+ "(\"22222222\",\"Jose Francisco Borbon\",1),"
				+ "(\"33333333\",\"Mike Matas Fernandez\",1),"
				+ "(\"44444444\",\"Alba Fonts Capdevila\",3),"
				+ "(\"55555555\",\"Carla Ribas Perea\",3),"
				+ "(\"66666666\",\"David Maza Olivar\",3),"
				+ "(\"77777777\",\"Adrian Marconi Mendoza\",5),"
				+ "(\"88888888\",\"Julia Cantos Ochoa\",5),"
				+ "(\"99999999\",\"Daniel Sierra Robles\",5)");
		mySQL.insertData(databaseName, "EQUIPOS", ""
				+ "(\"ABCD\",\"Equipo 1\",1),"
				+ "(\"BCDE\",\"Equipo 2\",2),"
				+ "(\"CDEF\",\"Equipo 3\",3),"
				+ "(\"DEFG\",\"Equipo 4\",4),"
				+ "(\"EFGH\",\"Equipo 5\",5),"
				+ "(\"FGHI\",\"Equipo 6\",6),"
				+ "(\"GHIJ\",\"Equipo 7\",7)");
		mySQL.insertData(databaseName, "RESERVA", ""
				+ "(\"11111111\",\"ABCD\",\"2022-02-01 13:23:44\",\"2023-02-01 13:23:44\"),"
				+ "(\"22222222\",\"ABCD\",\"2022-03-01 13:23:44\",\"2023-03-01 13:23:44\"),"
				+ "(\"33333333\",\"ABCD\",\"2022-04-01 13:23:44\",\"2023-04-01 13:23:44\"),"
				+ "(\"44444444\",\"CDEF\",\"2022-03-01 13:23:44\",\"2022-06-01 13:23:44\"),"
				+ "(\"55555555\",\"CDEF\",\"2022-03-01 13:23:44\",\"2022-06-15 13:23:44\"),"
				+ "(\"66666666\",\"CDEF\",\"2022-03-01 13:23:44\",\"2022-06-15 13:23:44\"),"
				+ "(\"77777777\",\"EFGH\",\"2023-01-15 13:23:44\",\"2023-12-03 13:23:44\"),"
				+ "(\"88888888\",\"EFGH\",\"2023-02-15 13:23:44\",\"2023-12-03 13:23:44\"),"
				+ "(\"99999999\",\"EFGH\",\"2023-03-15 13:23:44\",\"2023-12-03 13:23:44\")");
		
		//show data
		mySQL.printTableContent(databaseName, "FACULTAD");
		mySQL.printTableContent(databaseName, "INVESTIGADORES");
		mySQL.printTableContent(databaseName, "EQUIPOS");
		mySQL.printTableContent(databaseName, "RESERVA");
		
		//delete data
		System.out.println();//enter
		mySQL.deleteData(databaseName, "FACULTAD", "codigo = 3");
		mySQL.updateData(databaseName, "INVESTIGADORES", "facultad = 2", "facultad = null");
		mySQL.updateData(databaseName, "EQUIPOS", "facultad = 2", "facultad = null");
		
		//show data
		mySQL.printTableContent(databaseName, "FACULTAD");
		mySQL.printTableContent(databaseName, "INVESTIGADORES");
		mySQL.printTableContent(databaseName, "EQUIPOS");
		mySQL.printTableContent(databaseName, "RESERVA");
		
		//close connection
		mySQL.closeConnection();
	}
}
