package inc.flide.android.emoji_keyboard;

import android.content.Context;
import android.content.res.Resources;

public interface InputMethodServiceProxy {
    void sendDownAndUpKeyEvent(int keycodeDel, int i);

    void switchToPreviousInputMethod();

    void sendText(String unicodeJavaString);

    Context getContext();
}
