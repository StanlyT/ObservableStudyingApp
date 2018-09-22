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

public class FirstFragment extends Fragment {
    public static final String TAG = "#~";
    private Button firstButton;
    private TextView firstTextView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "1st fragment onViewCreated");
    }

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Log.d(TAG, "1st fragment onCreate");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        Log.d(TAG, "1st fragment onCreateView");
        View v = inflater.inflate(R.layout.first_fragment_layout, container, false);
        firstButton = (Button)v.findViewById(R.id.first_button);
        firstTextView = (TextView) v.findViewById(R.id.first_textview_fragment);


        firstButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).startSecondActivity();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "1st fragment onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "1st fragment onActivityCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "1st fragment onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "1st fragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "1st fragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "1st fragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "1st fragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "1st fragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "1st fragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "1st fragment onDetach");
    }
}
