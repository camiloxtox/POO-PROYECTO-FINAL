package repository;

import model.Country;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryRepository {
    private static List<Country> countries = new ArrayList<>();

    // CREATE - Add country
    public static void addCountry(Country country) {
        countries.add(country);
    }

    // READ - Get all countries
    public static List<Country> getAllCountries() {
        return countries;
    }

    // READ - Get country by ID
    public static Optional<Country> getCountryById(int id) {
        return countries.stream().filter(c -> c.getId() == id).findFirst();
    }

    // UPDATE - Update country details
    public static boolean updateCountry(int id, String newName, String newContinent) {
        Optional<Country> country = getCountryById(id);
        if (country.isPresent()) {
            country.get().setName(newName);
            country.get().setContinent(newContinent);
            return true;
        }
        return false;
    }

    // DELETE - Remove country by ID
    public static boolean deleteCountry(int id) {
        return countries.removeIf(c -> c.getId() == id);
    }
}
