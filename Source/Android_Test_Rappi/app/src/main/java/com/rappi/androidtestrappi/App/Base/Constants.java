package com.rappi.androidtestrappi.App.Base;

/**
 * Created by Sebastian on 10/02/16.
 */
public class Constants {

    public static String URL_SERVER = "https://www.reddit.com/reddits.json";


    public interface Application {
        String BUILD_TYPE_DEVELOPMENT = "staging";
        String BUILD_TYPE_TEST = "debug";
        String BUILD_TYPE_PRODUCTION = "release";


    }

    public interface FireBaseNotifications {


    }
    public interface Development {

    }

    public interface Preferences {
        String PREFERENCES_NAME = "redditsPreferences";
        String CURRENT_USER = "currentUser";
        String CURRENT_TOKEN = "currentToken";
        String LAST_EMAIL_LOGIN = "lastEmailUserLogin";
        String PUSH_TOKEN_SENT = "pushTokenSent";
    }




}
