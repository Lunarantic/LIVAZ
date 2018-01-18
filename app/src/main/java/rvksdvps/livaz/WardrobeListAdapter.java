package rvksdvps.livaz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 150840521010 on 22-01-2016.
 */
public class WardrobeListAdapter extends BaseAdapter
{
    ArrayList<Wardrobe> wardrobeList;
    Context context;

    public WardrobeListAdapter(ArrayList<Wardrobe> wardrobeList, Context context) {
        this.wardrobeList = wardrobeList;
        this.context = context;
    }

    @Override
    public int getCount()

    {
        return wardrobeList.size();
    }

    @Override
    public String getItem(int position)
    {
        return wardrobeList.get(position).wardrobeName;
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
        View view=inflater.inflate(R.layout.label_for_table_cell, null, false);

        TextView tableName = (TextView) view.findViewById(R.id.textViewName);
        Wardrobe wardrobe = wardrobeList.get(position);
        tableName.setText(wardrobe.wardrobeName);

        return view;
    }

    public  void addWardrobe(WardrobeDBHelper dbHelper, Wardrobe w)
    {
        if (dbHelper.insertRecord(w) == 1)
            wardrobeList.add(w);
    }

    public void deleteRecord(Wardrobe w)
    {
        wardrobeList.remove(w);
    }

    public boolean chkRecord(String wardNew) {
        int l = wardrobeList.size();
        for (int i = 0; i < l; i++)
        {
            if (wardrobeList.get(i).wardrobeName.equals(wardNew))
                return true;
        }
        return false;
    }
}
