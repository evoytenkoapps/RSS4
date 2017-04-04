package ru.evoytenkoapps.rss.data;

public class DBContract
{   
    public  static final class RSSData{
        // // Константы для БД
        // БД
        static final String DB_NAME = "RSSDB.db";
        static final int DB_VERSION = 1;

        // Таблица
        static final String DATA_TABLE = "Data";

        // Поля
        static final String DATA_ID = "_ID";
        static final String DATA_TITLE = "title";
        static final String DATA_DESCRIPTION = "description";
        static final String DATA_LINK = "link";
        
    }
}
