package module;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GoogleTranslateAPI {
    public String google_Translate(String string)//Hàm in ra từ dịch nghĩa qua api google translate
    {
//        Scanner scanner = new Scanner(System.in);
//        String string = scanner.next();
//        string = string.trim();
        try {
            URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            int respondCode = httpURLConnection.getResponseCode();

            if (respondCode != 200) {
                throw new RuntimeException("HTTPRESPOND CODE: " + respondCode);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                Scanner scanner1 = new Scanner(url.openStream());

                while (scanner1.hasNext()) {
                    stringBuilder.append(scanner1.nextLine());
                }

                scanner1.close();

                System.out.println(stringBuilder);

                //JSON Parser
                JSONParser parser = new JSONParser();
                JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(stringBuilder));

                //System.out.println(dataObject.get(0));
                return (String) dataObject.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static void main(String[] args) {
        GoogleTranslateAPI googleTranslateAPI = new GoogleTranslateAPI();
        System.out.print(googleTranslateAPI.google_Translate("hello"));
    }
}
