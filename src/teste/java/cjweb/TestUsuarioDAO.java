package cjweb;

import com.fabricadeprogramador.persistencia.dao.UsuarioDAO;
import com.fabricadeprogramador.persistencia.entidade.Usuario;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		
		testaCadastrar();
		System.out.println("Cadastrado com sucesso");

	}

	public static void testaCadastrar(){
		
		Usuario usuCad = new Usuario();
		usuCad.setNome("Carlos");
		usuCad.setLogin("CAR");
		usuCad.setSenha("123");
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.cadastrar(usuCad);
		
	}
}
