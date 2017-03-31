package ru.evoytenkoapps.rss;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.*;

public class ThirdActivity extends AppCompatActivity
{
    private String LOG_TAG = "RSS_BD";
    final static String EXTRA_NAME = "Description";

    //String path = "/storage/emulated/0/AppProjects/RSS/app/src/main/java/ru/evoytenkoapps/rss/MyDB";
    String path = "MyDB.db";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        String str;
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            // Берем текст переданного description
            str = extras.getString(EXTRA_NAME);
            TextView tv = (TextView) findViewById(R.id.tv);
            tv.setText(str);
        }   
    }
}
