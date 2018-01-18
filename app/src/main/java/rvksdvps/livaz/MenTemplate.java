package rvksdvps.livaz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by 150840521010 on 19-01-2016.
 */
public class MenTemplate extends AppCompatActivity implements View.OnClickListener
{
    ImageButton shirtTemplateButton,tshirtTemplateButton,trouserTemplateButton,jeansTemplateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.men_template);

        shirtTemplateButton = (ImageButton) findViewById(R.id.menTemplateImageButtonShirt);
        tshirtTemplateButton = (ImageButton) findViewById(R.id.menTemplateImageButtonTShirt);
        trouserTemplateButton = (ImageButton) findViewById(R.id.menTemplateImageButtonTrouser);
        jeansTemplateButton = (ImageButton) findViewById(R.id.menTemplateImageButtonJeans);

        shirtTemplateButton.setOnClickListener(this);
        tshirtTemplateButton.setOnClickListener(this);
        trouserTemplateButton.setOnClickListener(this);
        jeansTemplateButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TemplateActivity.class);

        switch (v.getId())
        {
            case R.id.menTemplateImageButtonTShirt :
            {
                intent.putExtra("Template", 2);
                break;
            }
            case R.id.menTemplateImageButtonShirt :
            {
                intent.putExtra("Template", 1);
                break;
            }

            case R.id.menTemplateImageButtonJeans :
            {
                intent.putExtra("Template", 4);
                break;
            }
            case R.id.menTemplateImageButtonTrouser :
            {
                intent.putExtra("Template", 3);
                break;
            }

        }

        startActivity(intent);
    }
}
