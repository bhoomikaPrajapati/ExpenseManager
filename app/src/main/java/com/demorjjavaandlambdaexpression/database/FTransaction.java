package com.demorjjavaandlambdaexpression.database;

import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by bhoomika on 1/2/17.
 */
@com.raizlabs.android.dbflow.annotation.Table(database = MyDatabase.class, allFields = true)
public class FTransaction extends BaseModel {
    @PrimaryKey(autoincrement = true)
    int ftId;
    long amount;
    String description;
    String sign;
    int amounttype;
    String f_date;

    public int getFtId() {
        return ftId;
    }

    public void setFtId(int ftId) {
        this.ftId = ftId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getAmounttype() {
        return amounttype;
    }

    public void setAmounttype(int amounttype) {
        this.amounttype = amounttype;
    }

    public String getF_date() {
        return f_date;
    }

    public void setF_date(String f_date) {
        this.f_date = f_date;
    }


}
