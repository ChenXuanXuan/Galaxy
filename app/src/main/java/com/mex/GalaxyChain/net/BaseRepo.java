package com.mex.GalaxyChain.net;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by LSJ on 17/3/25.
 */

public abstract class BaseRepo<T> {
    protected HttpClient client;
    protected T mService;
    private Class<T> mClassType;

    protected BaseRepo(){
        client=HttpClient.getInstance();
        Type type;
        Class clazz=getClass();
        do{
            type=clazz.getGenericSuperclass();
            clazz=getClass().getSuperclass();
        }while (!(type instanceof ParameterizedType));
        mClassType = (Class<T>) (((ParameterizedType)type).getActualTypeArguments()[0]);
    }
    public T getService() {
        if(mService==null){
            mService=client.getRetrofit().create(mClassType);
        }
        return mService;
    }
}
