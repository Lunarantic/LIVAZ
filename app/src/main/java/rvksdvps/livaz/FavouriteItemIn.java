package rvksdvps.livaz;

/**
 * Created by 150840521029 on 23-01-2016.
 */
public class FavouriteItemIn
{
    int topRowId;
    int bottomRowId;
    String wardrobeName;

    FavouriteItemIn()
    {

    }

    FavouriteItemIn(int topRowId, int bottomRowId, String wardrobeName)
    {
        this.topRowId = topRowId;
        this.bottomRowId = bottomRowId;
        this.wardrobeName=wardrobeName;
    }
}

