package rvksdvps.livaz;

import android.graphics.Bitmap;

/**
 * Created by Lunarantic on 25/01/16.
 */
public class FavouriteItemOut
{
    Bitmap topCloth;
    Bitmap bottomCloth;
    int rowid;

    FavouriteItemOut(Bitmap topCloth, Bitmap bottomCloth, int rowid)
    {
        this.rowid = rowid;
        this.topCloth = topCloth;
        this.bottomCloth = bottomCloth;
    }
}
