package rvksdvps.livaz;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

/**
 * Created by Lunarantic on 23/01/16.
 */
public class ColorNames
{
    public String getColorNames(int color)
    {
        int r = red(color);
        int g = green(color);
        int b = blue(color);

        if (r > 170)
        {
            if (b > 170)
            {
                if (g > 170)
                {
                    return ("White");
                }
                else if(g > 85)
                {
                    return ("Pink");
                }
                else
                {
                    return ("Magenta");
                }
            }
            else if(b > 85)
            {
                if (g > 170)
                {
                    return ("Yellow");
                }
                else if(g > 85)
                {
                    return ("Tomato");
                }
                else
                {
                    return ("DeepPink");
                }
            }
            else
            {
                if (g > 170)
                {
                    return ("Yellow");
                }
                else if(g > 85)
                {
                    return ("Orange");
                }
                else
                {
                    return ("Red");
                }
            }
        }
        else if(r > 85)
        {
            if (b > 170)
            {
                if (g > 170)
                {
                    return ("SkyBlue");
                }
                else if(g > 85)
                {
                    return ("Violet");
                }
                else
                {
                    return ("Violet");
                }
            }
            else if(b > 85)
            {
                if (g > 170)
                {
                    return ("ParrotGreen");
                }
                else if(g > 85)
                {
                    return ("Grey");
                }
                else
                {
                    return ("Purple");
                }
            }
            else
            {
                if (g > 170)
                {
                    return ("ParrotGreen");
                }
                else if(g > 85)
                {
                    return ("Olive");
                }
                else
                {
                    return ("Maroon");
                }
            }
        }
        else
        {
            if (b > 170)
            {
                if (g > 170)
                {
                    return ("Cyan");
                }
                else if(g > 85)
                {
                    return ("SeaBlue");
                }
                else
                {
                    return ("Blue");
                }
            }
            else if(b > 85)
            {
                if (g > 170)
                {
                    return ("ParrotGreen");
                }
                else if(g > 85)
                {
                    return ("Teal");
                }
                else
                {
                    return ("NavyBlue");
                }
            }
            else
            {
                if (g > 170)
                {
                    return ("Green");
                }
                else if(g > 85)
                {
                    return ("Green");
                }
                else
                {
                    return ("Black");
                }
            }
        }
    }
}
