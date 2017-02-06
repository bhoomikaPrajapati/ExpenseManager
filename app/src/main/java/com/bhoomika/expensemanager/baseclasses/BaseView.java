package com.bhoomika.expensemanager.baseclasses;

import android.app.Activity;


/**
 * Created by bhoomika on 10/9/16.
 */
public interface BaseView {

  public boolean hasInternet();

  public void showProgress(boolean show);

  public Activity getActivity();

  public void onLogoutSuccess(String message);
}