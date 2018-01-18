package rvksdvps.livaz;

import android.graphics.Bitmap;

/**
 * Created by 150840521010 on 22-01-2016.
 */
public class WardrobeItem
{
    String clothType;
    String colorName;
    Bitmap image;
    int colorNum;
    int rowid;
    String topOrBottom;

    WardrobeItem()
    {

    }

    WardrobeItem(String clothType, String colorName, int colorNum, Bitmap image, String topOrBottom)
    {
        this.clothType = clothType;
        this.colorName = colorName;
        this.colorNum = colorNum;
        this.image = image;
        this.topOrBottom = topOrBottom;
    }

    WardrobeItem(String clothType, String colorName, Bitmap image, String topOrBottom)
    {
        this.clothType = clothType;
        this.colorName = colorName;
        this.image = image;
        this.topOrBottom = topOrBottom;
    }

    WardrobeItem(String clothType, String colorName, Bitmap image, int rowid)
    {
        this.clothType = clothType;
        this.colorName = colorName;
        this.image = image;
        this.rowid = rowid;
    }
}
