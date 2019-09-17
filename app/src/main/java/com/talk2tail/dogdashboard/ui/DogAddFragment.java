package com.talk2tail.dogdashboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.model.entity.dto.Breed;
import com.talk2tail.common.model.entity.dto.TalkToTailColor;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.dogdashboard.presenter.DogAddPresenter;
import com.talk2tail.dogdashboard.view.DogAddView;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DogAddFragment extends MvpAppCompatFragment
        implements View.OnClickListener, DogAddView, BackButtonListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.add_dog_equals_names_cb)
    CheckBox equalsNamesView;

    @BindView(R.id.add_dog_is_sterilized_cb)
    CheckBox isSterilizedView;

    @BindView(R.id.add_dog_photo_iv)
    ImageView photoView;

    @BindView(R.id.add_dog_add_photo_iv)
    ImageView addPhotoView;

    @BindView(R.id.add_dog_sex_rg)
    RadioGroup sexRgView;

    @BindView(R.id.add_dog_sex_female)
    RadioButton sexFemaleView;

    @BindView(R.id.add_dog_sex_male)
    RadioButton sexMaleView;

    @BindView(R.id.add_dog_breeds_s)
    Spinner breedView;

    @BindView(R.id.add_dog_colors_s)
    Spinner colorView;

    @BindView(R.id.add_dog_name_te)
    TextView nameView;

    @BindView(R.id.add_dog_full_name_te)
    TextView fullNameView;

    @BindView(R.id.add_dog_birthday_te)
    TextView birthday;

    @BindView(R.id.add_dog_pedigree_te)
    TextView pedigreeView;

    @BindView(R.id.add_dog_stigma_te)
    TextView stigmaView;

    @BindView(R.id.add_dog_chip_te)
    TextView chipView;

    @BindView(R.id.save_btn)
    TextView saveBtn;

    @InjectPresenter
    DogAddPresenter presenter;

    private View view;

    private Unbinder unbinder;

    private String sex;

    private Breed breed;

    private TalkToTailColor color;

    public DogAddFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new DogAddFragment();
    }

    @ProvidePresenter
    protected DogAddPresenter createPresenter() {
        final DogAddPresenter dogAddPresenter = new DogAddPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(dogAddPresenter);
        return dogAddPresenter;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dog_add, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (sexFemaleView.isChecked()) {
            sex = "F";
        }
        if (sexMaleView.isChecked()) {
            sex = "M";
        }
        sexRgView.setOnCheckedChangeListener(this);
        breedView.setOnItemSelectedListener(this);
        saveBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Boolean backClick() {
        presenter.backClick();
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.add_dog_sex_female:
                sex = "F";
                break;
            case R.id.add_dog_sex_male:
                sex = "M";
                break;
        }
    }

    public void updateBreedColorList() {
        ArrayAdapter<TalkToTailColor> adapter;
        adapter = new ArrayAdapter<TalkToTailColor>(App.getInstance().getApplicationContext(),
                android.R.layout.simple_spinner_item, presenter.getColorBreeds());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorView.setAdapter(adapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(App.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateBreedList() {
        ArrayAdapter<Breed> adapter;
        adapter = new ArrayAdapter<Breed>(App.getInstance().getApplicationContext(),
                android.R.layout.simple_spinner_item, presenter.getDogBreeds());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breedView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.add_dog_breeds_s:
                breed = (Breed) parent.getSelectedItem();
                presenter.getBreedColors(breed.getId());
                break;
            case R.id.add_dog_colors_s:
                color = (TalkToTailColor) parent.getSelectedItem();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save_btn) {
            breed = (Breed) breedView.getSelectedItem();
            color = (TalkToTailColor) colorView.getSelectedItem();
            final int sterilized = isSterilizedView.isChecked() ? 1 : 0;
            presenter.addNewDog(
                    nameView.getText().toString(), fullNameView.getText().toString(), null,
                    sex, sterilized, new Date(), pedigreeView.getText().toString(),
                    chipView.getText().toString(), stigmaView.getText().toString(), breed, color);
        }
    }
}
