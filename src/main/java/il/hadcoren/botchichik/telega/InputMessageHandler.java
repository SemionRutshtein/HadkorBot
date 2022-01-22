package il.hadcoren.botchichik.telega;

import il.hadcoren.botchichik.telega.state.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface InputMessageHandler {
    SendMessage handle(Message message);

    BotState getHandlerName();
}