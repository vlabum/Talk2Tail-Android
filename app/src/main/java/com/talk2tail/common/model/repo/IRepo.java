package com.talk2tail.common.model.repo;

import com.talk2tail.common.model.entity.IRegisterUser;
import com.talk2tail.common.model.entity.IRegisterUserResponse;

import io.reactivex.Single;

public interface IRepo {
    Single<IRegisterUserResponse> registerUser(IRegisterUser registerUser);
}
