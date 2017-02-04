package com.demorjjavaandlambdaexpression.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by bhoomika on 30/1/17.
 */

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}
