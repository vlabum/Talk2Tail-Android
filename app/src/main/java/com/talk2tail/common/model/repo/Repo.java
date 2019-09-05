package com.talk2tail.common.model.repo;

import com.talk2tail.common.model.INetworkStatus;
import com.talk2tail.common.model.api.IDataSource;
import com.talk2tail.common.model.api.RegisterUser;
import com.talk2tail.common.model.entity.IRegisterUser;
import com.talk2tail.common.model.entity.IRegisterUserResponse;

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
    public Single<IRegisterUserResponse> registerUser(IRegisterUser registerUser) {
        if (networkStatus.isOnline() && registerUser instanceof RegisterUser) {
            return dataSource.restAuthLogin((RegisterUser) registerUser).subscribeOn(Schedulers.io())
                    .map(registerUserResponse -> registerUserResponse);
        }
        return null;
    }

}
