package com.bhoomika.expensemanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bhoomika.expensemanager.Balance;
import com.bhoomika.expensemanager.R;
import com.bhoomika.expensemanager.adapter.AdapterSelectAmountType;
import com.bhoomika.expensemanager.baseclasses.BaseFragment;
import com.bhoomika.expensemanager.database.Query;
import com.bhoomika.expensemanager.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExpensesFragment extends BaseFragment {

    @BindView(R.id.input_amount)
    EditText inputAmount;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.etExpenseDescription)
    EditText etExpenseDescription;
    @BindView(R.id.tvSave)
    TextView tvSave;
    private AdapterSelectAmountType adapterSelectAmountType;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expenses, container, false);
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
    public void onClick(View view)
    {
        if(view.getId()==R.id.tvSave)
        {
           int type=spinner.getSelectedItemPosition();
            if(type==0)
            {
                AppUtils.showValidation(getActivity(),"Please Select Amount Type");
            }
            else {
                boolean success = Query.expensesTransaction(AppUtils.getText(inputAmount), type, AppUtils.getText(etExpenseDescription));
                if(success) {
                    AppUtils.showValidation(getActivity(), "Your TransactionTable Add Successfully");
                    etExpenseDescription.setText("");
                    inputAmount.setText("");
                    spinner.setSelection(0);
                    EventBus.getDefault().postSticky(new Balance(Query.totalAmountCash(),Query.totalAmountCard()));
                }
            }

        }
    }

}
