package fazenda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DAOAnimal {

	public void inserir(Animal a) throws SQLException, ClassNotFoundException {
		Connection con;
		// try {
		// Veiculo vei = pesquisarPor(v.getPlaca());
		// throw new VeiculoJaCadastrado();
		// } catch (VeiculoNaoCadastrado e1) {
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		int abatido = (a.isAbatido() ? 1 : 0);
		int morto = (a.isMorto() ? 1 : 0);
		int vendido = (a.isVendido() ? 1 : 0);
		int vacinado = (a.isVacinado() ? 1 : 0);
		long nascimento = a.getNascimento().getTime();
		int discriminador = 0;
		if (a instanceof Bovino) {
			discriminador = 1;
		} else if (a instanceof Caprino) {
			discriminador = 2;
		} else if (a instanceof Suino) {
			discriminador = 3;
		}

		String cmd = "insert into animal " + "(numero, nome, nascimento, genero, valorcompra, valorvenda, vacinado, "
				+ "vendido, abatido, morto, discriminador, fazenda) " + "values (" + a.getNumero() + ",\'" + a.getNome()
				+ "\'," + nascimento + "," + a.getGenero() + "," + a.getValorDeCompra() + "," + a.getValorDeVenda()
				+ "," + vacinado + "," + vendido + "," + abatido + "," + morto + "," + discriminador + ","
				+ a.getFazenda().getNumero() + ")";
		System.out.println(cmd);
		st.execute(cmd);
		st.close();
		// }
	}

	public void removerTodos() throws SQLException, ClassNotFoundException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		String cmd = "delete from animal ";
		System.out.println(cmd);
		st.execute(cmd);
		st.close();
	}

	public ArrayList<Animal> pesquisarPorFazenda(int numFazenda) {
		Connection con;
		ArrayList<Animal> animais = new ArrayList<Animal>();
		try {
			con = Conexao.getConexao();
			Statement st = con.createStatement();
			String cmd = "select * from animal where fazenda = " + numFazenda;
			System.out.println(cmd);
			ResultSet rs = st.executeQuery(cmd);
			while (rs.next()) {
				int numAnimal = rs.getInt("numero");
				Animal a = pesquisarPor(numAnimal);
				animais.add(a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return animais;
	}

	public Animal pesquisarPor(int n) {
		Connection con;
		try {
			con = Conexao.getConexao();
			Statement st = con.createStatement();
			String cmd = "select * from animal where numero = " + n;
			System.out.println(cmd);
			ResultSet rs = st.executeQuery(cmd);
			Animal a = null;
			if (rs.next()) {
				String nome = rs.getString("nome");
				int discriminador = rs.getInt("discriminador");
				double valorCompra = rs.getDouble("valorcompra");
				double valorVenda = rs.getDouble("valorvenda");
				long nascimento = rs.getLong("nascimento");
				int genero = rs.getInt("genero");
				int abatido = rs.getInt("abatido");
				int morto = rs.getInt("morto");
				int vendido = rs.getInt("vendido");
				int vacinado = rs.getInt("vacinado");
				boolean aba = (abatido == 1) ? true : false;
				boolean mor = (morto == 1) ? true : false;
				boolean ven = (vendido == 1) ? true : false;
				boolean vac = (vacinado == 1) ? true : false;
				Date nasc = new Date(nascimento);
				int numFazenda = rs.getInt("fazenda");
				DAOFazenda daoF = new DAOFazenda();
				Fazenda f = daoF.pesquisarPor(numFazenda);
				if (discriminador == 1) {
					a = new Bovino(n, nome, nasc, genero, valorCompra, valorVenda, vac, mor, aba);
				} else if (discriminador == 2) {
					a = new Caprino(n, nome, nasc, genero, valorCompra, valorVenda, vac, mor, aba);
				} else if (discriminador == 3) {
					a = new Suino(n, nome, nasc, genero, valorCompra, valorVenda, vac, mor, aba);
				}
				a.setFazenda(f);
				st.close();
				return a;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
