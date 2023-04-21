package co.app.mercaditodesofi.controller;

import android.util.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import co.app.mercaditodesofi.model.Product;

public class ProductController {

    public static ArrayList<Product> getProductsByCategory(String category, String... extras)  {
        ArrayList<Product> products = new ArrayList<>();

        String result, subcategory = "";

        if(extras.length > 0)
            subcategory = extras[0];

        HashMap<String, String> params = new HashMap<>();
        params.put("params", "categoria=" + category + ";subcategoria=" + subcategory);

        try {

            result = RequestController.makeRequest(AppConstants.REQUEST_SERVER + "get_products.php", "GET", params);
            products = jsonToArray(result);

        } catch(Exception ex) {
            MessageController.showError(ex.toString() + ex.getMessage());
        }

        return products;
    }

    private static ArrayList<Product> jsonToArray(String encoded) throws IOException {

        ArrayList<Product> products = new ArrayList<>();

        JsonReader reader = new JsonReader(new StringReader(encoded));

        reader.beginArray();

        while(reader.hasNext()) {
            reader.beginArray();

            Product product = new Product();

            product.setId(reader.nextInt());
            product.setName(reader.nextString());
            product.setDescription(reader.nextString());
            product.setImgUrl(reader.nextString());
            product.setCategory(reader.nextString());
            product.setPrice(reader.nextInt());
            product.setSubcategory(reader.nextString());

            products.add(product);

            reader.endArray();
        }

        reader.endArray();
        reader.close();

        return products;
    }

}
