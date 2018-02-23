package br.edu.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class conectorUtil {

	private static ResourceBundle configDB = ResourceBundle.getBundle(Constants.CONECTOR_BD_PROPERTIES);

	public static Connection getConection() throws ClassNotFoundException, SQLException {
		Class.forName(configDB.getString(Constants.CONECTOR_BD_DRIVER));
		return DriverManager.getConnection(
				configDB.getString(Constants.CONECTOR_BD_URL),
				configDB.getString(Constants.CONECTOR_BD_USER), 
				configDB.getString(Constants.CONECTOR_BD_PASS));
	}

	public static void main(String[] args) {
		try {
			System.out.println(getConection());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
