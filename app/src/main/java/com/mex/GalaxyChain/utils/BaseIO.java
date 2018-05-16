package com.mex.GalaxyChain.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by albert on 4/16/15.
 */
public class BaseIO {
    private BaseIO(){}

    public static void silentClose(Closeable stream) {
        if (stream == null) {
            return;
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
