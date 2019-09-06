package com.talk2tail.common.model.api;

import com.talk2tail.login.view.LoginView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableSingleObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public abstract class SingleCallbackWrapperLogin<T extends Response> extends DisposableSingleObserver<T> {

    private WeakReference<LoginView> weakReference;

    public SingleCallbackWrapperLogin(LoginView view) {
        this.weakReference = new WeakReference<>(view);
    }

    public abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {
        LoginView view = weakReference.get();
        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) e).response().errorBody();
            view.showErrorMessage(getErrorMessage(responseBody));
        } else if (e instanceof SocketTimeoutException) {
            view.showErrorMessage(e.getMessage());
        } else if (e instanceof IOException) {
            view.showErrorMessage(e.getMessage());
        } else {
            view.showErrorMessage(e.getMessage());
        }
    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            //TODO: сделать разбор JSON
            return responseBody.string();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
