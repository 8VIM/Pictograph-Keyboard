package inc.flide.emoji_keyboard.view;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class LennyFaceKeyboardSinglePageView {

    private final Context context;
    private final BaseAdapter adapter;

    public LennyFaceKeyboardSinglePageView(Context context, BaseAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    public View getView() {

        final GridView lennyFaceGrid = new GridView(context);
        lennyFaceGrid.setNumColumns(GridView.AUTO_FIT);
        lennyFaceGrid.setAdapter(adapter);

        return lennyFaceGrid;
    }
}
