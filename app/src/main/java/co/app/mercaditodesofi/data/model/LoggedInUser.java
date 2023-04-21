package co.app.mercaditodesofi.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private int id;
    private String displayName;
    private String password;

    public LoggedInUser(int id, String displayName, String password) {
        this.id = id;
        this.displayName = displayName;
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
