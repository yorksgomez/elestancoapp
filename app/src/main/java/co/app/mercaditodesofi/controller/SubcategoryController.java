package co.app.mercaditodesofi.controller;

import android.util.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

public class SubcategoryController {

    public static ArrayList<String> getSubcategories(String category) {
        ArrayList<String> subcategories = new ArrayList<>();
        String result;

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("categoria", category);
            result = RequestController.makeRequest(AppConstants.REQUEST_SERVER + "get_subcategorias.php", "GET", params);
            subcategories = jsonToArray(result);
        } catch(Exception ex) {
            MessageController.showError(ex.toString());
        }

        return subcategories;
    }

    private static ArrayList<String> jsonToArray(String encoded) throws IOException {

        ArrayList<String> subcategories = new ArrayList<>();

        JsonReader reader = new JsonReader(new StringReader(encoded));

        reader.beginArray();

        while(reader.hasNext())
            subcategories.add(reader.nextString());

        reader.endArray();
        reader.close();

        return subcategories;
    }

}
