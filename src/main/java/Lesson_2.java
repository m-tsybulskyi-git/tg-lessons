import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Lesson_2 extends TelegramLongPollingBot {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(new Lesson_2());
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
            User user = message.getFrom();
            Chat chat = message.getChat();

            // start - log info
            // info - user
            System.out.println("User ID: " + user.getId());
            System.out.println("Username: " + user.getUserName());
            System.out.println("First name: " + user.getFirstName());
            System.out.println("Last name: " + user.getLastName());
            System.out.println("Language code: " + user.getLanguageCode());
            // info - chat
            System.out.println("Chat ID: " + chat.getId());
            if(chat.isUserChat()) {
                System.out.println("Username: " + chat.getUserName());
                System.out.println("First name:" + chat.getFirstName());
                System.out.println("Last name:" + chat.getLastName());
            }
            if(chat.isGroupChat()){
                System.out.println("Title: " + chat.getTitle());
                System.out.println("Description: " + chat.getDescription());
                System.out.println("Invite link: " + chat.getInviteLink());
                System.out.println("Pinned message" + chat.getPinnedMessage().getText());

            }
            //info - message
            System.out.println("Text: " + message.getText());
            System.out.println("Date " + message.getDate());
            if(message.isReply())
                System.out.println("Reply: " + message.getReplyToMessage().getText());

            // end - log info


        }
    }
}
