package PT2;

import db_connector.ConnectionMysql;

public class QueriesPT2 {
	public static void run() {
		//instanciate the class and open connection
		ConnectionMysql mySQL = new ConnectionMysql();
		mySQL.openConnection();
		
		//it deletes database if exists and create if not exist
		System.out.println();//enter
		String databaseName = "PT2";
		mySQL.dropDatabase(databaseName);
		mySQL.createDatabase(databaseName);
		
		//create tables
		System.out.println();//enter
		mySQL.createTable(databaseName, "DEPARTAMENTOS", "codigo INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100) NOT NULL, presupuesto INT");
		mySQL.createTable(databaseName, "EMPLEADOS", "dni VARCHAR(8) PRIMARY KEY, nombre VARCHAR(100) NOT NULL, apellidos VARCHAR(255) NOT NULL, departamento INT,"
				+ "FOREIGN KEY (departamento) REFERENCES DEPARTAMENTOS (codigo) ON DELETE SET NULL ON UPDATE CASCADE");
		
  		//insert data to tables
		System.out.println();//enter
		mySQL.insertData(databaseName, "DEPARTAMENTOS", ""
				+ "(\"Departamento 1\",20000),"
				+ "(\"Departamento 2\",46000),"
				+ "(\"Departamento 3\",50000),"
				+ "(\"Departamento 4\",10),"
				+ "(\"Departamento 5\",9000)");
		mySQL.insertData(databaseName, "EMPLEADOS", ""
				+ "(\"11111111\",\"Sergi\",\"Rodriguez Utge\",1),"
				+ "(\"22222222\",\"Marc\",\"Gonzalez Verges\",3),"
				+ "(\"33333333\",\"Josep\",\"Matas Prados\",4),"
				+ "(\"44444444\",\"Julia\",\"Pla Agramunt\",3),"
				+ "(\"55555555\",\"David\",\"Fernandez Gonzalez\",1)");
		
		//show data
		mySQL.printTableContent(databaseName, "DEPARTAMENTOS");
		mySQL.printTableContent(databaseName, "EMPLEADOS");
		
		//delete data
		System.out.println();//enter
		mySQL.deleteData(databaseName, "DEPARTAMENTOS", "codigo = 4");
		
		//show data
		mySQL.printTableContent(databaseName, "DEPARTAMENTOS");
		mySQL.printTableContent(databaseName, "EMPLEADOS");
		
		//close connection
		mySQL.closeConnection();
	}
}
