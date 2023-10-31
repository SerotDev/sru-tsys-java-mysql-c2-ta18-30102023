package PT6;

import db_connector.ConnectionMysql;

public class QueriesPT6 {
	public static void run() {
		//instanciate the class and open connection
		ConnectionMysql mySQL = new ConnectionMysql();
		mySQL.openConnection();
		
		//it deletes database if exists and create if not exist
		System.out.println();//enter
		String databaseName = "PT6";
		mySQL.dropDatabase(databaseName);
		mySQL.createDatabase(databaseName);
		
		//create tables
		System.out.println();//enter
		mySQL.createTable(databaseName, "PROVEEDORES", "id CHAR(4) PRIMARY KEY, nombre VARCHAR(100)");
		mySQL.createTable(databaseName, "PIEZAS", "codigo INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100)");
		mySQL.createTable(databaseName, "SUMINISTRA", "codigoPieza INT, idProveedor CHAR(4), precio INT,"
				+ "PRIMARY KEY (codigoPieza, idProveedor),"
				+ "FOREIGN KEY (codigoPieza) REFERENCES PIEZAS (codigo) ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "FOREIGN KEY (idProveedor) REFERENCES PROVEEDORES (id) ON DELETE CASCADE ON UPDATE CASCADE");
		
  		//insert data to tables
		System.out.println();//enter
		mySQL.insertData(databaseName, "PROVEEDORES", ""
				+ "(\"AAAA\",\"MSI\"),"
				+ "(\"AAAB\",\"Siemens\"),"
				+ "(\"AAAC\",\"Nvidia\"),"
				+ "(\"AAAD\",\"Intel\"),"
				+ "(\"AAAE\",\"AMD\"),"
				+ "(\"AAAF\",\"Sapphire\")");
		mySQL.insertData(databaseName, "PIEZAS", ""
				+ "(\"MSI Radeon RX\"),"
				+ "(\"Tesla K80\"),"
				+ "(\"Ventus 2X\"),"
				+ "(\"GeForce RTX 4060\"),"
				+ "(\"GeForce RTX 3070\"),"
				+ "(\"Core i9\"),"
				+ "(\"Core i7\"),"
				+ "(\"Ryzen 7\"),"
				+ "(\"Ryzen 5\"),"
				+ "(\"Pulse AMD\")");
		mySQL.insertData(databaseName, "SUMINISTRA", ""
				+ "(1,\"AAAB\",324),"
				+ "(1,\"AAAE\",344),"
				+ "(2,\"AAAC\",723),"
				+ "(3,\"AAAC\",164),"
				+ "(4,\"AAAC\",163),"
				+ "(5,\"AAAC\",845),"
				+ "(4,\"AAAA\",852),"
				+ "(5,\"AAAA\",181),"
				+ "(6,\"AAAD\",143),"
				+ "(7,\"AAAD\",272),"
				+ "(8,\"AAAE\",385),"
				+ "(9,\"AAAE\",357),"
				+ "(10,\"AAAF\",527),"
				+ "(10,\"AAAE\",724)");
		
		//show data
		mySQL.printTableContent(databaseName, "PROVEEDORES");
		mySQL.printTableContent(databaseName, "PIEZAS");
		mySQL.printTableContent(databaseName, "SUMINISTRA");
		
		//delete data
		System.out.println();//enter
		mySQL.deleteData(databaseName, "PIEZAS", "codigo = 10");
		mySQL.updateData(databaseName, "SUMINISTRA", "idProveedor = \"AAAA\"", "idProveedor = \"AAAD\"");
		
		//show data
		mySQL.printTableContent(databaseName, "PROVEEDORES");
		mySQL.printTableContent(databaseName, "PIEZAS");
		mySQL.printTableContent(databaseName, "SUMINISTRA");
		
		//close connection
		mySQL.closeConnection();
	}
}
