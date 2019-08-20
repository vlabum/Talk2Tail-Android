package com.talk2tail.main.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.dogdashboard.ui.DogDashboardFragment;
import com.talk2tail.dogvaccination.ui.DogVaccinationFragment;
import com.talk2tail.main.presenter.MainPresenter;
import com.talk2tail.main.view.MainView;
import com.talk2tail.ownerdashboard.ui.OwnerDashboardFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    public static Intent getMainIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @BindView(R.id.owner_dashboard_fab)
    protected FloatingActionButton fab;

    @InjectPresenter
    MainPresenter presenter;

    @Inject
    protected NavigatorHolder navigatorHolder;

    private Navigator navigator = new SupportAppNavigator(this, R.id.main_container);

    private Unbinder unbinder;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            final Fragment fragment;
            switch (item.getItemId()) {
                case R.id.tracker_menu_item:
                    fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                    if (!(fragment instanceof OwnerDashboardFragment)) {
                        presenter.goToOwnerDashboard();
                    }
                    return true;
                case R.id.diary_menu_item:
                    fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                    if (!(fragment instanceof DogDashboardFragment)) {
                        presenter.goToDogDashboard();
                    }
                    return true;
                case R.id.helpful_menu_item:
                    fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                    if (!(fragment instanceof DogVaccinationFragment)) {
                        presenter.goToDogVaccination();
                    }
                    return true;
                case R.id.contacts_menu_item:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        final BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @ProvidePresenter
    protected MainPresenter createPresenter() {
        final MainPresenter mainPresenter = new MainPresenter();
        App.getInstance().getAppComponent().inject(mainPresenter);
        return mainPresenter;
    }

    @Override
    public void onBackPressed() {
        final Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (fragment instanceof BackButtonListener && ((BackButtonListener) fragment).backClick()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_singledog) {
            presenter.goToSingleScreen();
            return true;
        }
        if (id == R.id.menu_multidog) {
            presenter.goToMultidogScreen();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.owner_dashboard_fab)
    protected void fabClick() {
        presenter.onFabClick();
    }

}
