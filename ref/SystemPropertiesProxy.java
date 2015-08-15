package com.example.basegl;
import java.lang.reflect.Method;

import android.content.Context;
import android.util.Log;

/**
 * @author Viking.Den
 *
 * @email vikingden@live.com
 *
 * @time 2014-7-17
 */
public class SystemPropertiesProxy
{
    private static final String TAG = "SystemPropertiesProxy";
    private static Method getLongMethod = null;
    private static Method getBooleanMethod = null;
    private static Method getIntMethod = null;
    
     public static int getInt(final String key, final int def) {
         try {
             if (getIntMethod == null) {
                 getIntMethod = Class.forName("android.os.SystemProperties")
                         .getMethod("getInt", String.class, int.class);
             }
             return ((Integer) getLongMethod.invoke(null, key, def)).intValue();
         } catch (Exception e) {
             Log.e(TAG, "Platform error: " + e.toString());
             return def;
         }
     }
       
    public static long getLong(final String key, final long def) {
        try {
            if (getLongMethod == null) {
                getLongMethod = Class.forName("android.os.SystemProperties")
                        .getMethod("getLong", String.class, long.class);
            }
            
            return ((Long) getLongMethod.invoke(null, key, def)).longValue();
        } catch (Exception e) {
            Log.e(TAG, "Platform error: " + e.toString());
            return def;
        }
    }
    
    public static boolean getBoolean(final String key, final boolean def) {
        try {
            if (getBooleanMethod == null) {
                getBooleanMethod = Class.forName("android.os.SystemProperties")
                        .getMethod("getBoolean", String.class,boolean.class);
            }
            return (Boolean)getBooleanMethod.invoke(null, key, def);
        } catch (Exception e) {
            Log.e(TAG, "Platform error: " + e.toString());
            return def;
        }
    }
}