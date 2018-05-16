package com.mex.GalaxyChain.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by LSJ on 16/11/13.
 */

public class Serialize {
	public static final String TAG = "Serialize";

	public static String toString(Serializable o) {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream so = new ObjectOutputStream(bo);
			so.writeObject(o);
			so.flush();
			return Base64.encode(bo.toByteArray());
		} catch (Exception e) {
			LNLog.e(TAG, e.getMessage());
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T fromString(String s, Class<T> tClass) {
		try {
			byte b[] = Base64.decode(s.getBytes());
			ByteArrayInputStream bi = new ByteArrayInputStream(b);
			ObjectInputStream si = new ObjectInputStream(bi);
			Object o = si.readObject();
			return tClass.cast(o);
		} catch (Exception e) {
			LNLog.e(TAG,e.getMessage());
			return null;
		}
	}
}
