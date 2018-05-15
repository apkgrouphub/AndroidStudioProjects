package com.example.diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PACHU on 21-02-2017.
 */

public class DB_HELPER extends SQLiteOpenHelper
{
    //database
    public static final String database_name="diary_database.db";

    //registration table and columns

    public static final String registration_table="reg_table";
    public static final String reg_col_u_id="u_id";
    //public static final String reg_col_rpo_pic="pro_photo";
    public static final String reg_col_fullname="full_name";
    public static final String reg_col_dob="dob";
    public static final String reg_col_email="email";
    public static final String reg_col_user_name="user_name";
    public static final String reg_col_pass="password";
    public static final String reg_col_phone="phone_number";

    //diary table and columns

    public static final String diary_table="diary_table";

    public static final String diary_col_d_id="d_id";
    public static final String diary_col_u_id=reg_col_u_id;
    public static final String diary_col_date="d_date";
    public static final String diary_col_remark="remark";
    public static final String diary_col_entries="entries";

    //Event table and columns

    public static final String event_table="event_table";

    public static final String event_col_e_id="e_id";
    public static final String event_col_u_id=reg_col_u_id;
    public static final String event_col_eventname="event_name";
    public static final String event_col_location="location";
    public static final String event_col_date="e_date";
    public static final String event_col_time="e_time";

    //attachment table and columns

    public static final String attachment_table="attachment_table";

    public static final String attachment_col_a_id="a_id";
    public static final String attachment_col_d_id=diary_col_d_id;
    public static final String attachment_col_filename="file_name";
    public static final String attachment_col_filetype="file_type";

    public DB_HELPER(Context context)
    {
        super(context,database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+registration_table+"("+
                reg_col_u_id+" integer primary key autoincrement,"+
                reg_col_fullname+" varchar(20),"+
                reg_col_dob+" varchar(20),"+
                reg_col_email+" varchar(20),"+
                reg_col_user_name+" varchar(50),"+
                reg_col_pass+" varchar(20),"+
                reg_col_phone+" varchar(20))");

        db.execSQL("create table "+diary_table+"("+
                diary_col_d_id+" integer primary key autoincrement,"+
                diary_col_u_id+" integer,"+
                diary_col_date+" varchar(20),"+
                diary_col_remark+" text,"+
                diary_col_entries+" text)");

        db.execSQL("create table "+event_table+"("+
                event_col_e_id+" integer primary key autoincrement,"+
                event_col_u_id+" integer,"+
                event_col_eventname+" varchar(50),"+
                event_col_location+" varchar(50),"+
                event_col_date+" varchar(20),"+
                event_col_time+" varchar(10))");

        db.execSQL("create table "+attachment_table+"("+
                attachment_col_a_id+" integer primary key autoincrement,"+
                attachment_col_d_id+" integer,"+
                attachment_col_filename+" varchar(500),"+
                attachment_col_filetype+" varchar(500))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists "+registration_table);
        db.execSQL("drop table if exists "+diary_table);
        db.execSQL("drop table if exists "+event_table);
        db.execSQL("drop table if exists "+attachment_table);
        onCreate(db);
    }

    // for login and regisration

    public boolean insertRegistrationData(String full_name,String dob,String email,String user_name,String password,String phone_number)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(reg_col_fullname,full_name);
        cv.put(reg_col_dob,dob);
        cv.put(reg_col_email,email);
        cv.put(reg_col_user_name,user_name);
        cv.put(reg_col_pass,password);
        cv.put(reg_col_phone,phone_number);
        long result=db.insert(registration_table,null,cv);
        if (result ==-1)
            return  false;
        else
            return true;
    }

    public Cursor getAlldata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+registration_table ,null);
        return  res;
    }
    public Cursor getdata(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+diary_table +" where "+diary_col_d_id+"='"+id+"' and "+diary_col_u_id+"='"+LoginActivity.user_id+"'",null);
        return  res;
    }
    public Cursor logdata(String logname, String logpass)
    {
        Cursor c=getReadableDatabase().rawQuery("select * from " + registration_table + " where user_name='" + logname + "' and password='" + logpass + "'",null);
        return c;
    }

    public Cursor log(String uid,String logname, String logpass)
    {
        Cursor c=getReadableDatabase().rawQuery("select * from " + registration_table + " where u_id='" + uid + "' and user_name='" + logname + "' and password='" + logpass + "'",null);
        return c;
    }

    //for diary

    public boolean insertDiary_noteData(String user_id,String date,String remark,String content)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(diary_col_u_id,user_id);
        cv.put(diary_col_date,date);
        cv.put(diary_col_remark,remark);
        cv.put(diary_col_entries,content);
        long drresult=db.insert(diary_table,null,cv);
        if (drresult ==-1)
            return  false;
        else
            return true;
    }

    public Cursor getAll_Diary_data()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+diary_table,null);
        return  res;
    }
    public Cursor getdate(String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cu=db.rawQuery("select * from "+diary_table +" where "+diary_col_date+"='" + date + "'",null);
        return  cu;
    }
    public Cursor get_d_id(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cu=db.rawQuery("select * from "+diary_table +" where "+diary_col_d_id+"='" + id + "'",null);
        return  cu;
    }


    public boolean updatedairy(String user_id,String remark,String content) {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(diary_col_remark,remark);
        cv.put(diary_col_entries,content);
       long updated=db.update(diary_table,cv,diary_col_d_id + "=" + user_id,null);
        if (updated ==-1)
            return  false;
        else
            return true;
    }

    public  boolean insertfile(String di_id,String file_path,String file_type)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(attachment_col_d_id,di_id);
        cv.put(attachment_col_filename,file_path);
        cv.put(attachment_col_filetype,file_type);
        long atresult=db.insert(attachment_table,null,cv);
        if (atresult ==-1)
            return  false;
        else
            return true;
    }
    public Cursor getvideo(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cu=db.rawQuery("select * from " + attachment_table + " where " + attachment_col_d_id + "='"+ id +"' and "+attachment_col_filetype+ "='Video'",null);
        return  cu;
    }

    public Cursor getaudio(String id,String type)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cu=db.rawQuery("select * from " + attachment_table + " where " + attachment_col_d_id +"='"+ id +"' and "+attachment_col_filetype+ "='" + type + "'",null);
        return  cu;
    }

    public Cursor findfile(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cu=db.rawQuery("select * from " + attachment_table+ " where " + attachment_col_d_id + "='" + id + "'",null);
        return  cu;
    }
    public Cursor getfile(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cu=db.rawQuery("select * from " + attachment_table+ " where "+attachment_col_a_id+"='"+ id +"' and "+attachment_col_d_id+ "='" + User.c_diary_id + "'",null);
        return  cu;
    }
    public Cursor video(String d_id,String type)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor v=db.rawQuery("select * from " + attachment_table + " where " + attachment_col_d_id +"='"+ d_id +"' and "+attachment_col_filetype+ "='" + type + "'",null);
        return v;
    }

}
