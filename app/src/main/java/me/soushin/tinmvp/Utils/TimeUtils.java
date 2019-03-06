package me.soushin.tinmvp.Utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换类
 *
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-06-23  13:19
 */

public class TimeUtils {

    public static String getNowStrTime(){
        long time = System.currentTimeMillis();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }
}
