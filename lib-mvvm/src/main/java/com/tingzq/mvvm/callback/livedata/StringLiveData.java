package com.tingzq.mvvm.callback.livedata;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public class StringLiveData extends MutableLiveData<String> {

    @Nullable
    @Override
    public String getValue() {
        return super.getValue() != null ? super.getValue() : "";
    }
}
