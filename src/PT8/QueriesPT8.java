package PT8;

import db_connector.ConnectionMysql;

public class QueriesPT8 {
	public static void run() {
		//instanciate the class and open connection
		ConnectionMysql mySQL = new ConnectionMysql();
		mySQL.openConnection();
		
		//it deletes database if exists and create if not exist
		System.out.println();//enter
		String databaseName = "PT8";
		mySQL.dropDatabase(databaseName);
		mySQL.createDatabase(databaseName);
		
		//create tables
		System.out.println();//enter
		mySQL.createTable(databaseName, "PRODUCTOS", "codigo INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100), precio INT");
		mySQL.createTable(databaseName, "CAJEROS", "codigo INT AUTO_INCREMENT PRIMARY KEY, nomApels VARCHAR(255)");
		mySQL.createTable(databaseName, "MAQUINAS_REGISTRADORAS", "codigo INT AUTO_INCREMENT PRIMARY KEY, piso INT");
		mySQL.createTable(databaseName, "VENTA", "cajero INT, maquina INT, producto INT,"
				+ "PRIMARY KEY (cajero, maquina, producto),"
				+ "FOREIGN KEY (cajero) REFERENCES CAJEROS (codigo) ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "FOREIGN KEY (maquina) REFERENCES MAQUINAS_REGISTRADORAS (codigo) ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "FOREIGN KEY (producto) REFERENCES PRODUCTOS (codigo) ON DELETE CASCADE ON UPDATE CASCADE");
		
  		//insert data to tables
		System.out.println();//enter
		mySQL.insertData(databaseName, "PRODUCTOS", ""
				+ "(\"Platano\",1),"
				+ "(\"Silla Gamer\",120),"
				+ "(\"Televisor Lg\",413),"
				+ "(\"Lechuga\",1),"
				+ "(\"Libreta\",2),"
				+ "(\"CocaCola\",1)");
		mySQL.insertData(databaseName, "CAJEROS", ""
				+ "(\"Antonio Fernandez Moreno\"),"
				+ "(\"Jose Francisco Borbon\"),"
				+ "(\"Mike Matas Fernandez\"),"
				+ "(\"Sergi Rodriguez Utge\"),"
				+ "(\"Alba Fonts Capdevila\"),"
				+ "(\"Carla Ribas Perea\")");
		mySQL.insertData(databaseName, "MAQUINAS_REGISTRADORAS", ""
				+ "(1),"
				+ "(1),"
				+ "(2),"
				+ "(2),"
				+ "(3),"
				+ "(5)");
		mySQL.insertData(databaseName, "VENTA", ""
				+ "(1,1,1),"
				+ "(3,1,2),"
				+ "(2,2,3),"
				+ "(1,2,4),"
				+ "(5,2,5),"
				+ "(2,2,6),"
				+ "(1,3,1),"
				+ "(4,3,2),"
				+ "(4,5,3),"
				+ "(6,5,4)");
		
		//show data
		mySQL.printTableContent(databaseName, "PRODUCTOS");
		mySQL.printTableContent(databaseName, "CAJEROS");
		mySQL.printTableContent(databaseName, "MAQUINAS_REGISTRADORAS");
		mySQL.printTableContent(databaseName, "VENTA");
		
		//delete data
		System.out.println();//enter
		mySQL.deleteData(databaseName, "CAJEROS", "codigo = 3");
		mySQL.updateData(databaseName, "PRODUCTOS", "precio = precio + 1", "precio < 5");
		
		//show data
		mySQL.printTableContent(databaseName, "PRODUCTOS");
		mySQL.printTableContent(databaseName, "CAJEROS");
		mySQL.printTableContent(databaseName, "MAQUINAS_REGISTRADORAS");
		mySQL.printTableContent(databaseName, "VENTA");
		
		//close connection
		mySQL.closeConnection();
	}
}
