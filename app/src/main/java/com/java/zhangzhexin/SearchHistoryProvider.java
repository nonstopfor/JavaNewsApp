package com.java.zhangzhexin;

import android.content.SearchRecentSuggestionsProvider;

public class SearchHistoryProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.java.zhangzhexin.SearchHistoryProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SearchHistoryProvider(){
        setupSuggestions(AUTHORITY, MODE);
    }
}
