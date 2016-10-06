package com.example.latoris.fragment.Words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class WordsContent {
    public static final List<WordItem> ITEMS = new ArrayList<>();
    public static final Map<String, WordItem> ITEM_MAP = new HashMap<String, WordItem>();

    static {
        addItem(new WordItem("1", "Apple", "Apple"));
        addItem(new WordItem ("2", "Orange", " Orange "));
        addItem(new WordItem ("3", "Banana", " Banana "));
        addItem(new WordItem ("4", "Lemon", " Lemon "));
    }


    private static void addItem(WordItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);

    }
    /**
     * A dummy item representing a piece of content.
     */
    public static class WordItem {
        public final String id;
        public final String content;
        public final String details;

        public WordItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
