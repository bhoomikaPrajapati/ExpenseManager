package com.bhoomika.expensemanager.baseclasses;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.bhoomika.expensemanager.activity.HomeActivity;


public class BaseFragment extends Fragment {

    private Dialog progressDialog;

    private boolean disallow;

    public static final String DISALLOW_INIT_TOOLBAR = "disallowInitToolbar";

    private String fragmentTitle;

    public void setFragmentTitle(String title) {
        this.fragmentTitle = title;
    }

    protected BaseActivity homeInstance;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (homeInstance == null)
            homeInstance = (BaseActivity) getActivity();



    }

    public void initArguments(@NonNull Bundle bundle) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public void popFragment() {
        Activity activity = getActivity();
        if (activity instanceof HomeActivity) {
            HomeActivity mainActivity = (HomeActivity) activity;
            mainActivity.popFragment();
        }
    }

}
