package ru.evoytenkoapps.rss;


import android.content.*;
import android.database.sqlite.*;
import android.os.*;
import android.util.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.xmlpull.v1.*;

import ru.evoytenkoapps.rss.data.DBHelper;

public class HTTPDownloadTask extends AsyncTask<  String, Void, List>
{
    // Нужно для возвоащения результата
    public AsyncResponse delegate = null;

    List lst = new ArrayList<String>();
    //String path = "/storage/emulated/0/AppProjects/RSS/app/src/main/java/ru/evoytenkoapps/rss/MyDB";
    String DB_NAME = "MyDB.db";

    private String LOG_TAG ="RSS_BD";

    @Override
    protected List doInBackground(String... params)
    {
        // TODO Auto-generated method stub
        String urlStr = params[0];
        InputStream is = null;

        lst.clear();
        try
        {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(10 * 1000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();
            int response = connection.getResponseCode();
            Log.d("debug", "The response is: " + response);
            is = connection.getInputStream();

            // Отработка XML
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            // включаем поддержку namespace (по умолчанию выключена)
            xmlFactoryObject.setNamespaceAware(true);
            // создаем парсер
            XmlPullParser myParser = xmlFactoryObject.newPullParser();
            myParser.setInput(is, null);

            int event;
            String text="";
            event = myParser.getEventType();

            boolean ITEM_START = false;
            boolean ITEM_END = false;

            while (event != XmlPullParser.END_DOCUMENT)
            {
                String name=myParser.getName();

                switch (event)
                {

                    case XmlPullParser.START_TAG:
                        if (name.equals("item"))
                        {
                            ITEM_START = true;
                            ITEM_END = false;
                        }

                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:


                        if (name.equals("title"))
                        {
                            if (ITEM_START == true && ITEM_END != true)
                            {
                                lst.add(text);
                            }

                        }

                        else if (name.equals("link"))
                        {
                            lst.add(text);
                        }

                        else if (name.equals("description"))
                        {
                            lst.add(text);
                        }

                        else if (name.equals("item"))
                        {
                            ITEM_START = false;
                            ITEM_END = true;
                        }
                        break;
                }

                event = myParser.next();
            }

            //return result;
            return lst;
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List data)
    {
        super.onPostExecute(data);
        if (data  != null)
        {
            List<String> tmplst = new ArrayList<String>();
            tmplst.addAll(data);
            // создаем объект для создания и управления версиями БД
            DBHelper dbHelper = new DBHelper(MainActivity.sMainContext, DB_NAME);
            // создаем объект для данных
            ContentValues cv = new ContentValues();
//
//            // получаем данные из полей ввода
//            String name = etName.getText().toString();
            // String email = etEmail.getText().toString();


            // подключаемся к БД
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Log.d(LOG_TAG, "--- Insert in mytable: ---");
            // подготовим данные для вставки в виде пар: наименование столбца - значение
            for (int i =0; i < tmplst.size(); i = i + 3)
            {

                cv.put("title", tmplst.get(i));
                cv.put("link", tmplst.get(i + 1));
                cv.put("description", tmplst.get(i + 2));

                // вставляем запись и получаем ее ID
                db.insert("mytable", null, cv);
            }
            // Log.d(LOG_TAG, "row inserted, ID = " + rowID);

//                case R.id.btnClear:
//                    Log.d(LOG_TAG, "--- Clear mytable: ---");
//
//
//                    int clearCount = db.delete("mytable", null, null);
//                    Log.d(LOG_TAG, "deleted rows count = " + clearCount);
//                    break;

            // закрываем подключение к БД
            dbHelper.close();

            delegate.processFinish(tmplst);
        }
    }
}
