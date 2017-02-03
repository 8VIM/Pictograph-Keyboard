package inc.flide.ime;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import inc.flide.emojiKeyboard.R;
import inc.flide.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.emoji_keyboard.view.KeyboardView;

public class EmojiKeyboardService extends InputMethodService implements InputMethodServiceProxy {

    private InputConnection inputConnection;

    private InputMethodManager previousInputMethodManager;
    private IBinder iBinder;

    public EmojiKeyboardService() {
        super();

        Logger.init("EmojiKeyboard").methodCount(5).logLevel(LogLevel.FULL).methodOffset(3);
    }

    @Override
    public View onCreateInputView() {

        previousInputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        iBinder = this.getWindow().getWindow().getAttributes().token;

        KeyboardView keyboardView = (KeyboardView) getLayoutInflater()
                .inflate(R.layout.emoji_keyboard_layout, null);

        return keyboardView.getView();
    }

    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInputView(attribute, restarting);
        inputConnection = getCurrentInputConnection();
    }

    public void sendText(String text) {
        inputConnection.commitText(text, 1);
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void sendDownKeyEvent(int keyEventCode, int flags) {
        inputConnection.sendKeyEvent(
                new KeyEvent(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        KeyEvent.ACTION_DOWN,
                        keyEventCode,
                        0,
                        flags
                )
        );
    }

    private void sendUpKeyEvent(int keyEventCode, int flags) {
        inputConnection.sendKeyEvent(
                new KeyEvent(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        KeyEvent.ACTION_UP,
                        keyEventCode,
                        0,
                        flags
                )
        );
    }

    public void sendDownAndUpKeyEvent(int keyEventCode, int flags){
        sendDownKeyEvent(keyEventCode, flags);
        sendUpKeyEvent(keyEventCode, flags);
    }

    public void switchToPreviousInputMethod() {

        try {
            previousInputMethodManager.switchToLastInputMethod(iBinder);
        } catch (Throwable t) { // java.lang.NoSuchMethodError if API_level<11
            Context context = getApplicationContext();
            CharSequence text = "Unfortunately input method switching isn't supported in your version of Android! You will have to do it manually :(";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public int getDrawableResourceId(String drawableName) {
        return getContext().getResources()
                .getIdentifier(drawableName, "drawable", getPackageName());
    }
}
