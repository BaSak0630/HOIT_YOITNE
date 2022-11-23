package ga.kimdh.testfirebase;

public class Bus {
    private String bus_id;
    private String bus_latitude;
    private String bus_longitude;
    Bus(){

    }
    public Bus(String bus_id, String bus_latitude,String bus_longitude){
        this.bus_id = bus_id;
        this.bus_latitude= bus_latitude;
        this.bus_longitude= bus_longitude;
    }

    public String getBus_latitude() {
        return bus_latitude;
    }

    public void setBus_latitude(String bus_latitude) {
        this.bus_latitude = bus_latitude;
    }

    public String getBus_longitude() {
        return bus_longitude;
    }

    public void setBus_longitude(String bus_longitude) {
        this.bus_longitude = bus_longitude;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }
}
