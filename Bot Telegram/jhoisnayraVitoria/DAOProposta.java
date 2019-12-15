package jhoisnayraVitoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOProposta {
	public void inserirProposta(Proposta proposta) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();

		String cmd = "insert into proposta(preco,prazo,cod_servico,nome_prest,email) values (" + proposta.getPreco()
				+ "," + proposta.getPrazo() + "," + proposta.getServico().getCodServico() + ",'"
				+ proposta.getPrestador().getNome() + "','" + proposta.getPrestador().getEmail() + "');";

		st.execute(cmd);
		st.close();
	}

	public Proposta buscarProposta(double preco) throws SQLException {
		Connection con;
		try {
			con = Conexao.getConexao();
			Statement st = con.createStatement();

			String cmd = "select * from proposta where preco = " + preco + ";";

			ResultSet rs = st.executeQuery(cmd);
			Proposta proposta = null;

			if (rs.next()) {
				DAOPrestador prestador = new DAOPrestador();
				DAOServico servico = new DAOServico();
				proposta = new Proposta(servico.buscarServico(rs.getInt("cod_servico"), 1),
						prestador.buscarPrestador(rs.getString("email")), rs.getDouble("preco"), rs.getInt("prazo"));
			}
			st.close();

			return proposta;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Proposta> listarPropostas() throws SQLException {
		ArrayList<Proposta> propostas = new ArrayList<Proposta>();
		Connection con;
		try {
			con = Conexao.getConexao();
			Statement st = con.createStatement();

			String cmd = "select * from proposta";

			ResultSet rs = st.executeQuery(cmd);
			DAOPrestador prestador = new DAOPrestador();
			DAOServico servico = new DAOServico();

			while (rs.next()) {
				Proposta p = new Proposta(servico.buscarServico(rs.getInt("cod_servico"), 1),
				prestador.buscarPrestador(rs.getString("email")), rs.getDouble("preco"), rs.getInt("prazo"));
				propostas.add(p);
			}
			st.close();

			return propostas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void apagar() throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		String cmd = "delete from prest_proposta;";
		st.execute(cmd);
		cmd = "delete from proposta;";
		st.execute(cmd);
		st.close();
	}
}
