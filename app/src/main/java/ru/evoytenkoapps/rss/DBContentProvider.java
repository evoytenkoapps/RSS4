package ru.evoytenkoapps.rss;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by evv on 31.03.2017.
 */

public class DBContentProvider extends ContentProvider {
    private final String LOG_TAG ="RSS_BD";

    // // Константы для БД
    // БД
    static final String DB_NAME = "MyDB.db";
    static final int DB_VERSION = 1;

    // Таблица
    static final String DATA_TABLE = "data";

    // Поля
    static final String DATA_ID = "id";
    static final String DATA_TITLE = "title";
    static final String DATA_DESCRIPTION = "description";
    static final String DATA_LINK = "link";

    // Скрипт создания таблицы
    static final String DB_CREATE =
            "create table "
            + DATA_TABLE + "("
            + DATA_ID + " integer primary key autoincrement, "
            + DATA_TITLE + " text, "
            + DATA_DESCRIPTION + " text,"
            + DATA_LINK + " text" + ");";

    // // Uri
    // authority
    static final String AUTHORITY = "ru.evoytenkoapps.rss";

    // DB_NAME
    static final String CONTACT_PATH = "contacts";

    // Общий Uri
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + CONTACT_PATH);

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


    @Override
    public boolean onCreate() {
        return false;
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
