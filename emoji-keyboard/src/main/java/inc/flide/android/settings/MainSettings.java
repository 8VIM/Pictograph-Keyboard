package inc.flide.android.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import inc.flide.android.emoji_keyboard.R;

public class MainSettings extends PreferenceActivity {

    public static final String CHANGE_ICON_SET_KEY = "icon_set";
    public static final String CHANGE_ICON_SET_VALUE_DEFAULT = "emojione_emoji_";

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
