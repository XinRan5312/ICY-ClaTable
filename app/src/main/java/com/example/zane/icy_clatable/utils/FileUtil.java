package com.example.zane.icy_clatable.utils;

import android.content.Context;
import android.os.Environment;

import com.example.zane.icy_clatable.app.App;

import java.io.File;

/**
 * Created by Zane on 16/3/14.
 */
public class FileUtil {

    private static Context mApplicationContext = App.getInstance();

    public static File getDiskCacheDir(String uniqueName) {
        String cachePath;
        if(!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {
            cachePath = mApplicationContext.getCacheDir().getPath();
        } else {
            cachePath = mApplicationContext.getExternalCacheDir().getPath();
        }

        return new File(cachePath + File.separator + uniqueName);
    }
}
