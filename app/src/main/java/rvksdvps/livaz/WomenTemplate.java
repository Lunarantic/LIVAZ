package rvksdvps.livaz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by 150840521010 on 19-01-2016.
 */
public class WomenTemplate extends AppCompatActivity implements View.OnClickListener
{
    ImageButton shirtTemplateButton,tshirtTemplateButton,kurtiTemplateButton,legginsTemplateButton,trouserTemplateButton,jeansTemplateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.women_template);

        shirtTemplateButton = (ImageButton) findViewById(R.id.womenTemplateImageButtonShirt);
        tshirtTemplateButton = (ImageButton) findViewById(R.id.womenTemplateImageButtonTShirt);
        kurtiTemplateButton = (ImageButton) findViewById(R.id.womenTemplateImageButtonKurti);
        legginsTemplateButton = (ImageButton) findViewById(R.id.womenTemplateImageButtonLeggins);
        trouserTemplateButton = (ImageButton) findViewById(R.id.womenTemplateImageButtonTrouser);
        jeansTemplateButton = (ImageButton) findViewById(R.id.womenTemplateImageButtonJeans);

        shirtTemplateButton.setOnClickListener(this);
        tshirtTemplateButton.setOnClickListener(this);
        kurtiTemplateButton.setOnClickListener(this);
        legginsTemplateButton.setOnClickListener(this);
        trouserTemplateButton.setOnClickListener(this);
        jeansTemplateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TemplateActivity.class);

        switch (v.getId()) {
            case R.id.womenTemplateImageButtonTShirt: {
                intent.putExtra("Template", 7);
                break;
            }
            case R.id.womenTemplateImageButtonKurti: {
                intent.putExtra("Template", 5);
                break;
            }
            case R.id.womenTemplateImageButtonLeggins: {
                intent.putExtra("Template", 8);
                break;
            }
            case R.id.womenTemplateImageButtonShirt: {
                intent.putExtra("Template", 6);
                break;
            }
            case R.id.womenTemplateImageButtonTrouser: {
                intent.putExtra("Template", 9);
                break;
            }
            case R.id.womenTemplateImageButtonJeans: {
                intent.putExtra("Template", 10);
                break;

            }
        }

        startActivity(intent);
    }
}
