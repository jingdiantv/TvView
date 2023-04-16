package com.tingzq.mvvm.callback.databind;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;

public class ShortObservableField extends ObservableField<Short> {


    @Nullable
    @Override
    public Short get() {
        Short value = super.get();
        return value != null ? value : (short) 0;
    }
}
