package com.demorjjavaandlambdaexpression.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.demorjjavaandlambdaexpression.Balance;
import com.demorjjavaandlambdaexpression.R;
import com.demorjjavaandlambdaexpression.adapter.AdapterSelectAmountType;
import com.demorjjavaandlambdaexpression.baseclasses.BaseActivity;
import com.demorjjavaandlambdaexpression.baseclasses.MyEditTextDatePicker;
import com.demorjjavaandlambdaexpression.database.Query;
import com.demorjjavaandlambdaexpression.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddFutureIncome extends BaseActivity {

    @BindView(R.id.inputDate)
    EditText inputDate;
    @BindView(R.id.inputAmount)
    EditText inputAmount;
    @BindView(R.id.etIncomeDescription)
    EditText etIncomeDescription;
    @BindView(R.id.tvSave)
    TextView tvSave;
    @BindView(R.id.tvSetAlarm)
    TextView tvSetAlarm;
    @BindView(R.id.spinner)
    Spinner spinner;
    private AdapterSelectAmountType adapterSelectAmountType;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_future_income);
        ButterKnife.bind(this);
        setSpinnerData();

    }

    public void showDatePickerDialog(View view) {
        new MyEditTextDatePicker(AddFutureIncome.this, R.id.inputDate);
    }

    private void setSpinnerData() {

        String[] amountType = getResources().getStringArray(R.array.amount_type);
        if (amountType != null && amountType.length > 0) {
            adapterSelectAmountType = new AdapterSelectAmountType(this, amountType);
            spinner.setAdapter(adapterSelectAmountType);
        }


    }


    @OnClick({R.id.tvSetAlarm, R.id.tvSave})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tvSetAlarm:

                break;
            case R.id.tvSave: {

                int type = spinner.getSelectedItemPosition();
                if (AppUtils.getText(inputAmount).isEmpty()) {
                    AppUtils.showValidation(this, "Please Enter Amount ");
                    return;
                }
                if (type == 0) {
                    AppUtils.showValidation(this, "Please Select Amount Type");
                } else {
                    boolean success = Query.futureIncomeTransaction(AppUtils.getText(inputAmount), type, AppUtils.getText(etIncomeDescription), AppUtils.getText(inputDate));
                    if (success) {
                        AppUtils.showValidation(this, "Your TransactionTable Add Successfully");
                        etIncomeDescription.setText("");
                        inputAmount.setText("");
                        spinner.setSelection(0);
                        inputDate.setText("");
                        EventBus.getDefault().postSticky(new Balance(Query.totalAmountCash(), Query.totalAmountCard()));
                    }
                }

            }
            default:
                break;

        }
    }

}
