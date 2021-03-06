package com.demorjjavaandlambdaexpression.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.demorjjavaandlambdaexpression.Balance;
import com.demorjjavaandlambdaexpression.R;
import com.demorjjavaandlambdaexpression.adapter.AdapterSelectAmountType;
import com.demorjjavaandlambdaexpression.baseclasses.BaseFragment;
import com.demorjjavaandlambdaexpression.database.Query;
import com.demorjjavaandlambdaexpression.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class IncomeFragment extends BaseFragment {

    @BindView(R.id.input_amount)
    EditText inputAmount;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.etIncomeResource)
    EditText etIncomeResource;
    @BindView(R.id.tvSave)
    TextView tvSave;
    private AdapterSelectAmountType adapterSelectAmountType;
    private int type;
    private boolean success;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_income, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
        setLitener();
        setSpinnerData();

    }

    private void setSpinnerData() {

        String[] amountType = getResources().getStringArray(R.array.amount_type);
        if (amountType != null && amountType.length > 0) {
            adapterSelectAmountType = new AdapterSelectAmountType(getActivity(), amountType);
            spinner.setAdapter(adapterSelectAmountType);
        }


    }


    private void setLitener() {

    }

    private void init() {


    }

    @OnClick({R.id.tvSave})
    public void onClick(View view) {
        if (view.getId() == R.id.tvSave) {
            type = spinner.getSelectedItemPosition();
            if (type == 0) {
                AppUtils.showValidation(getActivity(), "Please Select Amount Type");
            } else {
                success = Query.incomeTransaction(AppUtils.getText(inputAmount), type, AppUtils.getText(etIncomeResource));
                if (success) {
                    AppUtils.showValidation(getActivity(), "Your TransactionTable Add Successfully");
                    etIncomeResource.setText("");
                    inputAmount.setText("");
                    spinner.setSelection(0);
                    EventBus.getDefault().post(new Balance(Query.totalAmountCash(),Query.totalAmountCard()));
                }
            }

        }
    }

}
