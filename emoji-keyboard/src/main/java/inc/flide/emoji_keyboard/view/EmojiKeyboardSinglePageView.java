package inc.flide.emoji_keyboard.view;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class EmojiKeyboardSinglePageView {

    private final Context context;
    private final BaseAdapter adapter;

    public EmojiKeyboardSinglePageView(Context context, BaseAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    public View getView() {

        final GridView emojiGrid = new GridView(context);

        emojiGrid.setColumnWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
        emojiGrid.setNumColumns(GridView.AUTO_FIT);

        emojiGrid.setAdapter(adapter);
        return emojiGrid;
    }
}
