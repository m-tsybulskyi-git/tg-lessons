import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Lesson_1 extends TelegramLongPollingBot {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(new Lesson_1());
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
        }
    }
}

