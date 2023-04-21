package co.app.mercaditodesofi.ui.login;

import co.app.mercaditodesofi.data.model.LoggedInUser;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private LoggedInUser user;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName, LoggedInUser user) {
        this.user = user;
    }

    public String getDisplayName() {
        return displayName;
    }

    public LoggedInUser getUser() {
        return user;
    }
}
