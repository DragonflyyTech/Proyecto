package com.example.pa.Base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pa.Info2;

import java.util.ArrayList;
import java.util.List;

public class BDEspe extends BDService{

    Context context;

    public BDEspe(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long saveEspecialista(Info2 info2){
        long id = 0;
        try{
            BDService bdService = new BDService(context);
            SQLiteDatabase db = bdService.getWritableDatabase();

            ContentValues values= new ContentValues();
            id = db.insert(TABLE_ESPE, null, UserContract.EspeEntry.toContentValues(info2));

        }catch(Exception ex){
            ex.toString();
        }
        finally{
            return id;
        }
    }

    public List<Info2> getEspecialistas( )
    {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        List<Info2> especialistas = null;
        Info2 info2 = null;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT*FROM "+TABLE_ESPE,null);
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
        especialistas = new ArrayList<Info2>( );
        for( int i = 0; i < cursor.getCount(); i++)
        {
            info2 = new Info2( );
            info2.setUsuario(cursor.getString(0));
            info2.setContra(cursor.getString(1));
            info2.setNombre(cursor.getString( 2 ) );
            info2.setMail(cursor.getString(3));
            info2.setEdad(cursor.getString(4));
            especialistas.add( info2 );
            cursor.moveToNext( );
        }
        return especialistas;
    }
    public Info2 GetEspecialista(String user){
        Info2 info2 = new Info2();
        BDService bdService = new BDService(context);
        SQLiteDatabase db = bdService.getReadableDatabase();
        Cursor cursor=null;
        String query = "SELECT * FROM t_espe WHERE usuario = ?";
        String[] args = {user};

        cursor = db.rawQuery(query,args);
        if (cursor.moveToFirst()) {
            info2.setId_espe(cursor.getInt(0));
            info2.setUsuario(cursor.getString(1));
            info2.setContra(cursor.getString(2));
            info2.setNombre(cursor.getString( 3 ) );
            info2.setMail(cursor.getString(4));
            info2.setEdad(cursor.getString(5));
            return info2;
        }
        cursor.close();
        return null;
    }

    public Info2 GetEspecialista(String user,String email){
        Info2 info2 = new Info2();
        BDService bdService = new BDService(context);
        SQLiteDatabase db = bdService.getReadableDatabase();
        Cursor cursor = null;
        String query = "SELECT * FROM t_espe WHERE usuario = ? AND email = ?";
        String[] args = {user,email};

        cursor = db.rawQuery(query,args);
        if (cursor.moveToFirst()) {
            info2.setId_espe(cursor.getInt(0));
            info2.setUsuario(cursor.getString(1));
            info2.setContra(cursor.getString(2));
            info2.setNombre(cursor.getString( 3 ) );
            info2.setMail(cursor.getString(4));
            info2.setEdad(cursor.getString(5));
            return info2;
        }
        cursor.close();
        return null;
    }

    public boolean EditEspecialista(String user,String contra){
        boolean correcto = false;
        BDService bdService = new BDService(context);
        SQLiteDatabase db = bdService.getWritableDatabase();
        try{
            db.execSQL("UPDATE " + TABLE_ESPE + " SET contra = '" + contra + "' WHERE usuario ='" + user + "'");
            correcto = true;
        }catch(Exception ex){
            ex.toString();
        }
        return correcto;
    }
}
