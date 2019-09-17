package com.talk2tail.common.model.repo;

import com.talk2tail.common.AppConstants;
import com.talk2tail.common.model.INetworkStatus;
import com.talk2tail.common.model.api.IDataSource;
import com.talk2tail.common.model.api.dto.UserInfoFull;
import com.talk2tail.common.model.entity.api.BreedColorsResponse;
import com.talk2tail.common.model.entity.api.DogAddRequest;
import com.talk2tail.common.model.entity.api.LoginUser;
import com.talk2tail.common.model.entity.api.LoginUserResponse;
import com.talk2tail.common.model.entity.api.RegisterUser;
import com.talk2tail.common.model.entity.api.RegisterUserResponse;
import com.talk2tail.common.model.entity.dto.Breed;
import com.talk2tail.common.model.entity.dto.DogFull;
import com.talk2tail.common.model.entity.dto.DogShort;
import com.talk2tail.common.model.entity.dto.IDog;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class Repo implements IRepo {

    private IDataSource dataSource;

    private INetworkStatus networkStatus;

    private String token;

    public Repo(IDataSource dataSource, INetworkStatus networkStatus) {
        this.dataSource = dataSource;
        this.networkStatus = networkStatus;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = AppConstants.PREFIX_TOKEN + token;
    }

    @Override
    public Single<RegisterUserResponse> registerUser(RegisterUser registerUser) {
        if (networkStatus.isOnline()) {
            return dataSource.restAuthRegistration(registerUser).subscribeOn(Schedulers.io());
        }
        //TODO: пока приложение полностью on-line, в будущем нужно сделать возможным работу off-line
        return null;
    }

    @Override
    public Single<LoginUserResponse> loginUser(LoginUser loginUser) {
        if (networkStatus.isOnline()) {
            return dataSource.restAuthLogin(loginUser).subscribeOn(Schedulers.io());
        }
        //TODO: пока приложение полностью on-line, в будущем нужно сделать возможным работу off-line
        return null;
    }

    @Override
    public Single<List<DogShort>> getDogsShort(String token) {
        if (networkStatus.isOnline()) {
            return dataSource.getDogsShort(token).subscribeOn(Schedulers.io());
        }
        //TODO: пока приложение полностью on-line, в будущем нужно сделать возможным работу off-line
        return null;
    }

    @Override
    public Single<List<Breed>> getBreeds(String token) {
        if (networkStatus.isOnline()) {
            return dataSource.getBreeds(token).subscribeOn(Schedulers.io());
        }
        //TODO: пока приложение полностью on-line, в будущем нужно сделать возможным работу off-line
        return null;
    }

    @Override
    public Single<List<BreedColorsResponse>> getBreedColors(String token, int id) {
        if (networkStatus.isOnline()) {
            return dataSource.getBreedColors(token, id).subscribeOn(Schedulers.io());
        }
        //TODO: пока приложение полностью on-line, в будущем нужно сделать возможным работу off-line
        return null;
    }

    @Override
    public Single<DogFull> createDog(String token, IDog dog) {
        if (networkStatus.isOnline()) {
            return dataSource.createDog(token, new DogAddRequest(dog)).subscribeOn(Schedulers.io());
        }
        //TODO: пока приложение полностью on-line, в будущем нужно сделать возможным работу off-line
        return null;
    }

    public Single<List<UserInfoFull>> getUserFull(String token) {
        if (networkStatus.isOnline()) {
            return dataSource.getUserFull(token).subscribeOn(Schedulers.io());
        }
        //TODO: пока приложение полностью on-line, в будущем нужно сделать возможным работу off-line
        return null;
    }
}
