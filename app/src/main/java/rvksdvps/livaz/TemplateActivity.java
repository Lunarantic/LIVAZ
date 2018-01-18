package rvksdvps.livaz;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TemplateActivity extends AppCompatActivity implements View.OnClickListener
{
    ImageButton redButton,greyButton,greenButton,blueButton,whiteButton,blackButton,pinkButton,yellowButton;
    ImageButton galButton, camButton;
    Button pickButton;
    ImageView imageView;
    WardrobeItem wardrobeItem;
    int extra;
    WardrobeListAdapter adapter;
    ArrayList<Wardrobe> wardrobeList;
    WardrobeDBHelper dbHelper;
    // ListView listView;

    final Point p1 = new Point();
    Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        extra = getIntent().getIntExtra("Template",0);
        wardrobeItem = new WardrobeItem();

        switch (extra)
        {
            case 1:
            {
                wardrobeItem.clothType = "Shirt";
                wardrobeItem.topOrBottom = "T";
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.blackshirtm).copy(Bitmap.Config.ARGB_8888, true);
                break;
            }
            case 2:
            {
                wardrobeItem.clothType = "Tshirt";
                wardrobeItem.topOrBottom = "T";
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.blacktshirtm).copy(Bitmap.Config.ARGB_8888, true);
                break;
            }
            case 3:
            {
                wardrobeItem.clothType = "Trouser";
                wardrobeItem.topOrBottom = "B";
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.bluetrouserm).copy(Bitmap.Config.ARGB_8888, true);
                break;
            }
            case 4:
            {
                wardrobeItem.clothType = "Jeans";
                wardrobeItem.topOrBottom = "B";
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.blackjeansm).copy(Bitmap.Config.ARGB_8888, true);
                break;
            }
            case 5:
            {
                wardrobeItem.clothType = "Kurti";
                wardrobeItem.topOrBottom = "T";
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.blackkurti).copy(Bitmap.Config.ARGB_8888, true);
                break;
            }
            case 6:
            {
                wardrobeItem.clothType = "Shirt";
                wardrobeItem.topOrBottom = "T";
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.blackshirtw).copy(Bitmap.Config.ARGB_8888, true);
                break;
            }
            case 7:
            {
                wardrobeItem.clothType = "Tshirt";
                wardrobeItem.topOrBottom = "T";
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.blacktshirtw).copy(Bitmap.Config.ARGB_8888, true);
                break;
            }
            case 8:
            {
                wardrobeItem.clothType = "Leggin";
                wardrobeItem.topOrBottom = "B";
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.blueleggin).copy(Bitmap.Config.ARGB_8888, true);
                break;
            }
            case 9:
            {
                wardrobeItem.clothType = "Trouser";
                wardrobeItem.topOrBottom = "B";
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.blacktrouserw).copy(Bitmap.Config.ARGB_8888, true);
                break;
            }
            case 10:
            {
                wardrobeItem.clothType = "Jeans";
                wardrobeItem.topOrBottom = "B";
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.blackjeansw).copy(Bitmap.Config.ARGB_8888, true);
                break;
            }
        }

        pickButton = (Button) findViewById(R.id.templateAddToWardrobeButton);
        redButton= (ImageButton) findViewById(R.id.redBtn);
        greyButton= (ImageButton) findViewById(R.id.grayBtn);
        greenButton= (ImageButton) findViewById(R.id.greenBtn);
        blueButton= (ImageButton) findViewById(R.id.blueBtn);
        whiteButton= (ImageButton) findViewById(R.id.whiteBtn);
        blackButton= (ImageButton) findViewById(R.id.blackBtn);
        pinkButton= (ImageButton) findViewById(R.id.magentaBtn);
        yellowButton= (ImageButton) findViewById(R.id.yellowBtn);

        galButton = (ImageButton) findViewById(R.id.galButton);
        camButton = (ImageButton) findViewById(R.id.camButton);

        imageView= (ImageView) findViewById(R.id.templateImageView);



        redButton.setOnClickListener(this);
        greyButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);
        whiteButton.setOnClickListener(this);
        blackButton.setOnClickListener(this);
        pinkButton.setOnClickListener(this);
        yellowButton.setOnClickListener(this);

        camButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,3);
            }
        });

        pickButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    wardrobeItem.image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    openDialog();
                }
                catch (NullPointerException e)
                {
                    Toast.makeText(TemplateActivity.this, "Please pick the color", Toast.LENGTH_SHORT).show();
                }
            }
        });

        galButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent galIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galIntent, 7);
            }
        });

        dbHelper= new WardrobeDBHelper(getApplicationContext());
        String gen = (extra <= 4) ? "M" : "F";
        wardrobeList = dbHelper.getAllSelectedRecords(gen);
        adapter = new WardrobeListAdapter(wardrobeList,getApplicationContext());
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.redBtn :
            {
                wardrobeItem.colorName = "Red";
                if (extra == 2)
                    imageView.setImageResource(R.drawable.redtshirtm);
                else if (extra == 7)
                    imageView.setImageResource(R.drawable.redtshirtw);
                else if (extra == 5)
                    imageView.setImageResource(R.drawable.redkurti);
                else if (extra == 8)
                    imageView.setImageResource(R.drawable.redleggin);
                else if (extra == 1)
                    imageView.setImageResource(R.drawable.redshirtm);
                else if (extra == 6)
                    imageView.setImageResource(R.drawable.redshirtw);
                else if (extra == 9)
                    imageView.setImageResource(R.drawable.redtrouserw);
                else if (extra == 10)
                    imageView.setImageResource(R.drawable.redjeansw);
                else if (extra == 4)
                    imageView.setImageResource(R.drawable.redjeansm);
                else if (extra == 3)
                    imageView.setImageResource(R.drawable.redtrouserm);
                return;
            }
            case R.id.grayBtn :
            {
                wardrobeItem.colorName = "Grey";
                if (extra == 2)
                    imageView.setImageResource(R.drawable.greytshirtm);
                else if (extra == 7)
                    imageView.setImageResource(R.drawable.greytshirtw);
                else if (extra == 5)
                    imageView.setImageResource(R.drawable.greykurti);
                else if (extra == 8)
                    imageView.setImageResource(R.drawable.greyleggin);
                else if (extra == 1)
                    imageView.setImageResource(R.drawable.greyshirtm);
                else if (extra == 6)
                    imageView.setImageResource(R.drawable.greyshirtw);
                else if (extra == 9)
                    imageView.setImageResource(R.drawable.greytrouserw);
                else if (extra == 10)
                    imageView.setImageResource(R.drawable.greyjeansw);
                else if (extra == 4)
                    imageView.setImageResource(R.drawable.greyjeansm);
                else if (extra == 3)
                    imageView.setImageResource(R.drawable.greytrouserm);
                return;
            }
            case R.id.greenBtn :
            {
                wardrobeItem.colorName = "Green";
                if (extra == 2)
                    imageView.setImageResource(R.drawable.greentshirtm);
                else if (extra == 7)
                    imageView.setImageResource(R.drawable.greentshirtw);
                else if (extra == 5)
                    imageView.setImageResource(R.drawable.greenkurti);
                else if (extra == 8)
                    imageView.setImageResource(R.drawable.greenleggin);
                else if (extra == 1)
                    imageView.setImageResource(R.drawable.greenshirtm);
                else if (extra == 6)
                    imageView.setImageResource(R.drawable.greenshirtw);
                else if (extra == 9)
                    imageView.setImageResource(R.drawable.greentrouserw);
                else if (extra == 10)
                    imageView.setImageResource(R.drawable.greenjeansw);
                else if (extra == 4)
                    imageView.setImageResource(R.drawable.greenjeansm);
                else if (extra == 3)
                    imageView.setImageResource(R.drawable.greentrouserm);
                return;
            }
            case  R.id.blueBtn :
            {
                wardrobeItem.colorName = "Blue";
                if (extra == 2)
                    imageView.setImageResource(R.drawable.bluetshirtm);
                else if (extra == 7)
                    imageView.setImageResource(R.drawable.bluetshirtw);
                else if (extra == 5)
                    imageView.setImageResource(R.drawable.bluekurti);
                else if (extra == 8)
                    imageView.setImageResource(R.drawable.blueleggin);
                else if (extra == 1)
                    imageView.setImageResource(R.drawable.blueshirtm);
                else if (extra == 6)
                    imageView.setImageResource(R.drawable.blueshirtw);
                else if (extra == 9)
                    imageView.setImageResource(R.drawable.bluetrouserw);
                else if (extra == 10)
                    imageView.setImageResource(R.drawable.bluejeansw);
                else if (extra == 4)
                    imageView.setImageResource(R.drawable.bluejeansm);
                else if (extra == 3)
                    imageView.setImageResource(R.drawable.bluetrouserm);
                return;
            }
            case R.id.whiteBtn :
            {
                wardrobeItem.colorName = "White";
                if (extra == 2)
                    imageView.setImageResource(R.drawable.whitetshirtm);
                else if (extra == 7)
                    imageView.setImageResource(R.drawable.whitetshirtw);
                else if (extra == 5)
                    imageView.setImageResource(R.drawable.whitekurti);
                else if (extra == 8)
                    imageView.setImageResource(R.drawable.whiteleggin);
                else if (extra == 1)
                    imageView.setImageResource(R.drawable.whiteshirtm);
                else if (extra == 6)
                    imageView.setImageResource(R.drawable.whiteshirtw);
                else if (extra == 9)
                    imageView.setImageResource(R.drawable.whitetrouserw);
                else if (extra == 10)
                    imageView.setImageResource(R.drawable.whitejeansw);
                else if (extra == 4)
                    imageView.setImageResource(R.drawable.whitejeansm);
                else if (extra == 3)
                    imageView.setImageResource(R.drawable.whitetrouserm);
                return;
            }
            case R.id.blackBtn :
            {
                wardrobeItem.colorName = "Black";
                if (extra == 2)
                    imageView.setImageResource(R.drawable.blacktshirtm);
                else if (extra == 7)
                    imageView.setImageResource(R.drawable.blacktshirtw);
                else if (extra == 5)
                    imageView.setImageResource(R.drawable.blackkurti);
                else if (extra == 8)
                    imageView.setImageResource(R.drawable.blackleggin);
                else if (extra == 1)
                    imageView.setImageResource(R.drawable.blackshirtm);
                else if (extra == 6)
                    imageView.setImageResource(R.drawable.blackshirtw);
                else if (extra == 9)
                    imageView.setImageResource(R.drawable.blacktrouserw);
                else if (extra == 10)
                    imageView.setImageResource(R.drawable.blackjeansw);
                else if (extra == 4)
                    imageView.setImageResource(R.drawable.blackjeansm);
                else if (extra == 3)
                    imageView.setImageResource(R.drawable.blacktrouserm);
                return;
            }
            case  R.id.magentaBtn :
            {
                wardrobeItem.colorName = "Magneta";
                if (extra == 2)
                    imageView.setImageResource(R.drawable.magentatshirtm);
                else if (extra == 7)
                    imageView.setImageResource(R.drawable.magentatshirtw);
                else if (extra == 5)
                    imageView.setImageResource(R.drawable.magentakurti);
                else if (extra == 8)
                    imageView.setImageResource(R.drawable.magentaleggin);
                else if (extra == 1)
                    imageView.setImageResource(R.drawable.magentashirtm);
                else if (extra == 6)
                    imageView.setImageResource(R.drawable.magentashirtw);
                else if (extra == 9)
                    imageView.setImageResource(R.drawable.magentatrouserw);
                else if (extra == 10)
                    imageView.setImageResource(R.drawable.magentajeansw);
                else if (extra == 4)
                    imageView.setImageResource(R.drawable.magentajeansm);
                else if (extra == 3)
                    imageView.setImageResource(R.drawable.magentatrouserm);
                return;
            }
            case R.id.yellowBtn :
            {
                wardrobeItem.colorName = "Yellow";
                if (extra == 2)
                    imageView.setImageResource(R.drawable.yellowtshirtm);
                else if (extra == 7)
                    imageView.setImageResource(R.drawable.yellowtshirtw);
                else if (extra == 5)
                    imageView.setImageResource(R.drawable.yellowkurti);
                else if (extra == 8)
                    imageView.setImageResource(R.drawable.yellowleggin);
                else if (extra == 1)
                    imageView.setImageResource(R.drawable.yellowshirtm);
                else if (extra == 6)
                    imageView.setImageResource(R.drawable.yellowshirtw);
                else if (extra == 9)
                    imageView.setImageResource(R.drawable.yellowtrouserw);
                else if (extra == 10)
                    imageView.setImageResource(R.drawable.yellowjeansw);
                else if (extra == 4)
                    imageView.setImageResource(R.drawable.yellowjeansm);
                else if (extra == 3)
                    imageView.setImageResource(R.drawable.yellowtrouserm);
                return;
            }
        }
    }


    class TheTask extends AsyncTask<Void, Integer, Bitmap> {

        Bitmap bmp;
        Point pt;
        int replacementColor, targetColor;

        public TheTask(Bitmap bm, Point p, int sc, int tc) {
            this.bmp = bm;
            this.pt = p;
            this.replacementColor = tc;
            this.targetColor = sc;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            FloodFill f = new FloodFill();
            Bitmap image = f.floodFill(bmp, pt, targetColor, replacementColor);
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

// flood fill

    public class FloodFill {
        public Bitmap floodFill(Bitmap image, Point node, int targetColor,
                                int replacementColor) {
            int width = image.getWidth();
            int height = image.getHeight();
            int target = targetColor;
            int replacement = replacementColor;
            if (target != replacement) {
                Queue<Point> queue = new LinkedList<>();
                do {

                    int x = node.x;
                    int y = node.y;
                    while (x > 0 && image.getPixel(x - 1, y) == target) {
                        x--;

                    }
                    boolean spanUp = false;
                    boolean spanDown = false;
                    while (x < width && image.getPixel(x, y) == target) {
                        image.setPixel(x, y, replacement);
                        if (!spanUp && y > 0
                                && image.getPixel(x, y - 1) == target) {
                            queue.add(new Point(x, y - 1));
                            spanUp = true;
                        } else if (spanUp && y > 0
                                && image.getPixel(x, y - 1) != target) {
                            spanUp = false;
                        }
                        if (!spanDown && y < height - 1
                                && image.getPixel(x, y + 1) == target) {
                            queue.add(new Point(x, y + 1));
                            spanDown = true;
                        } else if (spanDown && y < height - 1
                                && image.getPixel(x, y + 1) != target) {
                            spanDown = false;
                        }
                        x++;
                    }
                }while ((node = queue.poll()) != null);
            }

            return image;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void openDialog()
    {


        AlertDialog.Builder dialog = new AlertDialog.Builder(TemplateActivity.this);
        dialog.setTitle("Add Wardrobe");
        LayoutInflater inflate=LayoutInflater.from(getApplicationContext());
        View view= inflate.inflate(R.layout.add_to_wardrobe_dialog, null, false);
        dialog.setView(view);


        ListView listView= (ListView) view.findViewById(R.id.showWardRobelistView);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String wardrobeName = adapter.getItem(position);

                WardrobeItemDBHelper wardrobeItemDBHelper = new WardrobeItemDBHelper(getApplicationContext(), (wardrobeName + ".db"), wardrobeName);
                if (wardrobeItemDBHelper.addClothToWardrobe(wardrobeItem) == 1)
                {


                    finish();
                }
                else
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(TemplateActivity.this);
                    alert.setTitle("Wardrobe not present");
                    alert.show();


                }

            }
        });



        /*dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Validation code required
                String wardName = wardrobeName.getText().toString();
                WardrobeItemDBHelper wardrobeItemDBHelper = new WardrobeItemDBHelper(getApplicationContext(), (wardName + ".db"), wardName);
                if (wardrobeItemDBHelper.addClothToWardrobe(wardrobeItem) == 1)
                 {
                    dialog.dismiss();
                    finish();
                }
                else
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(TemplateActivity.this);
                    alert.setTitle("Wardrobe not present");
                    alert.show();
                    dialog.dismiss();
                }
            }
        });*/

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == 3 && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap img = (Bitmap) extras.get("data");

            int xl = img.getWidth();
            int yl = img.getHeight();

            int x = xl / 2;
            int y = yl / 2;

            int targetColor = img.getPixel(x, y);

            ColorNames colorNames = new ColorNames();
            wardrobeItem.colorName = colorNames.getColorNames(targetColor);

            xl = mBitmap.getWidth();
            yl = mBitmap.getHeight();

            if (extra == 1 || extra == 2 || extra == 5 || extra == 6 || extra == 7)
                x = xl / 2;
            else if (extra == 10 || extra == 9 || extra == 3 || extra == 8)
                x = xl / 3;
            else
                x = xl / 4;
            y = yl / 2;

            int sourceColor = mBitmap.getPixel(x,y);

            p1.x = x;
            p1.y = y;

            new TheTask(mBitmap, p1, sourceColor, targetColor).execute();
        }
        if (requestCode == 7 && resultCode == RESULT_OK)
        {
            Uri uri = data.getData();

            try {
               Bitmap img = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);


            int xl = img.getWidth();
            int yl = img.getHeight();

            int x = xl / 2;
            int y = yl / 2;

            int targetColor = img.getPixel(x, y);

                ColorNames colorNames = new ColorNames();
                wardrobeItem.colorName = colorNames.getColorNames(targetColor);

            xl = mBitmap.getWidth();
            yl = mBitmap.getHeight();

                if (extra == 1 || extra == 2 || extra == 5 || extra == 6 || extra == 7)
            x = xl / 2;
                else if (extra == 10 || extra == 9 || extra == 3 || extra == 8)
                    x = xl / 3;
                else
                    x = xl / 4;

            y = yl / 2;

            int sourceColor = mBitmap.getPixel(x,y);

                p1.x = x;
                p1.y = y;

            new TheTask(mBitmap, p1, sourceColor, targetColor).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
