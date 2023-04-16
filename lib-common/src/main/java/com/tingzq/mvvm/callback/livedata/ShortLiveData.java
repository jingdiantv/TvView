package com.tingzq.mvvm.callback.livedata;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public class ShortLiveData extends MutableLiveData<Short> {

    @Nullable
    @Override
    public Short getValue() {
        return super.getValue() != null ? super.getValue() : 0;
    }
}
