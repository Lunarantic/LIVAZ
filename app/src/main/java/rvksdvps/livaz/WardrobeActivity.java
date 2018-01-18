package rvksdvps.livaz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WardrobeActivity extends AppCompatActivity
{
    ListView topItem, bottomItem;
    WardrobeItemDBHelper wardrobeItemDBHelper;
    ArrayList<WardrobeItem> topItems, bottomItems;
    WardrobeItemListAdapter topList, bottomList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);

        topItem = (ListView) findViewById(R.id.wardorbeItemTopListView);
        bottomItem = (ListView) findViewById(R.id.wardrobeItemBottomListView);

        final Intent intent = this.getIntent();
        final String wardName = intent.getStringExtra("SelectedWardrobe");

        wardrobeItemDBHelper = new WardrobeItemDBHelper(getApplicationContext(),(wardName+".db"),wardName);

        topItems = wardrobeItemDBHelper.getClothsInWardrobe("T");
        bottomItems = wardrobeItemDBHelper.getClothsInWardrobe("B");

        topList = new WardrobeItemListAdapter(topItems,getApplicationContext());
        bottomList = new WardrobeItemListAdapter(bottomItems,getApplicationContext());

        topItem.setAdapter(topList);
        bottomItem.setAdapter(bottomList);

        topItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(WardrobeActivity.this);
                dialog.setTitle("Select Occasion");
                dialog.setMessage("Formals OR Informals?");

                dialog.setPositiveButton("Formal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intenttop = new Intent(getApplicationContext(), SelectBottom.class);
                        int rowid = (int) topList.getItem(position);
                        intenttop.putExtra("TopSelected", rowid);
                        intenttop.putExtra("WardRobeName", wardName);
                        startActivity(intenttop);
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.setNeutralButton("Informal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intenttop = new Intent(getApplicationContext(), SelectBottom.class);
                        int rowid = (int) topList.getItem(position);
                        intenttop.putExtra("TopSelected", rowid);
                        intenttop.putExtra("WardRobeName", wardName);
                        startActivity(intenttop);
                    }
                });
                dialog.show();
            }


        });





        bottomItem.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(WardrobeActivity.this);
                dialog.setTitle("Select Occasion");
                dialog.setMessage("Formals OR Informals?");

                dialog.setPositiveButton("Formal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentbottom = new Intent(getApplicationContext(), SelectTop.class);
                        int rowid = (int) bottomList.getItem(position);
                        intentbottom.putExtra("BottomSelected", rowid);
                        intentbottom.putExtra("WardRobeName", wardName);
                        startActivity(intentbottom);
                    }
                });

                dialog.setNeutralButton("Informal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentbottom = new Intent(getApplicationContext(), SelectTop.class);
                        int rowid = (int) bottomList.getItem(position);
                        intentbottom.putExtra("BottomSelected", rowid);
                        intentbottom.putExtra("WardRobeName", wardName);
                        startActivity(intentbottom);
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

          topItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
              @Override
              public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
              {


                  Toast.makeText(WardrobeActivity.this, "inside top item long press", Toast.LENGTH_SHORT).show();


                  return false;
              }
          });

        bottomItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(WardrobeActivity.this, "inside Bottom item long press", Toast.LENGTH_SHORT).show();
                return false;
            }
        });



    }
}