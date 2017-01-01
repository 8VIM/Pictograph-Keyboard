package inc.flide.android.emoji_keyboard.adapter;

import android.content.Context;

import inc.flide.android.emoji_keyboard.EmojiKeyboardService;

import java.util.ArrayList;
import java.util.Arrays;

public class StaticEmojiAdapter extends BaseEmojiAdapter {

    public StaticEmojiAdapter(Context context, String[] emojiTextsAsStrings, ArrayList<Integer> iconIds) {
        super((EmojiKeyboardService) context);
        this.emojiTexts =  new ArrayList<String>(Arrays.asList(emojiTextsAsStrings));
        this.iconIds = iconIds;
    }
}