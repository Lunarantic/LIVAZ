package rvksdvps.livaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectBottom extends AppCompatActivity
{
    ListView bottomListView;
    ImageView topImage;
    ArrayList<WardrobeItem> bottomItems;
    WardrobeItemDBHelper wardrobeItemDBHelper;
    WardrobeItem selectedTop;
    WardrobeItemListAdapter bottomList;
    String wardName;
    int trowid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bottom);

        Intent intent = this.getIntent();
        trowid = intent.getIntExtra("TopSelected", -1);
        wardName = intent.getStringExtra("WardRobeName");

        wardrobeItemDBHelper = new WardrobeItemDBHelper(getApplicationContext(),(wardName+".db"),wardName);

        topImage = (ImageView) findViewById(R.id.selectBottomImageView);
        bottomListView = (ListView) findViewById(R.id.selectBottomListView);

        bottomItems = wardrobeItemDBHelper.getClothsInWardrobe("B");
        selectedTop = wardrobeItemDBHelper.getClothInWardrobe(trowid);

        topImage.setImageBitmap(selectedTop.image);
        bottomList = new WardrobeItemListAdapter(bottomItems,getApplicationContext());

        bottomListView.setAdapter(bottomList);

        bottomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(), Selection.class);
                int browid = (int) bottomList.getItem(position);
                intent1.putExtra("TopSelected", trowid);
                intent1.putExtra("WardRobeName", wardName);
                intent1.putExtra("BottomSelected", browid);
                startActivity(intent1);
            }
        });
    }
}
