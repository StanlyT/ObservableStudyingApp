package com.bignerdranch.android.mysecondfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class MainActivity extends FragmentActivity {
    public static final String TAG = "#~";

    FragmentManager fragmentManager = getSupportFragmentManager();
    public static final String TAG_FIRST_FRAGMENT = "FIRST_FRAGMENT_TAG";
    public static final String TAG_SECOND_FRAGMENT = "SECOND_FRAGMENT_TAG";
    public static final String TAG_THIRD_FRAGMENT = "THIRD_FRAGMENT_TAG";
    public static final String TAG_FOURTH_FRAGMENT = "FOURTH_FRAGMENT_TAG";

    public static final String FIRST_FRAGMENT = FirstFragment.class.getName();
    public static final String SECOND_FRAGMENT = SecondFragment.class.getName();
    public static final String THIRD_FRAGMENT = ThirdFragment.class.getName();
    public static final String FOURTH_FRAGMENT = FourthFragment.class.getName();

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "                                                                                                   HOST ACTIVITY onCreate");

        startFirstActivity();
    }

    public void startFirstActivity() {
        Log.d(TAG, "startFirstActivity() called");
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_FIRST_FRAGMENT);
        if (fragment == null) {
            Log.d(TAG, "TAG_FIRST_FRAGMENT was not found");
            fragment = Fragment.instantiate(this, FIRST_FRAGMENT);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, TAG_FIRST_FRAGMENT)
                    .commit();
        } else {
            Log.d(TAG, "FIRST_FRAGMENT NOT null");
        }
    }

    public void startSecondActivity() {
        Log.d(TAG, "                      startSecondActivity() called");
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_SECOND_FRAGMENT);
        if (fragment == null) {
            Log.d(TAG, "                         SECOND_FRAGMENT == null");
            fragment = Fragment.instantiate(this, SECOND_FRAGMENT);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, TAG_SECOND_FRAGMENT)
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit();
        } else {
            Log.d(TAG, "                         SECOND_FRAGMENT NOT null");
        }
    }

    public void startThirdActivity() {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_THIRD_FRAGMENT);
        if (fragment == null) {
            Log.d(TAG, "                                               THIRD_FRAGMENT == null");
            fragment = Fragment.instantiate(this, THIRD_FRAGMENT);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, TAG_THIRD_FRAGMENT)
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit();
        } else {
            Log.d(TAG, "                                               THIRD_FRAGMENT NOT null");
        }
    }

    public void startFourthActivity() {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_FOURTH_FRAGMENT);
        if (fragment == null) {
            Log.d(TAG, "                                               FOURTH_FRAGMENT == null");
            fragment = Fragment.instantiate(this, FOURTH_FRAGMENT);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, TAG_FOURTH_FRAGMENT)
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit();
        } else {
            Log.d(TAG, "                                               FOURTH_FRAGMENT NOT null");
        }
    }

    public void deleteCurrentFragment(Fragment fragment){
        fragmentManager.beginTransaction()
                .remove(fragment)
                .commit();
    }

    public void onTabSelected() {
        // Pop off everything up to and including the current tab
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "                                                                                                  HOST ACTIVITY onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "                                                                                                   HOST ACTIVITY onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "                                                                                                   HOST ACTIVITY onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "                                                                                                   HOST ACTIVITY onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "                                                                                                   HOST ACTIVITY onDestroy");
    }
}
