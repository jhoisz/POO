package jhoisnayraVitoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOPrestador {
	public void inserirPrestador(Prestador prestador) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();

		String cmd = "insert into prestador(email,nome,telefone) values ('" + prestador.getEmail() + "','"
				+ prestador.getNome() + "','" + prestador.getTelefone() + "');";

		st.execute(cmd);
		st.close();
	}

	public Prestador buscarPrestador(String email) throws SQLException {
		Connection con;
		try {
			con = Conexao.getConexao();
			Statement st = con.createStatement();

			String cmd = "select * from prestador where email = '" + email + "';";

			ResultSet rs = st.executeQuery(cmd);
			Prestador prestador = null;

			if (rs.next()) {
				prestador = new Prestador(rs.getString("email"), rs.getString("nome"), rs.getString("telefone"));
				prestador.setTipoPrestador(listarTiposServ(prestador));
			}
			st.close();
			return prestador;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Prestador> listarPrestadores() throws SQLException {
		ArrayList<Prestador> prestadores = new ArrayList<Prestador>();
		Connection con;

		try {
			con = Conexao.getConexao();
			Statement st = con.createStatement();

			String cmd = "select * from prestador";

			ResultSet rs = st.executeQuery(cmd);
			while (rs.next()) {
				Prestador prestador = new Prestador(rs.getString("email"), rs.getString("nome"),
						rs.getString("telefone"));
				prestador.setTipoPrestador(listarTiposServ(prestador));
				prestadores.add(prestador);
			}
			st.close();

			return prestadores;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<TipoDeServico> listarTiposServ(Prestador prestador) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		
		ArrayList<TipoDeServico> tipos = new ArrayList<TipoDeServico>();
		
		String cmd = "select * from tipoDeServico where codigo in (select cod_tipo from prest_servPossiveis where prestador = '"
				+ prestador.getNome() + "');";
		ResultSet rs = st.executeQuery(cmd);
		
		while (rs.next()) {
			TipoDeServico t = new TipoDeServico(rs.getInt("codigo"), rs.getString("descricao"));
			tipos.add(t);
		}
		
		return tipos;
	}

	public void inserirHabilidade(Prestador prestador, TipoDeServico tipo) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		
		String cmd = "insert into prest_servPossiveis(prestador, cod_tipo) values ('" +prestador.getNome() + "',"
				+ tipo.getCodTipoDeServico() + ");";
		
		st.execute(cmd);
		st.close();
	}

	public void apagar() throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		String cmd = "delete from prest_proposta;";
		st.execute(cmd);
		
		cmd = "delete from prest_servPossiveis;";
		st.execute(cmd);
		
		cmd = "delete from prestador;";
		st.execute(cmd);
		
		st.close();
	}
}
