package rvksdvps.livaz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Lunarantic on 23/01/16.
 */
public class WardrobeItemDBHelper extends SQLiteOpenHelper
{
    String tableName;
    static String column0Name = "RowId";
    static String column1Name = "ClothType";
    static String column2Name = "ColorName";
    static String column3Name = "ColorNum";
    static String column4Name = "Image";
    static String column5Name = "TopOrBottom";

    public WardrobeItemDBHelper(Context context, String dbName, String tableName)
    {
        super(context, dbName, null, 1);
        this.tableName = tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String create_table_query = "CREATE TABLE " + tableName + "(" + column0Name + " INTEGER PRIMARY KEY AUTOINCREMENT, " + column1Name + " TEXT, " + column2Name + " TEXT, " + column3Name + " INTEGER, "+ column4Name + " BLOB, "+column5Name+" TEXT);";
        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public int addClothToWardrobe(WardrobeItem wardrobeItem)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(column1Name, wardrobeItem.clothType);
        values.put(column2Name, wardrobeItem.colorName);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wardrobeItem.image.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte[] imagebytes = byteArrayOutputStream.toByteArray();

        values.put(column4Name, imagebytes);
        values.put(column5Name, wardrobeItem.topOrBottom);
        long id= database.insert(tableName,null,values);

        if (id > 0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public ArrayList<WardrobeItem> getClothsInWardrobe(String topOrBottom)
    {
        String query = "SELECT * FROM "+ tableName + " where "+column5Name+"='"+topOrBottom+"'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor=database.rawQuery(query, null);
        cursor.moveToFirst();
        ArrayList<WardrobeItem> itemList = new ArrayList<>();
        while (!cursor.isAfterLast())
        {
            int rowid = cursor.getInt(cursor.getColumnIndex(column0Name));
            String clothType = cursor.getString(cursor.getColumnIndex(column1Name));
            String colorName = cursor.getString(cursor.getColumnIndex(column2Name));
            byte[] imageByteArray = cursor.getBlob(cursor.getColumnIndex(column4Name));
            Bitmap image = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

            WardrobeItem wardrobeItem= new WardrobeItem(clothType,colorName,image,rowid);

            itemList.add(wardrobeItem);
            cursor.moveToNext();
        }
        cursor.close();

        return itemList;
    }

    public WardrobeItem getClothInWardrobe(int rowid)
    {
        String query = "SELECT * FROM "+ tableName + " where "+column0Name+"="+rowid;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor=database.rawQuery(query, null);
        cursor.moveToFirst();
        WardrobeItem itemList = new WardrobeItem();
        if (!cursor.isAfterLast())
        {
            String clothType = cursor.getString(cursor.getColumnIndex(column1Name));
            String colorName = cursor.getString(cursor.getColumnIndex(column2Name));
            byte[] imageByteArray = cursor.getBlob(cursor.getColumnIndex(column4Name));
            Bitmap image = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

            WardrobeItem wardrobeItem= new WardrobeItem(clothType,colorName,image,rowid);
            return  wardrobeItem;
        }
        cursor.close();

        return itemList;
    }

    public void removeTheClothFromWardrobe(int rowid)
    {
        String query="delete from "+tableName+" where "+column0Name+"="+rowid+";";
        SQLiteDatabase database=this.getReadableDatabase();
        database.execSQL(query);
    }

    public void dropTable(Wardrobe w)
    {
        Log.d("WardrobeItemDBHelper",w.wardrobeName);
        String query="drop table "+w.wardrobeName+";";
        SQLiteDatabase database=this.getReadableDatabase();
        database.execSQL(query);
    }
}
