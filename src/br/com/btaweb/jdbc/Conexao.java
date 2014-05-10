package br.com.btaweb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static String url = "jdbc:postgresql://localhost:5432/dbtest";
	private static String user = "postgres";
	private static String senha = "qwe123";

	
	public Connection getConnection(){
		try {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return DriverManager.getConnection(url, user, senha);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
