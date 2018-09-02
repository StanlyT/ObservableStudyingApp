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
import java.util.UUID;
public class SecondFragment extends Fragment{
    public static final String TAG = "#~";
    private Button secondButton;
    private TextView secondTextView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "                         2-nd fragment onAttach");
    }

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Log.d(TAG, "                         2-nd fragment onCreate");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View v = inflater.inflate(R.layout.second_fragment_layout, container, false);
        secondButton = (Button)v.findViewById(R.id.second_button);
        secondTextView = (TextView)v.findViewById(R.id.second_textview_fragment);

        Log.d(TAG, "                         2-nd fragment onCreateView");

        secondTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).deleteCurrentFragment(SecondFragment.this);
                ((MainActivity)getActivity()).fragmentManager.popBackStack();            }
        });

        secondButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).startThirdActivity();
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "                         2-nd fragment onActivityCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
//        Log.d(TAG, "                         2-nd fragment onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "                        2-nd fragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "                        2-nd fragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "                         2-nd fragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "                         2-nd fragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "                         2-nd fragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "                         2-nd fragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "                        2-nd fragment onDetach");
    }
}
