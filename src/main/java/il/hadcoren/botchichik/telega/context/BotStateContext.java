package il.hadcoren.botchichik.telega.context;

import il.hadcoren.botchichik.telega.InputMessageHandler;
import il.hadcoren.botchichik.telega.state.BotState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {
    private Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    public SendMessage processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }

    private InputMessageHandler findMessageHandler(BotState currentState) {
        if (isFillingProfileState(currentState)) {
            return messageHandlers.get(BotState.FILLING_DATA);
        }

        return messageHandlers.get(currentState);
    }

    private boolean isFillingProfileState(BotState currentState) {
        switch (currentState) {
            case FILLING_DATA:
            case SUCCESS_DATA_SAVE:
            case ALLOW_DATA_SAVE:
            case SHOW_PERSONAL_DATA:
            case GENERATE_QUESTION_MESSAGE:
            case GENERATED_RANDOM_ANSWER:
            case SHOW_ABOUT_INFORMATION:
                return true;
            default:
                return false;
        }
    }


}





