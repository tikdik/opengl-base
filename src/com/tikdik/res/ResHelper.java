package com.tikdik.res;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.tikdik.context.GlobalContext;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ResHelper {
    public static String loadFromAssetsFile(String fname) {
        String ret = null;
        try {
            InputStream ins = GlobalContext.gContext.getAssets().open(fname);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int ch = 0;
            while ( (ch = ins.read()) != -1) {
                bos.write(ch);
            }
            byte[] buff = bos.toByteArray();
            bos.close();
            ins.close();
            ret = new String(buff, "UTF-8");
            ret = ret.replace("\\r\\n", "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static Bitmap loadBitmapFromAssetFile(String fname) {
        InputStream is;
        try {
            is = GlobalContext.gContext.getAssets().open(fname);
            int size = is.available(); 
            byte[] buffer = new byte[size]; 
            is.read(buffer, 0, size);
            is.close(); 
            //Check if png or jpg
            BitmapFactory.Options opt = new BitmapFactory.Options(); 
            opt.inDither = false;
            opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return BitmapFactory.decodeByteArray(buffer, 0, size, opt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
