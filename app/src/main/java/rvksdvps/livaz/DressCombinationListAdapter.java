package rvksdvps.livaz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 150840521010 on 25-01-2016.
 */
public class DressCombinationListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<FavouriteItemOut> favouriteItemArrayList;

    public DressCombinationListAdapter(Context context, ArrayList<FavouriteItemOut> favouriteItemArrayList) {
        this.context = context;
        this.favouriteItemArrayList = favouriteItemArrayList;
    }

    @Override
    public int getCount() {
        return favouriteItemArrayList.size();

    }

    @Override
    public FavouriteItemOut getItem(int position) {
        return favouriteItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.grid_view_cell, null, false);

        ImageView topImageView= (ImageView) view.findViewById(R.id.GridTopimageView);
        ImageView bottomImageView= (ImageView) view.findViewById(R.id.GridBottomimageView);

        FavouriteItemOut favouriteItem=favouriteItemArrayList.get(position);

        topImageView.setImageBitmap(favouriteItem.topCloth);
        bottomImageView.setImageBitmap(favouriteItem.bottomCloth);

        return view;
    }


    public void removeItem(int position)
    {
        (new FavouriteDBHelper(context)).removeFavourite(favouriteItemArrayList.get(position).rowid);
        favouriteItemArrayList.remove(position);
        notifyDataSetChanged();
    }
}
