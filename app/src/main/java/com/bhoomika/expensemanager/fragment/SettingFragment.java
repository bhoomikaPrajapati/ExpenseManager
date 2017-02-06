package com.bhoomika.expensemanager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.activity.AddFutureExpenses;
import com.bhoomika.expensemanager.activity.AddFutureIncome;
import com.bhoomika.expensemanager.baseclasses.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bhoomika on 31/1/17.
 */

public class SettingFragment extends BaseFragment {

    @BindView(R.id.tvFutureIncome)
    TextView tvFutureIncome;
    @BindView(R.id.tvFutureExpenses)
    TextView tvFutureExpenses;
    @BindView(R.id.tvFeedback)
    TextView tvFeedback;
    @BindView(R.id.tvShareApplication)
    TextView tvShareApplication;
    @BindView(R.id.tvInviteFriend)
    TextView tvInviteFriend;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
        setLitener();


    }

    private void setLitener() {

    }

    private void init() {

    }

    @OnClick({R.id.tvFutureIncome, R.id.tvFutureExpenses, R.id.tvInviteFriend})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tvInviteFriend:

                break;

            case R.id.tvFutureExpenses:
                startActivity(new Intent(getActivity(), AddFutureExpenses.class));
                break;

            case R.id.tvFutureIncome:

                startActivity(new Intent(getActivity(), AddFutureIncome.class));

                break;

            default:
                break;

        }
    }


}

