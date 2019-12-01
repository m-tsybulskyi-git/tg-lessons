import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Lesson_3 extends TelegramLongPollingBot {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(new Lesson_3());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return [BOT_USERNAME];
    }

    @Override
    public String getBotToken() {
        return [YOUR_SECRET_TOKEN];
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            switch (message.getText()){
                case "/start":
                    SendMessage sendMessage = new SendMessage(); // метод для відправки повідомлень
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setText("Розпочнемо");
                    try{
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    break;
                case "/help":
                    try {
                        execute(new SendMessage()       // варіант для відправки повідомлення в одну лінію
                                .setChatId(message.getChatId())
                                .setText("Допомогти?"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    echo(message);

            }
        }
    }

    private void echo(Message message) {
        try {
            execute(new SendMessage()
                    .setChatId(message.getChatId())
                    .setText("bold: *" + message.getText() + "*\n"    // *bold*
                            +"italic: _" + message.getText() + "_\n" // _italic_
                            +"code-block: ```"+ message.getText() + "```") // ```code-block```
                    .setParseMode(ParseMode.MARKDOWN) // тип розмірки
                    .setReplyToMessageId(message.getMessageId()) // відповісти на повідомлення
            );
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
