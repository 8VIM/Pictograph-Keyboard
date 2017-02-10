package inc.flide.emoji_keyboard.adapter.unicode_art;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import inc.flide.emojiKeyboard.R;
import inc.flide.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.emoji_keyboard.onclicklisteners.LennyFaceOnClickListner;

public class UnicodeArtStaggeredGridAdapter extends RecyclerView.Adapter<UnicodeArtStaggeredGridAdapter.unicodeArtViewHolder>{

    private static InputMethodServiceProxy emojiKeyboardService;

    private List<String> unicodeArtList;

    public UnicodeArtStaggeredGridAdapter(InputMethodServiceProxy emojiKeyboardService, List<String> unicodeArtList) {
        this.unicodeArtList = unicodeArtList;
        UnicodeArtStaggeredGridAdapter.emojiKeyboardService = emojiKeyboardService;
    }

    private static void setupTextView(final TextView textView, String unicodeArt) {
        textView.setText(unicodeArt);
        textView.setOnClickListener(new LennyFaceOnClickListner(unicodeArt, emojiKeyboardService));
        textView.setBackgroundResource(R.drawable.ascii_art_button_selector);
    }

    @Override
    public unicodeArtViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((Context)emojiKeyboardService).inflate(R.layout.ascii_art_card_view, parent, false);
        unicodeArtViewHolder unicodeArtViewHolder = new unicodeArtViewHolder(view);
        return unicodeArtViewHolder;
    }

    @Override
    public void onBindViewHolder(unicodeArtViewHolder holder, int position) {
        setupTextView(holder.textView, unicodeArtList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (unicodeArtList == null) {
            return 0;
        }
        return unicodeArtList.size();
    }

    public static class unicodeArtViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public unicodeArtViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
