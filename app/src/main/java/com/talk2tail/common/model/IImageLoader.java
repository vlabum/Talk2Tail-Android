package com.talk2tail.common.model;

import android.net.Uri;

public interface IImageLoader<T> {

    void loadInto(String url, T container);

    void loadInto(String url, T container, int corners);

    void loadInto(Uri image, T container, int corders);
}
