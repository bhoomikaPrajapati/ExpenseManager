package com.demorjjavaandlambdaexpression.database;

import com.raizlabs.android.dbflow.annotation.*;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by bhoomika on 30/1/17.
 */

@com.raizlabs.android.dbflow.annotation.Table(database = MyDatabase.class, allFields = true)
public class TransactionTable extends BaseModel {

    @PrimaryKey(autoincrement = true)
    int tId;


    long amount;

    String description;

    int amountType;

    String sign;

    Date date;


    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountType() {
        return amountType;
    }

    public void setAmountType(int amountType) {
        this.amountType = amountType;
    }


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
