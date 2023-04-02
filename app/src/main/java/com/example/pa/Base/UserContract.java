package com.example.pa.Base;

import static com.example.pa.Base.BDService.TABLE_DIARIO;
import static com.example.pa.Base.BDService.TABLE_ESPE;
import static com.example.pa.Base.BDService.TABLE_USUARIOS;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.example.pa.Paciente.Info;
import com.example.pa.Info2;
import com.example.pa.InfoDiario;

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
            values.put("id", infoDiario.getId_user());
            return values;
        }
    }
}
