package com.example.pa.Base;

import static com.example.pa.Base.BDService.TABLE_DIARIO;
import static com.example.pa.Base.BDService.TABLE_ESPE;
import static com.example.pa.Base.BDService.TABLE_TEST;
import static com.example.pa.Base.BDService.TABLE_USUARIOS;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.example.pa.Paciente.InfoTest;
import com.example.pa.Especialista.Info;
import com.example.pa.Paciente.Info2;
import com.example.pa.Paciente.InfoDiario;

import java.io.Serializable;

public class UserContract implements Serializable {

    public static final String TAG = "UserContract";

    public static abstract class UserEntry implements BaseColumns
    {
        public static final String USUARIO = "usuario";

        public static final String getCreateTable( )
        {
            String table = "CREATE TABLE "+ TABLE_USUARIOS + "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "usuario TEXT NOT NULL UNIQUE," +
                    "contra TEXT NOT NULL," +
                    "nombre TEXT," +
                    "email TEXT," +
                    "edad TEXT," +
                    "cedula TEXT," +
                    ")";
            return table;
        }

        public static ContentValues toContentValues(Info info)
        {
            ContentValues values = new ContentValues();
            values.put("usuario", info.getUsuario());
            values.put("contra", info.getContra());
            values.put("nombre", info.getNombre());
            values.put("email", info.getMail());
            values.put("edad", info.getEdad());
            values.put("cedula", info.getCedula());
            return values;
        }
    }
    public abstract static class EspeEntry implements BaseColumns{
        public static final String getCreateTable( )
        {
            String table ="CREATE TABLE "+ TABLE_ESPE +"(" +
                    "id_espe INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "usuario TEXT NOT NULL UNIQUE," +
                    "contra TEXT NOT NULL," +
                    "nombre TEXT," +
                    "email TEXT," +
                    "edad TEXT," +
                    ")";
            return table;
        }
        public static ContentValues toContentValues(Info2 info2)
        {
            ContentValues values = new ContentValues();
            values.put("usuario", info2.getUsuario());
            values.put("contra", info2.getContra());
            values.put("nombre", info2.getNombre());
            values.put("email", info2.getMail());
            values.put("edad", info2.getEdad());
            return values;
        }
    }
    public abstract static class DiarioEntry implements BaseColumns{
        public static final String getCreateTable( )
        {
            String table ="CREATE TABLE "+ TABLE_DIARIO +"(" +
                    "id_diario INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "contenido TEXT," +
                    "emocion TEXT," +
                    "titulo TEXT," +
                    "fecha TEXT," +
                    "id INTEGER NOT NULL," +
                    ")";
            return table;
        }
        public static ContentValues toContentValues(InfoDiario infoDiario)
        {
            ContentValues values = new ContentValues();
            values.put("contenido", infoDiario.getContenido());
            values.put("emocion", infoDiario.getEmocion());
            values.put("titulo", infoDiario.getTitulo());
            values.put("fecha", infoDiario.getFecha());
            values.put("id", infoDiario.getId_user());
            return values;
        }
    }

    public abstract static class TestEntry implements BaseColumns{
        public static final String getCreateTable( )
        {
            String table ="CREATE TABLE "+ TABLE_TEST +"(" +
                    "id_test INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "pregunta1 TEXT," +
                    "pregunta2 TEXT," +
                    "pregunta3 TEXT," +
                    "pregunta4 TEXT," +
                    "pregunta5 TEXT," +
                    "id INTEGER NOT NULL," +
                    ")";
            return table;
        }
        public static ContentValues toContentValues(InfoTest infoTest)
        {
            ContentValues values = new ContentValues();
            values.put("pregunta1", infoTest.getPregunta1());
            values.put("pregunta2", infoTest.getPregunta2());
            values.put("pregunta3", infoTest.getPregunta3());
            values.put("pregunta4", infoTest.getPregunta4());
            values.put("pregunta5", infoTest.getPregunta5());
            values.put("id", infoTest.getId_user());

            return values;
        }
    }
}
