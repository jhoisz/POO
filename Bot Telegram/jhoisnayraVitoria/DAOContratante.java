package jhoisnayraVitoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOContratante {
	public void inserirContratante(Contratante contratante) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();

		String cmd = "insert into contratante(email, nome, telefone) values ('" + contratante.getEmail() + "','"
				+ contratante.getNome() + "','" + contratante.getTelefone() + "');";

		st.execute(cmd);
		st.close();
	}

	public Contratante buscarContratante(String email) throws SQLException {
		Connection con;
		try {
			con = Conexao.getConexao();
			Statement st = con.createStatement();

			String cmd = "select * from contratante where email = '" + email + "';";

			ResultSet rs = st.executeQuery(cmd);
			Contratante contratante = null;

			if (rs.next()) {
				contratante = new Contratante(rs.getString("email"), rs.getString("nome"), rs.getString("telefone"));
				contratante.setServicoAtivo(verificaServicoAtivo(contratante.getEmail()));
			}

			st.close();
			return contratante;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean verificaServicoAtivo(String email) throws SQLException {
		Connection con;
		con = Conexao.getConexao();

		PreparedStatement pst = con.prepareStatement(
				"select * from serv_contratante as sc join servico as s on sc.cod_Serv = s.codigo where s.contratado = 0 and sc.email_contratante = \'"
						+ email + "\';");
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			return true;
		}
		return false;
	}

	public ArrayList<Contratante> listarContratantes() throws SQLException {
		ArrayList<Contratante> contratantes = new ArrayList<Contratante>();
		Connection con;
		try {
			con = Conexao.getConexao();
			Statement st = con.createStatement();
			String cmd = "select * from contratante";
			ResultSet rs = st.executeQuery(cmd);
			while (rs.next()) {
				Contratante contratante = new Contratante(rs.getString("email"), rs.getString("nome"),
						rs.getString("telefone"));
				contratante.setServicoAtivo(verificaServicoAtivo(contratante.getEmail()));
				contratantes.add(contratante);
			}
			st.close();

			return contratantes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void apagar() throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		String cmd = "delete from prest_ServPossiveis;";
		st.execute(cmd);

		cmd = "delete from prest_proposta;";
		st.execute(cmd);

		cmd = "delete from serv_tipo;";
		st.execute(cmd);

		cmd = "delete from serv_contratante;";
		st.execute(cmd);

		cmd = "delete from serv_props;";
		st.execute(cmd);

		cmd = "delete from contratante;";
		st.execute(cmd);
		st.close();
	}
}
