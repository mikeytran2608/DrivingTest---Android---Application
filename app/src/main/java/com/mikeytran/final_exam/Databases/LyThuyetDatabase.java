package com.mikeytran.final_exam.Databases;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class LyThuyetDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "CauHoi.sqlite";
    private static final int DATABASE_VERSION = 1;
    public LyThuyetDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
