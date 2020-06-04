import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ApiConnection {
    public StringBuilder getApiTranslateData(String ip){

        StringBuilder sb = new StringBuilder();
        try {
            URL ip2country = new URL("https://api.ip2country.info/ip?" + ip);
            URLConnection yc = ip2country.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
            }
        }catch(IOException e){
            System.err.println("Problem z pobraniem danych z API");
            e.printStackTrace();
        }
        return sb;
    }
}
