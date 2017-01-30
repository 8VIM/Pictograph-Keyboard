package inc.flide.emoji_keyboard.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;

import inc.flide.emojiKeyboard.R;
import inc.flide.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.emoji_keyboard.adapter.EmojiPagerAdapter;
import inc.flide.emoji_keyboard.adapter.LennyFacePagerAdapter;
import inc.flide.emoji_keyboard.constants.Constants;
import inc.flide.emoji_keyboard.onclicklisteners.LongButtonPressRunnable;

public class KeyboardView extends View implements SharedPreferences.OnSharedPreferenceChangeListener{

    private LinearLayout keyboardLayout;

    private boolean isEmojiKeyboardVisible = true;
    private boolean isLennyFacesKeyboardVisible = false;

    private static final Handler longButtonPressHandler = new Handler();
    private InputMethodServiceProxy emojiKeyboardService;

    public KeyboardView(Context context) {
        super(context);
        initialize(context);
    }

    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public KeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {

        emojiKeyboardService = (InputMethodServiceProxy) context;

        LayoutInflater inflater = (LayoutInflater)   getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        keyboardLayout = (LinearLayout) inflater.inflate(R.layout.emoji_keyboard_view, null);

        ViewPager emojiViewPager = (ViewPager) keyboardLayout.findViewById(R.id.emojiKeyboardViewPager);
        PagerSlidingTabStrip emojiPagerSlidingTabStrip = (PagerSlidingTabStrip) keyboardLayout.findViewById(R.id.emojiKeyboardPagerSlidingTabStrip);
        emojiPagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.pager_color));
        EmojiPagerAdapter emojiPagerAdapter = new EmojiPagerAdapter(context, emojiViewPager, 0);
        emojiViewPager.setAdapter(emojiPagerAdapter);
        emojiPagerSlidingTabStrip.setViewPager(emojiViewPager);
        emojiViewPager.setCurrentItem(0);

        ViewPager lennyFaceViewPager = (ViewPager) keyboardLayout.findViewById(R.id.lennyFaceKeyboardViewPager);
        PagerSlidingTabStrip lennyFacePagerSlidingTabStrip = (PagerSlidingTabStrip) keyboardLayout.findViewById(R.id.lennyFaceKeyboardPagerSlidingTabStrip);
        lennyFacePagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.pager_color));
        LennyFacePagerAdapter lennyFacePagerAdapter = new LennyFacePagerAdapter(context, lennyFaceViewPager, 0);
        lennyFaceViewPager.setAdapter(lennyFacePagerAdapter);
        lennyFacePagerSlidingTabStrip.setViewPager(lennyFaceViewPager);
        lennyFaceViewPager.setCurrentItem(0);

        setupKeyboardButtons();
        PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(this);
    }

    public View getView() {
        return keyboardLayout;
    }

    private void setupKeyboardButtons() {

        LongButtonPressRunnable.setInputMethodService(emojiKeyboardService);
        LongButtonPressRunnable.setLongButtonPressHandler(longButtonPressHandler);

        setupDeleteButton();
        setupEnterButton();
        setupSpacebarButton();
        setupKeyboardButton();
        setupGoToSettingsButton();
        setupToogleLennyFacesAndEmojiKeyboard();
    }

    private void setupToogleLennyFacesAndEmojiKeyboard() {
        final ImageButton toogleLennyFacesAndEmojiKeyboardButton = (ImageButton) keyboardLayout.findViewById(R.id.toggleLennyFacesAndEmojiKeyboardButton);

        if(isEmojiKeyboardVisible) {
            toogleLennyFacesAndEmojiKeyboardButton.setImageResource(R.drawable.key_icon_switch_to_lenny_faces);
        } else {
            toogleLennyFacesAndEmojiKeyboardButton.setImageResource(R.drawable.key_icon_switch_to_emoji);
        }

        toogleLennyFacesAndEmojiKeyboardButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmojiKeyboardVisible) {
                    keyboardLayout.findViewById(R.id.emojiKeyboard).setVisibility(GONE);
                    keyboardLayout.findViewById(R.id.lennyFaceKeyboard).setVisibility(VISIBLE);
                    isEmojiKeyboardVisible=false;
                    isLennyFacesKeyboardVisible = true;
                    toogleLennyFacesAndEmojiKeyboardButton.setImageResource(R.drawable.key_icon_switch_to_emoji);
                } else {
                    keyboardLayout.findViewById(R.id.emojiKeyboard).setVisibility(VISIBLE);
                    keyboardLayout.findViewById(R.id.lennyFaceKeyboard).setVisibility(GONE);
                    isEmojiKeyboardVisible=true;
                    isLennyFacesKeyboardVisible = false;
                    toogleLennyFacesAndEmojiKeyboardButton.setImageResource(R.drawable.key_icon_switch_to_lenny_faces);
                }
            }
        });
    }


    private void setupGoToSettingsButton() {
        final ImageButton openSettingsButton = (ImageButton) keyboardLayout.findViewById(R.id.openSettingsButton);

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

        final ImageButton enterButon = (ImageButton) keyboardLayout.findViewById(R.id.enterButton);

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

    private void setupSpacebarButton() {

        final ImageButton spacebarButton = (ImageButton) keyboardLayout.findViewById(R.id.spaceBarButton);

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

        final ImageButton deleteButton = (ImageButton) keyboardLayout.findViewById(R.id.deleteButton);

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

        ImageButton keyboardButton = (ImageButton) keyboardLayout.findViewById(R.id.switchToKeyboardButton);

        keyboardButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                emojiKeyboardService.switchToPreviousInputMethod();
            }
        });
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("icon_set")){
            ViewPager emojiViewPager = (ViewPager) keyboardLayout.findViewById(R.id.emojiKeyboardViewPager);
            EmojiPagerAdapter emojiPagerAdapter = new EmojiPagerAdapter(getContext(), emojiViewPager, 0);
            emojiViewPager.setAdapter(emojiPagerAdapter);
            emojiViewPager.invalidate();
        }
    }

}
