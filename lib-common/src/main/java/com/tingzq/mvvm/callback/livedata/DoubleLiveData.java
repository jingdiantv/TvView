package com.tingzq.mvvm.callback.livedata;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public class DoubleLiveData extends MutableLiveData<Double> {

    @Nullable
    @Override
    public Double getValue() {
        return super.getValue() != null ? super.getValue() : 0.0;
    }
}
