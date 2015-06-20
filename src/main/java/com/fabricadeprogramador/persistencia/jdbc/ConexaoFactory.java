package com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnection(){
		try{
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/cjweb","postgres","95009800");
		}catch(SQLException ex){
			throw new RuntimeException("Nao Conectou com o Banco", ex);
		}
	}
}
