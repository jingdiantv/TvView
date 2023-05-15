package com.tingzq.mvvm.callback;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.tingzq.mvvm.callback.livedata.event.SingleLiveEvent;

import java.util.Map;

public class UiObservable extends SingleLiveEvent {


    /** 显示弹出框 */
    private SingleLiveEvent<String> showDialogEvent = new SingleLiveEvent();

    /** 关闭弹出框 */
    private SingleLiveEvent<Void> closeDialogEvent = new SingleLiveEvent();

    /** 进入活动 */
    private SingleLiveEvent<Map<String, Object>> startActivityEvent = new SingleLiveEvent();

    /** 进入碎片容器活动 */
    private SingleLiveEvent<Map<String, Object>> startFragmentContainerActivityEvent = new SingleLiveEvent();

    /** 关闭界面 */
    private SingleLiveEvent<Void> finishEvent = new SingleLiveEvent();

    /** 返回上一层 */
    private SingleLiveEvent<Void> onBackPressedEvent = new SingleLiveEvent();

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer observer) {
        super.observe(owner, observer);
    }

    public SingleLiveEvent<String> getShowDialogEvent() {
        return showDialogEvent;
    }

    public SingleLiveEvent<Void> getCloseDialogEvent() {
        return closeDialogEvent;
    }

    public SingleLiveEvent<Map<String, Object>> getStartActivityEvent() {
        return startActivityEvent;
    }

    public SingleLiveEvent<Map<String, Object>> getStartFragmentContainerActivityEvent() {
        return startFragmentContainerActivityEvent;
    }

    public SingleLiveEvent<Void> getFinishEvent() {
        return finishEvent;
    }

    public SingleLiveEvent<Void> getOnBackPressedEvent() {
        return onBackPressedEvent;
    }
}
