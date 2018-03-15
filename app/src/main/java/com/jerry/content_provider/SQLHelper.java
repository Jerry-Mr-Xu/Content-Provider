package com.jerry.content_provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * <p>
 * Created by xujierui on 2018/3/12.
 */

public class SQLHelper extends SQLiteOpenHelper {
    private static final String TAG = SQLHelper.class.getSimpleName();

    private static final String DB_NAME = "book_provider.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME_BOOK = "book";

    private static final String CREATE_TABLE_BOOK = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_BOOK + " (id INTEGER PRIMARY KEY, name TEXT, price FLOAT)";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
