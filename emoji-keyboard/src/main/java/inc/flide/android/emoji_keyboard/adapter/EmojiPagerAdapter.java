package inc.flide.android.emoji_keyboard.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import inc.flide.android.emoji_keyboard.constants.CategorizedEmojiList;
import inc.flide.android.emoji_keyboard.MainSettings;
import inc.flide.android.emoji_keyboard.R;
import inc.flide.android.emoji_keyboard.Utility;
import inc.flide.android.emoji_keyboard.view.EmojiKeyboardView;

public class EmojiPagerAdapter extends PagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

    private final int ICONS[] = {
                                R.drawable.ic_emoji_category_people,
                                R.drawable.ic_emoji_category_nature,
                                R.drawable.ic_emoji_category_foods,
                                R.drawable.ic_emoji_category_activity,
                                R.drawable.ic_emoji_category_travel,
                                R.drawable.ic_emoji_category_objects,
                                R.drawable.ic_emoji_category_symbols,
                                R.drawable.ic_emoji_category_flags};

    private ViewPager pager;
    private ArrayList<View> pages;
    private int keyboardHeight;

    public EmojiPagerAdapter(Context context, ViewPager pager, int keyboardHeight) {
        super();

        CategorizedEmojiList categorizedEmojiList = CategorizedEmojiList.getInstance();
        categorizedEmojiList.initializeCategoziedEmojiList(Utility.loadEmojiData(context.getResources(), context.getPackageName()));

        this.pager = pager;
        this.keyboardHeight = keyboardHeight;
        this.pages = new ArrayList<View>();

        StaticEmojiAdapter.setFilePrefix(getPreferedIconSet());
        pages.add(new EmojiKeyboardView.KeyboardSinglePageView(context, new StaticEmojiAdapter(context, categorizedEmojiList.getPeople())).getView());
        pages.add(new EmojiKeyboardView.KeyboardSinglePageView(context, new StaticEmojiAdapter(context, categorizedEmojiList.getNature())).getView());
        pages.add(new EmojiKeyboardView.KeyboardSinglePageView(context, new StaticEmojiAdapter(context, categorizedEmojiList.getFood())).getView());
        pages.add(new EmojiKeyboardView.KeyboardSinglePageView(context, new StaticEmojiAdapter(context, categorizedEmojiList.getActivity())).getView());
        pages.add(new EmojiKeyboardView.KeyboardSinglePageView(context, new StaticEmojiAdapter(context, categorizedEmojiList.getTravel())).getView());
        pages.add(new EmojiKeyboardView.KeyboardSinglePageView(context, new StaticEmojiAdapter(context, categorizedEmojiList.getObjects())).getView());
        pages.add(new EmojiKeyboardView.KeyboardSinglePageView(context, new StaticEmojiAdapter(context, categorizedEmojiList.getSymbols())).getView());
        pages.add(new EmojiKeyboardView.KeyboardSinglePageView(context, new StaticEmojiAdapter(context, categorizedEmojiList.getFlags())).getView());


    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        pager.addView(pages.get(position), position, keyboardHeight);
        return pages.get(position);
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object) {
        pager.removeView(pages.get(position));
    }

    @Override
    public int getCount() {
        return ICONS.length;
    }

    @Override
    public int getPageIconResId(int position) {
        return ICONS[position];
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private String getPreferedIconSet() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(pager.getContext());
        return sharedPreferences.getString(MainSettings.CHANGE_ICON_SET_KEY, MainSettings.CHANGE_ICON_SET_VALUE_DEFAULT);
    }
}
