package com.bignerdranch.android.mysecondfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.UnicastSubject;
import rx.subscriptions.CompositeSubscription;

public class FirstFragment extends Fragment {
    public static final String TAG = "#~";
    private Button firstButton;
    private TextView firstTextView;
    Subscription subscriptionRef1;
    Subscription subscriptionRef2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        log("1st fragment onCreateView");
        log(".......................................................");

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

//        createObservableWithMap();

//        createObservableWithBuffer(4);

//        createObservableWithTake(8);

//        createObservableWithSkip(10);

//        observableWithFilter();

//        observableWithMerge();

//        observableWithZip();

//        observableWithTakeUntil();

//        observableWithAll();

//        observableWithAction();

//        observableWithSubscription();

//        observableWithCompositeSubscription();

//        callFirstCustomObservable();

//        callSecondCustomObservable();

//        callColdObservableExample();

//        callHotObservableExample();

//        callObservableWithReplay();

//        callObservableWithReplay();

//        callRefCountExample();

//        callCacheExample();

//        callPublishSubjectExample();

//        callReplaySubjectExample();

//        callBehaviorSubjectExample();

//        callAsyncSubjectExample();

        callUnicastSubjectExample();

//        log(".....................................end.of.onCreateView()...");
        return v;
    }

    private void callUnicastSubjectExample() {
        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("1 observer_________________onCompleted");
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "1 observer onNext  value        |" + aLong + "|");
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("2 observer_________________onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                log("    2 observer onError " + e.getMessage());
            }
            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "    2 observer onNext value             |" + aLong + "|");
            }
        };

        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(20);

        final UnicastSubject<Long> subject = UnicastSubject.create();

        log("subject subscribe");
        observable.subscribe(subject);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                log("observer1 subscribe");
                subscriptionRef1 = subject.subscribe(observer1);
            }
        }, 2500);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                log("observer2 subscribe");
                subject.subscribe(observer2);
            }
        }, 4500);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                log("observer1 unsubscribe");
                subscriptionRef1.unsubscribe();
            }
        }, 6500);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                log("observer2 subscribe");
                subject.subscribe(observer2);
            }
        }, 8500);
    }

    private void callAsyncSubjectExample() {
        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("1 observer_________________onCompleted");
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "1 observer onNext  value        |" + aLong + "|");
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("2 observer_________________onCompleted");
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "    2 observer onNext value             |" + aLong + "|");
            }
        };

        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(4);

        final AsyncSubject<Long> subject = AsyncSubject.create();

        log("subject subscribe");
        observable.subscribe(subject);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                log("1 observer subscribe");
                subject.subscribe(observer1);
            }
        }, 4000);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                log("   2 observer subscribe");
                subject.subscribe(observer2);
            }
        }, 7500);
    }

    private void callBehaviorSubjectExample() {
        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("1 observer_________________onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "1 observer onNext  value        |" + aLong + "|");
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("2 observer_________________onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "    2 observer onNext value             |" + aLong + "|");
            }
        };

        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10);

        final BehaviorSubject<Long> subject = BehaviorSubject.create(-1L);

        log("1 observer subscribe");
        subject.subscribe(observer1);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                log("subject subscribe");
                observable.subscribe(subject);
            }
        }, 2000);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                log("2 observer subscribe");
                subject.subscribe(observer2);
            }
        }, 7500);
    }

    private void callReplaySubjectExample() {
        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("1 observer_________________onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "1 observer onNext  value        |" + aLong + "|");
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("2 observer_________________onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "    2 observer onNext value             |" + aLong + "|");
            }
        };

        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10);

        final ReplaySubject<Long> subject = ReplaySubject.create();     log("subscribe subject");
        observable.subscribe(subject);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                log("1 observer subscribe");
                subject.subscribe(observer1);
            }
        }, 3500);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                log("2 observer subscribe");
                subject.subscribe(observer2);
            }
        }, 5500);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                log("subject onNext ");
                subject.onNext(100L);
            }
        }, 7500);
    }

    private void callPublishSubjectExample() {
        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("1 observer_________________onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "1 observer onNext  value        |" + aLong + "|");
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                log("2 observer_________________onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "    2 observer onNext value             |" + aLong + "|");
            }
        };

        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10);

        final PublishSubject<Long> subject = PublishSubject.create();     log("subscribe subject");
        observable.subscribe(subject);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                log("1 observer subscribe");
                subject.subscribe(observer1);
            }
        }, 3500);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                log("2 observer subscribe");
                subject.subscribe(observer2);
            }
        }, 5500);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                log("subject onNext ");
                subject.onNext(100L);
            }
        }, 7500);
    }


    private void callCacheExample() {

        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "1 observer_________________onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "1 observer onNext  value        |" + aLong + "|");
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "2 observer_________________onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "    2 observer onNext value             |" + aLong + "|");
            }
        };

        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10)
//                .publish()     //  для
//                .refCount();   //  сравнения
                .cache();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "1 observer subscribed");
                subscriptionRef1 = observable.subscribe(observer1);
            }
        }, 1500);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "2 observer subscribed");
                subscriptionRef2 = observable.subscribe(observer2);
            }
        }, 4000);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "XXX 1 observer XXX unsubscribe");
                subscriptionRef1.unsubscribe();
            }
        }, 5500);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "XXX 2 observer XXX unsubscribe");
                subscriptionRef2.unsubscribe();
            }
        }, 6500);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "AGAIN 1 observer subscribe");
                observable.subscribe(observer1);
            }
        }, 7000); // 6499 для сравнения
    }

    private void callRefCountExample() {

        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "1 observer_________________onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "1 observer onNext  value  |" + aLong + "|");
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "2 observer_________________onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "    2 observer onNext value         |" + aLong + "|");
            }
        };

        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(7)
                .publish()
                .refCount();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "1 observer subscribed");
                subscriptionRef1 = observable.subscribe(observer1);
            }
        }, 1500);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "2 observer subscribed");
                subscriptionRef2 = observable.subscribe(observer2);
            }
        }, 3000);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "XXX 1 observer XXX unsubscribe");
                subscriptionRef1.unsubscribe();
            }
        }, 5000);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "XXX 2 observer XXX unsubscribe");
                subscriptionRef2.unsubscribe();
            }
        }, 6000);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "AGAIN 1 observer subscribe");
                observable.subscribe(observer1);
            }
        }, 6500);
    }

    private void callObservableWithReplay() {
        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer1 onNext value     |" + aLong + "|");
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer2 onNext value              |" + aLong + "|");
            }
        };

        final ConnectableObservable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(50)
                .replay();

        Log.d(TAG, "observable connect");
        final Subscription subscription = observable.connect();

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "observer1 subscribe");
                observable.subscribe(observer1);
            }
        }, 2500);

        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "observer2 subscribe");
                observable.subscribe(observer2);
            }
        }, 13000);

        Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "subscription.unsubscribe()");
                subscription.unsubscribe();
                Log.d(TAG, "subscription.isUnsubscribed() " + subscription.isUnsubscribed());
            }
        }, 20000);
    }

    private void callHotObservableExample() {
        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "1 observer onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "1 observer onNext value     |" + aLong + "|");
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "  2 observer onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "  2 observer onNext value          |" + aLong + "|");
            }
        };

        final ConnectableObservable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(8)
                .publish();

        Log.d(TAG, "obsrvable connect");
        observable.connect();

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                observable.subscribe(observer1);
            }
        }, 2500);

        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                observable.subscribe(observer2);
            }
        }, 4500);
    }

    private void callColdObservableExample() {
        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "1 observer onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "1 observer onNext value     |" + aLong + "|");
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "  2 observer onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "  2 observer onNext value          |" + aLong + "|");
            }
        };
        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(6);

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                observable.subscribe(observer1);
            }
        }, 5000);

        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                observable.subscribe(observer2);
            }
        }, 8500);
    }

    private void callSecondCustomObservable() {
        Observable.OnSubscribe<Integer> onSubscribe = new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (subscriber.isUnsubscribed()) { // ! 1 !
                        return;
                    }
                    subscriber.onNext(i);
                }

                if (subscriber.isUnsubscribed()) {     // ! 2 !
                    return;
                }

                subscriber.onCompleted();
            }
        };

        Observable<Integer> observable = Observable
                .create(onSubscribe)
                .subscribeOn(Schedulers.io());

        // create observer
        Observer<Integer> observer = new Observer<Integer>() {
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

        final Subscription subscription = observable.subscribe(observer);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                subscription.unsubscribe();
            }
        }, 2000);
    }

    private void callFirstCustomObservable() {
        Observable.OnSubscribe<Integer> onSubscribe = new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        };
        // create observable
        Observable<Integer> observable = Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io());
        // create observer
        Observer<Integer> observer = new Observer<Integer>() {
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

    private void observableWithCompositeSubscription() {
        final Subscription subscription1;
        final Subscription subscription2;

        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS);

        Action1<Long> actionObserver1 = new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, "1-st Observer. onNest: " + aLong);
            }
        };

        Action1<Long> actionObserver2 = new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, "2nd Observer. onNest: " + aLong);
            }
        };

        subscription1 = observable.subscribe(actionObserver1);
        subscription2 = observable.subscribe(actionObserver2);

        CompositeSubscription compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(subscription1);
        compositeSubscription.add(subscription2);

        Log.d(TAG, "subscription1 is unsubscribed " + subscription1.isUnsubscribed());
        Log.d(TAG, "subscription2 is unsubscribed " + subscription2.isUnsubscribed());

        // unsubscribe CompositeSubscription
        Log.d(TAG, "unsubscribe CompositeSubscription");
        compositeSubscription.unsubscribe();

        Log.d(TAG, "subscription1 is unsubscribed " + subscription1.isUnsubscribed());
        Log.d(TAG, "subscription2 is unsubscribed " + subscription2.isUnsubscribed());

    }

    private void observableWithSubscription() {
        Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS);
        Action1<Long> action = new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, "onNext :: " + aLong);
            }
        };

        final Subscription subscription = observable.subscribe(action);

        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                subscription.unsubscribe();
            }
        }, 3000);
    }

    private void observableWithAction() {
        Observable<String> observable = Observable.from(new String[]{"one", "two", "three"});
        Action1<String> action = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "onNext: " + s);
            }
        };
        observable.subscribe(action);

    }

    private void observableWithAll() {
        Func1<Integer, Boolean> lessThanTen = new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer i) {
                return i < 10;
            }
        };

        Observable<Boolean> observable = Observable
                .from(new Integer[]{1, 2, 3, 4, 5, 6})
                .all(lessThanTen);

        Observer<Boolean> observer = new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Boolean arg) {
                Log.d(TAG, "onNext: " + arg);
            }
        };
        observable.subscribe(observer);
    }

    private void observableWithTakeUntil() {
        Func1<Integer, Boolean> untilFour = new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer i) {
                // until i == 444
                return i == 444;
            }
        };

        Observable<Integer> observable = Observable
                .from(new Integer[]{1, 2, 3, 442, 443, 444, 445, 446})
                .takeUntil(untilFour);

        Observer<Integer> observer = new Observer<Integer>() {
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

    private void observableWithZip() {
        Func2<Integer, String, String> func2 = new Func2<Integer, String, String>() {
            @Override
            public String call(Integer integer, String s) {
                return s + "-" + integer;
            }
        };

        Observable<String> observable = Observable
                .from(new Integer[]{12, 7, 95, 34, 2})
                .zipWith(Observable.from(new String[]{"B", "K", "E", "T", "CO"}), func2);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(String str) {
                Log.d(TAG, "onNext: " + str);
            }
        };
        observable.subscribe(observer);
    }

    private void observableWithMerge() {
        Observable<Integer> observable = Observable
                .from(new Integer[]{1, 2, 3, 4, 5})
                .mergeWith(Observable.from(new Integer[]{6, 7, 8, 9, 10}));

        Observer<Integer> observer = new Observer<Integer>() {
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

    private void observableWithFilter() {
        Func1<String, Boolean> func1 = new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return s.contains("5");
            }
        };

        Observable<String> observable = Observable
                .from(new String[]{"11", "252", "33", "44", "555", "656"})
                .filter(func1);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(String arg) {
                Log.d(TAG, "onNext: " + arg);
            }
        };
        observable.subscribe(observer);
    }

    private void createObservableWithSkip(int count) {
        Observable<Integer> observable = Observable
                .from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16})
                .skip(count);
        Observer<Integer> observer = new Observer<Integer>() {
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

    private void createObservableWithTake(final int count) {
        Observable<Integer> observable = Observable
                .from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16})
                .take(count);
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted. There printed only " + count + " elements");
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

    private void createObservableWithBuffer(int count) {
        Observable<List<Integer>> observable = Observable
                .from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .buffer(count);
        Observer<List<Integer>> observer = new Observer<List<Integer>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(List<Integer> list) {
                Log.d(TAG, "onNext: " + list);
            }
        };
        observable.subscribe(observer);
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

        Observer<Integer> observer = new Observer<Integer>() {
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

        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
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

    private void log(String d) {
        Log.d(TAG, d);
    }

    private void postDelayed(Runnable runnable, long ms) {
        getActivity().getWindow().getDecorView().postDelayed(runnable, ms);
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
