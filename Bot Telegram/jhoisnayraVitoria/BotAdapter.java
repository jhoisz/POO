package jhoisnayraVitoria;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotAdapter {
	private String mensagem;
	private String contexto;
	private long idTelegram;
	private String nome;
	private String acao;

	public BotAdapter(String mensagem, String contexto, Update u, String acao) {
		this.nome = u.getMessage().getFrom().getFirstName();
		this.idTelegram = u.getMessage().getChatId();
		this.mensagem = u.getMessage().getText();
		this.contexto = contexto;
		this.acao = acao;
	}

	public static Prestador buscarPrestador(String email) throws SQLException {
		DAOPrestador dp = new DAOPrestador();
		Prestador p = dp.buscarPrestador(email);
		return p;
	}

	public static Servico buscarServico(String codigo) throws NumberFormatException, SQLException {
		DAOServico ds = new DAOServico();
		Servico s = ds.buscarServico(Integer.parseInt(codigo), 0);
		return s;
	}

	public static Proposta buscarProposta(String preco) throws NumberFormatException, SQLException {
		DAOProposta dp = new DAOProposta();
		Proposta p = dp.buscarProposta(Double.parseDouble(preco));
		return p;
	}

	public static Contratante buscarContratante(String email) throws SQLException {
		DAOContratante contr = new DAOContratante();
		Contratante a = contr.buscarContratante(email);
		// System.out.println("Nome: " + a.getNome() + "email: " + a.getEmail());
		return a;
	}

	public static TipoDeServico buscarTipo(String codigo) throws SQLException {
		DAOTipoDeServico tds = new DAOTipoDeServico();
		TipoDeServico a = tds.buscar(Integer.parseInt(codigo));
		return a;
	}

	public static String listarPropostas() throws SQLException {
		DAOProposta prop = new DAOProposta();
		ArrayList<Proposta> p = prop.listarPropostas();
		String propostas = "";

		if (p.size() == 0) {
			propostas = "Nao existem propostas cadastradas\n";
		} else {
			for (Proposta pp : p) {
				propostas = propostas + "\nCodigo servico: " + pp.getServico().getCodServico()
						+ "  \nEmail do prestador: " + pp.getPrestador().getEmail() + "\nPrazo: " + pp.getPrazo()
						+ "\nValor: " + pp.getPreco() + "\n---------------------------------------------";
			}
		}
		return propostas;
	}

	public static String listarPrestadores() throws SQLException {
		DAOPrestador pre = new DAOPrestador();
		ArrayList<Prestador> a = pre.listarPrestadores();
		String s = "";
		if (a.size() == 0) {
			return "Nao ha prestadores!";
		} else {
			for (Prestador p : a) {
				s = s + "\nEmail: " + p.getEmail() + "  \nNome: " + p.getNome() + " \nTelefone: " + p.getTelefone()
						+ "\n---------------------------------------------";
			}
		}
		return s;
	}

	public static String listarServicos() throws SQLException {
		DAOServico serv = new DAOServico();
		ArrayList<Servico> s = serv.listarServicos();
		String servicos = "";
		if (s.size() == 0) {
			return "Nao ha servicos!";
		} else {
			for (Servico a : s) {
				servicos = servicos + "\nCodigo: " + a.getCodServico() + "  \nEmail do contratante: "
						+ a.getContratante().getEmail() + " \nDescricao: " + a.getDescricao() + "\nPrazo Maximo: "
						+ a.getPrazoMaximo() + "\nValor: " + a.getValor() + "\nTipo de Servico: "
						+ a.getTipo().getCodTipoDeServico() + "\n---------------------------------------------";
			}
		}
		return servicos;
	}

	public static String listarContratantes() throws SQLException {
		DAOContratante contr = new DAOContratante();
		ArrayList<Contratante> a = contr.listarContratantes();
		String s = "";

		if (a.size() == 0) {
			return "Nao ha contratantes!";
		} else {
			for (Contratante c : a) {
				s = s + "\nEmail: " + c.getEmail() + "  \nNome: " + c.getNome() + " \nTelefone: " + c.getTelefone()
						+ "\n---------------------------------------------";
			}
		}
		return s;
	}

	public static String listarTipos() throws SQLException {
		DAOTipoDeServico tds = new DAOTipoDeServico();
		ArrayList<TipoDeServico> a = tds.listarTiposDeServicos();
		String s = "";
		if (a.size() == 0) {
			return "Nao ha tipos de servicos!";
		} else {
			for (TipoDeServico t : a) {
				s = s + "\nCodigo: " + t.getCodTipoDeServico() + "  \nDescrição: " + t.getDescr()
						+ "\n---------------------------------------------";
			}
		}
		return s;
	}

	public static String listarPropostasDeServico(int cod) throws SQLException {
		DAOProposta prop = new DAOProposta();
		ArrayList<Proposta> p = prop.listarPropostas();
		String propostas = "";

		if (p.size() == 0) {
			return "Nao ha propostas para este servico!";
		} else {
			for (Proposta pp : p) {
				if (pp.getServico().getCodServico() == cod) {
					propostas = propostas + "\nCodigo servico: " + pp.getServico().getCodServico()
							+ "  \nEmail do prestador: " + pp.getPrestador().getEmail() + "\nPrazo: " + pp.getPrazo()
							+ "\nValor: " + pp.getPreco() + "\n---------------------------------------------";
				}
			}
		}
		return propostas;
	}

	public SendMessage inicio() {
		System.out.println("Entrou inicio");
		SendMessage send = new SendMessage();
		send.setChatId(idTelegram);
		send.setText(nome + ", seja bem vindo ao Pregão de Serviços. Selecione uma opção:");

		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		List<KeyboardRow> keyboard = new ArrayList<>();

		KeyboardRow row = new KeyboardRow();
		row.add("Menu Serviço");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Menu Proposta");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Menu Prestador");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Menu Contratante");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Menu Tipo");
		keyboard.add(row);

		keyboardMarkup.setKeyboard(keyboard);
		keyboardMarkup.setResizeKeyboard(true);
		send.setReplyMarkup(keyboardMarkup);

		return send;
	}

	public SendMessage menu_contratante() {
		System.out.println("Entrou contratante");
		SendMessage send = new SendMessage();
		send.setChatId(idTelegram);
		System.out.println(acao);
		send.setText(nome + ", o que deseja fazer com relação a contratante? Selecione uma opção:");

		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		List<KeyboardRow> keyboard = new ArrayList<>();

		KeyboardRow row = new KeyboardRow();
		row.add("Cadastrar contratante");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Listar contratantes");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Voltar ao menu inicial");
		keyboard.add(row);

		keyboardMarkup.setKeyboard(keyboard);
		keyboardMarkup.setResizeKeyboard(true);
		send.setReplyMarkup(keyboardMarkup);

		return send;
	}

	public SendMessage menu_prestador() {
		System.out.println("Entrou prestador");
		SendMessage send = new SendMessage();
		send.setChatId(idTelegram);
		System.out.println(acao);
		send.setText(nome + ", o que deseja fazer com relação a prestadores? Selecione uma opção:");

		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		List<KeyboardRow> keyboard = new ArrayList<>();

		KeyboardRow row = new KeyboardRow();
		row.add("Cadastrar prestador");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Listar prestadores");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Adicionar tipo de servico ao prestador");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Voltar ao menu inicial");
		keyboard.add(row);

		keyboardMarkup.setKeyboard(keyboard);
		keyboardMarkup.setResizeKeyboard(true);
		send.setReplyMarkup(keyboardMarkup);

		return send;

	}

	public SendMessage menu_servico() {
		System.out.println("Entrou servicor");
		SendMessage send = new SendMessage();
		send.setChatId(idTelegram);
		System.out.println(acao);
		send.setText(nome + ", o que deseja fazer com relação a serviços? Selecione uma opção:");

		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		List<KeyboardRow> keyboard = new ArrayList<>();

		KeyboardRow row = new KeyboardRow();
		row.add("Cadastrar servico");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Listar servicos");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Finalizar servico");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Voltar ao menu inicial");
		keyboard.add(row);

		keyboardMarkup.setKeyboard(keyboard);
		keyboardMarkup.setResizeKeyboard(true);
		send.setReplyMarkup(keyboardMarkup);

		return send;
	}

	public SendMessage menu_tipo() {
		System.out.println("Entrou tipo");
		SendMessage send = new SendMessage();
		send.setChatId(idTelegram);
		System.out.println(acao);
		send.setText(nome + ", o que deseja fazer com relação a tipos? Selecione uma opção:");

		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		List<KeyboardRow> keyboard = new ArrayList<>();

		KeyboardRow row = new KeyboardRow();
		row.add("Cadastrar tipo");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Listar tipos");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Voltar ao menu inicial");
		keyboard.add(row);

		keyboardMarkup.setKeyboard(keyboard);
		keyboardMarkup.setResizeKeyboard(true);
		send.setReplyMarkup(keyboardMarkup);

		return send;
	}

	public SendMessage menu_proposta() {
		System.out.println("Entrou proposta");
		SendMessage send = new SendMessage();
		send.setChatId(idTelegram);
		System.out.println(acao);
		send.setText(nome + ", o que deseja fazer com relação a porpostas? Selecione uma opção:");

		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		List<KeyboardRow> keyboard = new ArrayList<>();

		KeyboardRow row = new KeyboardRow();
		row.add("Cadastrar proposta");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Listar propostas");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Propostas relacionadas a um servico");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Contratar proposta");
		keyboard.add(row);

		row = new KeyboardRow();
		row.add("Voltar ao menu inicial");
		keyboard.add(row);

		keyboardMarkup.setKeyboard(keyboard);
		keyboardMarkup.setResizeKeyboard(true);
		send.setReplyMarkup(keyboardMarkup);

		return send;
	}
}
