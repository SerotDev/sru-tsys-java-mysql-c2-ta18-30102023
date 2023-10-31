package PT1;

import db_connector.ConnectionMysql;

public class QueriesPT1 {
	public static void run() {
		//instanciate the class and open connection
		ConnectionMysql mySQL = new ConnectionMysql();
		mySQL.openConnection();
		
		//it deletes database if exists and create if not exist
		System.out.println();//enter
		String databaseName = "PT1";
		mySQL.dropDatabase(databaseName);
		mySQL.createDatabase(databaseName);
		
		//create tables
		System.out.println();//enter
		mySQL.createTable(databaseName, "FABRICANTES", "codigo INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100) NOT NULL");
		mySQL.createTable(databaseName, "ARTICULOS", "codigo INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100) NOT NULL, precio INT, fabricante INT,"
				+ "FOREIGN KEY (fabricante) REFERENCES FABRICANTES (codigo) ON DELETE CASCADE ON UPDATE CASCADE");
		
		//insert data to tables
		System.out.println();//enter
		String query = "";
		for (int i = 1; i < 10; i++) {
			if (i == 1) {
				query += "(\"Fabricante " + i + "\")"; //insert fabricante 1
			} else {
				query += ",(\"Fabricante " + i + "\")"; //insert fabricante 2 to 10
			}
		}
		mySQL.insertData(databaseName, "FABRICANTES", query);
		mySQL.insertData(databaseName, "ARTICULOS", "(\"Sony Xperia Z\", 120, 1),"
				+ "(\"Sony Lambda\", 141, 1),"
				+ "(\"Mi 3 plus X\",220,2),"
				+ "(\"Mi 4 Compact\",113,2),"
				+ "(\"LG Plus Note\",415,4),"
				+ "(\"LG Mariloli\",180,4),"
				+ "(\"LG Maripili\",215,4)");
		
		//show data
		mySQL.printTableContent(databaseName, "FABRICANTES");
		mySQL.printTableContent(databaseName, "ARTICULOS");
		
		//delete data
		System.out.println();//enter
		mySQL.deleteData(databaseName, "ARTICULOS", "fabricante = 4");
		
		//show data
		mySQL.printTableContent(databaseName, "FABRICANTES");
		mySQL.printTableContent(databaseName, "ARTICULOS");
		
		//close connection
		mySQL.closeConnection();
	}
}
