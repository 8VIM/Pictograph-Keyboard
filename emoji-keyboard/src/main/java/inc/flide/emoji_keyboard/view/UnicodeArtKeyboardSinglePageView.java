package inc.flide.emoji_keyboard.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import inc.flide.emoji_keyboard.adapter.unicode_art.UnicodeArtStaggeredGridAdapter;

import static android.support.v7.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS;

public class UnicodeArtKeyboardSinglePageView {

    private final Context context;
    private final RecyclerView.Adapter<UnicodeArtStaggeredGridAdapter.unicodeArtViewHolder> adapter;

    public UnicodeArtKeyboardSinglePageView(Context context, RecyclerView.Adapter<UnicodeArtStaggeredGridAdapter.unicodeArtViewHolder> adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    public View getView() {

        final RecyclerView recyclerView = new RecyclerView(context);
        final StaggeredGridLayoutManager recyclerViewLayoutManager= new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerViewLayoutManager.setAutoMeasureEnabled(true);
        recyclerViewLayoutManager.setGapStrategy(GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerView.setAdapter(adapter);
        return recyclerView;
    }
}
