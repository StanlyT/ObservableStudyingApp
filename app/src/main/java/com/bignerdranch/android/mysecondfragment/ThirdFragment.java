package com.bignerdranch.android.mysecondfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.mysecondfragment.MainActivity;
import com.bignerdranch.android.mysecondfragment.R;

import java.lang.annotation.Target;

public class ThirdFragment extends Fragment{
    public static final String TAG = "#~";
    private Button thirdButton;
    private TextView thirdTextView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "                                               3-rd fragment onAttach");
    }

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Log.d(TAG, "                                               3-rd fragment onCreate");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View v = inflater.inflate(R.layout.third_fragment_layout, container, false);
        thirdButton = (Button)v.findViewById(R.id.third_button);
        thirdTextView = (TextView)v.findViewById(R.id.third_textview_fragment);
        Log.d(TAG, "                                               3-rd fragment onCreateView");

        thirdTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "------------------- onClick -------------------");
                ((MainActivity)getActivity()).deleteCurrentFragment(ThirdFragment.this);
                ((MainActivity)getActivity()).fragmentManager.popBackStack();
//                ((MainActivity)getActivity()).onTabSelected();
//                ((MainActivity)getActivity()).startFirstActivity();
//                ((MainActivity)getActivity()).startSecondActivity();
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).startFourthActivity();
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "                                               3-rd fragment onActivityCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
//        Log.d(TAG, "                                               3-rd fragment onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "                                              3-rd fragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "                                              3-rd fragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "                                               3-rd fragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "                                               3-rd fragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "                                               3-rd fragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "                                               3-rd fragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "                                              3-rd fragment onDetach");
    }
}
