package rvksdvps.livaz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 150840521010 on 22-01-2016.
 */
public class WardrobeItemListAdapter extends BaseAdapter
{
    ArrayList<WardrobeItem> wardrobeList;
    Context context;

    public WardrobeItemListAdapter(ArrayList<WardrobeItem> wardrobeList, Context context) {
        this.wardrobeList = wardrobeList;
        this.context = context;
    }

    @Override
    public int getCount()

    {
        return wardrobeList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return wardrobeList.get(position).rowid;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.wardrobe_list_table_item, null, false);

        WardrobeItem wardrobe = wardrobeList.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.wardrobeItemImageView);
        imageView.setImageBitmap(wardrobe.image);

        return view;
    }
}
