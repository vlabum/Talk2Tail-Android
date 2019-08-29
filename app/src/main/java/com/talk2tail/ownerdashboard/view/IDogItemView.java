package com.talk2tail.ownerdashboard.view;

public interface IDogItemView {

    int getPos();

    void setPhoto(String url);

    void setName(String dogName);

    void setAge(String age);

    void setWeight(String weight);

    void setFirstCircleColor(int color);

    void setSecondCircleColor(int color);

    void setThirdCircleColor(int color);

}
