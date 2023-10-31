package PT5;

import db_connector.ConnectionMysql;

public class QueriesPT5 {
	public static void run() {
		//instanciate the class and open connection
		ConnectionMysql mySQL = new ConnectionMysql();
		mySQL.openConnection();
		
		//it deletes database if exists and create if not exist
		System.out.println();//enter
		String databaseName = "PT5";
		mySQL.dropDatabase(databaseName);
		mySQL.createDatabase(databaseName);
		
		//create tables
		System.out.println();//enter
		mySQL.createTable(databaseName, "DESPACHOS", "numero INT AUTO_INCREMENT PRIMARY KEY, capacidad INT");
		mySQL.createTable(databaseName, "DIRECTORES", "dni VARCHAR(8) PRIMARY KEY, nomApels VARCHAR(255) NOT NULL, dniJefe VARCHAR(8), despacho INT,"
				+ "FOREIGN KEY (dniJefe) REFERENCES DIRECTORES (dni) ON DELETE SET NULL ON UPDATE CASCADE,"
				+ "FOREIGN KEY (despacho) REFERENCES DESPACHOS (numero) ON DELETE SET NULL ON UPDATE CASCADE");
		
  		//insert data to tables
		System.out.println();//enter
		mySQL.insertData(databaseName, "DESPACHOS", ""
				+ "(3),"
				+ "(2),"
				+ "(5),"
				+ "(2),"
				+ "(3)");
		mySQL.insertData(databaseName, "DIRECTORES", ""
				+ "(\"11111111\",\"Sergi Rodriguez Utge\",null,1),"
				+ "(\"22222222\",\"Antonio Rodriguez Gonzalez\",\"11111111\",2),"
				+ "(\"33333333\",\"Maria Peinado Ramos\",\"11111111\",4),"
				+ "(\"44444444\",\"Julia Cortes Molina\",\"22222222\",5),"
				+ "(\"55555555\",\"Jose Perez Navarro\",\"22222222\",3),"
				+ "(\"66666666\",\"Pedro Valero Gimenez\",\"11111111\",4),"
				+ "(\"77777777\",\"Antonio Ochoa Gil\",\"22222222\",4),"
				+ "(\"88888888\",\"Nerea Prados Mata\",\"33333333\",1),"
				+ "(\"99999999\",\"Juan Peralta Mota\",\"33333333\",1)");
		
		//show data
		mySQL.printTableContent(databaseName, "DESPACHOS");
		mySQL.printTableContent(databaseName, "DIRECTORES");
		
		//delete data
		System.out.println();//enter
		mySQL.deleteData(databaseName, "DESPACHOS", "numero = 5");
		mySQL.updateData(databaseName, "DIRECTORES", "dniJefe = \"22222222\"", "dniJefe = \"33333333\"");
		
		//show data
		mySQL.printTableContent(databaseName, "DESPACHOS");
		mySQL.printTableContent(databaseName, "DIRECTORES");
		
		//close connection
		mySQL.closeConnection();
	}
}
