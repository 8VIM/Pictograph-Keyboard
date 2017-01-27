package inc.flide.settings;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import inc.flide.emojiKeyboard.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class EmojiKeyboardSettingsActivityFragment extends Fragment {

    public EmojiKeyboardSettingsActivityFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_emoji_keyboard_settings, container, false);
    }
}
