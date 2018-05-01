package com.example.intel.navigationwithfragment.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.intel.navigationwithfragment.Expandable.ExpandActivity;
import com.example.intel.navigationwithfragment.Fragments.HomeFragment;
import com.example.intel.navigationwithfragment.Fragments.ImagesDesignWithrecy;
import com.example.intel.navigationwithfragment.Fragments.Profile;
import com.example.intel.navigationwithfragment.Fragments.RecylerWithHorizontalAndVerticalDesign;
import com.example.intel.navigationwithfragment.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private Toolbar mToolbar;
    DrawerLayout mDrawer;
    NavigationView mNavView;

    LinearLayout navHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //mToolbar.setNavigationIcon();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openDrawer(GravityCompat.START);
            }
        });
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        mNavView = (NavigationView) findViewById(R.id.nav_view);

        /*navHeader = (LinearLayout) mNavView.getHeaderView(0);
        navImage = (CircleImageView) navHeader.findViewById(R.id.nav_image);
        navName = (TextView) navHeader.findViewById(R.id.nav_name);
        navProfession = (TextView) navHeader.findViewById(R.id.nav_profession);*/
        displaySelectedScreen(R.id.home1);
        mNavView.getMenu().getItem(0).setChecked(true);
        mNavView.setNavigationItemSelectedListener(MainActivity.this);
    }

    private void displaySelectedScreen(int itemId) {

        int selectPos = 0;

        prevPos = lastPos;
        lastPos = itemId;

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {


            case R.id.home1:
                fragment = new HomeFragment();
                selectPos = 0;
                break;

            case R.id.firstscreen:
                fragment = new RecylerWithHorizontalAndVerticalDesign();
                selectPos = 1;
                break;

            case R.id.profile:
                fragment = new Profile();
                selectPos = 2;
                break;
            case R.id.history:
                fragment = new ImagesDesignWithrecy();
                selectPos = 3;
                break;
            case R.id.expand_screen:
                Intent intent=new Intent(MainActivity.this, ExpandActivity.class);
                startActivity(intent);
                break;
            case R.id.progressbar_screen:
                Intent intent1=new Intent(MainActivity.this, ProgressBarScreen.class);
                startActivity(intent1);
                break;
           /*
            case R.id.sign_out:
                //fragment = new HomeFragment();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mGlobalValues.clear();
                startActivity(intent);
                break;*/
        }

        mNavView.getMenu().getItem(selectPos).setChecked(true);

        // Replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentBody, fragment);
            ft.commit();

        }

        mDrawer.closeDrawer(GravityCompat.START);
    }

    int lastPos = 0;
    int prevPos = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (prevPos != -1) {
                displaySelectedScreen(prevPos);
                prevPos = -1;
                return true;
            }
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedScreen(item.getItemId());

        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START))
            mDrawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}
