package ru.evoytenkoapps.rss.data;
import android.content.*;
import android.database.sqlite.*;
import android.util.*;


// Класс который создает БД и тиблицу
public class DBCreater extends SQLiteOpenHelper
{
    private final String LOG_TAG ="RSS_BD";

    public DBCreater(Context context, String path)
    {
        super(context, path, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // Cздаем таблицу
        db.execSQL(DBContract.DBRSS.DATA.TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }


}

