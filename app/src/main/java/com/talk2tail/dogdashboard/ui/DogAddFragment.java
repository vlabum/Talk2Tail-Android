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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DogAddFragment extends MvpAppCompatFragment
        implements DogAddView, BackButtonListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.add_dog_equals_names_cb)
    CheckBox equalsNames;
    @BindView(R.id.add_dog_is_sterilized_cb)
    CheckBox isSterilized;
    @BindView(R.id.add_dog_photo_iv)
    ImageView photo;
    @BindView(R.id.add_dog_add_photo_iv)
    ImageView addPhoto;
    @BindView(R.id.add_dog_sex_rg)
    RadioGroup sexRg;
    @BindView(R.id.add_dog_sex_female)
    RadioButton sexFemale;
    @BindView(R.id.add_dog_sex_male)
    RadioButton sexMale;
    @BindView(R.id.add_dog_breeds_s)
    Spinner breed;
    @BindView(R.id.add_dog_colors_s)
    Spinner color;
    @BindView(R.id.add_dog_name_te)
    TextView name;
    @BindView(R.id.add_dog_full_name_te)
    TextView fullName;
    @BindView(R.id.add_dog_birthday_te)
    TextView birthday;
    @BindView(R.id.add_dog_pedigree_te)
    View pedigree;
    @BindView(R.id.add_dog_stigma_te)
    TextView stigma;
    @BindView(R.id.add_dog_chip_te)
    TextView chip;
    @InjectPresenter
    DogAddPresenter presenter;
    private View view;
    private Unbinder unbinder;
    private String sex;

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
        if (sexFemale.isChecked()) {
            sex = "F";
        }
        if (sexMale.isChecked()) {
            sex = "M";
        }
        sexRg.setOnCheckedChangeListener(this);
        breed.setOnItemSelectedListener(this);
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
        color.setAdapter(adapter);
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
        breed.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.add_dog_breeds_s) {
            Breed breed = (Breed) ((Spinner) parent).getSelectedItem();
            presenter.getBreedColors(breed.getId());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
