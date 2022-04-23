package com.huoshan.mymusic;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

// 应用获取系统 sdcard 目录读写权限
public class MoveFileFromRaw2Sdcard {
    //权限数组（申请定位）
    private static String[] permissions = new String[]{
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    };
    //返回code
    public static final int OPEN_SET_REQUEST_CODE = 100;
    //如果返回true表示缺少权限
    public static boolean lacksPermission(Activity activity) {
        for (String permission : permissions) {
            //判断是否缺少权限，true=缺少权限
            if(ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED){
                return true;
            }
        }
        return false;
    }
    //调用此方法判断是否拥有权限
    public static void initPermissions( Activity activity) {
        if (lacksPermission(activity)) {//判断是否拥有权限
            //请求权限，第二参数权限String数据，第三个参数是请求码便于在onRequestPermissionsResult 方法中根据code进行判断
            ActivityCompat.requestPermissions(activity, permissions, OPEN_SET_REQUEST_CODE);
        } else {
            //拥有权限执行操作
        }
    }
}
