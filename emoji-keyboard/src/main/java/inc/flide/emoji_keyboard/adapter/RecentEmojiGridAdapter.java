package inc.flide.emoji_keyboard.adapter;

import android.content.Context;

import inc.flide.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.emoji_keyboard.constants.EmojiCategory;
import inc.flide.emoji_keyboard.sqlite.EmojiDataSource;

public class RecentEmojiGridAdapter extends BaseEmojiGridAdapter {

    public RecentEmojiGridAdapter(Context context) {
        super((InputMethodServiceProxy) context);
        dataSource = EmojiDataSource.getInstance(context);
    }

    private final EmojiDataSource dataSource;

    @Override
    public int getCount() {
        setEmojiList(dataSource.getAllEntriesInDescendingOrderOfCount());
        return getEmojiList().size();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        dataSource.close();
    }
}