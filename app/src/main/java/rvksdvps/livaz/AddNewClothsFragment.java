package rvksdvps.livaz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by 150840521013 on 27-01-2016.
 */
public class AddNewClothsFragment extends Fragment
{
    ImageButton imgMenBtn,imgWomenBtn;

    public void onResume() {
        super.onResume();

        //changing the title of the screen
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Add New Cloths");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.add_new_cloth, container, false);

        imgMenBtn= (ImageButton) rootView.findViewById(R.id.imageButtonMen);
        imgWomenBtn= (ImageButton) rootView.findViewById(R.id.imageButtonWomen);

        imgMenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MenTemplate.class);
                startActivity(intent);
            }
        });

        imgWomenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WomenTemplate.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
