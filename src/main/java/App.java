import Model.IpCountry;
import Model.IpTable;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class App {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:ipadress_storage.db";

    private Connection conn;
    private Statement stat;

    public App() {
        try {
            Class.forName(App.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        createTables();
    }
    public boolean createTables()  {
        String createIpTable = "CREATE TABLE IF NOT EXISTS IpAdress (id INTEGER PRIMARY KEY AUTOINCREMENT, ip varchar(255))";
        String createIpCountryTable = "CREATE TABLE IF NOT EXISTS IpCountry (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ip varchar(255)," +
                "country varchar(255)," +
                "status int(1)," +
                "time DATE)";
        try {
            stat.execute(createIpTable);
            stat.execute(createIpCountryTable);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean clearTables()  {
        String clearIpTable = "DELETE FROM IpAdress";
        String clearIpCountry = "DELETE FROM IpCountry";
        try {
            stat.execute(clearIpTable);
            stat.execute(clearIpCountry);
        } catch (SQLException e) {
            System.err.println("Blad przy czyszczeniu");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean insertIpCountry(String ip, String country, boolean status, Timestamp time) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into IpCountry values (NULL, ?, ?, ?, ?);");
            prepStmt.setString(1, ip);
            prepStmt.setString(2, country);
            prepStmt.setInt(3, status ? 1 : 0);
            prepStmt.setTimestamp(4, time);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu ip country");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertIpTable(String ip) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into IpAdress values (NULL, ?);");
            prepStmt.setString(1, ip);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu ip");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<IpTable> selecIpAdressTable() {
        List<IpTable> ipAddress = new LinkedList<IpTable>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM IpAdress");
            int id;
            String ip;
            while(result.next()) {
                id = result.getInt("id");
                ip = result.getString("ip");

                ipAddress.add(new IpTable(id, ip));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ipAddress;
    }
    public List<IpTable> selecIpAdress() {
        List<IpTable> ipAddress = new LinkedList<IpTable>();
        try {
            ResultSet result = stat.executeQuery("SELECT ip FROM IpAdress");
            String ip;
            while(result.next()) {
                ip = result.getString("ip");

                ipAddress.add(new IpTable(ip));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ipAddress;
    }
    public List<IpCountry> selectIpCountry() {
        List<IpCountry> ipCountries = new LinkedList<IpCountry>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM IpCountry");
            int id;
             String ip;
             String country;
             boolean status;
             Timestamp time;
            while(result.next()) {
                id = result.getInt("id");
                ip = result.getString("ip");
                country = result.getString("country");
                status = result.getInt("status") == 1;
                time = result.getTimestamp("time");


                ipCountries.add(new IpCountry(id, ip, country, status, time));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ipCountries;
    }
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }
    public String randomIp(){
        Random r = new Random();
        return(r.nextInt(195) + "." + r.nextInt(240) + "." + r.nextInt(256) + "." + r.nextInt(256)).toString();
    }
}

