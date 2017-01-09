package inc.flide.android.emoji_keyboard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import inc.flide.android.emoji_keyboard.EmojiKeyboardService;
import inc.flide.android.emoji_keyboard.sqlite.EmojiDataSource;
import inc.flide.android.emoji_keyboard.sqlite.RecentEntry;

import java.util.ArrayList;

public class RecentEmojiAdapter extends BaseEmojiAdapter {

    public RecentEmojiAdapter(Context context) {
        super((EmojiKeyboardService) context);
        //this.emojiList = dataSource.getAllEntriesInDescendingOrderOfCount();
    }

    private EmojiDataSource dataSource;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        dataSource.close();
    }
}