package ru.evoytenkoapps.rss.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by evv on 31.03.2017.
 * Пытаемся мегрировать на gtihub
 * 2 попытка
 */

public class DBContentProvider extends ContentProvider {
    private final String LOG_TAG ="RSS_BD";

    // Скрипт создания таблицы
    static final String DB_CREATE =
            "create table "
            + DBContract.RSSDB.DB_NAME + "("
            + DBContract.RSSDB.TableData.TABLE_NAME + " integer primary key autoincrement, "
            + DBContract.RSSDB.TableData.ID + " text, "
            + DBContract.RSSDB.TableData.TITLE+ " text,"
            + DBContract.RSSDB.TableData.DESCRIPTION + " text,"
            + DBContract.RSSDB.TableData.LINK +" text" + ");";

    // // Uri
    // authority
    static final String AUTHORITY = "ru.evoytenkoapps.rss";

    // DB_NAME
    static final String CONTACT_PATH = "contacts";

    // Общий Uri
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTACT_PATH);

    // Типы данных
    // набор строк
    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + CONTACT_PATH;

    // одна строка
    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + CONTACT_PATH;

    //// UriMatcher
    // общий Uri
    static final int URI_CONTACTS = 1;

    // Uri с указанным ID
    static final int URI_CONTACTS_ID = 2;

DBHelper dbHelper;

    @Override
    public boolean onCreate() {
        Log.d(LOG_TAG, "Create DB from Content Provider");
        dbHelper = new DBHelper( getContext(), DBContract.RSSDB.DB_NAME);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
