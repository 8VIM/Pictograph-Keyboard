package inc.flide.emoji_keyboard.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;

import inc.flide.emojiKeyboard.R;
import inc.flide.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.emoji_keyboard.adapter.EmojiPagerAdapter;
import inc.flide.emoji_keyboard.constants.Constants;
import inc.flide.emoji_keyboard.onclicklisteners.LongButtonPressRunnable;

public class EmojiKeyboardView extends View implements SharedPreferences.OnSharedPreferenceChangeListener{

    private ViewPager viewPager;
    private LinearLayout layout;

    private static final Handler longButtonPressHandler = new Handler();
    private InputMethodServiceProxy emojiKeyboardService;

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

        emojiKeyboardService = (InputMethodServiceProxy) context;

        LayoutInflater inflater = (LayoutInflater)   getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        layout = (LinearLayout) inflater.inflate(R.layout.emoji_keyboard_view, null);

        viewPager = (ViewPager) layout.findViewById(R.id.emojiKeyboard);

        PagerSlidingTabStrip pagerSlidingTabStrip = (PagerSlidingTabStrip) layout.findViewById(R.id.emojiCategorytab);

        pagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.pager_color));

        EmojiPagerAdapter emojiPagerAdapter = new EmojiPagerAdapter(context, viewPager, height);

        viewPager.setAdapter(emojiPagerAdapter);

        setupKeyboardButtons();

        pagerSlidingTabStrip.setViewPager(viewPager);

        viewPager.setCurrentItem(0);

        PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(this);
    }

    private void setupKeyboardButtons() {

        LongButtonPressRunnable.setInputMethodService(emojiKeyboardService);
        LongButtonPressRunnable.setLongButtonPressHandler(longButtonPressHandler);

        setupDeleteButton();
        setupEnterButton();
        setupSpacebarButton();
        setupKeyboardButton();
        setupGoToSettingsButton();
    }

    private void setupGoToSettingsButton() {
        final ImageButton openSettingsButton = (ImageButton) layout.findViewById(R.id.openSettings);

        openSettingsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emojiKeyboardSettingsIntent = new Intent(getContext(),inc.flide.settings.SharedPreferencesSetting.class);
                emojiKeyboardSettingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(emojiKeyboardSettingsIntent);
            }
        });
    }

    private void setupEnterButton() {

        final ImageButton enterButon = (ImageButton) layout.findViewById(R.id.enterKey);

        enterButon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                emojiKeyboardService.sendDownAndUpKeyEvent(KeyEvent.KEYCODE_ENTER, 0);
            }
        });

        final Runnable longEnterButtonPressRunnable = new LongButtonPressRunnable(KeyEvent.KEYCODE_ENTER, enterButon);
        enterButon.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longButtonPressHandler.postDelayed(longEnterButtonPressRunnable, Constants.DELAY_MILLIS_LONG_PRESS_CONTINUATION);
                return true;
            }
        });
    }

    public View getView() {
        return layout;
    }

    private void setupSpacebarButton() {

        final ImageButton spacebarButton = (ImageButton) layout.findViewById(R.id.spaceBar);

        spacebarButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                emojiKeyboardService.sendDownAndUpKeyEvent(KeyEvent.KEYCODE_SPACE, 0);
            }
        });

        final Runnable longSpacebarButtonPressRunnable = new LongButtonPressRunnable(KeyEvent.KEYCODE_SPACE, spacebarButton);
        spacebarButton.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longButtonPressHandler.postDelayed(longSpacebarButtonPressRunnable, Constants.DELAY_MILLIS_LONG_PRESS_CONTINUATION);
                return true;
            }
        });
    }
    private void setupDeleteButton() {

        final ImageButton deleteButton = (ImageButton) layout.findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                emojiKeyboardService.sendDownAndUpKeyEvent(KeyEvent.KEYCODE_DEL, 0);
            }
        });

        final LongButtonPressRunnable longDeleteButtonPressRunnable = new LongButtonPressRunnable(KeyEvent.KEYCODE_DEL, deleteButton);
        deleteButton.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longButtonPressHandler.postDelayed(longDeleteButtonPressRunnable, Constants.DELAY_MILLIS_LONG_PRESS_CONTINUATION);
                return true;
            }
        });
    }


    private void setupKeyboardButton() {

        ImageButton keyboardButton = (ImageButton) layout.findViewById(R.id.switchToKeyboard);

        keyboardButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                emojiKeyboardService.switchToPreviousInputMethod();
            }
        });
    }

    private int height;
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("icon_set")){
            EmojiPagerAdapter emojiPagerAdapter = new EmojiPagerAdapter(getContext(), viewPager, height);
            viewPager.setAdapter(emojiPagerAdapter);
            viewPager.invalidate();
        }
    }

    public static class KeyboardSinglePageView {

        private final Context context;
        private final BaseAdapter adapter;

        public KeyboardSinglePageView(Context context, BaseAdapter adapter) {
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
}
