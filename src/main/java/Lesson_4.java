import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;


import java.util.ArrayList;
import java.util.List;

public class Lesson_4 extends TelegramLongPollingBot {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(new Lesson_4());
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
            Message message = update.getMessage();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());

            switch (message.getText()){
                case "/start":
                    sendMessage.setText("Розпочнемо");
                    try{
                        sendMessage.setReplyMarkup(new ReplyKeyboardRemove());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    try{
                        sendMessage.setText(message.getText());
                        sendMessage.setReplyMarkup(setKeyboard());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    private ReplyKeyboardMarkup setKeyboard() {

        List<KeyboardRow> keyboard = new ArrayList<>();

        /////////////////////////////////////////////////
        KeyboardRow keyboardRow_1 = new KeyboardRow();

        KeyboardButton keyboardButton_1_1 = new KeyboardButton().setText("1 Ряд 1 Кнопка");
        keyboardRow_1.add(keyboardButton_1_1);

        KeyboardButton keyboardButton_1_2 = new KeyboardButton().setText("1 Ряд 2 Кнопка");
        keyboardRow_1.add(keyboardButton_1_2);

        keyboard.add(keyboardRow_1);
        /////////////////////////////////////////////////
        KeyboardRow keyboardRow_2 = new KeyboardRow();

        KeyboardButton keyboardButton_2_1 = new KeyboardButton().setText("2 Ряд 1 Кнопка");
        keyboardRow_2.add(keyboardButton_2_1);

        keyboard.add(keyboardRow_2);
        /////////////////////////////////////////////////
        KeyboardRow keyboardRow_3 = new KeyboardRow();

        KeyboardButton keyboardButton_3_1 = new KeyboardButton().setText("3 Ряд 1 Кнопка");
        keyboardRow_3.add(keyboardButton_3_1);

        KeyboardButton keyboardButton_3_2 = new KeyboardButton().setText("3 Ряд 2 Кнопка");
        keyboardRow_3.add(keyboardButton_3_2);

        keyboard.add(keyboardRow_3);
        /////////////////////////////////////////////////
        return new ReplyKeyboardMarkup()
                .setResizeKeyboard(true) // дозволити, змінювати розмір, до комфортного, дефолтне значення false (кнопки займають всю площу стандартної клавіатури )
                .setOneTimeKeyboard(false) // після натискання кнопки, клавіатура скривається
                .setKeyboard(keyboard);
    }
}

