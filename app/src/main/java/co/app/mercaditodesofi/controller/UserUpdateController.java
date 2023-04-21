package co.app.mercaditodesofi.controller;

import java.util.HashMap;

import co.app.mercaditodesofi.data.LoginRepository;

public class UserUpdateController {

    public static void updateCity(int id) {

        try {
            HashMap<String, String> params = new HashMap<>();

            params.put("id_ciudad", String.valueOf(id));
            params.put("id_usuario", String.valueOf(LoginRepository.getInstance(null).getUser().getId()));

            RequestController.makeRequest(AppConstants.REQUEST_SERVER + "update_city.php", "GET", params);
        } catch(Exception ex) {
            MessageController.showError(ex.toString());
        }

    }

}
