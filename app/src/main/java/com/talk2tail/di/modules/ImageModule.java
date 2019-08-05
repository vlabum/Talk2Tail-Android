package com.talk2tail.di.modules;

import android.widget.ImageView;

import com.talk2tail.common.model.IImageLoader;
import com.talk2tail.common.ui.GlideImageLoader;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Named("Glide")
    @Provides
    IImageLoader<ImageView> imageLoader() {
        return new GlideImageLoader();
    }

}
