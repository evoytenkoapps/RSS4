package ru.evoytenkoapps.rss;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import android.util.Log;
import android.view.View;
import android.widget.*;


public class SecondActivity extends AppCompatActivity implements AsyncResponse
{
    private RecyclerView mRecyclerView;
    private String LOG_TAG = "RSS_BD";

    RecyclerView.Adapter mAdapter;
    List<String> myDataset = new ArrayList<String>();
    final static String EXTRA_NAME = "lstData";

    //String path = "/storage/emulated/0/AppProjects/RSS/app/src/main/java/ru/evoytenkoapps/rss/MyDB";
    String path = "MyDB.db";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        if (extras != null)
        {
            HTTPDownloadTask httpDT = new HTTPDownloadTask();
            httpDT.delegate = SecondActivity.this;
            // Берем ссылку на переданный RSS канал
            String str = extras.getString(EXTRA_NAME);
            // Запускаем загрузку RSS и сохраняем все в базу
            httpDT.execute(str);
        }

        setContentView(R.layout.activity_second);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view2);
        // если мы уверены, что изменения в контенте не изменят размер layout-а RecyclerView
        // передаем параметр true - это увеличивает производительность
        mRecyclerView.setHasFixedSize(true);
        // используем linear layout manager
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(LayoutManager);
    }

    // processFinish Вызывается после
    @Override
    public void processFinish(List<String> output)
    {
        // Убераем Progress Bar
        // TODO: Implement this method
        ProgressBar pg = (ProgressBar) findViewById(R.id.progr_bar);
        pg.setVisibility(View.INVISIBLE);

        // Берем данные из БД
        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        DBStore dbHelper = new DBStore(MainActivity.sMainContext, path);
        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst())
        {
            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int titleColIndex = c.getColumnIndex("title");
            int descriptionColIndex = c.getColumnIndex("description");
            int linkColIndex = c.getColumnIndex("link");
            do {
                // получаем значения по номерам столбцов и пишем все в лог
                myDataset.add(c.getString(titleColIndex));
//                Log.d(LOG_TAG,
//                        "ID = " + c.getInt(idColIndex) +
//                                ", title = " + c.getString(titleColIndex) +
//                                ", description = " + c.getString(descriptionColIndex) +
//                                ", link = " + c.getString(linkColIndex));
//                
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }
        else
        {
            Log.d(LOG_TAG, "0 rows");
        }

        db.close();
        // создаем адаптер
        mAdapter = new RecyclerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

    }
}
