package com.fabricadeprogramador.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fabricadeprogramador.persistencia.entidade.Usuario;
import com.fabricadeprogramador.persistencia.jdbc.ConexaoFactory;

public class UsuarioDAO {

		private Connection con = ConexaoFactory.getConnection();
		
		public void cadastrar(Usuario usu){
			
			String sql = "INSERT INTO usuario (nome,login,senha) VALUES (?,?,md5(?))";
			try (PreparedStatement preparador = con.prepareStatement(sql)){
				
				preparador.setString(1, usu.getNome());
				preparador.setString(2, usu.getLogin());
				preparador.setString(3, usu.getSenha());
				
				preparador.execute();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			
			}
		}
	
	
}
