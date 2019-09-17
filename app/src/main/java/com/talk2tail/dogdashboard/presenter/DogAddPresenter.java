package com.talk2tail.dogdashboard.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.talk2tail.common.model.DogWeighData;
import com.talk2tail.common.model.api.SingleCallbackWrapperAddDog;
import com.talk2tail.common.model.entity.TalkToTailEvent;
import com.talk2tail.common.model.entity.api.BreedColorsResponse;
import com.talk2tail.common.model.entity.dto.Breed;
import com.talk2tail.common.model.entity.dto.DogFull;
import com.talk2tail.common.model.entity.dto.TalkToTailColor;
import com.talk2tail.common.model.repo.IRepo;
import com.talk2tail.dogdashboard.view.DogAddView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import lombok.Getter;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class DogAddPresenter extends MvpPresenter<DogAddView> {

    @Getter
    final List<TalkToTailEvent> events = new ArrayList<>();
    @Inject
    protected Router router;
    @Inject
    protected IRepo repo;
    @Getter
    private List<TalkToTailColor> colorBreeds;
    @Getter
    private List<Breed> dogBreeds;
    private Scheduler mainThreadScheduler;
    private LineGraphSeries<DataPoint> series;
    private DogWeighData dogWeighData = new DogWeighData();

    public DogAddPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getBreeds();
        getBreedColors(1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void backClick() {
        router.exit();
    }

    public void getBreeds() {
        SingleCallbackWrapperAddDog<List<Breed>> breeds = repo.getBreeds(repo.getToken())
                .observeOn(mainThreadScheduler)
                .subscribeWith(new SingleCallbackWrapperAddDog<List<Breed>>(getViewState()) {
                    @Override
                    public void onSuccess(List<Breed> breedList) {
                        dogBreeds = breedList;
                        getViewState().updateBreedList();
                    }
                });

    }

    public void getBreedColors(int id) {
        SingleCallbackWrapperAddDog<List<BreedColorsResponse>> colors = repo.getBreedColors(repo.getToken(), id)
                .observeOn(mainThreadScheduler)
                .subscribeWith(new SingleCallbackWrapperAddDog<List<BreedColorsResponse>>(getViewState()) {
                    @Override
                    public void onSuccess(List<BreedColorsResponse> colorList) {
                        colorBreeds = new ArrayList<>();
                        for (BreedColorsResponse b : colorList) {
                            colorBreeds.add(b.getColor());
                        }
                        getViewState().updateBreedColorList();
                    }
                });

    }

    public void addNewDog(
            String shortNickname, String fullNickname, String photo, String gender, int isSterilized,
            Date birthDate, String pedigree, String chip, String stigma, Breed breed, TalkToTailColor color
    ) {
        final DogFull dog = new DogFull(shortNickname, fullNickname, photo, gender, isSterilized,
                birthDate, pedigree, chip, stigma, breed, color);
        SingleCallbackWrapperAddDog<DogFull> dogResp = repo.createDog(repo.getToken(), dog)
                .observeOn(mainThreadScheduler)
                .subscribeWith(new SingleCallbackWrapperAddDog<DogFull>(getViewState()) {
                    @Override
                    public void onSuccess(DogFull dogFull) {
                        getViewState().showErrorMessage("DOG DOBAVLEN");
                    }
                });
    }

}
