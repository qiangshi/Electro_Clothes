package com.business.electr.clothes.view;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by yangjingsong on 16/4/21.
 */
public class TimeCounter extends CountDownTimer {

    private TextView button;
    private int mStringId;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     * @param button
     * @param strId
     */
    public TimeCounter(long millisInFuture, long countDownInterval, TextView button, int strId) {
        super(millisInFuture, countDownInterval);
        this.button = button;
        this.button.setEnabled(false);
        mStringId = strId;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        button.setText("重新获取("+millisUntilFinished / 1000 + "s)");
    }

    @Override
    public void onFinish() {
        button.setText(mStringId);
        button.setEnabled(true);
    }
}
