package com.example.studentadmin.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="student_db";
    public static final String  TABLE ="student";
    private static final String  cid="id";
    private static final String  cname="name";
    private static final String  clname="lname";
    private static final String  cmarks="marks";
    public DBHelper(Context context) {
        super(context,DB_NAME, null,1);
        SQLiteDatabase db= this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqldb) {
        sqldb.execSQL("CREATE TABLE "+TABLE+" (id integer PRIMARY KEY,name text,lname text,marks integer);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqldb, int i, int i1) {
        sqldb.execSQL("DROP TABLE IF EXISTS " +TABLE);
        onCreate(sqldb);
    }

    public boolean insertData(int id,String name,String lname,int marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(cid,id);
        cv.put(cname,name);
        cv.put(clname,lname);
        cv.put(cmarks,marks);
        db.insert(TABLE,null,cv);
        return true;
    }
    public Cursor showData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur =db.rawQuery("select * from "+TABLE,null);
        return cur;
    }
    public Integer deleteData(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int row = db.delete(TABLE,"id=?",new String[]{id.toString()});
        return row;
    }
    public boolean updateData(Integer id,String name,String lname,Integer marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(cid,id);
        cv.put(cname,name);
        cv.put(clname,lname);
        cv.put(cmarks,marks);
        db.update(TABLE,cv,"id=?",new String[]{id.toString()});
        return true;
    }
}
