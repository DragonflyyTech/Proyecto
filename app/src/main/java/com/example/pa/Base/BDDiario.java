package com.example.pa.Base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pa.Paciente.InfoDiario;

import java.util.ArrayList;
import java.util.List;

public class BDDiario extends BDService{

    Context context;
    public BDDiario(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long saveDiario(InfoDiario infoDiario){
        long id = 0;
        try{
            BDService bdService = new BDService(context);
            SQLiteDatabase sqLiteDatabase = bdService.getWritableDatabase();

            ContentValues values= new ContentValues();
            id = sqLiteDatabase.insert(TABLE_DIARIO,null, UserContract.DiarioEntry.toContentValues(infoDiario));
        }
        catch (Exception ex){
            ex.toString();
        }
        finally{
            return id;
        }
    }
    public List<InfoDiario> getDiario(int id)
    {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        List<InfoDiario> diarios = null;
        InfoDiario infoDiario = null;
        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT*FROM " + TABLE_DIARIO +" WHERE id = "+ id,null);
        if( cursor == null )
        {
            return new ArrayList<InfoDiario>();
        }
        if( cursor.getCount() < 1)
        {
            return new ArrayList<InfoDiario>();
        }
        if( !cursor.moveToFirst() )
        {
            return new ArrayList<InfoDiario>();
        }
        Log.d(TAG, "" + cursor.getCount());
        diarios = new ArrayList<InfoDiario>( );
        for( int i = 0; i < cursor.getCount(); i++)
        {
            infoDiario = new InfoDiario( );
            infoDiario.setId_diario(cursor.getInt(0));
            infoDiario.setContenido(cursor.getString(1));
            infoDiario.setEmocion(cursor.getString(2));
            infoDiario.setTitulo(cursor.getString(3));
            infoDiario.setFecha(cursor.getString(4));
            infoDiario.setId_user(cursor.getInt(5));
            diarios.add(infoDiario);
            cursor.moveToNext( );
        }
        Log.d("Diarios",diarios.toString());
        return diarios;

    }
}
