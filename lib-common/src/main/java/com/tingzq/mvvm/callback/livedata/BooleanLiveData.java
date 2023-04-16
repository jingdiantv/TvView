package com.tingzq.mvvm.callback.livedata;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public class BooleanLiveData extends MutableLiveData<Boolean> {

    @Nullable
    @Override
    public Boolean getValue() {
        return super.getValue() != null && super.getValue();
    }
}
