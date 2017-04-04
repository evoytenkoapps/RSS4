package ru.evoytenkoapps.rss.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;


public class DBContentProvider extends ContentProvider {
    private final String LOG_TAG = "RSS_BD";

    // Uri
    static final String AUTHORITY = "ru.evoytenkoapps.providers.rss";

    // Path
    static final String PATH = DBContract.DBRSS.DATA.NAME_TABLE;

    // Общий Uri
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);

    // Общий MIME
    // Набор строк
    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + PATH;
    // Одна строка
    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + PATH;


    //// UriMatcher
    // общий Uri
    static final int CONSTANT1 = 1;
    // Uri с указанным ID
    static final int CONSTANT2 = 2;

    // описание и создание UriMatcher
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PATH, CONSTANT1);
        uriMatcher.addURI(AUTHORITY, PATH + "/#", CONSTANT2);
    }

    DBCreater dbCreater;
    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Log.d(LOG_TAG, "Create DB from Content Provider");
        dbCreater = new DBCreater(getContext(), DBContract.DBRSS.DB_NAME);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Log.d(LOG_TAG, "query, " + uri.toString());

        // проверяем Uri
        switch (uriMatcher.match(uri)) {

            // общий Uri
            case CONSTANT1:

                Log.d(LOG_TAG, "CONSTANT1");
                // если сортировка не указана, сортируем по ID начиная с последних
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = DBContract.DBRSS.DATA.ID + " DESC";
                }
                break;

            // Uri с ID
            case CONSTANT2:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "CONSTANT2, " + id);
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbCreater.getWritableDatabase();
        Cursor cursor = db.query(DBContract.DBRSS.DATA.NAME_TABLE, projection, selection,
                selectionArgs, null, null, sortOrder);
        // просим ContentResolver уведомлять этот курсор
        // об изменениях данных в CONTACT_CONTENT_URI
        cursor.setNotificationUri(getContext().getContentResolver(), CONTACT_CONTENT_URI);
        return cursor;
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
