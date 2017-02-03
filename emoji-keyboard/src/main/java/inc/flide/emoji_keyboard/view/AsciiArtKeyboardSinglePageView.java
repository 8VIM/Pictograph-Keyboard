package inc.flide.emoji_keyboard.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import inc.flide.emoji_keyboard.adapter.BaseAsciiArtAdapter;

import static android.support.v7.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS;

public class AsciiArtKeyboardSinglePageView {

    private final Context context;
    private final RecyclerView.Adapter<BaseAsciiArtAdapter.AsciiArtViewHolder> adapter;

    public AsciiArtKeyboardSinglePageView(Context context, RecyclerView.Adapter<BaseAsciiArtAdapter.AsciiArtViewHolder> adapter) {
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
