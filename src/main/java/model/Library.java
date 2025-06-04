package model;

public class Library {
    private int id;
    private String name;
    private String address;
    private int cityId; // Referencia a la ciudad donde se encuentra

    public Library() {}

    public Library(int id, String name, String address, int cityId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cityId = cityId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getCityId() { return cityId; }
    public void setCityId(int cityId) { this.cityId = cityId; }
}
