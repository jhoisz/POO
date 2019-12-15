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

public class BotExample extends TelegramLongPollingBot {

	HashMap<Long, String> interacoes = new HashMap<Long, String>();
	String contexto = "inicio";
	String acao = "";
	Prestador p = new Prestador("", "", "");
	Contratante c = new Contratante("", "", "");
	TipoDeServico tds = new TipoDeServico(0, "");
	Servico s = new Servico(0, null, "", 0, 0, null);
	Proposta prop = new Proposta(null, null, 0, 0);

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
		BotAdapter ba = new BotAdapter(mensagem, contexto, u, acao);

		System.out.println(mensagem);

		System.out.println("Contexto: " + contexto);
		System.out.println("Mensagem: " + mensagem);

		if (mensagem.contains("apague tudo")) {
			Conexao c = new Conexao();
			try {
				c.apagar();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (contexto == "inicio" || mensagem.contains("menu inicial")) {
			SendMessage send = ba.inicio();
			try {
				execute(send); // Sending our message object to user
				contexto = send.getText();
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
		if (mensagem.contains("Menu Serviço") || contexto.contains("Menu Serviço")) {
			SendMessage send = ba.menu_servico();
			try {
				execute(send); // Sending our message object to user
				contexto = send.getText();
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
		cadastrarServico(mensagem, idTelegram);
		listarServico(mensagem, idTelegram);
		finalizarServico(mensagem, idTelegram);

		if (mensagem.contains("Menu Prestador") || contexto.contains("Menu Prestador")) {
			SendMessage send = ba.menu_prestador();
			try {
				execute(send); // Sending our message object to user
				contexto = send.getText();
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
		contr_prestador(mensagem, idTelegram);
		list_prestador(mensagem, idTelegram);

		try {
			adicionarServicoPrestador(mensagem, idTelegram);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (mensagem.contains("Menu Contratante") || contexto.contains("Menu Contratante")) {
			SendMessage send = ba.menu_contratante();
			try {
				execute(send); // Sending our message object to user
				contexto = send.getText();
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
		contr_contratante(mensagem, idTelegram);
		list_contratante(mensagem, idTelegram);

		if (mensagem.contains("Menu Tipo") || contexto.contains("Menu Tipo")) {
			SendMessage send = ba.menu_tipo();
			try {
				execute(send);
				contexto = send.getText();
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
		contr_tipo(mensagem, idTelegram);
		list_tipo(mensagem, idTelegram);

		if (mensagem.contains("Menu Proposta") || contexto.contains("Menu Proposta")) {
			SendMessage send = ba.menu_proposta();
			try {
				execute(send);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
			contexto = send.getText();
		}
		cadastrarProposta(mensagem, idTelegram);
		contratarProposta(mensagem, idTelegram);
		listarProposta(mensagem, idTelegram);
		listarPropostaDeServico(mensagem, idTelegram);

//		excluirDados(mensagem, idTelegram);

	}

	public void contr_prestador(String mensagem, long idTelegram) {
		if (mensagem.contains("Cadastrar prestador")) {
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
				e.printStackTrace();
				mensagem = "Erro no cadastro do prestador.";
			}
			contexto = "Menu Prestador";
			enviarMensagem(idTelegram, mensagem);
		}
	}

	public void list_contratante(String mensagem, long idTelegram) {
		if (mensagem.contains("Listar contratantes")) {
			try {
				mensagem = BotAdapter.listarContratantes();
				System.out.println(mensagem);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mensagem = "Erro ao recuperar contratantes!";
			}
			enviarMensagem(idTelegram, mensagem);
			contexto = "Menu Contratante";
		}
	}

	public void list_prestador(String mensagem, long idTelegram) {
		if (mensagem.contains("Listar prestador")) {
			try {
				mensagem = BotAdapter.listarPrestadores();
				System.out.println(mensagem);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mensagem = "Erro ao recuperar prestadores!";
			}
			enviarMensagem(idTelegram, mensagem);
			contexto = "Menu Prestador";
		}
	}

	public void list_tipo(String mensagem, long idTelegram) {
		if (mensagem.contains("Listar tipos")) {
			try {
				mensagem = BotAdapter.listarTipos();
				System.out.println(mensagem);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mensagem = "Erro ao recuperar tipos!";
			}
			enviarMensagem(idTelegram, mensagem);
			contexto = "Menu Tipo";
		}
	}

	public void listarServico(String mensagem, long idTelegram) {
		if (mensagem.contains("Listar servicos")) {
			try {
				mensagem = BotAdapter.listarServicos();
				System.out.println(mensagem);
			} catch (Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao recuperar servicos!";
			}
			enviarMensagem(idTelegram, mensagem);
			contexto = "Menu Servico";
		}
	}

	public void listarProposta(String mensagem, long idTelegram) {
		if (mensagem.contains("Listar propostas")) {
			try {
				mensagem = " ";
				mensagem += BotAdapter.listarPropostas();
				System.out.println(mensagem);
			} catch (Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao recuperar propostas!";
			}
			enviarMensagem(idTelegram, mensagem);
			contexto = "Menu Proposta";
		}
	}

	public void listarPropostaDeServico(String mensagem, long idTelegram) {
		Servico serv = new Servico(0, null, "", 0, 0, null);
		if (mensagem.contains("Propostas relacionadas a um servico")) {
			contexto = "CodeServ";
			mensagem = "Vamos começar? Primeiro eu preciso do codigo do servico.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("CodeServ")) {
			try {
				serv = BotAdapter.buscarServico(mensagem);
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			if (serv != null) {
				try {
					mensagem = BotAdapter.listarPropostasDeServico(serv.getCodServico());
					System.out.println(mensagem);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					mensagem = "Erro ao recuperar propostas!";
					e.printStackTrace();
				}
			} else {
				mensagem = "Codigo de servico nao existe! Inicie a busca novamente";
				enviarMensagem(idTelegram, mensagem);
			}
			contexto = "Menu Proposta";
			enviarMensagem(idTelegram, mensagem);
		}
	}

	public void finalizarServico(String mensagem, long idTelegram) {
		if (mensagem.contains("Finalizar servico")) {
			contexto = "finalizaCode";
			mensagem = "Vamos comecar? Primeiro eu preciso do codigo do servico que deseja finalizar.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("finalizaCode")) {
			try {
				Servico serv = BotAdapter.buscarServico(mensagem);

				if (serv != null) {
					if (serv.isContratado()) {
						try {
							DAOServico daos = new DAOServico();
							daos.finalizado(s);
							mensagem = "servico finalizado com sucesso!";
						} catch (Exception e) {
							e.printStackTrace();
							mensagem = "Erro ao finalizar servico!";
						}

					} else {
						mensagem = "Nao é possivel finalizar um servico nao contratado!";
					}
				} else {
					mensagem = "Codigo de servico nao existe! Inicie a opreacao novamente";
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			contexto = "Menu Servico";
			enviarMensagem(idTelegram, mensagem);
		}
	}

	public void contratarProposta(String mensagem, long idTelegram) {
		if (mensagem.contains("Contratar proposta")) {
			contexto = "contrato preco";
			mensagem = "Vamos comecar? Primeiro eu preciso do preco da proposta que deseja contratar.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("contrato preco")) {
			try {
				Proposta pp = BotAdapter.buscarProposta(mensagem);
				if (pp != null && s != null) {
					DAOServico daoS = new DAOServico();
					daoS.seleciona(pp.getServico().getCodServico(), pp);
					daoS.contratado(pp.getServico());
					mensagem = "proposta contratada com sucesso!";
				} else {
					mensagem = "Erro ao contratar proposta. Dados do servico ou preco invalidos.";
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			contexto = "Menu Proposta";
			enviarMensagem(idTelegram, mensagem);
		}
	}

	public void cadastrarServico(String mensagem, long idTelegram) {
		if (mensagem.contains("Cadastrar servico")) {
			contexto = "servico contratante";
			mensagem = "Vamos começar? Primeiro eu preciso do email do contratante.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("servico contratante")) {
			try {
				Contratante con = BotAdapter.buscarContratante(mensagem);

				if (con != null) {
					s.setContratante(con);
				} 
//				else {
//					mensagem = "Email de contratante invalido! Inicie o cadastro novamente";
//					enviarMensagem(idTelegram, mensagem);
//					mensagem = "Cadastrar servico";
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			contexto = "servico tipo";
			mensagem = "Agora eu preciso do codigo do tipo do servico.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("servico tipo")) {
			try {
				TipoDeServico ts = BotAdapter.buscarTipo(mensagem);
				if (ts != null) {
					s.setTipo(ts);
				}
//				} else {
//					mensagem = "Codigo de contratante invalido! Inicie o cadastro novamente";
//					enviarMensagem(idTelegram, mensagem);
//					mensagem = "Cadastrar servico";
//				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

			contexto = "servico valor";
			mensagem = "Agora eu preciso do valor do servico.";
			enviarMensagem(idTelegram, mensagem);

		} else if (contexto.contains("servico valor")) {
			s.setValor(Double.parseDouble(mensagem));
//			contexto = "servico codigo";
//			mensagem = "Agora eu preciso do codigo do servico.";
//			enviarMensagem(idTelegram, mensagem);
//		} else if (contexto.contains("servico codigo")) {
//			s.setCodServico(Integer.parseInt(mensagem));
//			contexto = "servico descricao";
//			mensagem = "Agora eu preciso da descricao do servico.";
//			enviarMensagem(idTelegram, mensagem);
//		} else if (contexto.contains("servico descricao")) {
//			s.setDescricao(mensagem);
			contexto = "servico prazoMax";
			mensagem = "Agora eu preciso do prazo maximo do servico.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("servico prazoMax")) {
			s.setPrazoMaximo(Integer.parseInt(mensagem));

			DAOServico daoS = new DAOServico();
			try {
				if (s.getContratante() != null && s.getTipo() != null) {
					daoS.inserirServico(s);
					mensagem = "Servico cadastrado com sucesso!";
				} else {
					mensagem = "Erro no cadastro do servico. Dados do contratante ou prestador invalidos!";
				}
			} catch (Exception e) {
				e.printStackTrace();
				mensagem = "Erro no cadastro do servico.";
			}
			contexto = "Menu Servico";
			enviarMensagem(idTelegram, mensagem);
		}
	}

	public void cadastrarProposta(String mensagem, long idTelegram) {
		if (mensagem.contains("Cadastrar proposta")) {
			contexto = "proposta servico";
			mensagem = "Vamos começar? Primeiro eu preciso do codigo de servico da proposta.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("proposta servico")) {
			try {
				s = BotAdapter.buscarServico(mensagem);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			prop.setServico(s);

			contexto = "proposta prestador";
			mensagem = "Agora eu preciso do email do prestador.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("proposta prestador")) {
			try {
				p = BotAdapter.buscarPrestador(mensagem);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			prop.setPrestador(p);

			contexto = "proposta valor";
			mensagem = "Agora eu preciso do valor da proposta";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("proposta valor")) {
			prop.setPreco(Double.parseDouble(mensagem));
			contexto = "proposta prazo";
			mensagem = "Agora eu preciso do prazo da proposta";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("proposta prazo")) {
			prop.setPrazo(Integer.parseInt(mensagem));

			DAOProposta daoProp = new DAOProposta();
			try {
				if (prop.getPrestador() != null && prop.getServico() != null) {
					daoProp.inserirProposta(prop);
					mensagem = "Proposta cadastrada com sucesso!";
				} else {
					mensagem = "Dados do prestador ou servico invalidos!";

				}
			} catch (Exception e) {
				e.printStackTrace();
				mensagem = "Erro no cadastro do prestador.";
			}
			contexto = "Menu Proposta";
			enviarMensagem(idTelegram, mensagem);
		}
	}

	public void contr_contratante(String mensagem, long idTelegram) {
		if (mensagem.contains("Cadastrar contratante") || contexto.contains("Cadastrar contratante")) {
			contexto = "contratante nome";
			mensagem = "Vamos começar? Primeiro eu preciso do nome do contratante.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("contratante nome")) {
			c.setNome(mensagem);
			contexto = "contratante email";
			mensagem = "Agora eu preciso do email do contratante.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("contratante email")) {
			c.setEmail(mensagem);
			contexto = "contratante telefone";
			mensagem = "Agora eu preciso do telefone do contratante, no formato (99) 99999-9999.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("contratante telefone")) {
			c.setTelefone(mensagem);
			DAOContratante daoP = new DAOContratante();
			try {
				daoP.inserirContratante(c);
				mensagem = "Contratante cadastrado com sucesso!";
			} catch (Exception e) {
				e.printStackTrace();
				mensagem = "Erro no cadastro do contratante.";
			}
			contexto = "Menu Contratante";
			enviarMensagem(idTelegram, mensagem);
		}
	}

	public void adicionarServicoPrestador(String mensagem, long idTelegram) throws SQLException {
//		Prestador prest = new Prestador("", "", "");
//		TipoDeServico t = new TipoDeServico(null, "");

		if (mensagem.contains("Adicionar tipo de servico ao prestador")
				|| contexto.contains("Adicionar tipo de servico ao prestador")) {
			contexto = "serv email";
			mensagem = "Vamos começar? Primeiro eu preciso do email do prestador para adicionar o servico.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("serv email")) {
			p = BotAdapter.buscarPrestador(mensagem);
			
			System.out.println("prestador: "+ p.getEmail()+ "\nNome: "+ p.getNome());
			
			contexto = "prestador tipoServ";
			mensagem = "Agora eu preciso do codigo tipo de servico que deseja adicionar ao prestador.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("prestador tipoServ")) {
			tds = BotAdapter.buscarTipo(mensagem);

			System.out.println("Tiposerv: "+ tds.getCodTipoDeServico()+ "\nDescr: "+ tds.getDescr());
			
			DAOPrestador daoP = new DAOPrestador();

			try {
				if (p != null && tds != null) {
					daoP.inserirHabilidade(p, tds);
					mensagem = "servico adicionado ao prestador com sucesso!";
				} else {
					mensagem = "Erro ao adicionar servico ao prestador. Dados do prestador ou tipo de servico invalidos!";
				}
			} catch (Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao adicionar servico ao prestador";
			}
			contexto = "Menu prestador";
			enviarMensagem(idTelegram, mensagem);
		}
	}

	public void contr_tipo(String mensagem, long idTelegram) {
		if (mensagem.contains("Cadastrar tipo") || contexto.contains("Cadastrar tipo")) {
			contexto = "tipo codigo";
			mensagem = "Vamos começar? Primeiro eu preciso do codigo do tipo, ele deve ser um inteiro qualquer, só não pode ser repetido.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("tipo codigo")) {
			int cod = Integer.parseInt(mensagem);
			tds.setCodTipoDeServico(cod);
			contexto = "tipo descrição";
			mensagem = "Agora eu preciso da descrição do tipo de serviço.";
			enviarMensagem(idTelegram, mensagem);
		} else if (contexto.contains("tipo descrição")) {
			tds.setDescr(mensagem);
			DAOTipoDeServico t = new DAOTipoDeServico();
			try {
				t.inserirTipoDeServico(tds);
				mensagem = "Tipo cadastrado com sucesso!";
			} catch (Exception e) {
				e.printStackTrace();
				mensagem = "Erro no cadstro do tipo";
			}
			contexto = "Menu Tipo";
			enviarMensagem(idTelegram, mensagem);
		}

	}

	@Override
	public String getBotToken() {
		// Token gerado na criacao do bot
		return "954707664:AAHUSrO9K2k1TJrSEM9HvseNgD44zplnkN4";
	}

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBot = new TelegramBotsApi();
		BotExample bot = new BotExample();
		Date data = new Date();
		System.out.println(data);

		try {
			telegramBot.registerBot(bot);
			System.out.println("conectou");
		} catch (TelegramApiRequestException e) {
			System.out.println("Erro no Bot");
			e.printStackTrace();
		}
	}
}