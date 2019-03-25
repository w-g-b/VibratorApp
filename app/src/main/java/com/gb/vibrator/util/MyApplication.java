package com.gb.vibrator.util;

import android.app.Application;
import android.content.Context;

/**
 * Create by wgb on 2019/3/25.
 */
public class MyApplication extends Application {
  private static Context sContext;

  @Override public void onCreate() {
    super.onCreate();
    sContext = getApplicationContext();
  }

  public static Context getContext() {
    return sContext;
  }
}
