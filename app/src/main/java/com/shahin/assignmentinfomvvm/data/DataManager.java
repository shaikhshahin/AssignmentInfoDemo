package com.shahin.assignmentinfomvvm.data;


import com.preference.PowerPreference;
import com.preference.Preference;
import com.shahin.assignmentinfomvvm.App;
import com.shahin.assignmentinfomvvm.data.db.database.LogDatabase;
import com.shahin.assignmentinfomvvm.data.network.services.UserService;

/**
 * Created by Shahin on 16/11/2019.
 */

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public Preference getPrefs() {
        return PowerPreference.defult();
    }

    public LogDatabase getLogDatabse() {
        return LogDatabase.getInstance(App.getInstance());
    }

    public UserService getUserService() {
        return UserService.getInstance();
    }

}
