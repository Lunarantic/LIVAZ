package rvksdvps.livaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Selection extends AppCompatActivity
{
    ImageView topImage, bottomImage;
    Button addToFav;
    int trowid, browid;
    WardrobeItemDBHelper wardrobeItemDBHelper;
    String wardName;
    WardrobeItem selectedTop, selectedBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Intent intent = this.getIntent();
        trowid = intent.getIntExtra("TopSelected",-1);
        browid = intent.getIntExtra("BottomSelected",-1);
        wardName = intent.getStringExtra("WardRobeName");

        wardrobeItemDBHelper = new WardrobeItemDBHelper(getApplicationContext(),(wardName+".db"),wardName);

        topImage = (ImageView) findViewById(R.id.selectedTopImageView);
        bottomImage = (ImageView) findViewById(R.id.selectedBottomImageView);
        addToFav = (Button) findViewById(R.id.selectedAddToFavButton);

        selectedTop = wardrobeItemDBHelper.getClothInWardrobe(trowid);
        selectedBottom = wardrobeItemDBHelper.getClothInWardrobe(browid);

        bottomImage.setImageBitmap(selectedBottom.image);
        topImage.setImageBitmap(selectedTop.image);

        addToFav.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FavouriteItemIn favouriteItemIn = new FavouriteItemIn(trowid,browid,wardName);
                FavouriteDBHelper favouriteDBHelper = new FavouriteDBHelper(getApplicationContext());
                favouriteDBHelper.addtoFavourites(favouriteItemIn);


                Toast.makeText(getApplicationContext(), "Added successfully!!!", Toast.LENGTH_SHORT).show();

                addToFav.setClickable(false);

                finish();
            }
        });
    }
}
