package co.app.mercaditodesofi.data;

import java.io.IOException;
import java.util.HashMap;

import javax.security.auth.login.LoginException;

import co.app.mercaditodesofi.controller.AppConstants;
import co.app.mercaditodesofi.controller.RequestController;
import co.app.mercaditodesofi.controller.SecurityController;
import co.app.mercaditodesofi.data.model.LoggedInUser;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("usuario", username);
            params.put("contrasena", SecurityController.encryptToSHA256(password));

            String host = AppConstants.REQUEST_SERVER + "login.php",
                   method = "GET";

            String response = RequestController.makeRequest(host, method, params);

            if(Integer.valueOf(response) > 0) {
                LoggedInUser user = new LoggedInUser(Integer.valueOf(response), username, SecurityController.encryptToSHA256(password));

                return new Result.Success<>(user);
            } else {
                throw new LoginException("Logging error");
            }

        } catch (LoginException | IOException e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
