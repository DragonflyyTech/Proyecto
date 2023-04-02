package com.example.pa.Base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pa.Paciente.Info;

import java.util.ArrayList;
import java.util.List;

public class BDUsers extends BDService{

    Context context;
    public BDUsers(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long saveUser(Info info){
        long id = 0;
        try{
            BDService bdService = new BDService(context);
            SQLiteDatabase db = bdService.getWritableDatabase();

            ContentValues values= new ContentValues();
            id = db.insert(TABLE_USUARIOS, null, UserContract.UserEntry.toContentValues(info));

        }catch(Exception ex){
            ex.toString();
        }
        finally{
            return id;
        }
    }

    public List<Info> getUsuarios( )
    {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        List<Info> usuarios = null;
        Info info = null;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT*FROM "+TABLE_USUARIOS,null);
        if( cursor == null )
        {
            return null;
        }
        if( cursor.getCount() < 1)
        {
            return null;
        }
        if( !cursor.moveToFirst() )
        {
            return null;
        }
        Log.d(TAG, "" + cursor.getCount());
        usuarios = new ArrayList<Info>( );
        for( int i = 0; i < cursor.getCount(); i++)
        {
            info = new Info( );
            info.setUsuario(cursor.getString(0));
            info.setContra(cursor.getString(1));
            info.setNombre(cursor.getString( 2 ) );
            info.setMail(cursor.getString(3));
            info.setEdad(cursor.getString(4));
            usuarios.add( info );
            cursor.moveToNext( );
        }
        return usuarios;
    }
    public Info GetUsuario(String user){
        Info info = new Info();
        BDService bdService = new BDService(context);
        SQLiteDatabase db = bdService.getReadableDatabase();
        Cursor cursor=null;
        String query = "SELECT * FROM t_usuarios WHERE usuario = ?";
        String[] args = {user};

        cursor = db.rawQuery(query,args);
        if (cursor.moveToFirst()) {
            info.setId_user(cursor.getInt(0));
            info.setUsuario(cursor.getString(1));
            info.setContra(cursor.getString(2));
            info.setNombre(cursor.getString( 3 ) );
            info.setMail(cursor.getString(4));
            info.setEdad(cursor.getString(5));
            return info;
        }
        cursor.close();
        return null;
    }

    public Info GetUsuario(String user,String email){
        Info info = new Info();
        BDService bdService = new BDService(context);
        SQLiteDatabase db = bdService.getReadableDatabase();
        Cursor cursor = null;
        String query = "SELECT * FROM t_usuarios WHERE usuario = ? AND email = ?";
        String[] args = {user,email};

        cursor = db.rawQuery(query,args);
        if (cursor.moveToFirst()) {
            info.setId_user(cursor.getInt(0));
            info.setUsuario(cursor.getString(1));
            info.setContra(cursor.getString(2));
            info.setNombre(cursor.getString( 3 ) );
            info.setMail(cursor.getString(4));
            info.setEdad(cursor.getString(5));
            return info;
        }
        cursor.close();
        return null;
    }

    public boolean EditUser(String user,String contra){
        boolean correcto = false;
        BDService bdService = new BDService(context);
        SQLiteDatabase db = bdService.getWritableDatabase();
        try{
            db.execSQL("UPDATE " + TABLE_USUARIOS + " SET contra = '" + contra + "' WHERE usuario ='" + user + "'");
            correcto = true;
        }catch(Exception ex){
            ex.toString();
        }
        return correcto;
    }
}
