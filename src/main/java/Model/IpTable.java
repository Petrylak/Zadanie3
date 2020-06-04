package Model;

public class IpTable {
    private int id;
    private String ipAdress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public IpTable(int id, String ipAdress) {
        this.id = id;
        this.ipAdress = ipAdress;
    }

    public IpTable(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    @Override
    public String toString() {
        return "["+id+"]-"+ipAdress;
    }
}
