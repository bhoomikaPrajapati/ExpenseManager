package com.demorjjavaandlambdaexpression;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by bhoomika on 30/1/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
        // This instantiates DBFlow
       FlowManager.init(new FlowConfig.Builder(this).build());


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Chunkfive.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
}
