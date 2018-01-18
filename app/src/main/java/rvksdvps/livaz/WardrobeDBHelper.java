package rvksdvps.livaz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Lunarantic on 23/01/16.
 */
public class WardrobeDBHelper extends SQLiteOpenHelper
{
    static String dbName = "WardrobeList.db";
    static String tableName = "WardrobeListTable";
    static String wardrobeName = "wardrobename";
    static String gender = "gender";
    static String column3Name = "username";

    public WardrobeDBHelper(Context context)
    {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String create_table_query = "create table WardrobeListTable(wardrobename text primary key, gender text, "+column3Name+" text);";//here create a query
        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int insertRecord(Wardrobe w) {
        SQLiteDatabase dataBase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(wardrobeName,w.wardrobeName);
        value.put(gender, w.gender);
        value.put(column3Name,w.uname);

        long id = dataBase.insert(tableName, null, value);

        if (id > 0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }



    public ArrayList<Wardrobe> getAllRecords(String uname)
    {
        String query="select * from "+tableName+" where "+column3Name+"='"+uname+"'";
        SQLiteDatabase database=this.getReadableDatabase();

        Cursor cursor=database.rawQuery(query, null);
        cursor.moveToFirst();

        ArrayList<Wardrobe> wardrobeList= new ArrayList<>();

        while (!cursor.isAfterLast())
        {
            String name= cursor.getString(0);
            String gender=cursor.getString(1);
            Wardrobe wardrobe = new Wardrobe(name,gender);
            wardrobeList.add(wardrobe);

            cursor.moveToNext();
        }
        cursor.close();

        return  wardrobeList;

    }

    //for selected gender record

    public ArrayList<Wardrobe> getAllSelectedRecords(String gen)
    {
        String query="select * from "+tableName+" where gender='"+gen+"'";
        SQLiteDatabase database=this.getReadableDatabase();

        Cursor cursor=database.rawQuery(query, null);
        cursor.moveToFirst();

        ArrayList<Wardrobe> wardrobeList= new ArrayList<>();

        while (!cursor.isAfterLast())
        {
            String name= cursor.getString(0);
            String gender=cursor.getString(1);
            Wardrobe wardrobe = new Wardrobe(name,gender);
            wardrobeList.add(wardrobe);

            cursor.moveToNext();
        }
        cursor.close();

        return  wardrobeList;

    }



    public void deleteRecord(Wardrobe w)
    {
        String query="delete from "+tableName+" where wardrobename='"+w.wardrobeName+"';";
        SQLiteDatabase database=this.getReadableDatabase();
        database.execSQL(query);
    }
}
