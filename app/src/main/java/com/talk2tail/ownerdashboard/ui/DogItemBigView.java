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
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class DogItemBigView extends LinearLayout implements IDogItemView {

    @Inject
    @Named("Glide")
    protected IImageLoader<ImageView> imageLoader;
    @BindView(R.id.dog_photo_big_iv)
    ImageView dogPhoto;
    @BindView(R.id.dog_circle_third_big_civ)
    CircleImageView circle1;
    @BindView(R.id.dog_circle_second_big_civ)
    CircleImageView circle2;
    @BindView(R.id.dog_circle_first_big_civ)
    CircleImageView circle3;
    @BindView(R.id.dog_name_big_tv)
    TextView dogName;
    @BindView(R.id.dog_age_big_tv)
    TextView dogAge;
    @BindView(R.id.dog_weight_big_tv)
    TextView dogWeight;
    @BindView(R.id.weights_big_iv)
    ImageView weightIcon;
    private Unbinder unbinder;


    public DogItemBigView(Context context) {
        super(context);

        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vi.inflate(R.layout.dog_item_big, this);
        setGravity(CENTER);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        int marginInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 0, getResources()
                        .getDisplayMetrics());
        params.setMargins(marginInDp, marginInDp, marginInDp, marginInDp);
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.width = MATCH_PARENT;
        params.height = WRAP_CONTENT;

        this.setLayoutParams(params);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public int getPos() {
        return 0;
    }

    @Override
    public void setPhoto(String url) {
//        imageLoader.loadInto(url, dogImageView);  //TODO: Glide меняет расположение
    }

    @Override
    public void setName(String dogName) {
        this.dogName.setText(dogName);
    }

    @Override
    public void setAge(String age) {
        this.dogAge.setText(age);
    }

    @Override
    public void setWeight(String weight) {
        this.dogWeight.setText(weight);
    }

    @Override
    public void setFirstCircleColor(int color) {
        circle1.setBackgroundColor(color);
    }

    @Override
    public void setSecondCircleColor(int color) {
        circle2.setBackgroundColor(color);
    }

    @Override
    public void setThirdCircleColor(int color) {
        circle3.setBackgroundColor(color);
    }

}
