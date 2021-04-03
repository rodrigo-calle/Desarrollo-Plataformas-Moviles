package com.upc.sqliteapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LibroBD extends SQLiteOpenHelper {

    public LibroBD(Context context){
        super(context, Constantes.NOMBRE_DB, null, Constantes.VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //se ejecuta un query para la creacion de tabla
        String query =
                        "CREATE TABLE "+Constantes.NOMBRE_TABLA+
                                "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                " titulo TEXT NOT NULL, "+
                                " autor TEXT NOT NULL, "+
                                " npaginas INTEGER NOT NULL);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
