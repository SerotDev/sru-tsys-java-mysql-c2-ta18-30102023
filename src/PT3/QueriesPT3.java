package PT3;

import db_connector.ConnectionMysql;

public class QueriesPT3 {
	public static void run() {
		//instanciate the class and open connection
		ConnectionMysql mySQL = new ConnectionMysql();
		mySQL.openConnection();
		
		//it deletes database if exists and create if not exist
		System.out.println();//enter
		String databaseName = "PT3";
		mySQL.dropDatabase(databaseName);
		mySQL.createDatabase(databaseName);
		
		//create tables
		System.out.println();//enter
		mySQL.createTable(databaseName, "ALMACENES", "codigo INT AUTO_INCREMENT PRIMARY KEY, lugar VARCHAR(100) NOT NULL, capacidad INT");
		mySQL.createTable(databaseName, "CAJAS", "numReferencia CHAR(5) PRIMARY KEY, contenido VARCHAR(100), valor INT, almacen INT,"
				+ "FOREIGN KEY (almacen) REFERENCES ALMACENES (codigo) ON DELETE CASCADE ON UPDATE CASCADE");
		
  		//insert data to tables
		System.out.println();//enter
		mySQL.insertData(databaseName, "ALMACENES", ""
				+ "(\"Tarragona\",2),"
				+ "(\"Castellon\",3),"
				+ "(\"Zaragoza\",1),"
				+ "(\"Madrid\",4),"
				+ "(\"Barcelona\", 5)");
		mySQL.insertData(databaseName, "CAJAS", ""
				+ "(\"AAAAA\",\"Play Station 5 Pro\",690,1),"
				+ "(\"AAAAB\",\"Portatil Asus XP10\",1500,4),"
				+ "(\"AAAAC\",\"Manta Termica X300\",15,3),"
				+ "(\"AAAAD\",\"Taladro industrial\",38,1),"
				+ "(\"AAAAE\",\"Dentadura Halloween\",8,4)");
		
		//show data
		mySQL.printTableContent(databaseName, "ALMACENES");
		mySQL.printTableContent(databaseName, "CAJAS");
		
		//delete data
		System.out.println();//enter
		mySQL.deleteData(databaseName, "ALMACENES", "codigo = 4");
		
		//show data
		mySQL.printTableContent(databaseName, "ALMACENES");
		mySQL.printTableContent(databaseName, "CAJAS");
		
		//close connection
		mySQL.closeConnection();
	}
}
