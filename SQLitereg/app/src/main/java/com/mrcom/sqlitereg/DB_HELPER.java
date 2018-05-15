package com.mrcom.sqlitereg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PACHU on 20-02-2017.
 */

public class DB_HELPER extends SQLiteOpenHelper
{
    //database
    public static final String database_name="registration.db";
    //table 1
    public static final String table_name="reg_table";
    public static final String col_1="id";
    public static final String col_2="name";
    public static final String col_3="password";
    public static final String col_4="age";

    public DB_HELPER(Context context)
    {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+table_name+"("+col_1+" integer primary key autoincrement,"+col_2+" varchar(20),"+col_3+" varchar(20),"+col_4+" integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists "+table_name);
        onCreate(db);
    }

    public boolean insertData(String name,String password,String age)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col_2,name);
        cv.put(col_3,password);
        cv.put(col_4,age);
        long result=db.insert(table_name,null,cv);
        if (result ==-1)
            return  false;
        else
            return true;
    }

    public Cursor log(String logname, String logpass)
    {
        Cursor c=getReadableDatabase().rawQuery("select * from " + table_name + " where name='" + logname + "' and Password='" + logpass + "'",null);
        return c;
    }

    public Cursor getAlldata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table_name,null);
        return  res;
    }

    public boolean deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
         db.delete(table_name, null, null);
        return true;
    }
}
