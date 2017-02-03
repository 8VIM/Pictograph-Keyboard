package inc.flide.emoji_keyboard.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import inc.flide.emojiKeyboard.R;
import inc.flide.emoji_keyboard.constants.EmojiCategory;
import inc.flide.emoji_keyboard.utilities.CategorizedEmojiList;
import inc.flide.emoji_keyboard.view.EmojiKeyboardSinglePageView;
import inc.flide.settings.SharedPreferencesSetting;
import inc.flide.emoji_keyboard.utilities.Utility;

public class EmojiPagerAdapter extends PagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

    private final int ICONS[] = {
                                R.drawable.ic_emoji_category_recent,
                                R.drawable.ic_emoji_category_people,
                                R.drawable.ic_emoji_category_nature,
                                R.drawable.ic_emoji_category_foods,
                                R.drawable.ic_emoji_category_activity,
                                R.drawable.ic_emoji_category_travel,
                                R.drawable.ic_emoji_category_objects,
                                R.drawable.ic_emoji_category_symbols,
                                R.drawable.ic_emoji_category_flags};

    private final ViewPager pager;
    private final ArrayList<View> pages;
    private final int keyboardHeight;

    public EmojiPagerAdapter(Context context, ViewPager pager, int keyboardHeight) {
        super();

        CategorizedEmojiList categorizedEmojiList = CategorizedEmojiList.getInstance();
        categorizedEmojiList.initializeCategoziedEmojiList(Utility.loadEmojiData(context.getResources(), context.getPackageName()));

        this.pager = pager;
        this.keyboardHeight = keyboardHeight;
        this.pages = new ArrayList<>();

        BaseEmojiGridAdapter.setFilePrefix(getPreferedIconSet());
        pages.add(new EmojiKeyboardSinglePageView(context,
                new RecentEmojiGridAdapter(context)).getView());
        pages.add(new EmojiKeyboardSinglePageView(context,
                new StaticEmojiGridAdapter(context, categorizedEmojiList.getPeople())).getView());
        pages.add(new EmojiKeyboardSinglePageView(context,
                new StaticEmojiGridAdapter(context, categorizedEmojiList.getNature())).getView());
        pages.add(new EmojiKeyboardSinglePageView(context,
                new StaticEmojiGridAdapter(context, categorizedEmojiList.getFood())).getView());
        pages.add(new EmojiKeyboardSinglePageView(context,
                new StaticEmojiGridAdapter(context, categorizedEmojiList.getActivity())).getView());
        pages.add(new EmojiKeyboardSinglePageView(context,
                new StaticEmojiGridAdapter(context, categorizedEmojiList.getTravel())).getView());
        pages.add(new EmojiKeyboardSinglePageView(context,
                new StaticEmojiGridAdapter(context, categorizedEmojiList.getObjects())).getView());
        pages.add(new EmojiKeyboardSinglePageView(context,
                new StaticEmojiGridAdapter(context, categorizedEmojiList.getSymbols())).getView());
        pages.add(new EmojiKeyboardSinglePageView(context,
                new StaticEmojiGridAdapter(context, categorizedEmojiList.getFlags())).getView());


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
        return sharedPreferences.getString(SharedPreferencesSetting.CHANGE_ICON_SET_KEY, SharedPreferencesSetting.CHANGE_ICON_SET_VALUE_DEFAULT);
    }
}
