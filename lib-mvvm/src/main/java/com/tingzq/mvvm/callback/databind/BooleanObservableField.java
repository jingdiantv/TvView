package com.tingzq.mvvm.callback.databind;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;

public final class BooleanObservableField extends ObservableField<Boolean> {


    @Nullable
    @Override
    public Boolean get() {

        Boolean value = super.get();
        return value != null ? value : false;
    }
}
