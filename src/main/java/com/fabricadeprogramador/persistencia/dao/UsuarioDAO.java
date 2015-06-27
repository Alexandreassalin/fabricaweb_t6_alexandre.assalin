package com.fabricadeprogramador.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.fabricadeprogramador.persistencia.entidade.Usuario;
import com.fabricadeprogramador.persistencia.jdbc.ConexaoFactory;

public class UsuarioDAO {

	Connection con = ConexaoFactory.getConnection();

	public void cadastrar(Usuario usu) {

		String sql = "INSERT INTO usuario (nome,login,senha) VALUES (?,?,md5(?))";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());

			preparador.execute();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public void alterar(Usuario usu) {

		String sql = "UPDATE usuario SET nome = ?, login = ?, senha = md5(?) WHERE id = ?";
		// Pegar a Conexão

		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());

			preparador.execute();

		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao alterar o usuario", ex);
		}

	}

	public void excluir(Integer id) {

		// Montar o sql
		String sql = "DELETE FROM usuario  WHERE id = ?";
		// Pegando o statement

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id);

			preparador.execute();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir", e);
		}

	}

	public void salvar(Usuario usu) {

		if (usu.getId() != null && usu.getId() > 0) {

			alterar(usu);

		} else {
			cadastrar(usu);
		}

	}

	public Usuario buscarPorId(Integer id) {

		// Montar o sql
		String sql = "SELECT * FROM  usuario  WHERE id = ?";
		// Pegando o statement

		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			preparador.setInt(1, id);

			ResultSet result = preparador.executeQuery();

			if (result.next()) {

				Usuario usuRetorno = new Usuario();
				usuRetorno.setNome(result.getString("nome"));
				usuRetorno.setLogin(result.getString("login"));
				usuRetorno.setSenha(result.getString("senha"));
				usuRetorno.setId(result.getInt("id"));

				return usuRetorno;

			} else {

				System.out.println("Não Existe usuario com essa id!!!");
				return null;
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao Buscar Id", e);
		}

	}

	public List<Usuario> buscarTodos() {

		// Montar o sql
		String sql = "SELECT * FROM  usuario ORDER BY id ASC";//Order By metodo de ordenação Banco de dados
		
		// Pegando o statement

		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			List<Usuario> listaRetorno = new ArrayList<Usuario>();
			ResultSet result = preparador.executeQuery();

			while (result.next()) {

				Usuario usuRetorno = new Usuario();
				usuRetorno.setNome(result.getString("nome"));
				usuRetorno.setLogin(result.getString("login"));
				usuRetorno.setSenha(result.getString("senha"));
				usuRetorno.setId(result.getInt("id"));

				listaRetorno.add(usuRetorno);
			}
			return listaRetorno;

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao Buscar Todos", e);
		}

	}
	
	public Usuario autenticar(Usuario usu){
		// Montar o sql
		String sql = "SELECT * FROM  usuario WHERE login = ? AND senha = md5(?)";
		// Pegando o statement

				try (PreparedStatement preparador = con.prepareStatement(sql)) {

				
					preparador.setString(1, usu.getLogin());
					preparador.setString(2, usu.getSenha());
				
					ResultSet result = preparador.executeQuery();

						
					if (result.next()) {

						Usuario usuRetorno = new Usuario();
						usuRetorno.setNome(result.getString("nome"));
						usuRetorno.setLogin(result.getString("login"));
						usuRetorno.setSenha(result.getString("senha"));
						usuRetorno.setId(result.getInt("id"));

						return usuRetorno;

					} else {

						System.out.println("Login incorreto ou senha incorretos!!!");
						return null;
					}


				} catch (SQLException e) {
					throw new RuntimeException("Erro ao Autenticar", e);
				}
		
		
	}
	
}
