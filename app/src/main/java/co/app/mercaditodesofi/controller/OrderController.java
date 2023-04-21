package co.app.mercaditodesofi.controller;

import android.util.JsonWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

import co.app.mercaditodesofi.model.Product;

public class OrderController {


    public static void addOrder(int id_usuario, String direccion, String forma_pago) {

        try {

            ArrayList<Product> products = CartController.getProducts();
            ArrayList<Integer> cantidades = CartController.getCantidades();

            StringWriter str_writed = new StringWriter();

            JsonWriter writer = new JsonWriter(str_writed);

            writer.beginArray();

            for(int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                writer.beginArray();
                writer.value(product.getId());
                writer.value(cantidades.get(i) / product.getPrice());
                writer.endArray();
            }

            writer.endArray();

            writer.flush();

            HashMap<String, String> params = new HashMap<>();
            params.put("id_usuario", String.valueOf(id_usuario));
            params.put("direccion", direccion);
            params.put("forma_pago", forma_pago);
            params.put("productos", str_writed.toString());

            RequestController.makeRequest(AppConstants.REQUEST_SERVER + "add_pedidos.php", "GET", params);
        } catch(Exception ex) {
            MessageController.showError(ex.toString());
        }

    }
}
