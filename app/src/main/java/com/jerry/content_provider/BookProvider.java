package com.jerry.content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 书本信息提供者
 * <p>
 * Created by xujierui on 2018/3/12.
 */

public class BookProvider extends ContentProvider {
    private static final String TAG = BookProvider.class.getSimpleName();

    private static final String AUTHORITY = "com.jerry.content_provider.authority";

    private static final int URI_CODE_BOOK = 0;

    private Context context;
    private SQLiteDatabase database;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "book", URI_CODE_BOOK);
    }

    @Override
    public boolean onCreate() {
        context = getContext();
        database = new SQLHelper(context).getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tableName = getTableName(uri);
        if (tableName == null || tableName.isEmpty()) {
            Log.e(TAG, "query: unsupported uri = " + uri);
        }

        return database.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String tableName = getTableName(uri);
        if (tableName == null || tableName.isEmpty()) {
            Log.e(TAG, "insert: unsupported uri = " + uri);
        }

        database.insert(tableName, null, values);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (tableName == null || tableName.isEmpty()) {
            Log.e(TAG, "delete: unsupported uri = " + uri);
        }

        return database.delete(tableName, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (tableName == null || tableName.isEmpty()) {
            Log.e(TAG, "update: unsupported uri = " + uri);
        }

        return database.update(tableName, values, selection, selectionArgs);
    }

    private String getTableName(Uri uri) {
        String tableName = null;

        switch (uriMatcher.match(uri)) {
            case URI_CODE_BOOK:
                tableName = SQLHelper.TABLE_NAME_BOOK;
                break;
            default:
                break;
        }

        return tableName;
    }
}
