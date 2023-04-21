package co.app.mercaditodesofi.controller;

import android.util.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DepartmentController {

    public static ArrayList<String> getDepartments() {

        try {
            return jsonDecode(RequestController.makeRequest(AppConstants.REQUEST_SERVER + "get_big_cities.php", "GET", new HashMap<String, String>()));
        } catch(Exception ex) {
            MessageController.showError(ex.toString() + ex.getMessage());
        }

        return new ArrayList<>();
    }

    private static ArrayList<String> jsonDecode(String encoded) throws IOException {

        ArrayList<String> departments = new ArrayList<>();

        JsonReader reader = new JsonReader(new StringReader(encoded));

        reader.beginArray();

        while(reader.hasNext()) {

            String department = reader.nextString();

            departments.add(department);

        }

        reader.endArray();
        reader.close();

        return departments;
    }

}
