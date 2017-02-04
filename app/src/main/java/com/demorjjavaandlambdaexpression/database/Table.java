package com.demorjjavaandlambdaexpression.database;

import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by bhoomika on 1/2/17.
 */


@com.raizlabs.android.dbflow.annotation.Table(database = MyDatabase.class, allFields = true)
public class Table extends BaseModel {
    @PrimaryKey(autoincrement = true)
    int id;

    String name;
}
