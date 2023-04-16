package com.tingzq.mvvm.callback.databind;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;

public final class ByteObservableField extends ObservableField<Byte> {


    @Nullable
    @Override
    public Byte get() {
        Byte value = super.get();

        return value != null ? value : 0;
    }
}
