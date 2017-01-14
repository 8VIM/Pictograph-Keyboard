package inc.flide.emoji_keyboard.sqlite;

import inc.flide.emoji_keyboard.utilities.Emoji;

class RecentEntry {

    private final long id;
    private final Emoji emoji;
    private long count;

    public void incrementUsageCountByOne() {
        this.count = this.count+1;
    }

    public RecentEntry(Emoji emoji, long count, long id) {
        this.count = count;
        this.id = id;
        this.emoji = emoji;
    }

    public long getId() {
        return id;
    }

    public long getCount() {
        return count;
    }

    public Emoji getEmoji() {
        return emoji;
    }

}
