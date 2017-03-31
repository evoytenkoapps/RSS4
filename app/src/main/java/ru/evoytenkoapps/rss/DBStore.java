package ru.evoytenkoapps.rss;
import android.content.*;
import android.database.sqlite.*;
import android.util.*;



class DBStore extends SQLiteOpenHelper
{
    private final String LOG_TAG ="RSS_BD"; 


    public DBStore(Context context, String path)
    {
        // конструктор суперкласса
        super(context, path, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table mytable ("
                   + "id integer primary key autoincrement," 
                   + "title text,"
                   + "description text,"
                   + "link text" + ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }


}

