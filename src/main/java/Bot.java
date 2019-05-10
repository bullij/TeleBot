import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args)
    {
        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi =new TelegramBotsApi();

        try{
            telegramBotsApi.registerBot(new Bot());
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    public void onUpdateReceived(Update update) {
        Message message=update.getMessage();

        SendMessage sendMessage= new SendMessage();
        sendMessage.setChatId(message.getChatId());

        if (message!=null && message.hasText()) {
            String text = message.getText();
            if ("/help".equals(text)) {
                try {
                    execute(sendMessage.setText(message.getText() + "how can i help"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if ("/settings".equals(text)) {
                try {
                    execute(sendMessage.setText(message.getText() + "What are we going to customize?"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if ("/start".equals(text)){
                try {
                    execute(sendMessage.setText(message.getText() + "Asss"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    public String getBotUsername() {
        return "marine1_bot";
    }

    public String getBotToken() {
        return "494479588:AAGxg4NYW_jlerSDUfYPxIApfOxyx4MonXc";
    }
}
