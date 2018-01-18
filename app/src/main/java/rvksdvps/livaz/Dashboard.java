package rvksdvps.livaz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed()
    {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            // super.onBackPressed();
            //    exitDialog();
            count++;
            if (count == 1)
            {
                Snackbar.make(drawer, "Press again to exit!!!", Snackbar.LENGTH_LONG).setAction("Action", null)
                        .show();
            }
            else if (count==2)
            {
                finish();
            }
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        count = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();

        count = 0;
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_AddNewClothes)
        {
            Fragment clothsFragment = new AddNewClothsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,clothsFragment).commit();
        } else if (id == R.id.nav_DressCobmination)
        {
            Fragment dressFragment = new DressCombinationFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,dressFragment).commit();
        } else if (id == R.id.nav_wardrobes)
        {
            Fragment wardrobeFragment = new WardrobesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,wardrobeFragment).commit();
        }else if(id==R.id.nav_AboutUs)
        {
            Fragment aboutUsFragment = new AboutUsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,aboutUsFragment).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void exitDialog()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(Dashboard.this);
        dialog.setTitle("Exit?");
        dialog.setMessage("Do you want to Exit?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Dashboard.this, "Yes i wanna exit", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(Dashboard.this, "i wanna stay on this page", Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });

        dialog.show();
    }
}
