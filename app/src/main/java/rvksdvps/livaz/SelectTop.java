package rvksdvps.livaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectTop extends AppCompatActivity
{
    ListView topListView;
    ImageView bottomImage;
    ArrayList<WardrobeItem> topItems;
    WardrobeItemDBHelper wardrobeItemDBHelper;
    WardrobeItem selectedBottom;
    WardrobeItemListAdapter topList;
    String wardName;
    int browid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_top);

        Intent intent = this.getIntent();
        browid = intent.getIntExtra("BottomSelected", -1);
        wardName = intent.getStringExtra("WardRobeName");

        wardrobeItemDBHelper = new WardrobeItemDBHelper(getApplicationContext(),(wardName+".db"),wardName);

        bottomImage = (ImageView) findViewById(R.id.selectTopImageView);
        topListView = (ListView) findViewById(R.id.selectTopListView);

        topItems = wardrobeItemDBHelper.getClothsInWardrobe("T");
        selectedBottom = wardrobeItemDBHelper.getClothInWardrobe(browid);

        bottomImage.setImageBitmap(selectedBottom.image);
        topList = new WardrobeItemListAdapter(topItems,getApplicationContext());

        topListView.setAdapter(topList);

        topListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(), Selection.class);
                int trowid = (int) topList.getItem(position);
                intent1.putExtra("TopSelected", trowid);
                intent1.putExtra("WardRobeName", wardName);
                intent1.putExtra("BottomSelected", browid);
                startActivity(intent1);
            }
        });
    }
}
