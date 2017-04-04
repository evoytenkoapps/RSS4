package ru.evoytenkoapps.rss.data;

public class DBContract

{
    // Класс базы
    public static final  class RSSDB{
        // Константы для БД
        // БД
        static final String DB_NAME = "RSSDB.db";
        static final int DB_VERSION = 1;

    // Класс таблицы
    public  static final class TableData{
        // Таблица
        static final String TABLE_NAME = "Data";

        // Поля
        static final String ID = "_ID";
        static final String TITLE = "title";
        static final String DESCRIPTION = "description";
        static final String LINK = "link";
        
    }
}

}

