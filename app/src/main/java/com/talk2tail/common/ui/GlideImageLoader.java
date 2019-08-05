package com.talk2tail.common.ui;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.talk2tail.common.model.IImageLoader;

public class GlideImageLoader implements IImageLoader<ImageView> {

    @Override
    public void loadInto(String url, ImageView container) {
        Glide.with(container.getContext())
                .load(url)
                .into(container);
    }

    @Override
    public void loadInto(String url, ImageView container, int corners) {
        final MultiTransformation<Bitmap> multi = new MultiTransformation<>(
                new RoundedCorners(corners)
        );

        Glide.with(container.getContext())
                .load(url)
                .apply(RequestOptions.bitmapTransform(multi))
                .into(container);
    }
}
