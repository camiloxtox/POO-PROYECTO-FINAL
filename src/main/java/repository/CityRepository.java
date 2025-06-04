package repository;

import model.City;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityRepository {
    private static List<City> cities = new ArrayList<>();

    public static void addCity(City city) { cities.add(city); }
    public static List<City> getAllCities() { return cities; }
    public static Optional<City> getCityById(int id) { return cities.stream().filter(c -> c.getId() == id).findFirst(); }
    public static boolean updateCity(int id, String newName, int newCountryId) {
        Optional<City> city = getCityById(id);
        if (city.isPresent()) {
            city.get().setName(newName);
            city.get().setCountryId(newCountryId);
            return true;
        }
        return false;
    }
    public static boolean deleteCity(int id) { return cities.removeIf(c -> c.getId() == id); }
}
