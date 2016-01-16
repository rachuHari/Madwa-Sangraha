package com.madwa.sangraha.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by admin on 10-01-2016.
 */
public class Utils {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
