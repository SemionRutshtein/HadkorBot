package il.hadcoren.botchichik.telega;

import com.vdurmont.emoji.EmojiParser;
import il.hadcoren.botchichik.config.BotConfig;
import il.hadcoren.botchichik.dao.UserDao;
import il.hadcoren.botchichik.model.ConnectedUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class HadBot extends TelegramLongPollingBot {

    private final UserDao userDao;
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String str = ":stuck_out_tongue_winking_eye:";
        String smile = EmojiParser.parseToUnicode(str);

        if (update.hasMessage() && update.getMessage().hasText()) {

            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if (message_text.equals("/start")) {
                SendMessage message = new SendMessage(); // Create a message object object
                message.setChatId(String.valueOf(chat_id));
                message.setText("Had " + message_text + " From Hadbot \n" + smile);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (message_text.equals("/menu")) {
                SendMessage menuMessage = new SendMessage(); // Create a message object object
                menuMessage.setChatId(String.valueOf(chat_id));
                menuMessage.setText("Menu Keyboard");

                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

                List<KeyboardRow> keyboard = new ArrayList<>();

                KeyboardRow row = new KeyboardRow();
                row.add("First Button");
                row.add("Second Button");
                keyboard.add(row);

                row = new KeyboardRow();
                row.add("Third Button");
                row.add("Had Button");
                keyboard.add(row);

                row = new KeyboardRow();
                row.add("Third Button");
                keyboard.add(row);
                keyboardMarkup.setKeyboard(keyboard);
                menuMessage.setReplyMarkup(keyboardMarkup);
                try {
                    execute(menuMessage);
                } catch (TelegramApiException e) {
                    log.error(e.getMessage());
                }
            }


            }
        }
}
//            ConnectedUser newConnectedUser = ConnectedUser.builder()
//                    .id(String.valueOf(update.getMessage().getChatId()))
//                    .nikeName(update.getMessage().getForwardFrom().getUserName())
//                    .shortDescription(update.getMessage().getForwardFrom().getLastName())
//                    .longDescription(update.getMessage().getForwardFrom().toString())
//                    .build();
//            userDao.addNewConnectedUser(newConnectedUser);
//
//            SendMessage message = new SendMessage();
//            message.setChatId(String.valueOf(chat_id));
//            message.setText("Had " + message_text + " From Hadbot " + "\n"
//            + "Had Nadav Shalom YaAH!" + smile);
//      try {
//              execute(message);
//              } catch (TelegramApiException e) {
//              e.printStackTrace();
//              }