package com.bignerdranch.android.mysecondfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class FirstFragment extends Fragment {
    public static final String TAG = "#~";
    private Button firstButton;
    private TextView firstTextView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        Log.d(TAG, "1st fragment onCreateView");
        Log.d(TAG, ".......................................................");

        View v = inflater.inflate(R.layout.first_fragment_layout, container, false);
        firstButton = (Button) v.findViewById(R.id.first_button);
        firstTextView = (TextView) v.findViewById(R.id.first_textview_fragment);


        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).startSecondActivity();
            }
        });

//        createObservableWithFrom();

//        createObservableWithRange();

//        createObservableWithInterval();

//        createObservableWithFromCallable();

        createObservableWithMap();



        Log.d(TAG, ".....................................end.of.onCreateView()...");
        return v;
    }

    private void createObservableWithMap() {
        Func1<String, Integer> stringToInteger = new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return Integer.parseInt(s);
            }
        };

        Observable<Integer> observable = Observable
                .from(new String[]{"1", "2", "3", "4", "5", "6", "7"})
                .map(stringToInteger);

        Observer<Integer> observer = new Observer<Integer>(){
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Integer arg) {
                Log.d(TAG, "onNext: " + arg);
            }
        };
        observable.subscribe(observer);
    }

    private void createObservableWithFrom() {
        Log.d(TAG, "\n.");

        Observable<String> observable = Observable.from(new String[]{"one", "two", "three"});

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }
        };

        observable.subscribe(observer);

        Log.d(TAG, "\n.");
    }

    private void createObservableWithRange() {
        Log.d(TAG, "\n.");

        Observable<Integer> observable = Observable.range(100, 10);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }
        };

        observable.subscribe(observer);

        Log.d(TAG, "\n.");
    }

    public void createObservableWithInterval() {
        Log.d(TAG, "\n.");

        Observable<Long> observable = Observable.interval(50, TimeUnit.MILLISECONDS);
        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "onNext: " + aLong);
            }
        };
        observable.subscribe(observer);
        Log.d(TAG, "\n.");
    }

    public void createObservableWithFromCallable() {
        Log.d(TAG, "\n.");

        Observable.fromCallable(new MyCallalbeClassWithLongOperation("5"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer intArg) {
                        Log.d(TAG, "call onNext " + intArg);
                    }
                });
        Log.d(TAG, "\n.");
    }


//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        Log.d(TAG, "1st fragment onCreate");
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Log.d(TAG, "1st fragment onViewCreated");
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        Log.d(TAG, "1st fragment onAttach");
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Log.d(TAG, "1st fragment onActivityCreated");
//    }
//
//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        Log.d(TAG, "1st fragment onViewStateRestored");
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        Log.d(TAG, "1st fragment onStart");
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.d(TAG, "1st fragment onResume");
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Log.d(TAG, "1st fragment onPause");
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        Log.d(TAG, "1st fragment onStop");
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        Log.d(TAG, "1st fragment onDestroyView");
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.d(TAG, "1st fragment onDestroy");
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        Log.d(TAG, "1st fragment onDetach");
//    }
}
