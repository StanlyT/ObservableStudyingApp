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

public class FourthFragment extends Fragment{
    public static final String TAG = "#~";
    private Button fourthButton;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "                                                                             4-th fragment onAttach");
    }

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Log.d(TAG, "                                                                            4-th fragment onCreate");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View v = inflater.inflate(R.layout.fourth_fragment_layout, container, false);
        fourthButton = (Button)v.findViewById(R.id.fourth_button);
        Log.d(TAG, "                                                                             4-th fragment onCreateView");

        fourthButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              //  ((MainActivity)getActivity()).start
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "                                                                             4-th fragment onActivityCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
//        Log.d(TAG, "                                                                             4-th fragment onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "                                                                            4-th fragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "                                                                            4-th fragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "                                                                             4-th fragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "                                                                             4-th fragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "                                                                             4-th fragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "                                                                             4-th fragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "                                                                            4-th fragment onDetach");
    }
}
