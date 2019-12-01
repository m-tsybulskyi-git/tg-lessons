import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
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

public class Lesson_5 extends TelegramLongPollingBot {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(new Lesson_5());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return [BOT_USERNAME];
    }

    @Override
    public String getBotToken() {
        return  [YOUR_SECRET_TOKEN];
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
        System.out.println("Callback-ID: " + callbackQuery.getId());
        System.out.println("Username: " + callbackQuery.getFrom().getUserName());
        System.out.println("CallBack.Data: "+ callbackQuery.getData());
        System.out.println("CallBack.Message.Text: " + callbackQuery.getMessage().getText());

        if(callbackQuery.getMessage().isReply())
            System.out.println("CallBack.Message.ReplyMessage.Text: " + callbackQuery.getMessage().getReplyToMessage().getText());
    }

    private void updateHasMessage(Message message){

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        switch (message.getText()) {
            case "/start":
                sendMessage.setText("Розпочнемо");
                try {
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
                .setCallbackData("button_1.1")
                .setText("1 Ряд 1 Кнопка");
        inlineKeyboardRow.add(inlineKeyboardButton);
        inlineKeyboard.add(inlineKeyboardRow);

        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboard);
    }
}


