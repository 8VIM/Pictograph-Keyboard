package inc.flide.android.emoji_keyboard.adapter;

import android.content.Context;

import java.util.List;

import inc.flide.android.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.android.emoji_keyboard.utilities.Emoji;

public class StaticEmojiAdapter extends BaseEmojiAdapter {

    public StaticEmojiAdapter(Context context, List<Emoji> emojiList) {
        super((InputMethodServiceProxy) context);
        this.emojiList = emojiList;
    }
}