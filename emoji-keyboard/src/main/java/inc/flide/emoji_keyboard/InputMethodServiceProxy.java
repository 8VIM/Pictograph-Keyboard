package inc.flide.emoji_keyboard;

import android.content.Context;

public interface InputMethodServiceProxy {
    void sendDownAndUpKeyEvent(int keyCode, int flags);

    void switchToLastInputMethod();

    void sendText(String unicodeJavaString);

    Context getContext();

    int getDrawableResourceId(String resourceString);
}
