package rvksdvps.livaz;

/**
 * Created by 150840521010 on 22-01-2016.
 */
public class Wardrobe
{
    String wardrobeName,gender, uname;

    public Wardrobe(String wardrobeName, String gender, String uname)
    {
        this.wardrobeName = wardrobeName;
        this.gender = gender;
        this.uname = uname;
    }

    public Wardrobe(String wardrobeName, String gender)
    {
        this.wardrobeName = wardrobeName;
        this.gender = gender;
    }
}
