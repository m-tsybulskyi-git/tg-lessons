import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;


import java.util.ArrayList;
import java.util.List;

public class Lesson_6 extends TelegramLongPollingBot {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(new Lesson_6());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "tgApiLessons_by44days_bot";
    }

    @Override
    public String getBotToken() {
        return "935812180:AAFfgteJ4tO6dT0xw4Yi3tcfvM3RqrX_2j0";
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            updateHasMessage(update.getMessage());
        }
        else if(update.hasCallbackQuery()){
            updateHasCallbackQuery(update.getCallbackQuery());
        }
    }

    private void updateHasCallbackQuery(CallbackQuery callbackQuery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery()
                .setCallbackQueryId(callbackQuery.getId())
                .setText(callbackQuery.getMessage().getText())
                .setShowAlert(false);
        EditMessageText editMessageText = new EditMessageText()
                .setMessageId(callbackQuery.getMessage().getMessageId())
                .setChatId(callbackQuery.getMessage().getChatId())
                .setText("Змінимось разом");
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup()
                .setReplyMarkup(setInlineKeyboard())
                .setMessageId(callbackQuery.getMessage().getMessageId())
                .setChatId(callbackQuery.getMessage().getChatId());
        try{
            execute(editMessageText);
            execute(editMessageReplyMarkup);
            execute(answerCallbackQuery);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void updateHasMessage(Message message){

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        switch (message.getText()) {
            case "/start":
                sendMessage.setText("Розпочнемо");
                try {
                    execute(new DeleteMessage()
                            .setChatId(message.getChatId().toString())
                            .setMessageId(message.getMessageId()));

                    execute(sendMessage.setReplyMarkup(setInlineKeyboard()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            default:
                sendMessage.setText(message.getText());
                try {
                    execute(sendMessage.setReplyMarkup(setInlineKeyboard()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
        }
    };

    private InlineKeyboardMarkup setInlineKeyboard() {
        List<List<InlineKeyboardButton>> inlineKeyboard = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton()
                .setCallbackData("button")
                .setText("Кнопка");
        inlineKeyboardRow.add(inlineKeyboardButton);
        inlineKeyboard.add(inlineKeyboardRow);

        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboard);
    }
}


