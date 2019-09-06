package com.talk2tail.common.model.repo;

import com.talk2tail.common.model.entity.api.LoginUser;
import com.talk2tail.common.model.entity.api.LoginUserResponse;
import com.talk2tail.common.model.entity.api.RegisterUser;
import com.talk2tail.common.model.entity.api.RegisterUserResponse;

import io.reactivex.Single;

public interface IRepo {

    Single<RegisterUserResponse> registerUser(RegisterUser registerUser);

    Single<LoginUserResponse> loginUser(LoginUser loginUser);

}