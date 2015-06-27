package cjweb;

import java.util.List;
import java.util.Scanner;

import com.fabricadeprogramador.persistencia.dao.UsuarioDAO;
import com.fabricadeprogramador.persistencia.entidade.Usuario;

public class TestUsuarioDAO {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		UsuarioDAO usuDao = new UsuarioDAO();
		Usuario usu = new Usuario();
		// testAlterar(teclado, usuDao, usu);
		// testExcluir(teclado, usu, usuDao);
		// testSalvar(teclado, usuDao, usu);
		// testBuscarPorId(teclado, usuDao);
		//testBuscarTodos(usuDao);
		testAutenticar(teclado, usuDao, usu);
		
		// no final fechar leitor
		teclado.close();
	}

	public static void testSalvar(Scanner teclado, UsuarioDAO usuDao,
			Usuario usu) {

		System.out.println("Id do Usuario");
		usu.setId(teclado.nextInt());

		System.out.println("Nome do Usuario");
		usu.setNome(teclado.next());

		System.out.println("login Usuario");
		usu.setLogin(teclado.next());

		System.out.println("senha do Usuario");
		usu.setSenha(teclado.next());

		usuDao.salvar(usu);
		System.out.println("Salvo com Sucesso");

	}

	public static void testAlterar(Scanner teclado, UsuarioDAO usuDao,
			Usuario usu) {

		System.out.println("Id do Usuario");
		usu.setId(teclado.nextInt());

		System.out.println("Nome do Usuario");
		usu.setNome(teclado.next());

		System.out.println("login Usuario");
		usu.setLogin(teclado.next());

		System.out.println("senha do Usuario");
		usu.setSenha(teclado.next());

		usuDao.alterar(usu);
		System.out.println("Alterado com Sucesso");

	}

	public static void testExcluir(Scanner teclado, Usuario usu,
			UsuarioDAO usuDao) {

		System.out.println("Id do Usuario");
		Integer id = teclado.nextInt();
		usuDao.excluir(id);
		System.out.println("Usuario com - " + id + " - Excluido com Sucesso");

	}

	public static void testBuscarPorId(Scanner teclado, UsuarioDAO usuDao) {

		System.out.println("Digite o Id do Usuario a ser buscado");
		Integer id = teclado.nextInt();

		Usuario usuRetornado = usuDao.buscarPorId(id);

		if (usuRetornado != null) {

			System.out.println("Nome:  " + usuRetornado.getNome());
			System.out.println("Login: " + usuRetornado.getLogin());
			System.out.println("Senha: " + usuRetornado.getSenha());
			System.out.println("Id: " + usuRetornado.getId());

		}
	}
	public static void testBuscarTodos(UsuarioDAO usuDao) {

		List<Usuario> lista= usuDao.buscarTodos();

		for(Usuario usu: lista) {
			System.out.println(" ");
			System.out.println("Id: " + usu.getId());
			System.out.println("Nome:  " + usu.getNome());
			System.out.println("Login: " + usu.getLogin());
			System.out.println("Senha: " + usu.getSenha());
			System.out.println(" ");
		}
	}
	public static void testAutenticar(Scanner teclado, UsuarioDAO usuDao, Usuario usu) {

		System.out.println("Digite o Login do Usuario: ");
		usu.setLogin(teclado.nextLine());

		System.out.println("Digite a senha do Usuario: ");
		usu.setSenha(teclado.nextLine());

		Usuario usuRetornado = usuDao.autenticar(usu);

		if (usuRetornado != null) {

			System.out.println("Login: " + usuRetornado.getLogin());
			System.out.println("Senha: " + usuRetornado.getSenha());
			
		}
	}

}
