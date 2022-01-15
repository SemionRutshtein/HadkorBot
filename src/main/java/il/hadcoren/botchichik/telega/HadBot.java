package il.hadcoren.botchichik.telega;

import com.vdurmont.emoji.EmojiParser;
import il.hadcoren.botchichik.dao.UserDao;
import il.hadcoren.botchichik.model.ConnectedUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class HadBot extends TelegramLongPollingBot {

    private final UserDao userDao;

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

            ConnectedUser newConnectedUser = ConnectedUser.builder()
                    .id(String.valueOf(update.getMessage().getForwardFrom().getId()))
                    .nikeName(update.getMessage().getForwardFrom().getUserName())
                    .shotDescription(update.getMessage().getForwardFrom().getLastName() + update.getMessage().getForwardFromChat())
                    .longDescription(update.getMessage().getForwardFrom().toString())
                    .build();
            userDao.addNewConnectedUser(newConnectedUser);

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chat_id));
            message.setText("Had " + message_text + " From Hadbot " + "\n"
            + "Had Nadav Shalom YaAH!" + smile);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Can not sleep");
            }
            SendMessage messageBack = new SendMessage();
            message.setChatId(String.valueOf(chat_id));
            message.setText(userDao.getAllConnectedUsers().toString()   );

        }
    }
}
