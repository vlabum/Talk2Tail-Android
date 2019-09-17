package com.talk2tail.common.model.repo;

import com.talk2tail.common.model.entity.api.BreedColorsResponse;
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

public interface IRepo {

    String getToken();

    void setToken(String token);

    Single<RegisterUserResponse> registerUser(RegisterUser registerUser);

    Single<LoginUserResponse> loginUser(LoginUser loginUser);

    Single<List<DogShort>> getDogsShort(String token);

    Single<List<Breed>> getBreeds(String token);

    Single<List<BreedColorsResponse>> getBreedColors(String token, int id);

    Single<DogFull> createDog(String token, IDog dogAddRequest);

}
