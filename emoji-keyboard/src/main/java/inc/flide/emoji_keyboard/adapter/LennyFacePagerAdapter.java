package inc.flide.emoji_keyboard.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import inc.flide.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.emoji_keyboard.utilities.Utility;
import inc.flide.emoji_keyboard.view.EmojiKeyboardSinglePageView;
import inc.flide.emoji_keyboard.view.LennyFaceKeyboardSinglePageView;

public class LennyFacePagerAdapter extends PagerAdapter {

    public final String[] TITLES = {"Joy",
                                    "Flip off",
                                    "IDK",
                                    "Lenny",
                                    "Glasses",
                                    "Dongers",
                                    "Dissapointed",
                                    "Raise Arms",
                                    "Anger",
                                    "Peace",
                                    "Thumbs Up",
                                    "Bow/Respect" };

    private final ViewPager pager;
    private final ArrayList<View> pages;
    private final int keyboardHeight;

    private final List<List<String>> lennyFacesCompleteList = new ArrayList<>();

    public LennyFacePagerAdapter(Context context, ViewPager pager, int keyboardHeight) {
        super();

        this.pager = pager;
        this.keyboardHeight = keyboardHeight;
        this.pages = new ArrayList<>();

        lennyFacesCompleteList.add(Utility.initArrayList("(つ°ヮ°)つ", "(≧▽≦)", "（＾ω＾）", "（＾ｖ＾）", "ヽ(＾Д＾)ﾉ", "( ﾟ▽ﾟ)/", "(☞ﾟヮﾟ)☞", "ヾ(＾∇＾)", "☜(ﾟヮﾟ☜)", "৵( °͜ °৵)", "⊂((・▽・))⊃", "ヽ(ヅ)ノ", "(´∇ﾉ｀*)ノ", "☜(⌒▽⌒)☞" ));
        lennyFacesCompleteList.add(Utility.initArrayList("凸ಠ益ಠ)凸", "t(=n=)", "凸(¬‿¬)", "( ︶︿︶)_╭∩╮", "凸(⊙▂⊙ )", "╭∩╮(-_-)╭∩╮", "凸( •̀_•́ )凸", "凸(｀0´)凸", "凸(⊙▂⊙ )", "凸( •̀ 3 •́ )凸", "t(- n -)t", "凸(-0-メ)", "໒( ಠ ڡ ಠ )७╭∩╮", "t( -_- t )" ));
        lennyFacesCompleteList.add(Utility.initArrayList("¯\\_(ツ)_/¯", "┐(‘～`;)┌", "(・・；)", "¯\\_(⊙_ʖ⊙)_/¯", "(♠_♦)", "(=ಠ ل͟ ಠ=)", "(●__●)", "(ﾟヘﾟ)", "乁| ･ิ ∧ ･ิ |ㄏ", "(。ヘ°)", "乁| ･ 〰 ･ |ㄏ", "(ﾟｰﾟ;", "¯\\_| ✖ 〜 ✖ |_/¯", "ヽ(゜～゜o)ノ" ));
        lennyFacesCompleteList.add(Utility.initArrayList("(☞ ͡° ͜ʖ ͡°)☞", "( ͡~ ͜ʖ ͡°)", "║ ಡ ͜ ʖ ಡ ║", "(⌐ ͡■ ͜ʖ ͡■)", "┌(° ͜ʖ͡°)┘", "ʕ ͡° ʖ̯ ͡°ʔ", "( ͝° ͜ʖ͡°)╭∩╮", "[̲̅$̲̅(̲̅ ͡° ͜ʖ ͡°̲̅)̲̅$̲̅]", "( ͡° ͜ʖ ͡°)=ε✄", "໒( • ͜ʖ • )७", "(ง ° ͜ ʖ °)ง", "(つ ͡° ͜ʖ ͡°)つ", "ʕ ͡° ͜ʖ ͡°ʔ", "( ͡° ͜ʖ ͡°)", "໒(˵͡° ͜ʖ °͡˵)७", "( ͡° ʖ̯ ͡°)" ));
        lennyFacesCompleteList.add(Utility.initArrayList("(⌐▨_▨)", "ヽ(⌐■_■)ノ♪♬", "(▼-▼*)", "ヾ(▼ﾍ▼；)", "(ｷ▼⊿▼)ﾉ", "ಠ_ರೃ", "┌(メ▼▼)┘", "щ(▼ﾛ▼щ)", "（■Д■*）", "(╭ರ_⊙)", "(⌐■益■)", "( •_•)>⌐■-■", "(⌐■_■)–︻╦╤─", "ヾ(●ε●)ノ" ));
        lennyFacesCompleteList.add(Utility.initArrayList("༼⁰o⁰；༽", "凸༼ຈل͜ຈ༽凸", "╭∩╮༼☯۝☯༽╭∩╮", "༼ つ ͡° ͜ʖ ͡° ༽つ", "ヽ༼ಠل͜ಠ༽ﾉ", "ヽ༼ ͠° ͟ل͜ ͠° ༽ﾉ", "╰༼ ❛ ʖ̫ ❛ ༽╯", "¯\\_༼ ಥ ‿ ಥ ༽_/¯", "ヽ༼ ♥ ل͜ ♥ ༽ﾉ", "༼⊙ʖ̯⊙༽", "༼つಠ益ಠ༽つ ─=≡ΣO))", "ヽ༼ ຈل͜ຈ༼ ▀̿̿Ĺ̯̿̿▀̿ ̿༽Ɵ͆ل͜Ɵ͆ ༽ﾉ" ));
        lennyFacesCompleteList.add(Utility.initArrayList("⊙︿⊙", "ಥ_ಥ", "●︿●", "ಠ╭╮ಠ", "(ㄒoㄒ)", "(╥_╥)", "(;﹏;)", "(∩︵∩)", "(T_T)", "(///_-)", "(-_-｡)", "໒( ಥ Ĺ̯ ಥ )७", "ლ(ಥ Д ಥ )ლ", "(￣ー￣)" ));
        lennyFacesCompleteList.add(Utility.initArrayList("(゜Q゜)ノ？", "〜(￣▽￣〜)", "╰[ ⁰﹏⁰ ]╯", "ヾ(ﾟｪﾟゞ)", "└( ՞ ~ ՞ )┘", "ヽ(✿ ͜ʖ✿)ﾉ", "╰(. •́ ͜ʖ •̀ .)╯", "╰| ՞ Ĺ̯ ՞ |╯", "(:ㄏ■ Д ■ :)ㄏ", "(」゜ロ゜)」", "╰[✖Ĺ̯ಠ]╯", "乁( • ω •乁)", "щ(゜ロ゜щ)", "\\(¬﹏¬)/" ));
        lennyFacesCompleteList.add(Utility.initArrayList("ಠ_ಠ", "(’益’)", "(#｀皿´)", "ლಠ益ಠ)ლ", "-`д´-", "(╬ಠ益ಠ)", "(ノ-_-)ノ~┻━┻", "☜(`o´)", "(ﾉ；；)ﾉ~┻━┻", "(ﾉಠ_ಠ)ﾉ", "ಠ▃ಠ", "(つ◉益◉)つ", "(¬▂¬)", "(ﾉಠдಠ)ﾉ︵┻━┻" ));
        lennyFacesCompleteList.add(Utility.initArrayList("(^_^)v", "(^_-)v", "(-_☆)V", "( v￣▽￣)", "y(^ｰ^)y", "ｖ(⌒ｏ⌒)ｖ", "v(￣ｰ￣)v", "♪v('∇'*)⌒☆", "✌('ω')", "y(￣ー￣)y", "( =￣+∇￣=)v", "（＾コ＾）V", "v(≧∇≦v)三(v≧∇≦)v" ));
        lennyFacesCompleteList.add(Utility.initArrayList("(＾＾)ｂ", "d(-_^)", "d(-_☆)", "(･ω･)b", "(*TｰT)b", "d(⌒ー⌒)", "ｄ(*￣o￣)", "d(￣◇￣)b", "d(>_･ )", "(o^-’)b", "(￣ε￣〃)ｂ", "d(ﾟｰﾟ@)", "b(~_^)d", "d(･∀･○)" ));
        lennyFacesCompleteList.add(Utility.initArrayList("m(＿ ＿)m", "r(￣_￣;)", "m(￢0￢)m", "へ(´д｀へ)", "o(_ _)o", "(シ_ _)シ", "(´д｀人)", "m(._.)m", "(人´∩｀)", "ヾ(_ _。）", "m(；∇；)m", "(-人-)", "(*_ _)人", "r(≧ω≦*)" ));



        for(List<String> list: lennyFacesCompleteList) {
            pages.add(
                    new LennyFaceKeyboardSinglePageView(
                            context , new BaseLennyFaceAdapter(
                                        (InputMethodServiceProxy) context, list))
                    .getView()
            );
        }
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
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
