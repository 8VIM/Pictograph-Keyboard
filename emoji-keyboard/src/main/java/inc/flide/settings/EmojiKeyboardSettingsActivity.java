package inc.flide.settings;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import java.util.List;

import inc.flide.emojiKeyboard.R;
import inc.flide.emoji_keyboard.constants.Constants;

public class EmojiKeyboardSettingsActivity extends Activity {

    private boolean isEmojiKeyboardEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_keyboard_settings);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

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
            //show a dialog box detailing what needs to be done with a button that takes you to lala land.
            pleaseEnableInputMethodDialog();
        }
    }

    private void pleaseEnableInputMethodDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.please_enable_ime_dialog_layout);
        dialog.setCancelable(false);
        dialog.setTitle("Please Enable IME");

        Button enableImeButton = (Button) dialog.findViewById(R.id.enableImeButton);
        enableImeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS), 0);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
