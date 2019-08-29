package com.talk2tail.ownerdashboard.ui;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.talk2tail.R;
import com.talk2tail.common.model.IImageLoader;
import com.talk2tail.common.ui.CircleImageView;
import com.talk2tail.ownerdashboard.view.IDogItemView;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class DogItemView extends LinearLayout implements IDogItemView {

    private Unbinder unbinder;

    private int pos;

    @Inject
    @Named("Glide")
    protected IImageLoader<ImageView> imageLoader;

    @BindView(R.id.dog_name)
    TextView dogNameTextView;

    @BindView(R.id.dog_age)
    TextView dogAgeTextView;

    @BindView(R.id.dog_weight)
    TextView dogWeightTextView;

    @BindView(R.id.dog_photo)
    CircleImageView dogImageView;

    @BindView(R.id.dog_circle_first)
    CircleImageView firstCircle;

    @BindView(R.id.dog_circle_second)
    CircleImageView secondCircle;

    @BindView(R.id.dog_circle_third)
    CircleImageView thirdCircle;

    public DogItemView(Context context, int pos) {
        super(context);
        this.pos = pos;

        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vi.inflate(R.layout.dog_item, this);
        setGravity(CENTER);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//        params.setGravity(CENTER);
        int marginInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
                        .getDisplayMetrics());
        params.setMargins(marginInDp, marginInDp, marginInDp, marginInDp);
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.width = 0;
        params.height = WRAP_CONTENT;

        this.setLayoutParams(params);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public int getPos() {
        return pos;
    }

    @Override
    public void setPhoto(String url) {
//        imageLoader.loadInto(url, dogImageView);  //TODO: Glide меняет расположение
    }

    @Override
    public void setName(String dogName) {
        dogNameTextView.setText(dogName);
    }

    @Override
    public void setAge(String age) {
        dogAgeTextView.setText(age);
    }

    @Override
    public void setWeight(String weight) {
        dogWeightTextView.setText(weight);
    }

    @Override
    public void setFirstCircleColor(int color) {
        firstCircle.setBackgroundColor(color);
    }

    @Override
    public void setSecondCircleColor(int color) {
        secondCircle.setBackgroundColor(color);
    }

    @Override
    public void setThirdCircleColor(int color) {
        thirdCircle.setBackgroundColor(color);
    }

}
