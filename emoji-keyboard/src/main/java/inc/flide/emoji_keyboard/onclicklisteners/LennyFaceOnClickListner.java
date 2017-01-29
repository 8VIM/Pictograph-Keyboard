package inc.flide.emoji_keyboard.onclicklisteners;

import android.view.View;

import inc.flide.emoji_keyboard.InputMethodServiceProxy;
import inc.flide.emoji_keyboard.sqlite.EmojiDataSource;
import inc.flide.emoji_keyboard.utilities.CategorizedEmojiList;
import inc.flide.emoji_keyboard.utilities.Emoji;

public class LennyFaceOnClickListner implements View.OnClickListener {

    private final String lennyFace;
    private final InputMethodServiceProxy inputMethodService;

    public LennyFaceOnClickListner(String lennyFace, InputMethodServiceProxy inputMethodService) {
        this.lennyFace = lennyFace;
        this.inputMethodService = inputMethodService;
    }

    @Override
    public void onClick(View view) {
        inputMethodService.sendText(lennyFace);

/*        new Thread(new Runnable() {
            @Override
            public void run() {
                EmojiDataSource.getInstance(inputMethodService.getContext())
                        .addEntry( CategorizedEmojiList.getInstance()
                                .searchForEmojiIgnoreModifier(emoji.getUnicodeHexcode(), emoji.getCategory().toString())
                        );
            }
        }).start();*/
    }
}
