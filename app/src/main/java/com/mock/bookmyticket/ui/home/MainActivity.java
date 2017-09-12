
package com.mock.bookmyticket.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mock.bookmyticket.R;
import com.mock.bookmyticket.ui.base.BaseActivity;
import com.mock.bookmyticket.ui.bookticket.BookTicketFragment;
import com.mock.bookmyticket.ui.history.TicketHistoryFragment;

public class MainActivity extends BaseActivity implements MainMvpView {

    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    Toolbar toolbar;
    MainMvpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        mPresenter.onAttach(this);
        if (savedInstanceState == null) {
            mPresenter.onDrawerOptionBooKClick();
        }
        setUp();
    }

    @Override
    protected void setUp() {
        // Toolbar setup
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().show();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        // Drawer Layout  setup
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        // NavigationView Layout  setup
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                switch (item.getItemId()) {

                    case R.id.nav_book:
                        mPresenter.onDrawerOptionBooKClick();
                        break;
                    case R.id.nav_history:
                        mPresenter.onDrawerOptionHistoryClick();
                        break;
                    case R.id.nav_abt_us:
                        mPresenter.onDrawerOptionContactClick();
                        break;


                    default:
                        onDrawerOptionBookTicket();
                        break;

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Profile picture setup on Navigation drawer using Glide library
        Glide.with(this).load(R.drawable.metrouser).apply(RequestOptions.circleCropTransform()).into((ImageView) findViewById(R.id.iv_profile_pic));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDrawerOptionBookTicket() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        BookTicketFragment bookTicketFragment = new BookTicketFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.container, bookTicketFragment, BookTicketFragment.class.getCanonicalName()).commit();

    }

    @Override
    public void onDrawerOptionViewHistory() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (null != fragmentManager) {
            TicketHistoryFragment ticketHistoryFragment;
            Fragment fragment = fragmentManager.findFragmentByTag(TicketHistoryFragment.class.getCanonicalName());
            if (fragment instanceof TicketHistoryFragment) {
                ticketHistoryFragment = (TicketHistoryFragment) fragment;
            } else {
                ticketHistoryFragment = new TicketHistoryFragment();
            }
            fragmentManager.beginTransaction()
                    .replace(R.id.container, ticketHistoryFragment, TicketHistoryFragment.class.getCanonicalName()).commit();
        }
    }

    @Override
    public void onDrawerOptionContact() {

    }
}
