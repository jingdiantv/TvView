package com.tingzq.mvvm.callback.databind;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;

public class IntObservableField extends ObservableField<Integer> {



    @Nullable
    @Override
    public Integer get() {

        Integer value = super.get();

        return value != null ? value : 0;
    }
}
