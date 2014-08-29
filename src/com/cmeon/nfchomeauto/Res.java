package com.cmeon.nfchomeauto;

import android.util.Log;

import java.lang.reflect.Field;

public class Res {
    public static int getResourceId(String drawableId) {
        try {
            Class res = R.drawable.class;
            Field field = res.getField(drawableId);
            return field.getInt(null);
        } catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
            return -1;
        }
    }
    public static Integer[] getResourceId(String[] drawableId) {
        try {
            Class res = R.drawable.class;
            Integer[] r = new Integer[drawableId.length];
            for (int i=0; i<drawableId.length; i++) {
                Field field = res.getField(drawableId[i]);
                r[i] = field.getInt(null);
            }
            return r;
        } catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
            return new Integer[0];
        }
    }
}
