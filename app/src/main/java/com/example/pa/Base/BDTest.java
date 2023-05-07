package com.example.pa.Base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pa.Paciente.InfoTest;

import java.util.ArrayList;
import java.util.List;

public class BDTest extends BDService{

    Context context;

    public BDTest(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long saveTest(InfoTest infoTest){
        long id = 0;
        try{
            BDService bdService = new BDService(context);
            SQLiteDatabase sqLiteDatabase = bdService.getWritableDatabase();

            ContentValues values= new ContentValues();
            id = sqLiteDatabase.insert(TABLE_TEST,null, UserContract.TestEntry.toContentValues(infoTest));
        }
        catch (Exception ex){
            ex.toString();
        }
        finally{
            return id;
        }
    }
    public List<InfoTest> getTest(int id)
    {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        List<InfoTest> test = null;
        InfoTest infoTest = null;
        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT*FROM " + TABLE_TEST +" WHERE id = "+ id,null);
        if( cursor == null )
        {
            return new ArrayList<InfoTest>();
        }
        if( cursor.getCount() < 1)
        {
            return new ArrayList<InfoTest>();
        }
        if( !cursor.moveToFirst() )
        {
            return new ArrayList<InfoTest>();
        }
        Log.d(TAG, "" + cursor.getCount());
        test = new ArrayList<InfoTest>( );
        for( int i = 0; i < cursor.getCount(); i++)
        {
            infoTest = new InfoTest();
            infoTest.setId_test(cursor.getInt(0));
            infoTest.setPregunta1(cursor.getString(1));
            infoTest.setPregunta2(cursor.getString(2));
            infoTest.setPregunta3(cursor.getString(3));
            infoTest.setPregunta4(cursor.getString(4));
            infoTest.setPregunta5(cursor.getString(5));
            infoTest.setId_user(cursor.getInt(6));
            test.add(infoTest);
            cursor.moveToNext();
        }
        Log.d("Test",test.toString());
        return test;

    }
}
