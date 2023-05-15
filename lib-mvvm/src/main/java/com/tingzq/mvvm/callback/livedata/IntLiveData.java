package com.tingzq.mvvm.callback.livedata;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public class IntLiveData extends MutableLiveData<Integer> {

    @Nullable
    @Override
    public Integer getValue() {
        return super.getValue() != null ?super.getValue() : 0;
    }
}
