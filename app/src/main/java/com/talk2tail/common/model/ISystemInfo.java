package com.talk2tail.common.model;

public interface ISystemInfo {

    int getBuildVersion();

    boolean needToConfigSSL();

    boolean isStreamApiAvailable();
}
