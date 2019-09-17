package com.talk2tail.main.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainView, View.OnClickListener {

    private static long ANIMATION_DURATION = 130;
    private static long SHOW_HIDE_ANIMATION_DURATION = 250;
    @BindView(R.id.transparent_v)
    protected View transparent;

    @BindView(R.id.owner_dashboard_fab)
    protected FloatingActionButton fab;
    @BindView(R.id.fab_menu_cv)
    protected CardView fab_menu_cv;
    @BindView(R.id.add_dog_fab_menu_cv)
    protected CardView addDog;
    @BindView(R.id.add_audio_fab_menu_cv)
    protected CardView addAudio;
    @BindView(R.id.add_event_fab_menu_cv)
    protected CardView addEvent;
    @BindView(R.id.add_task_fab_menu_cv)
    protected CardView addTask;
    private boolean fabIsPressed = false;

    @InjectPresenter
    MainPresenter presenter;

    @Inject
    protected NavigatorHolder navigatorHolder;

    private Navigator navigator = new SupportAppNavigator(this, R.id.main_container);

    private Unbinder unbinder;

    public static Intent getMainIntent(Context context, int countPets) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("countPets", countPets);
        return intent;
    }

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
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fabBackground)));
        transparent.setOnClickListener(this);
        fab_menu_cv.setOnClickListener(this);
        addDog.setOnClickListener(this);
        addAudio.setOnClickListener(this);
        addEvent.setOnClickListener(this);
        addTask.setOnClickListener(this);
        int countPets = getIntent().getIntExtra("countPets", 0);
        presenter.setCountPets(countPets);
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
        switch (item.getItemId()) {
            case R.id.menu_singledog:
                presenter.goToOwnerDashOne();
                return true;
            case R.id.menu_multidog:
                presenter.goToMultiDogScreen();
                return true;
            case R.id.menu_nodog:
                presenter.goToOwnerDashEmpty();
                return true;
            case R.id.menu_user_profile:
                presenter.goToUserProfileScreen();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.owner_dashboard_fab)
    protected void fabClick() {
        presenter.onFabClick();
        if (!fabIsPressed) {
            showFabMenu();
        } else {
            hideFabMenu();
        }
    }

    private void hideFabMenu() {
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fabBackground)));
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_add_black_24dp));
        fab.setSupportImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.fabItem)));
        fab_menu_cv.animate().alpha(0).setDuration(SHOW_HIDE_ANIMATION_DURATION).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                fab_menu_cv.setVisibility(View.GONE);
                fab_menu_cv.animate().setListener(null);
            }
        });
        transparent.setVisibility(View.GONE);
        // уберем пункты меню вниз, чтобы их не было видно
        // высота меню равна высоте заголовка + высота всех четырех пунктов
        float translationY = getResources().getDimension(R.dimen.fabMenuCaptionHeight) +
                4 * getResources().getDimension(R.dimen.fabMenuItemHeight);
        addDog.setTranslationY(translationY);
        addAudio.setTranslationY(translationY);
        addEvent.setTranslationY(translationY);
        addTask.setTranslationY(translationY);
        fabIsPressed = false;
    }

    private void showFabMenu() {
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fabBackgroundMenu)));
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_close_black_24dp));
        fab.setSupportImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.fabBackground)));
        fab_menu_cv.setVisibility(View.VISIBLE);
        transparent.setVisibility(View.VISIBLE);
        fab_menu_cv.animate().alpha(1.0f).setDuration(SHOW_HIDE_ANIMATION_DURATION);
        // каждый пункт меню поднимем на свою высоту.
        float startPos = getResources().getDimension(R.dimen.fabMenuCaptionHeight);
        float heightItem = getResources().getDimension(R.dimen.fabMenuItemHeight);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(ANIMATION_DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.add(ObjectAnimator.ofFloat(addDog, "translationY", (startPos)));
        animators.add(ObjectAnimator.ofFloat(addAudio, "translationY", (startPos + heightItem)));
        animators.add(ObjectAnimator.ofFloat(addEvent, "translationY", (startPos + 2 * heightItem)));
        animators.add(ObjectAnimator.ofFloat(addTask, "translationY", (startPos + 3 * heightItem)));
        animatorSet.playSequentially(animators);
        animatorSet.start();
        fabIsPressed = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.transparent_v:
                hideFabMenu();
                break;
            case R.id.add_dog_fab_menu_cv:
                presenter.goToDogAddScreen();
                break;
            case R.id.add_audio_fab_menu_cv:
                Toast.makeText(this, "Add Audio", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_event_fab_menu_cv:
                Toast.makeText(this, "Add Event", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_task_fab_menu_cv:
                Toast.makeText(this, "Add Task", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "aaaaaaaaaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
        }
        hideFabMenu();
    }
}
