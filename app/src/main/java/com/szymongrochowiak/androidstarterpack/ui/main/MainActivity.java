package com.szymongrochowiak.androidstarterpack.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.szymongrochowiak.androidstarterpack.R;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;
import com.szymongrochowiak.androidstarterpack.ui.common.activities.base.BaseActivity;
import com.szymongrochowiak.androidstarterpack.ui.main.dagger.DaggerMainComponent;
import com.szymongrochowiak.androidstarterpack.ui.main.dagger.MainComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Szymon Grochowiak
 */
public class MainActivity extends BaseActivity<MainView, MainPresenter>
        implements MainView, NavigationView.OnNavigationItemSelectedListener {

    @NonNull
    private MainComponent mMainComponent;

    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @Override
    protected void injectDependencies() {
        mMainComponent = DaggerMainComponent.builder().applicationComponent(getDaggerApplicationComponent()).build();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return mMainComponent.presenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().startRepository();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mFloatingActionButton.setOnClickListener(
                view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        getPresenter().queryBerry();
    }

    @Override
    protected void onDestroy() {
        // TODO needed proper handling of configuration changes, consider changing this?
        if (!isChangingConfigurations()) {
            getPresenter().destroyRepository();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent(@NonNull Berry berry) {
        hideLoading();
        mTextView.setText(berry.getName());
    }

    @Override
    public void showError(@NonNull String errorMessage) {
        hideLoading();
        mTextView.setText(errorMessage);
    }

    private void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }
}
