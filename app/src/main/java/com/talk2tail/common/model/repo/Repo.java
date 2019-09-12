package com.talk2tail.common.model.repo;

import com.talk2tail.common.model.INetworkStatus;
import com.talk2tail.common.model.api.IDataSource;
import com.talk2tail.common.model.entity.api.DogShortResponse;
import com.talk2tail.common.model.entity.api.LoginUser;
import com.talk2tail.common.model.entity.api.LoginUserResponse;
import com.talk2tail.common.model.entity.api.RegisterUser;
import com.talk2tail.common.model.entity.api.RegisterUserResponse;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class Repo implements IRepo {

    private IDataSource dataSource;

    private INetworkStatus networkStatus;

    public Repo(IDataSource dataSource, INetworkStatus networkStatus) {
        this.dataSource = dataSource;
        this.networkStatus = networkStatus;
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
    public Single<List<DogShortResponse>> getDogsShort(String token) {
        if (networkStatus.isOnline()) {
            return dataSource.getDogsShort(token).subscribeOn(Schedulers.io());
        }
        //TODO: пока приложение полностью on-line, в будущем нужно сделать возможным работу off-line
        return null;
    }
}
