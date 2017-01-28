package inc.flide.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import inc.flide.emojiKeyboard.R;
import inc.flide.emoji_keyboard.constants.Constants;

public class SharedPreferencesSetting extends PreferenceActivity {

    public static final String CHANGE_ICON_SET_KEY = "icon_set";
    public static final String CHANGE_ICON_SET_VALUE_DEFAULT = "emojione_emoji_";

    private boolean isEmojiKeyboardEnabled;
    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }


    @Override
    protected void onStart() {
        super.onStart();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> enabledInputMethodList = inputMethodManager.getEnabledInputMethodList();
        isEmojiKeyboardEnabled = false;
        for(InputMethodInfo inputMethodInfo: enabledInputMethodList) {
            if(inputMethodInfo.getId().compareTo(Constants.EMOJI_KEYBOARD_ID) == 0) {
                isEmojiKeyboardEnabled = true;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isEmojiKeyboardEnabled) {
            pleaseEnableInputMethodDialog();
        }
    }

    private void pleaseEnableInputMethodDialog() {
        final MaterialDialog enableInputMethodNotificationDialog = new MaterialDialog.Builder(this)
                .title(R.string.enable_ime_dialog_title)
                .content(R.string.enable_ime_dialog_content)
                .neutralText(R.string.enable_ime_dialog_neutral_button_text)
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .build();

        enableInputMethodNotificationDialog.getBuilder()
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        startActivityForResult(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS), 0);
                        enableInputMethodNotificationDialog.dismiss();
                    }
                });

        enableInputMethodNotificationDialog.show();
    }
}
