package jhoisnayraVitoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOServico {
	public int inserirServico(Servico servico) throws SQLException {
		Connection con;
		int codigo = -1;
		con = Conexao.getConexao();
		Statement st = con.createStatement();

		String cmd = "insert into servico " + "(descricao,valor,PrazoMaximo,ObservacoesContratante,ObservacoesPrestador"
				+ ",DataFinalizacao,NotaContratante,NotaPrestador,contratado,finalizado)" + "values (\'"
				+ servico.getDescricao() + "\'," + servico.getValor() + "," + servico.getPrazoMaximo() + ",\'"
				+ servico.getObservacoesContratante() + "\',\'" + servico.getObservacoesPrestador() + "\'," + servico.getDataFinalizado()
				+ "," + servico.getNotaC() + "," + servico.getNotaP() + "," + servico.isContratado() + ","
				+ servico.isFinalizado() + ");";

		st.execute(cmd);
		st.close();

		codigo = ultimoCodigo();
		servico.setCodServico(codigo);
		setTipo(servico);
		setContratante(servico);

		return codigo;
	}

	public int ultimoCodigo() throws SQLException {
		Connection con;
		int codigo = -1;
		con = Conexao.getConexao();

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select max(codigo) from servico");

		if (rs.next())
			codigo = rs.getInt("max(codigo)");

		return codigo;
	}

	public Servico buscarServico(int codServico, int opcao) throws SQLException {
		Connection con;
		try {
			con = Conexao.getConexao();
			Statement st = con.createStatement();

			String cmd = "select * from servico where codigo = '" + codServico + "';";

			ResultSet rs = st.executeQuery(cmd);
			Servico servico = null;

			if (rs.next()) {
				int cod = rs.getInt("codigo");

				Statement sm = con.createStatement();

				cmd = "select email_contratante from serv_contratante where cod_Serv = " + rs.getInt("codigo") + ";";

				ResultSet rs2 = sm.executeQuery(cmd);

				DAOContratante daoContratante = new DAOContratante();
				Contratante contratante = null;

				if (rs2.next()) {
					contratante = daoContratante.buscarContratante(rs2.getString("email_contratante"));
				}

				cmd = "select * from serv_tipo where cod_Serv = " + cod + ";";

				Statement sta = con.createStatement();
				ResultSet rt = sta.executeQuery(cmd);

				DAOTipoDeServico t = new DAOTipoDeServico();

				String descricao = rs.getString("descricao");
				double valor = rs.getDouble("valor");
				int prazoMax = rs.getInt("prazoMaximo");
				boolean contratado = rs.getBoolean("contratado");
				boolean finalizado = rs.getBoolean("finalizado");

				if (rt.next()) {
					int codTipo = rt.getInt("cod_tipo");

					TipoDeServico tipo = t.buscar(codTipo);

					servico = new Servico(cod, contratante, descricao, valor, prazoMax, tipo);
					servico.setContratado(contratado);
					servico.setFinalizado(finalizado);

					if (opcao == 0) {
						servico.setPropostas(listarPropostas(servico));
					}
				}
				sta.close();
				sm.close();
			}
			st.close();

			return servico;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Servico> listarServicos() {
		ArrayList<Servico> servicos = new ArrayList<Servico>();
		Connection con;
		try {
			con = Conexao.getConexao();
			Statement st = con.createStatement();

			String cmd = "select * from servico;";

			ResultSet rs = st.executeQuery(cmd);
			Servico s = null;

			while (rs.next()) {
				Statement sm = con.createStatement();
				int cod = rs.getInt("codigo");

				cmd = "select email_contratante from serv_contratante where cod_Serv = " + cod + ";";

				ResultSet rs2 = sm.executeQuery(cmd);
				Contratante contratante = null;

				if (rs2.next()) {
					DAOContratante c = new DAOContratante();
					contratante = c.buscarContratante(rs2.getString("email_contratante"));
				}

				cmd = "select cod_tipo from serv_tipo where cod_Serv = " + cod + ";";
				rs2 = sm.executeQuery(cmd);

				TipoDeServico tipos = null;

				if (rs2.next()) {
					DAOTipoDeServico daoTipos = new DAOTipoDeServico();
					tipos = daoTipos.buscar(rs2.getInt("cod_tipo"));
				}

				String descricao = rs.getString("descricao");
				double valor = rs.getDouble("valor");
				int prazoMaximo = rs.getInt("PrazoMaximo");
				boolean contratado = rs.getBoolean("contratado");
				boolean finalizado = rs.getBoolean("finalizado");

				s = new Servico(cod, contratante, descricao, valor, prazoMaximo, tipos);

				s.setContratado(contratado);
				s.setFinalizado(finalizado);
				s.setPropostas(listarPropostas(s));

				servicos.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicos;
	}

	public ArrayList<Proposta> listarPropostas(Servico servico) throws SQLException {
		ArrayList<Proposta> propostas = new ArrayList<Proposta>();
		Connection con;
		try {
			con = Conexao.getConexao();
			Statement st = con.createStatement();

			String cmd = "select * from proposta where cod_servico = " + servico.getCodServico() + ";";

			ResultSet rs = st.executeQuery(cmd);
			DAOPrestador daoPrestador = new DAOPrestador();
			DAOServico daoServico = new DAOServico();

			while (rs.next()) {
				Proposta pestador = new Proposta(daoServico.buscarServico(rs.getInt("cod_servico"), 1),
						daoPrestador.buscarPrestador("nome_prest"), rs.getDouble("preco"), rs.getInt("prazo"));
				propostas.add(pestador);
			}
			st.close();

			return propostas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void seleciona(int codigo, Proposta proposta) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();

		String cmd = "insert into serv_props (cod_Serv,prestador,preco) values(" + codigo
				+ ",\'" + proposta.getPrestador().getEmail() + "\'," + proposta.getPreco()+ ");";
				
				//+ proposta.getPrestador().getEmail() + "," + proposta.getPreco() + ");";

		st.executeUpdate(cmd);
		st.close();
	}

	public void finalizado(Servico servico) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		
		String cmd = "update servico set finalizado = " + 1 + " where codigo = " + servico.getCodServico() + ";";
		
		st.executeUpdate(cmd);
		st.close();
	}

	public void setTipo(Servico servico) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		
		String cmd = "insert into serv_tipo (cod_Serv,cod_tipo) values (" + servico.getCodServico() + ","
				+ servico.getTipo().getCodTipoDeServico() + ");";
		
		st.execute(cmd);
		st.close();
	}

	public void setContratante(Servico servico) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		
		String cmd = "insert into serv_contratante (cod_Serv,email_contratante) values (" + servico.getCodServico() + ",\'"
				+ servico.getContratante().getEmail() + "\');";
		
		st.execute(cmd);
		st.close();
	}

	public void contratado(Servico servico) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		
		String cmd = "update servico set contratado = " + 1 + " where codigo = " + servico.getCodServico() + ";";
		
		st.execute(cmd);
		st.close();
	}

	public void avaliarContratante(Servico servico, int nota) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		
		String cmd = "update servico set NotaContratante = " + nota + " where codigo = " + servico.getCodServico() + ";";
		
		st.execute(cmd);
		st.close();
	}

	public void avaliarPrestador(Servico servico, int nota) throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		
		String cmd = "update servico set NotaPrestador = " + nota + " where codigo = " + servico.getCodServico() + ";";
		
		st.execute(cmd);
		st.close();
	}
	

	public void apagar() throws SQLException {
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
	
		String cmd = "delete from servico;";
		
		st.execute(cmd);
		st.close();
	}
}