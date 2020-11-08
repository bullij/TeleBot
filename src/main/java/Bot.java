import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.regex.Pattern;

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
            try {
                execute(sendMessage.setText(text + " : " + getAnswer(text)));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    public String getAnswer(String msg){
        String ans = "";
        Pattern purl = Pattern.compile("(https://)?(www).+(ru)");

        switch (msg){
            case ("/start"):
                 ans = "дратути";
                break;
            default:
                if(purl.matcher(msg).find()){
                    String url = purl.matcher(msg).toString();
                    Document doc;
                    doc = Jsoup.parse(url);
                    String title = doc.title();
                    ans = "уууу, сайтик про " + title + " )))";

                }
                else {
                    ans = "я не понимаю!!!(";
                }
        }
        return ans;
    }

    public String getBotUsername() {
        return "marine1_bot";
    }

    public String getBotToken() {
        return "494479588:AAGxg4NYW_jlerSDUfYPxIApfOxyx4MonXc";
    }
}
