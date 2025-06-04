package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.City;
import repository.CityRepository;

import java.util.List;
import java.util.Optional;

public class CityController {
    public static void configureRoutes(Javalin app) {
        app.get("/cities", CityController::getAllCities);
        app.get("/cities/{id}", CityController::getCityById);
        app.post("/cities", CityController::createCity);
        app.put("/cities/{id}", CityController::updateCity);
        app.delete("/cities/{id}", CityController::deleteCity);
    }

    public static void getAllCities(Context ctx) { ctx.json(CityRepository.getAllCities()); }
    public static void getCityById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<City> city = CityRepository.getCityById(id);
        city.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("City not found"));
    }
    public static void createCity(Context ctx) {
        City newCity = ctx.bodyAsClass(City.class);
        CityRepository.addCity(newCity);
        ctx.status(201).json(newCity);
    }
    public static void updateCity(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        City cityData = ctx.bodyAsClass(City.class);
        boolean updated = CityRepository.updateCity(id, cityData.getName(), cityData.getCountryId());
        if (updated) { ctx.result("City successfully updated"); }
        else { ctx.status(404).result("City not found"); }
    }
    public static void deleteCity(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = CityRepository.deleteCity(id);
        if (deleted) { ctx.result("City successfully deleted"); }
        else { ctx.status(404).result("City not found"); }
    }
}
