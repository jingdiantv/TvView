package com.tingzq.mvvm.callback.databind;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;

public class FloatObservableField extends ObservableField<Float> {


    @Nullable
    @Override
    public Float get() {

        Float value = super.get();
        return value != null ? value : 0f;
    }
}
