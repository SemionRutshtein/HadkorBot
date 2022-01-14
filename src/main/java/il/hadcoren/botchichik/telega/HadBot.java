package il.hadcoren.botchichik.telega;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class HadBot extends TelegramLongPollingBot {

//    @Value("{telegas.botToken}")
//    private String token;
//
//    @Value("{telegas.userName}")
//    private String name;

    @Override
    public String getBotUsername() {
        return "BotBottoBot";
//        return name;
    }

    @Override
    public String getBotToken() {
//        return token;
        return "1462914779:AAEuZZCpRlkaMvme_D3owMEhQgAElU6d0gE";
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chat_id));
            message.setText("Had " + message_text + " From Hadbot " + "\n"
            + " Had Nadav Shalom " );
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
