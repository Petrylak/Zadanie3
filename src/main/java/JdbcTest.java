import Model.IpCountry;
import Model.IpTable;
import org.json.JSONObject;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JdbcTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        App app = new App();
        ApiConnection apiConnection = new ApiConnection();
        int userInput;
        do {
            System.out.println("1. Dodaj 100 randomowych Ip do bazy\n" +
                    "2. Wyświetl tabele IpAdress \n" +
                    "3. Usuń dane z tabeli IpAdress oraz IpCountry\n" +
                    "4. Wyślij dane do API i zapisz w tabeli IpCountry \n" +
                    "5. Wyświetl randomowe ip z bazy \n" +
                    "6. Wyświetl tabele IpCountry");
            userInput = scanner.nextInt();
            switch(userInput){
                case 1:{
                    for (int i = 0; i < 100 ; i++) {
                        app.insertIpTable(app.randomIp());
                    }
                    break;
                }
                case 2:{
                    List<IpTable> ipAddress = app.selecIpAdressTable();
                    System.out.println("Lista ip: ");
                    for(IpTable c: ipAddress)
                        System.out.println(c);
                    break;
                }
                case 3:{
                    app.clearTables();
                    break;
                }
                case 4:{
                    for (int i = 0; i < app.selecIpAdress().size(); i++) {
                        String ip =app.selecIpAdress().get(i).getIpAdress();
                        System.out.println(ip);
                        System.out.println();
                        JSONObject json = new JSONObject(apiConnection.getApiTranslateData(ip).toString());
                        app.insertIpCountry(ip, json.getString("countryName"), true, new Timestamp(System.currentTimeMillis()));
                    }
                }
                case 5:{
                    Random r = new Random();
                    System.out.println(app.selecIpAdress().get(r.nextInt(50)).getIpAdress());
                    break;
                }
                case 6:{
                    List<IpCountry> ipCountries = app.selectIpCountry();
                    System.out.println("Lista ip & country: ");
                    for(IpCountry c: ipCountries)
                        System.out.println(c);
                    break;
                }
                default:{
                    app.closeConnection();
                    return;
                }
            }
        }while(true);
    }
}
