package com.talk2tail.userprofile.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.model.IImageLoader;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.common.ui.CircleImageView;
import com.talk2tail.userprofile.presenter.UserProfilePresenter;
import com.talk2tail.userprofile.view.UserProfileView;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UserProfileFragment extends MvpAppCompatFragment implements UserProfileView, BackButtonListener {

    private View view;
    private Unbinder unbinder;

    @Inject
    @Named("Glide")
    protected IImageLoader<ImageView> imageLoader;

    @InjectPresenter
    UserProfilePresenter presenter;

    @BindView(R.id.user_photo)
    CircleImageView userPhotoView;

    @BindView(R.id.user_name)
    TextView userName;

    @BindView(R.id.user_city)
    TextView userCity;

    @BindView(R.id.user_phone)
    TextView userPhone;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new UserProfileFragment();
    }

    @ProvidePresenter
    protected UserProfilePresenter createPresenter() {
        final UserProfilePresenter userProfilePresenter = new UserProfilePresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(userProfilePresenter);
        return userProfilePresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Boolean backClick() {
        presenter.backClick();
        return true;
    }

    @Override
    public void setPhoto(String url) {
        imageLoader.loadInto(url, userPhotoView);
    }

    @Override
    public void setName(String userName) {
        this.userName.setText(userName);
    }

    @Override
    public void setCity(String userCity) {
        this.userCity.setText(userCity);
    }

    @Override
    public void setPhone(String userPhone) {
        this.userPhone.setText(userPhone);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
