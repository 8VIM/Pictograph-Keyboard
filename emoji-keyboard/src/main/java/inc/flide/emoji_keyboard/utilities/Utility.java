package inc.flide.emoji_keyboard.utilities;

import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static String convertStringToUnicode(String unicode) {

        String[] unicodeParts = unicode.split("-");
        int[] codePoints = new int[unicodeParts.length];

        for (int i = 0 ; i<unicodeParts.length; i++){
            codePoints[i] = Integer.valueOf(unicodeParts[i], 16);
        }

        int offset = 0;
        int count = unicodeParts.length;

        return new String(codePoints, offset, count);
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
        } catch(Exception exception){
            exception.printStackTrace();
        }
        finally {
            try {
                if(inputStream != null) {
                    inputStream.close();
                }
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }

        return emojiData;
    }
}
