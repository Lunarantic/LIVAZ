package rvksdvps.livaz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 150840521013 on 27-01-2016.
 */
public class DressCombinationFragment  extends Fragment

{
    GridView gridView;
    FavouriteDBHelper favouriteDBHelper;
    ArrayList<FavouriteItemOut> favArrayList;
    DressCombinationListAdapter dComAdapter;

    @Override
    public void onResume() {
        super.onResume();

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Favorites");
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView=inflater.inflate(R.layout.dress_combination, container, false);

        gridView= (GridView) rootView.findViewById(R.id.dressCombGridView);

        favouriteDBHelper = new FavouriteDBHelper(getContext());
        favArrayList = favouriteDBHelper.getFavouritesOfWardrobe();
        dComAdapter = new DressCombinationListAdapter(getContext(),favArrayList);
        gridView.setAdapter(dComAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDressDialog(position);
            }
        });
        return rootView;
    }


    public void openDressDialog(final int position)
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(getContext());
        dialog.setTitle("Selected Combination");


        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.pop_up_selected_collection, null, false);

        dialog.setView(view);

        ImageView topImageView= (ImageView) view.findViewById(R.id.imageViewSTop);
        ImageView bottomImageView= (ImageView) view.findViewById(R.id.imageViewSBottom);

        FavouriteItemOut favouriteItem=favArrayList.get(position);

        topImageView.setImageBitmap(favouriteItem.topCloth);
        bottomImageView.setImageBitmap(favouriteItem.bottomCloth);


        dialog.setNegativeButton("Remove", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dComAdapter.removeItem(position);
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
