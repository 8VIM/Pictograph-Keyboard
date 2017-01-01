package inc.flide.android.emoji_keyboard;

import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import inc.flide.android.emoji_keyboard.constants.Emoji;
import inc.flide.android.emoji_keyboard.constants.EmojiJSONReader;

public class Utility {
    public static final ArrayList<Integer> initArrayList(int... ints) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : ints)
        {
            list.add(i);
        }
        return list;
    }

    public static List<Emoji> loadEmojiData(Resources resources, String packageName) {

        List<Emoji> emojiData = new ArrayList<>();
        InputStream inputStream = null;

        try {
            inputStream = resources.openRawResource(resources.getIdentifier("raw/emoji", "raw", packageName));
            EmojiJSONReader emojiJSONReader = new EmojiJSONReader(inputStream);
            emojiData = emojiJSONReader.loadEmojiData();
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
        } catch (IOException exception){
            exception.printStackTrace();
        } catch(Exception exception){
            exception.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return emojiData;
    }
}
