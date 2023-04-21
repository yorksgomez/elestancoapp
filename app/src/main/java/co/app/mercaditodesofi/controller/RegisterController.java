package co.app.mercaditodesofi.controller;

import java.util.HashMap;

public class RegisterController {

    public static void register(String nombre, String nacimiento, String telefono, String usuario, String contrasena, int id_ciudad) {

        HashMap<String, String> params = new HashMap<>();
        params.put("nombre", nombre);
        params.put("nacimiento", nacimiento);
        params.put("telefono", telefono);
        params.put("usuario", usuario);
        params.put("contrasena", SecurityController.encryptToSHA256(contrasena));
        params.put("id_ciudad", String.valueOf(id_ciudad));

        try {

            RequestController.makeRequest(AppConstants.REQUEST_SERVER + "user_register.php", "GET", params);

        } catch(Exception ex) {
            MessageController.showError(ex.toString() + ex.getMessage());
        }

    }

}
