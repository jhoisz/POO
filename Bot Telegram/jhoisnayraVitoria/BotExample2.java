package jhoisnayraVitoria;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class BotExample2 extends TelegramLongPollingBot {
	HashMap<Long, String> interacoes = new HashMap<Long, String>();
	String contexto = "inicio";
	String acao = "";
	Prestador p = new Prestador("", "", "");
	Contratante c = new Contratante("", "", "");
	TipoDeServico t = new TipoDeServico(null, "");
	
	@Override
	public String getBotUsername() {
		return "AJPregaoBot";
	}

	public void enviarMensagem(long idTelegram, String mensagem) {
		// Mensagem a ser enviada
		SendMessage send = new SendMessage();
		send.setChatId(idTelegram);
		send.setText(mensagem);

		try {
			execute(send);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpdateReceived(Update u) {
		// Dados da mensagem recebida pelo bot
		String nome = u.getMessage().getFrom().getFirstName();
		long idTelegram = u.getMessage().getChatId();
		String mensagem = u.getMessage().getText();

		System.out.println(mensagem);
//		if (mensagem.contains("Prestador")) {
//			contexto = "prestador";
//			if (mensagem.contains("Cadastrar")) {
//				acao = "cadastrar";
//			} else if (mensagem.contains("Listar")) {
//				acao = "listar";
//			}
//		} else if (mensagem.contains("Serviço")) {
//			contexto = "serviço";
//		}

		System.out.println("Contexto: " + contexto);
		System.out.println("Mensagem: " + mensagem);

		if (contexto == "inicio" || mensagem.contains("menu inicial")) {
			System.out.println("Entrou inicio");
			SendMessage send = new SendMessage();
			send.setChatId(idTelegram);
			send.setText(nome + ", seja bem vindo ao Pregao de Servicos. Selecione uma opcao:");

			ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
			List<KeyboardRow> keyboard = new ArrayList<>();

			KeyboardRow row = new KeyboardRow();
			row.add("Menu Servico");
			keyboard.add(row);

			row = new KeyboardRow();
			row.add("Menu Prestador");
			keyboard.add(row);
			
			row = new KeyboardRow();
			row.add("Menu Contratante");
			keyboard.add(row);
			
			row = new KeyboardRow();
			row.add("Menu Tipo de Servico");
			keyboard.add(row);
			
			
			keyboardMarkup.setKeyboard(keyboard);
			keyboardMarkup.setResizeKeyboard(true);
			send.setReplyMarkup(keyboardMarkup);

			try {
				execute(send); // Sending our message object to user
				contexto = "";
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		} 		
		/***********************MENU PRESTADOR*****************************************/
		else if (mensagem.contains("Menu Prestador") || contexto.contains("Menu Prestador")) {
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
			row.add("Voltar ao menu inicial");
			keyboard.add(row);

			keyboardMarkup.setKeyboard(keyboard);
			keyboardMarkup.setResizeKeyboard(true);
			send.setReplyMarkup(keyboardMarkup);

			try {
				execute(send); // Sending our message object to user
				contexto = "";
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		} else if (mensagem.contains("Listar prestadores")) {
			try {
				mensagem = listarPrestador();
				System.out.println(mensagem);
				enviarMensagem(idTelegram, mensagem);
				contexto = "";
			} catch (Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao recuperar prestadores!";
			}
		} else if (mensagem.contains("Cadastrar prestador")) {
			contexto = "prestador nome";
			mensagem = "Vamos começar? Primeiro eu preciso do nome do prestador.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("prestador nome")) {
			p.setNome(mensagem);
			contexto = "prestador email";
			mensagem = "Agora eu preciso do email do prestador.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("prestador email")) {
			p.setEmail(mensagem);
			contexto = "prestador telefone";
			mensagem = "Agora eu preciso do telefone do prestador, no formato (99) 99999-9999.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("prestador telefone")) {
			p.setTelefone(mensagem);
			DAOPrestador daoP = new DAOPrestador();
            try {
				daoP.inserirPrestador(p);
				mensagem = "Prestador cadastrado com sucesso!";
			} catch (Exception e) {
				mensagem = "Erro no cadastro do prestador.";
			}
			enviarMensagem(idTelegram, mensagem);
			contexto = "Menu Prestador";
		} 		
		/***********************MENU CONTRATANTE*****************************************/
		else if (mensagem.contains("Menu Contratante") || contexto.contains("Menu Contratante")) {
			System.out.println("Entrou Contratante");
			SendMessage send = new SendMessage();
			send.setChatId(idTelegram);
			System.out.println(acao);
			send.setText(nome + ", o que deseja fazer com relacao a contratantes? Selecione uma opcao:");

			ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
			List<KeyboardRow> keyboard = new ArrayList<>();

			KeyboardRow row = new KeyboardRow();
			row.add("Cadastrar Contratante");
			keyboard.add(row);

			row = new KeyboardRow();
			row.add("Listar Contratantes");
			keyboard.add(row);

			row = new KeyboardRow();
			row.add("Voltar ao menu inicial");
			keyboard.add(row);

			keyboardMarkup.setKeyboard(keyboard);
			keyboardMarkup.setResizeKeyboard(true);
			send.setReplyMarkup(keyboardMarkup);

			try {
				execute(send); // Sending our message object to user
				contexto = "";
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		} else if (mensagem.contains("Listar Contratantes")) {
			try {
				mensagem = listarContratante();
				System.out.println(mensagem);
				enviarMensagem(idTelegram, mensagem);
				contexto = "";
			} catch (Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao recuperar contratantes!";
			}
		} else if (mensagem.contains("Cadastrar Contratante")) {
			contexto = "Contratante nome";
			mensagem = "Vamos comecar? Primeiro eu preciso do nome do Contratante.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("Contratante nome")) {
			c.setNome(mensagem);
			contexto = "Contratante email";
			mensagem = "Agora eu preciso do email do Contratante.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("Contratante email")) {
			c.setEmail(mensagem);
			contexto = "Contratante telefone";
			mensagem = "Agora eu preciso do telefone do Contratante, no formato (99) 99999-9999.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("Contratante telefone")) {
			c.setTelefone(mensagem);
			DAOContratante daoC = new DAOContratante();
            try {
				daoC.inserirContratante(c);
				mensagem = "Contratante cadastrado com sucesso!";
			} catch (Exception e) {
				mensagem = "Erro no cadastro do Contratante.";
			}
			enviarMensagem(idTelegram, mensagem);
			contexto = "Menu Contratante";
		} 	
 		
		/***********************MENU TIPO DE SERVICO*****************************************/
		else if (mensagem.contains("Menu Tipo de Servico") || contexto.contains("Menu Tipo de Servico")) {
			System.out.println("Entrou Tipo de Servico");
			SendMessage send = new SendMessage();
			send.setChatId(idTelegram);
			System.out.println(acao);
			send.setText(nome + ", o que deseja fazer com relacao a Tipo de Servico? Selecione uma opcao:");

			ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
			List<KeyboardRow> keyboard = new ArrayList<>();

			KeyboardRow row = new KeyboardRow();
			row.add("Cadastrar Tipo de Servico");
			keyboard.add(row);

			row = new KeyboardRow();
			row.add("Listar Tipos de Servicos");
			keyboard.add(row);

			row = new KeyboardRow();
			row.add("Voltar ao menu inicial");
			keyboard.add(row);

			keyboardMarkup.setKeyboard(keyboard);
			keyboardMarkup.setResizeKeyboard(true);
			send.setReplyMarkup(keyboardMarkup);

			try {
				execute(send); // Sending our message object to user
				contexto = "";
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		} else if (mensagem.contains("Listar Tipos de Servicos")) {
			try {
				mensagem = listarTipos();
				System.out.println(mensagem);
				enviarMensagem(idTelegram, mensagem);
				contexto = "";
			} catch (Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao recuperar Tipos de Servicos!";
			}
		} else if (mensagem.contains("Cadastrar Tipo de Servico")) {
			contexto = "Tipo de Servico codigo";
			mensagem = "Vamos comecar? Primeiro eu preciso do codigo do Tipo de Servico.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("Tipo de Servico codigo")) {
			t.setCodTipoDeServico(Integer.parseInt(mensagem));
			contexto = "Tipo de Servico descricao";
			mensagem = "Agora eu preciso da descricao do Tipo de Servico.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("Tipo de Servico descricao")) {
			t.setDescr(mensagem);
			DAOTipoDeServico daoT = new DAOTipoDeServico();
            try {
				daoT.inserirTipoDeServico(t);
				mensagem = "Tipo de Servico cadastrado com sucesso!";
			} catch (Exception e) {
				mensagem = "Erro no cadastro do Tipo de Servico.";
			}
			enviarMensagem(idTelegram, mensagem);
			contexto = "Menu Tipo de Servico";
		} 	

		
	}

	private String listarPrestador() throws SQLException{
		String resposta = "";
		DAOPrestador daoP = new DAOPrestador();
		ArrayList<Prestador> prests = daoP.listarPrestadores();
		if (prests.size() == 0) {
			resposta = "Nao existem prestadores cadastrados\n";
		} else {
			resposta = "Lista de prestadores cadastrados:\n";
			for (Prestador prest : prests) {
				resposta = resposta + "Nome: " + prest.getNome() + " E-mail: " + prest.getEmail() + "\n";
			}
		}
		return resposta;
	}
	
	private String listarContratante() throws SQLException{
		String resposta = "";
		DAOContratante daoC = new DAOContratante();
		ArrayList<Contratante> contr = daoC.listarContratantes();
		if (contr.size() == 0) {
			resposta = "Nao existem contratantes cadastrados\n";
		} else {
			resposta = "Lista de contratantes cadastrados:\n";
			for (Contratante con : contr) {
				resposta = resposta + "Nome: " + con.getNome() + " E-mail: " + con.getEmail() + "\n";
			}
		}
		return resposta;
	}
	private String listarTipos() throws SQLException{
		String resposta = "";
		DAOTipoDeServico daoT = new DAOTipoDeServico();
		ArrayList<TipoDeServico> tipos = daoT.listarTiposDeServicos();
		if (tipos.size() == 0) {
			resposta = "Nao existem tipos de servicos cadastrados\n";
		} else {
			resposta = "Lista de tipos de servicos cadastrados:\n";
			for (TipoDeServico ts : tipos) {
				resposta = resposta + "Codigo: " + ts.getCodTipoDeServico() + " Descricao: " + ts.getDescr() + "\n";
			}
		}
		return resposta;
	}

	@Override
	public String getBotToken() {
		// Token gerado na criacao do bot
		return "954707664:AAHUSrO9K2k1TJrSEM9HvseNgD44zplnkN4";
	}

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBot = new TelegramBotsApi();
		BotExample2 bot = new BotExample2();
		Date data = new Date();
		System.out.println(data);

		try {
			telegramBot.registerBot(bot);
		} catch (TelegramApiRequestException e) {
			System.out.println("Erro no Bot");
			e.printStackTrace();
		}
	}
}