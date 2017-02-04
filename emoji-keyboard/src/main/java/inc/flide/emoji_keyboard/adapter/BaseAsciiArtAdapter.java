package inc.flide.emoji_keyboard.adapter;

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

public class BaseAsciiArtAdapter extends RecyclerView.Adapter<BaseAsciiArtAdapter.AsciiArtViewHolder>{

    private static InputMethodServiceProxy emojiKeyboardService;

    private List<String> asciiArtList;

    public BaseAsciiArtAdapter(InputMethodServiceProxy emojiKeyboardService, List<String> asciiArtList) {
        this.asciiArtList = asciiArtList;
        BaseAsciiArtAdapter.emojiKeyboardService = emojiKeyboardService;
    }

    private static void setupTextView(final TextView textView, String asciiArt) {
        textView.setText(asciiArt);
        textView.setOnClickListener(new LennyFaceOnClickListner(asciiArt, emojiKeyboardService));
        textView.setBackgroundResource(R.drawable.ascii_art_button_selector);
    }

    @Override
    public AsciiArtViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((Context)emojiKeyboardService).inflate(R.layout.ascii_art_card_view, parent, false);
        AsciiArtViewHolder asciiArtViewHolder = new AsciiArtViewHolder(view);
        return asciiArtViewHolder;
    }

    @Override
    public void onBindViewHolder(AsciiArtViewHolder holder, int position) {
        setupTextView(holder.textView, asciiArtList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (asciiArtList == null) {
            return 0;
        }
        return asciiArtList.size();
    }

    public static class AsciiArtViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public AsciiArtViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
