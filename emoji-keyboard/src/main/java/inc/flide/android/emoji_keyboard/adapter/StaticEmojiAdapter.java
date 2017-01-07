package inc.flide.android.emoji_keyboard.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import inc.flide.android.emoji_keyboard.R;
import inc.flide.android.emoji_keyboard.Utility;
import inc.flide.android.emoji_keyboard.constants.CategorizedEmojiList;
import inc.flide.android.emoji_keyboard.EmojiKeyboardService;
import inc.flide.android.emoji_keyboard.constants.Emoji;
import inc.flide.android.logging.Logger;

public class StaticEmojiAdapter extends BaseEmojiAdapter {

    public StaticEmojiAdapter(Context context, List<Emoji> emojiList) {
        super((EmojiKeyboardService) context);
        this.emojiList = emojiList;
    }

    @Override
    public int getIconIdBasedOnPosition(int position) {
        return getIconIdBasedOnEmoji(emojiList.get(position));
    }

    @Override
    public String getEmojiUnicodeString(int position) {
        return emojiList.get(position).getUnicodeJavaString();
    }
}