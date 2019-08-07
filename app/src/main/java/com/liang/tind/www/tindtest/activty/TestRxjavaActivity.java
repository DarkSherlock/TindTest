package com.liang.tind.www.tindtest.activty;

import android.util.Log;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * created by Administrator
 * <p>
 * date 2018/6/4
 */
public class TestRxjavaActivity extends BaseActivity {
    public android.os.Handler mHandler = new android.os.Handler();

    private boolean isComplete = true;
    Subscription mDisposable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_rxjava;
    }

    @Override
    protected void init() {
//        List<String> strings = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            strings.add(String.valueOf(i));
//        }
//        strings.add(String.valueOf(1));
//        strings.remove("1");
//        strings.remove("1");
//        Log.e(TAG, "strings="+strings);


//        HashSet<String> set = new HashSet<>();
//        set.add("1");
//        set.add("1");
//
//
//        try {
//            getTest(1);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        Observable<String> list1 = Observable.fromIterable(new ArrayList<String>());
//        Observable<String> list2 = Observable.fromIterable(new ArrayList<String>());
//        Observable.zip(list1, list2, new BiFunction<String, String, List<String>>() {
//            @Override
//            public List<String> apply(String s, String s2) throws Exception {
//                List<String> list = new ArrayList<>();
//                if (s.equals(s2))
//                    return null;
//            }
//        }).subscribe(new Consumer<List<String>>() {
//            @Override
//            public void accept(List<String> strings) throws Exception {
//
//            }
//        });

        List<Map<String,Integer>> list = new ArrayList<>();
        HashMap<String,Integer> map1 = new HashMap<>();
        HashMap<String,Integer> map2 = new HashMap<>();
        map1.put("key",1);
        map2.put("key",2);
        list.add(map1);
        list.add(map2);
//        list.add(3+"");
//        list.add(4+"");
        Observable.fromIterable(list)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<Map<String,Integer>, ObservableSource<Map<String,Integer>>>() {
                    @Override
                    public ObservableSource<Map<String,Integer>> apply(Map<String,Integer> value) throws Exception {
                        Observable<Map<String,Integer>> observable = Observable.create(new ObservableOnSubscribe<Map<String,Integer>>() {
                            @Override
                            public void subscribe(ObservableEmitter<Map<String,Integer>> emitter) throws Exception {
                                Log.i(TAG, "apply(TestRxjavaActivity.java:79): " + value);
                                if (value.get("key") == 2) {
                                    Thread.sleep(1000);
                                    value.put("key",3);
                                    Log.i(TAG, "apply(TestRxjavaActivity.java:84): wake up");
                                }
                                emitter.onNext(value);
                                emitter.onComplete();
                            }
                        }).subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread());

                        return observable;
                    }
                })
                .subscribe(new Observer<Map<String, Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe(TestRxjavaActivity.java:113): ");
                    }

                    @Override
                    public void onNext(Map<String, Integer> stringIntegerMap) {
                        Log.i(TAG, "onNext(TestRxjavaActivity.java:118): "+stringIntegerMap);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG, "onError(TestRxjavaActivity.java:123): ",e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete(TestRxjavaActivity.java:128): ");
                    }
                });

    }

}
