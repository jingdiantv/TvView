package com.tingzq.mvvm.callback.databind;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;

public class StringObservableField extends ObservableField<String> {

    @Nullable
    @Override
    public String get() {
        String value = super.get();
        return value != null ? value : "";
    }
}
