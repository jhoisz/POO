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
import java.util.List;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class Bot extends TelegramLongPollingBot {

	@Override
	public String getBotUsername() {
		return "teste";
	}
	
	public void enviarMensagem(long idTelegram, String mensagem) {
		//Mensagem a ser enviada
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
		//Dados da mensagem recebida pelo bot
		String nome = u.getMessage().getFrom().getFirstName();
		long idTelegram = u.getMessage().getChatId();
		String mensagem = u.getMessage().getText();
		
		System.out.println(nome + " (" + idTelegram + "): " + mensagem);
		
		enviarMensagem(idTelegram, "Devolvendo: " + mensagem);
	}

	@Override
	public String getBotToken() {
		// Token gerado na criacao do bot
		return "495230889:AAGqRuQ5Pq2ZgZco3dqmsh9UMuZzT2aNzU8";
	}

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBot = new TelegramBotsApi();
		Bot bot = new Bot();
		
		try {
			telegramBot.registerBot(bot);
		} catch (TelegramApiRequestException e) {
			System.out.println("Erro no Bot");
			e.printStackTrace();
		}
	}	
}