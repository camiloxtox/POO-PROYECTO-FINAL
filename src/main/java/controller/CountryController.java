package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Country;
import repository.CountryRepository;
import java.util.List;
import java.util.Optional;

public class CountryController {
    public static void configureRoutes(Javalin app) {
        app.get("/countries", CountryController::getAllCountries);
        app.get("/countries/{id}", CountryController::getCountryById);
        app.post("/countries", CountryController::createCountry);
        app.put("/countries/{id}", CountryController::updateCountry);
        app.delete("/countries/{id}", CountryController::deleteCountry);
    }

    public static void getAllCountries(Context ctx) {
        List<Country> countries = CountryRepository.getAllCountries();
        ctx.json(countries);
    }

    public static void getCountryById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Country> country = CountryRepository.getCountryById(id);
        country.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Country not found"));
    }

    public static void createCountry(Context ctx) {
        Country newCountry = ctx.bodyAsClass(Country.class);
        CountryRepository.addCountry(newCountry);
        ctx.status(201).json(newCountry);
    }

    public static void updateCountry(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Country countryData = ctx.bodyAsClass(Country.class);
        boolean updated = CountryRepository.updateCountry(id, countryData.getName(), countryData.getContinent());
        if (updated) {
            ctx.result("Country successfully updated");
        } else {
            ctx.status(404).result("Country not found");
        }
    }

    public static void deleteCountry(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = CountryRepository.deleteCountry(id);
        if (deleted) {
            ctx.result("Country successfully deleted");
        } else {
            ctx.status(404).result("Country not found");
        }
    }
}
