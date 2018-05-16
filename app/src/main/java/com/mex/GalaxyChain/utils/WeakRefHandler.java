package com.mex.GalaxyChain.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;


public abstract class WeakRefHandler<T> extends Handler {
    private final WeakReference<T> wr;

    public WeakRefHandler(T t) {
        this.wr = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        T t = wr.get();
        if (t == null){
            return;
        }
        handleMessage(t,msg);
    }

    protected abstract void handleMessage(T t, Message msg);
}
