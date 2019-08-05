package com.talk2tail.common.model;

public interface IImageLoader<T> {

    void loadInto(String url, T container);

    void loadInto(String url, T container, int corners);
}
