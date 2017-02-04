package com.demorjjavaandlambdaexpression.view;

import com.demorjjavaandlambdaexpression.baseclasses.BaseView;

/**
 Created by bhoomika on 27/1/17.
 */

public interface IncomeView extends BaseView {
    public void onSuccessAddIncome(String message);

    public void onFailedAddIncome(String message);
}
