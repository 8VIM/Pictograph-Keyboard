package inc.flide.emoji_keyboard.adapter;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import inc.flide.emojiKeyboard.R;
import inc.flide.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.emoji_keyboard.onclicklisteners.LennyFaceOnClickListner;

public class BaseLennyFaceAdapter extends BaseAdapter {

    private static InputMethodServiceProxy emojiKeyboardService;

    private List<String> lennyFaceList;

    public BaseLennyFaceAdapter(InputMethodServiceProxy emojiKeyboardService, List<String> lennyFaceList) {
        this.lennyFaceList = lennyFaceList;
        BaseLennyFaceAdapter.emojiKeyboardService = emojiKeyboardService;
    }

    @Override
    public int getCount() {
        if (lennyFaceList == null) {
            return 0;
        }
        return lennyFaceList.size();
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        final TextView textView = setupTextView(convertView, lennyFaceList.get(position));

        textView.setOnClickListener(new LennyFaceOnClickListner(lennyFaceList.get(position), emojiKeyboardService));

        return textView;
    }

    private static TextView setupTextView(final View convertView, String lennyFace) {
        final TextView textView;
        if (convertView == null) {
            textView = new TextView(emojiKeyboardService.getContext());
            int scale = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, emojiKeyboardService.getContext().getResources().getDisplayMetrics());
            textView.setPadding(scale, (int)(scale*1.2), scale, (int)(scale * 1.2));
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(lennyFace);
        textView.setBackgroundResource(R.drawable.btn_background);

        return textView;
    }

    @Override
    public Object getItem(int position) {
        return lennyFaceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
