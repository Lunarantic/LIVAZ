package rvksdvps.livaz;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 150840521010 on 27-01-2016.
 */
public class WardrobesFragment extends Fragment
{
    FloatingActionButton addNewWardrobe;
    WardrobeDBHelper dbHelper;
    ArrayList<Wardrobe> arrayList;
    ListView listView;
    WardrobeListAdapter adapter;
    WardrobeItemDBHelper wardrobeItemDBHelper;
    FavouriteDBHelper favouriteDBHelper;


    public void onResume() {
        super.onResume();

        //changing the title of the screen
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Wardrobe List");
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.wardrobes, container, false);

        addNewWardrobe = (FloatingActionButton) rootView.findViewById(R.id.wardrobesAddFab);
        listView= (ListView) rootView.findViewById(R.id.wardrobesListView);

        dbHelper= new WardrobeDBHelper(getContext());
        arrayList = dbHelper.getAllRecords("exp");
        adapter = new WardrobeListAdapter(arrayList,getContext());

        Log.d("Array contain", arrayList.toString());

        addNewWardrobe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openDialog();
            }
        });
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String wardrobeName = adapter.getItem(position);

                Intent intent = new Intent(getContext(), WardrobeActivity.class);
                intent.putExtra("SelectedWardrobe",wardrobeName);
                startActivity(intent);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                deleteDialog(position);
                return true;
            }
        });


        return rootView;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void openDialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Add Wardrobe");
        LayoutInflater inflate=LayoutInflater.from(getContext());
        View view= inflate.inflate(R.layout.wardrobe_list_dialog, null, false);
        dialog.setView(view);

        final EditText wardrobeName;
        final RadioButton btnMale,btnFemale;

        wardrobeName = (EditText) view.findViewById(R.id.editTextWardrobeName);

        btnMale= (RadioButton) view.findViewById(R.id.radioButtonMale);
        btnFemale = (RadioButton) view.findViewById(R.id.radioButtonFemale);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        String wardName = wardrobeName.getText().toString();
                        String gender = btnMale.isChecked() ? "M" : (btnFemale.isChecked() ? "F" : "X");

                        //Validation code for avoid duplicate wardrobe name

                        if (adapter.chkRecord(wardName)) {
                            Toast.makeText(getContext(), "Wardrobe name already exists!!", Toast.LENGTH_SHORT).show();

                        } else {
                            //Validation code required
                            if (!(wardName.equals("")) && !(gender.equals("X"))) {
                                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                Wardrobe wardrobe = new Wardrobe(wardName, gender, "exp");
                                adapter.addWardrobe(dbHelper, wardrobe);
                                //adapter.wardrobeList.add(wardrobe);
                                // WardrobeItemDBHelper wardrobeItemDBHelper = new WardrobeItemDBHelper(getApplicationContext(),(wardName+".db"),wardName);

                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

        );

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()

                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }

        );

        dialog.show();
    }


    public void deleteDialog(final int position)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Delete");
        dialog.setMessage("Do you want to delete Wardrobe??");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Wardrobe w=arrayList.get(position);
                dbHelper.deleteRecord(w);
               //wardrobeItemDBHelper.dropTable(w);
                adapter.wardrobeList.remove(w);
                adapter.notifyDataSetChanged();

            }
        });

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

}
