package inc.flide.emoji_keyboard.adapter.emoji;

import android.content.Context;

import java.util.List;

import inc.flide.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.emoji_keyboard.adapter.emoji.BaseEmojiGridAdapter;
import inc.flide.emoji_keyboard.utilities.Emoji;

public class StaticEmojiGridAdapter extends BaseEmojiGridAdapter {

    public StaticEmojiGridAdapter(Context context, List<Emoji> emojiList) {
        super((InputMethodServiceProxy) context);
        setEmojiList(emojiList);
    }
}