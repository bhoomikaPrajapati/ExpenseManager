/*
 * Copyright (c) 2016.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.demorjjavaandlambdaexpression.baseclasses;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public abstract class MVPActivity<P extends BasePresenter<V>, V extends BaseView> extends BaseActivity {

    private P presenter;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView(attachView());
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    public abstract
    @NonNull
    P createPresenter();

    public abstract
    @NonNull
    V attachView();

    public P getPresenter() {
        return presenter;
    }



    public Activity getActivity() {
        return this;
    }
}
