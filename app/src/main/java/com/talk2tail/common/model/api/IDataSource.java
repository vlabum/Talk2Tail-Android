package com.talk2tail.common.model.api;

import com.talk2tail.common.model.api.dto.UserInfoFull;
import com.talk2tail.common.model.entity.api.DogShortResponse;
import com.talk2tail.common.model.entity.api.LoginUser;
import com.talk2tail.common.model.entity.api.LoginUserResponse;
import com.talk2tail.common.model.entity.api.RegisterUser;
import com.talk2tail.common.model.entity.api.RegisterUserResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IDataSource {

    @POST("/api/v1/rest-auth/registration/")
    Single<RegisterUserResponse> restAuthRegistration(@Body RegisterUser registerUser);

    @POST("/api/v1/rest-auth/login/")
    Single<LoginUserResponse> restAuthLogin(@Body LoginUser loginUser);

    @GET("/api/v1/user/dogs/")
    Single<List<DogShortResponse>> getDogsShort(@Header("authorization") String token);

    @GET("/api/v1/rest-auth/user/show")
    Single<List<UserInfoFull>> getUserFull(@Header("Authorization") String token);

}
