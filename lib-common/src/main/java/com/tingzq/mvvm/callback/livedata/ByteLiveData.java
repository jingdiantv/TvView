package com.tingzq.mvvm.callback.livedata;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public class ByteLiveData extends MutableLiveData<Byte> {

    @Nullable
    @Override
    public Byte getValue() {
        return super.getValue() != null ? super.getValue() : 0;
    }
}
