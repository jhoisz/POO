package jhoisnayraVitoria;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MeuPregao implements InterfacePregao {

	private int cod = 0;
	private DAOProposta proposta = new DAOProposta();
	private DAOContratante contratante = new DAOContratante();
	private DAOPrestador prestador = new DAOPrestador();
	private DAOTipoDeServico tds = new DAOTipoDeServico();
	private DAOServico serv = new DAOServico();

	public void allClear() throws SQLException {
		proposta.apagar();
		contratante.apagar();
		prestador.apagar();
		tds.apagar();
		serv.apagar();
	}

	public void cadastrarContratante(String email, String nome, String telefone) throws SQLException {
		Contratante c = new Contratante(email, nome, telefone);
		Contratante v = pesquisarContratante(email);
		if (v == null && c.getEmail() != "") {
			contratante.inserirContratante(c);
		}
	}

	public void cadastrarPrestador(String email, String nome, String telefone) throws SQLException {
		Prestador p = new Prestador(email, nome, telefone);
		Prestador a = pesquisarPrestador(email);
		if (a == null && p.getEmail() != "") {
			prestador.inserirPrestador(p);
		}
	}

	public void adicionarTipoDeServico(int cod, String descr) throws SQLException {
		TipoDeServico t = new TipoDeServico(cod, descr);
		TipoDeServico v = pesquisarTipo(cod);
		if (v == null) {
			tds.inserirTipoDeServico(t);
		}
	}

	public Contratante pesquisarContratante(String email) throws SQLException {
		Contratante c = contratante.buscarContratante(email);
		return c;
	}

	public Prestador pesquisarPrestador(String email) throws SQLException {
		Prestador p = prestador.buscarPrestador(email);
		return p;
	}

	public TipoDeServico pesquisarTipo(int codTipoDeServico) throws SQLException {
		TipoDeServico t = tds.buscar(codTipoDeServico);
		return t;
	}

	public Servico pesquisarServico(int codServico) throws SQLException {
		Servico se = serv.buscarServico(codServico, 1);
		return se;
	}

	public int cadastrarServico(String emailContratante, String descricao, double valor, int prazoMaximo,
			int codTipoDeServico) throws SQLException {
		Contratante c = pesquisarContratante(emailContratante);
		TipoDeServico t = pesquisarTipo(codTipoDeServico);
		if (c != null && t != null && emailContratante != "" && emailContratante != null) {
			if (!c.isServicoAtivo()) {
				Servico s = new Servico(cod, c, descricao, valor, prazoMaximo, t);
				int codigo = serv.inserirServico(s);
				c.setServicoAtivo(true);
				return codigo;
			}
		}
		return -1;
	}

	public void adicionarServicoPrestador(String email, int tipo) throws SQLException {
		Prestador p = pesquisarPrestador(email);
		TipoDeServico t = pesquisarTipo(tipo);
		if (p != null && t != null) {
			prestador.inserirHabilidade(p, t);
		}
	}

	public ArrayList<Servico> listarServicos(double valor, int prazoMaximo, int tipo, boolean contratado,
			boolean finalizado, int avaliacaoMediaContratante) {
		boolean t = false;
		ArrayList<Servico> a = new ArrayList<Servico>();
		System.out.println("vv" + serv.listarServicos().size());
		for (Servico s : serv.listarServicos()) {
			t = false;
			if (tipo == 0 || s.getTipo().getCodTipoDeServico() == tipo) {
				t = true;
			}
			if (s.getPrazoMaximo() >= prazoMaximo && s.getValor() >= valor && t && s.isContratado() == contratado
					&& s.isFinalizado() == finalizado
					&& avaliacaoMediaContratante <= s.getContratante().getAvaliacaoMedia()) {
				a.add(s);
			}
		}
		return a;
	}

	public void cadastrarProposta(int codigoServico, String emailPrestador, double valor, int prazo)
			throws SQLException {
		boolean compatibilidade = false;
		boolean valores = false;
		Prestador p = pesquisarPrestador(emailPrestador);
		Servico s = pesquisarServico(codigoServico);
		if (p == null || s == null) {
			return;
		}
		for (TipoDeServico t : p.getTipoPrestador()) {
			if (t.getCodTipoDeServico() == s.getTipo().getCodTipoDeServico())
				compatibilidade = true;
		}
		if (valor <= s.getValor() && prazo <= s.getPrazoMaximo()) {
			valores = true;

		}
		if (valores && compatibilidade) {
			Proposta po = new Proposta(s, p, valor, prazo);
			proposta.inserirProposta(po);
		}
	}

	public ArrayList<Proposta> listarPropostas(int codigoServico, double valor, int prazoMaximo,
			int avaliacaoMediaPrestador) throws SQLException {
		ArrayList<Proposta> arrayp = new ArrayList<Proposta>();
		Servico s = pesquisarServico(codigoServico);
		boolean bvalor = false;
		boolean bprazo = false;
		boolean bavalia = false;
		if (valor == 0) {
			bvalor = true;
		}
		if (prazoMaximo == 0) {
			bprazo = true;
		}
		if (avaliacaoMediaPrestador == 0) {
			bavalia = true;
		}
		for (Proposta p : proposta.listarPropostas()) {
			if (p.getServico().getCodServico() == s.getCodServico()) {
					//&& (!p.getServico().isContratado() || p.getServico().isFinalizado())) {
				if (p.getPreco() <= valor) {
					bvalor = true;
				}
				if (p.getPrazo() <= prazoMaximo) {
					bprazo = true;
				}
				if (p.getServico().getNotaC() >= avaliacaoMediaPrestador) {
					bavalia = true;
				}
				if (bvalor && bprazo && bavalia) {
					arrayp.add(p);
				}
			}
		}
		return arrayp;
	}

	public void contratarProposta(int codigoServico, String emailPrestador) throws SQLException {
		Servico s = pesquisarServico(codigoServico);
		for (Proposta pro : proposta.listarPropostas()) {
			if (pro.getPrestador().getEmail().equals(emailPrestador)
					&& pro.getServico().getCodServico() == s.getCodServico()) {
				s.getContratante().setServicoAtivo(false);
				s.setContratado(true);
				serv.contratado(s);
				s.setPropostaEscolhida(pro);
			}
		}
	}

	public void finalizarServico(int codigoServico, Date data) throws SQLException {
		Servico s = pesquisarServico(codigoServico);
		if (s.isContratado()) {
			s.setFinalizado(true);
			serv.finalizado(s);

		}
	}

	public void avaliarPrestador(int codigoServico, int nota, String observacoes) throws SQLException {
		Servico s = pesquisarServico(codigoServico);
		if (s.isFinalizado()) {
			serv.avaliarPrestador(s, nota);
		}
	}

	public void avaliarContratante(int codigoServico, int nota, String observacoes) throws SQLException {
		Servico s = pesquisarServico(codigoServico);
		if (s.isFinalizado()) {
			serv.avaliarContratante(s, nota);
		}
	}
}
