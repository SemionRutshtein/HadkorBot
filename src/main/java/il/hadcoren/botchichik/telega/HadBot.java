package il.hadcoren.botchichik.telega;

import com.vdurmont.emoji.EmojiParser;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class HadBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    @Getter
    private String name;

    @Value("${bot.token}")
    @Getter
    private String token;

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String str = ":stuck_out_tongue_winking_eye:";
        String smile = EmojiParser.parseToUnicode(str);

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chat_id));
            message.setText("Had " + message_text + " From Hadbot " + "\n"
            + "Had Nadav Shalom YaAH!" + smile);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
