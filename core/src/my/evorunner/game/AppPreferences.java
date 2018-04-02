package my.evorunner.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * App preference file
 * Version 1.0
 * Last Edited: Matthew Phan
 * Changelog 1.0
 */
// This class is used to store data about user preferences in game settings
public class AppPreferences {
    public static final String PREFS_NAME = "Saved Data";
    private Preferences preferences;

    protected Preferences getPrefs() {
        if (preferences == null)
            preferences = Gdx.app.getPreferences(PREFS_NAME);
        return preferences;
    }



    
}