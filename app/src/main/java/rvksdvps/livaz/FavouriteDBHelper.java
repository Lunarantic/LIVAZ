package rvksdvps.livaz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by 150840521029 on 23-01-2016.
 */
public class FavouriteDBHelper extends SQLiteOpenHelper
{
    static String dbName = "Favourites.db";
    static String tableName = "Favourites";
    static String column0Name = "RowId";
    static String column1Name = "TopRowId";
    static String column2Name = "BottomRowId";
    static String column3Name = "WardrobeName";
    Context context;


    public FavouriteDBHelper(Context context) {
        super(context, dbName, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table_query = "CREATE TABLE " + tableName + "(" + column0Name + " INTEGER PRIMARY KEY AUTOINCREMENT, " + column1Name + " INTEGER, " + column2Name + " INTEGER, " + column3Name + " TEXT);";
        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String addtoFavourites(FavouriteItemIn favouriteItemIn)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(column1Name, favouriteItemIn.topRowId);
        values.put(column2Name, favouriteItemIn.bottomRowId);
        values.put(column3Name, favouriteItemIn.wardrobeName);

        long id= database.insert(tableName,null,values);

        if (id > 0)
        {
            return "Added successfully";
        }
        else
        {
            return "Couldn't add";
        }
    }

    public ArrayList<FavouriteItemOut> getFavouritesOfWardrobe()
    {
        String query = "SELECT * FROM "+ tableName;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor=database.rawQuery(query, null);
        cursor.moveToFirst();

        ArrayList<FavouriteItemOut> favList = new ArrayList<>();

        while (!cursor.isAfterLast())
        {
            int rowid = cursor.getInt(cursor.getColumnIndex(column0Name));
            int toprowid = cursor.getInt(cursor.getColumnIndex(column1Name));
            int bottomrowid = cursor.getInt(cursor.getColumnIndex(column2Name));
            String wardrobename = cursor.getString(cursor.getColumnIndex(column3Name));

            WardrobeItemDBHelper wardrobeItemDBHelper = new WardrobeItemDBHelper(context, (wardrobename+".db"), wardrobename);

            FavouriteItemOut favouriteItemOut = new FavouriteItemOut(wardrobeItemDBHelper.getClothInWardrobe(toprowid).image, wardrobeItemDBHelper.getClothInWardrobe(bottomrowid).image,rowid);

            favList.add(favouriteItemOut);
            cursor.moveToNext();
        }
        cursor.close();

        return favList;
    }

    public void removeFavourite(int rowid)
    {
        String query="delete from "+tableName+" where "+column0Name+"="+rowid+";";
        SQLiteDatabase database=this.getReadableDatabase();
        database.execSQL(query);
    }

    public void removeFavFromWadrobName(Wardrobe w)
    {
        String query="delete from "+tableName+" where "+column3Name+"="+w.wardrobeName+";";
        SQLiteDatabase database=this.getReadableDatabase();
        database.execSQL(query);

    }



}
