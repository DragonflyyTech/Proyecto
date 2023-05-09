package com.example.pa.Base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDService extends SQLiteOpenHelper {

    public static final String TAG = "BDService";
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "REGISTROS.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_ESPE = "t_espe";
    public static final String TABLE_DIARIO = "t_diario";
    public static final String TABLE_TEST = "t_test";

    public BDService(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario TEXT UNIQUE NOT NULL," +
                "contra TEXT NOT NULL," +
                "nombre TEXT," +
                "email TEXT," +
                "edad TEXT,"+
                "cedula TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_ESPE +"(" +
                "id_espe INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario TEXT UNIQUE NOT NULL," +
                "contra TEXT NOT NULL," +
                "nombre TEXT," +
                "email TEXT," +
                "edad TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_DIARIO +"(" +
                "id_diario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "contenido TEXT ," +
                "emocion TEXT ," +
                "titulo TEXT," +
                "fecha TEXT," +
                "id INTEGER NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_TEST +"(" +
                "id_test INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pregunta1 TEXT ," +
                "pregunta2 TEXT ," +
                "pregunta3 TEXT," +
                "pregunta4 TEXT," +
                "pregunta5 TEXT," +
                "id INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ESPE);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_DIARIO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TEST);
        onCreate(sqLiteDatabase);

    }
}
