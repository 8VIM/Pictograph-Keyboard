package inc.flide.emoji_keyboard.adapter;

import android.content.Context;

import java.util.List;

import inc.flide.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.emoji_keyboard.constants.EmojiCategory;
import inc.flide.emoji_keyboard.utilities.Emoji;

public class StaticEmojiGridAdapter extends BaseEmojiGridAdapter {

    public StaticEmojiGridAdapter(Context context, List<Emoji> emojiList) {
        super((InputMethodServiceProxy) context);
        setEmojiList(emojiList);
    }
}