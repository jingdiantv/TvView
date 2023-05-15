package com.tingzq.mvvm.callback.databind;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;

public class DoubleObservableField extends ObservableField<Double> {


    @Nullable
    @Override
    public Double get() {
        Double value = super.get();
        return value != null ? value : 0.0;
    }
}
