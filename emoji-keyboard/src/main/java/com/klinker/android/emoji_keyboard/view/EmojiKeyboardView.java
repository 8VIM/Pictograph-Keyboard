package com.klinker.android.emoji_keyboard.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.klinker.android.emoji_keyboard.EmojiKeyboardService;
import com.klinker.android.emoji_keyboard.adapter.EmojiPagerAdapter;
import com.klinker.android.emoji_keyboard_trial.R;

public class EmojiKeyboardView extends View implements SharedPreferences.OnSharedPreferenceChangeListener{

    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private LinearLayout layout;

    private EmojiPagerAdapter emojiPagerAdapter;
    private EmojiKeyboardService emojiKeyboardService;

    public EmojiKeyboardView(Context context) {
        super(context);
        initialize(context);
    }

    public EmojiKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public EmojiKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {

        emojiKeyboardService = (EmojiKeyboardService) context;

        LayoutInflater inflater = (LayoutInflater)   getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        layout = (LinearLayout) inflater.inflate(R.layout.keyboard_main, null);

        viewPager = (ViewPager) layout.findViewById(R.id.emojiKeyboard);

        pagerSlidingTabStrip = (PagerSlidingTabStrip) layout.findViewById(R.id.emojiCategorytab);

        pagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.pager_color));

        emojiPagerAdapter = new EmojiPagerAdapter(context, viewPager, height);

        viewPager.setAdapter(emojiPagerAdapter);

        setupDeleteButton();

        pagerSlidingTabStrip.setViewPager(viewPager);

        viewPager.setCurrentItem(1);

        PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(this);
    }

    public View getView() {
        return layout;
    }

    public void notifyDataSetChanged() {
        emojiPagerAdapter.notifyDataSetChanged();
        viewPager.refreshDrawableState();
    }

    private void setupDeleteButton() {

        Button delete = (Button) layout.findViewById(R.id.deleteButton);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emojiKeyboardService.sendDownAndUpKeyEvent(KeyEvent.KEYCODE_DEL, 0);
            }
        });

        delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                emojiKeyboardService.switchToPreviousInputMethod();
                return false;
            }
        });
    }


    private int width;
    private int height;
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        width = View.MeasureSpec.getSize(widthMeasureSpec);
        height = View.MeasureSpec.getSize(heightMeasureSpec);

        Log.d("emojiKeyboardView", width +" : " + height);
        setMeasuredDimension(width, height);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Log.d("sharedPreferenceChange", "function called on change of shared preferences with key " + key);
        if (key.equals("icon_set")){
            emojiPagerAdapter = new EmojiPagerAdapter(getContext(), viewPager, height);
            viewPager.setAdapter(emojiPagerAdapter);
            this.invalidate();
        }
    }
}
