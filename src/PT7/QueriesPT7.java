package PT7;

import db_connector.ConnectionMysql;

public class QueriesPT7 {
	public static void run() {
		//instanciate the class and open connection
		ConnectionMysql mySQL = new ConnectionMysql();
		mySQL.openConnection();
		
		//it deletes database if exists and create if not exist
		System.out.println();//enter
		String databaseName = "PT7";
		mySQL.dropDatabase(databaseName);
		mySQL.createDatabase(databaseName);
		
		//create tables
		System.out.println();//enter
		mySQL.createTable(databaseName, "CIENTIFICOS", "dni VARCHAR(8) PRIMARY KEY, nomApels VARCHAR(255)");
		mySQL.createTable(databaseName, "PROYECTOS", "id CHAR(4) PRIMARY KEY, nombre VARCHAR(255), horas INT");
		mySQL.createTable(databaseName, "ASIGNADO_A", "cientifico VARCHAR(8), proyecto CHAR(4),"
				+ "PRIMARY KEY (cientifico, proyecto),"
				+ "FOREIGN KEY (cientifico) REFERENCES CIENTIFICOS (dni) ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "FOREIGN KEY (proyecto) REFERENCES PROYECTOS (id) ON DELETE CASCADE ON UPDATE CASCADE");
		
  		//insert data to tables
		System.out.println();//enter
		mySQL.insertData(databaseName, "CIENTIFICOS", ""
				+ "(\"11111111\",\"Antonio Fernandez Moreno\"),"
				+ "(\"11111112\",\"Jose Francisco Borbon\"),"
				+ "(\"11111113\",\"Mike Matas Fernandez\"),"
				+ "(\"11111114\",\"Alba Fonts Capdevila\"),"
				+ "(\"11111115\",\"Carla Ribas Perea\"),"
				+ "(\"11111116\",\"David Maza Olivar\"),"
				+ "(\"11111117\",\"Adrian Marconi Mendoza\"),"
				+ "(\"11111118\",\"Julia Cantos Ochoa\"),"
				+ "(\"11111119\",\"Daniel Sierra Robles\")");
		mySQL.insertData(databaseName, "PROYECTOS", ""
				+ "(\"AAAA\",\"Proyecto 1\",158),"
				+ "(\"AAAB\",\"Proyecto 2\",176),"
				+ "(\"AAAC\",\"Proyecto 3\",257),"
				+ "(\"AAAD\",\"Proyecto 4\",258),"
				+ "(\"AAAE\",\"Proyecto 5\",679),"
				+ "(\"AAAF\",\"Proyecto 6\",861),"
				+ "(\"AAAG\",\"Proyecto 7\",375),"
				+ "(\"AAAH\",\"Proyecto 8\",284),"
				+ "(\"AAAI\",\"Proyecto 9\",965)");
		mySQL.insertData(databaseName, "ASIGNADO_A", ""
				+ "(\"11111113\",\"AAAC\"),"
				+ "(\"11111114\",\"AAAC\"),"
				+ "(\"11111115\",\"AAAA\"),"
				+ "(\"11111116\",\"AAAA\"),"
				+ "(\"11111111\",\"AAAA\"),"
				+ "(\"11111112\",\"AAAB\"),"
				+ "(\"11111117\",\"AAAB\"),"
				+ "(\"11111118\",\"AAAG\"),"
				+ "(\"11111119\",\"AAAG\")");
		
		//show data
		mySQL.printTableContent(databaseName, "CIENTIFICOS");
		mySQL.printTableContent(databaseName, "PROYECTOS");
		mySQL.printTableContent(databaseName, "ASIGNADO_A");
		
		//delete data
		System.out.println();//enter
		mySQL.deleteData(databaseName, "CIENTIFICOS", "dni = \"11111119\"");
		mySQL.updateData(databaseName, "ASIGNADO_A", "proyecto = \"AAAH\"", "proyecto = \"AAAA\"");
		
		//show data
		mySQL.printTableContent(databaseName, "CIENTIFICOS");
		mySQL.printTableContent(databaseName, "PROYECTOS");
		mySQL.printTableContent(databaseName, "ASIGNADO_A");
		
		//close connection
		mySQL.closeConnection();
	}
}
