package com.example.android.architecture.blueprints.todoapp.poc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android.architecture.blueprints.todoapp.Injection;
import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsActivity;
import com.example.android.architecture.blueprints.todoapp.util.ActivityUtils;

/**
 * Created by user on 6/13/2017.
 */

public class PocActivity extends AppCompatActivity {

    PocPresenter mPocPresenter;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pocs_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        // customize up icon to be drawable
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        if (mNavigationView != null)
            setupDrawerContent(mDrawerLayout);

        PocFragment pocFragment = (PocFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (pocFragment == null) {
            pocFragment = PocFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), pocFragment, R.id.contentFrame);
        }
        mPocPresenter = new PocPresenter(Injection.providePocRepo(), pocFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void setupDrawerContent(final DrawerLayout drawerLayout) {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.list_navigation_menu_item:
                        // resume parent activity
                        NavUtils.navigateUpFromSameTask(PocActivity.this);
                        break;

                    case R.id.statistics_navigation_menu_item:
                        Intent intent = new Intent(PocActivity.this, StatisticsActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.pocs_menu_item:
                        // nothing to do , current activity
                }
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
