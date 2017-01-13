package inc.flide.android.emoji_keyboard.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import inc.flide.android.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.android.emoji_keyboard.R;
import inc.flide.android.emoji_keyboard.sqlite.EmojiDataSource;
import inc.flide.android.emoji_keyboard.utilities.CategorizedEmojiList;
import inc.flide.android.emoji_keyboard.utilities.Emoji;

public abstract class BaseEmojiAdapter extends BaseAdapter {

    protected InputMethodServiceProxy emojiKeyboardService;
    protected List<Emoji> emojiList;
    private static String filePrefix;

    public BaseEmojiAdapter(InputMethodServiceProxy emojiKeyboardService ) {
        this.emojiKeyboardService = emojiKeyboardService;
    }

    @Override
    public int getCount() {
        if (emojiList == null) {
            return 0;
        }
        return emojiList.size();
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        final ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(emojiKeyboardService.getContext());
            int scale = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, emojiKeyboardService.getContext().getResources().getDisplayMetrics());
            imageView.setPadding(scale, (int)(scale*1.2), scale, (int)(scale * 1.2));
            imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) convertView;
            imageView.setLongClickable(false);
        }

        Drawable[] layers = new Drawable[2];
        layers[0] = emojiKeyboardService.getContext().getResources().getDrawable(getIconIdBasedOnPosition(position));
        layers[1] = emojiKeyboardService.getContext().getResources().getDrawable(R.drawable.ic_diversityindicator);

        imageView.setImageResource(getIconIdBasedOnPosition(position));
        imageView.setBackgroundResource(R.drawable.btn_background);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emoji emoji = emojiList.get(position);
                emojiKeyboardService.sendText(emoji.getUnicodeJavaString());
                EmojiDataSource.getInstance(emojiKeyboardService.getContext()).addEntry(emoji);
            }
        });

        if (doEmojiSupportDiversity(position)) {

            LayerDrawable layerDrawable = new LayerDrawable(layers);
            imageView.setImageDrawable(layerDrawable);
            imageView.setLongClickable(true);
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    setupDiversityEmojiPopup(imageView, position);
                    return true;
                }
            });
        }
        return imageView;
    }

    private void setupDiversityEmojiPopup(ImageView imageView, int position) {
        LayoutInflater layoutInflater = (LayoutInflater)emojiKeyboardService.getContext()
                                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View popupView = layoutInflater.inflate(R.layout.popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout popupViewLinearLayout = (LinearLayout) popupView.findViewById(R.id.popupWindowLinearLayout);
        List<ImageView> diversityEmojis = getDiversityEmojisImageViewList(position, popupWindow);
        for(ImageView view: diversityEmojis) {
            popupViewLinearLayout.addView(view);
        }

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAsDropDown(imageView,0,-imageView.getHeight()*2);
    }

    public boolean doEmojiSupportDiversity(int position) {
        return emojiList.get(position).isDiversityAvailable();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected List<ImageView> getDiversityEmojisImageViewList(final int position, final PopupWindow popupWindow) {

        List<Emoji> diversityEmojiList = CategorizedEmojiList.getInstance().getDiversityEmojisList(emojiList.get(position));

        List<ImageView> diversityEmojiImageViewList = new ArrayList<>();

        for (final Emoji emoji: diversityEmojiList) {
            ImageView imageView = new ImageView(emojiKeyboardService.getContext());
            int scale = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, emojiKeyboardService.getContext().getResources().getDisplayMetrics());
            imageView.setPadding(scale, (int)(scale*1.2), scale, (int)(scale * 1.2));
            imageView.setAdjustViewBounds(true);
            imageView.setImageResource(getIconIdBasedOnEmoji(emoji));
            imageView.setBackgroundResource(R.drawable.btn_background);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emojiKeyboardService.sendText(emoji.getUnicodeJavaString());
                    EmojiDataSource.getInstance(emojiKeyboardService.getContext()).addEntry(emoji);
                    popupWindow.dismiss();
                }
            });
            diversityEmojiImageViewList.add(imageView);
        }
        return diversityEmojiImageViewList;
    }

    protected int getIconIdBasedOnEmoji(Emoji emoji) {
        String resourceString = filePrefix + emoji.getUnicodeHexcode().replace('-','_');
        int resourceId;
        resourceId = getDrawableResourceId(resourceString);
        if (resourceId == 0) {
            resourceId = getDrawableResourceId("ic_not_available_sign");
        }

        return resourceId;
    }

    protected int getDrawableResourceId(String drawableName) {
        return emojiKeyboardService.getContext().getResources()
                .getIdentifier(drawableName, "drawable", emojiKeyboardService.getContext().getPackageName());
    }

    public int getIconIdBasedOnPosition(int position) {
        return getIconIdBasedOnEmoji(emojiList.get(position));
    }

    public static void setFilePrefix(String prefix) {
        filePrefix = prefix;
    }
}
