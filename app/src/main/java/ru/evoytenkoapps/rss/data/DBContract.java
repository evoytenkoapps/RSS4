package ru.evoytenkoapps.rss.data;

public class DBContract

{
    // Класс базы
    public static final class DBRSS {
        // Константы для БД
        // БД
        static final String DB_NAME = "DBRSS.db";
        static final int DB_VERSION = 1;


        // Класс таблицы DATA в которой будут все данные
        public static final class DATA {

            // Название таблицы
            static final String NAME_TABLE = "DATA";

            // Поля
            static final String ID = "_ID";
            static final String TITLE = "TITLE";
            static final String DESCRIPTION = "DESCRIPTION";
            static final String LINK = "LINK";

            // Строка для создания таблицы
            static final String DB_CREATE =
                "create table "
                + DB_NAME + "("
                + NAME_TABLE + " integer primary key autoincrement,"
                + ID + " text,"
                + TITLE + " text,"
                + DESCRIPTION + " text,"
                + LINK + " text" + ");";

        }
    }

}

