package model;

public class Country {
    private int id;
    private String name;
    private String continent;
    private String code;  // Country code (e.g., "US", "FR", "JP")

    public Country() {}

    public Country(int id, String name, String continent, String code) {
        this.id = id;
        this.name = name;
        this.continent = continent;
        this.code = code;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContinent() { return continent; }
    public void setContinent(String continent) { this.continent = continent; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
