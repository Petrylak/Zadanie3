package Model;

import java.sql.Timestamp;

public class IpCountry {
    private int id;
    private String ip;
    private String country;
    private boolean status;
    private Timestamp time;

    public IpCountry(String ip, String country, boolean status, Timestamp time) {
        this.ip = ip;
        this.country = country;
        this.status = status;
        this.time = time;
    }

    public IpCountry(int id, String ip, String country, boolean status, Timestamp time) {
        this.id = id;
        this.ip = ip;
        this.country = country;
        this.status = status;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "["+id+"] - "+ip+ " " +country+ " " +status+ " " + time;
    }
}
