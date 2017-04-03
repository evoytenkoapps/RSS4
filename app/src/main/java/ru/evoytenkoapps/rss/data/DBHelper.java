package ru.evoytenkoapps.rss.data;
import android.content.*;
import android.database.sqlite.*;
import android.util.*;


// Класс который создает БД

public class DBHelper extends SQLiteOpenHelper
{
    private final String LOG_TAG ="RSS_BD";
    // Таблица
    static final String DATA_TABLE = "data";

    public DBHelper(Context context, String path)
    {
        super(context, path, null, 1);
        // test
        //test2
        //test3
    }



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table "+ DATA_TABLE +" ("
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

