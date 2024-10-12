package br.com.restaurante.sistemadelivery.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

		private static ConexaoBD instance;
		private static  Connection conn;
		
		// variáveis da conexão
		
		private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		private final String USER = "rm98480";
		private final String PASSWORD = "280602";
		
		private ConexaoBD() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Conexão estabelecida");
			} catch(ClassNotFoundException e) {
				System.err.println("Driver não encontrado " + e.getMessage());
			} catch(SQLException e) {
				System.err.println("Falha na conexão" + e.getMessage());
			}
		}
		
		
		public static synchronized ConexaoBD getInstance() {
			if(instance == null) {
				instance = new ConexaoBD();
			}
			return instance;
		}
		
		public static Connection getConnection() {
			return conn;
		}
	} 


