package inc.flide.emoji_keyboard.onclicklisteners;

import android.view.View;
import android.widget.PopupWindow;

import inc.flide.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.emoji_keyboard.onclicklisteners.EmojiOnClickListner;
import inc.flide.emoji_keyboard.utilities.Emoji;

public class PopupWindowEmojiOnClickListner extends EmojiOnClickListner {

    private final PopupWindow popupWindow;
    public PopupWindowEmojiOnClickListner(Emoji emoji, InputMethodServiceProxy inputMethodService, final PopupWindow popupWindow) {
        super(emoji, inputMethodService);
        this.popupWindow = popupWindow;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        popupWindow.dismiss();
    }

}
