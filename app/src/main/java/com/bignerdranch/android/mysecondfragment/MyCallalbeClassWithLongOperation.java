package com.bignerdranch.android.mysecondfragment;

import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyCallalbeClassWithLongOperation implements Callable<Integer> {
    public static final String TAG = "#~";
    private final String DATA;

    public MyCallalbeClassWithLongOperation(String DATA) {
        this.DATA = DATA;
    }

    @Override
    public Integer call() throws Exception {
        return longAction(DATA);
    }

    private int longAction(String text){
        Log.d(TAG, "longAction was called");
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {e.printStackTrace();}
        return Integer.parseInt(text);
    }
}
