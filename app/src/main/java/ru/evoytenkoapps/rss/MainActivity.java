package ru.evoytenkoapps.rss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.content.*;


public class MainActivity extends AppCompatActivity
{
    TextView tv;
    private String finalUrl="https://news.yandex.ru/index.rss";
    private RecyclerView mRecyclerView;
    static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static List <String> myDataset;
static Context sMainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sMainContext =this;
        
        myDataset = new ArrayList<String>();
        
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // если мы уверены, что изменения в контенте не изменят размер layout-а RecyclerView
        // передаем параметр true - это увеличивает производительность
        mRecyclerView.setHasFixedSize(true);
        // используем linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        myDataset.add(finalUrl);

        // Вызываем адаптер MainRecyclerAdapter
        mAdapter = new MainRecyclerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }



}
