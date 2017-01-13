package inc.flide.android.emoji_keyboard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import inc.flide.android.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.android.emoji_keyboard.sqlite.EmojiDataSource;

public class RecentEmojiAdapter extends BaseEmojiAdapter {

    public RecentEmojiAdapter(Context context) {
        super((InputMethodServiceProxy) context);
        dataSource = EmojiDataSource.getInstance(context);
        dataSource.openInReadWriteMode();
        this.emojiList = dataSource.getAllEntriesInDescendingOrderOfCount();
    }

    private EmojiDataSource dataSource;

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        this.emojiList = dataSource.getAllEntriesInDescendingOrderOfCount();
        return super.getView(position, convertView, parent);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        dataSource.close();
    }
}