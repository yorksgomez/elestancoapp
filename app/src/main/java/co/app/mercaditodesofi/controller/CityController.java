package co.app.mercaditodesofi.controller;

import android.util.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import co.app.mercaditodesofi.model.City;

public class CityController {

    public static ArrayList<City> getCityFromDepartment(String departamento) {

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("departamento", departamento);
            return jsonDecode(RequestController.makeRequest(AppConstants.REQUEST_SERVER + "get_small_cities.php", "GET", params));
        } catch(Exception ex) {
            MessageController.showError(ex.toString() + ex.getMessage());
        }

        return null;
    }

    public static ArrayList<City> jsonDecode(String encoded) throws IOException {
        ArrayList<City> cities = new ArrayList<>();

        JsonReader reader = new JsonReader(new StringReader(encoded));

        reader.beginArray();

        while(reader.hasNext()) {
            reader.beginArray();

            int id = reader.nextInt();
            String department = reader.nextString(),
                   city = reader.nextString();


            cities.add(new City(id, department, city));

            reader.endArray();
        }

        reader.endArray();
        reader.close();

        return cities;
    }

}
