package com.liang.tind.www.tindtest.activty;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;
import com.liang.tind.www.tindtest.util.RxBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.reactivestreams.Subscription;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * created by Administrator
 * <p>
 * date 2018/6/4
 */
public class TestRxjavaActivity extends BaseActivity {
    public android.os.Handler mHandler = new android.os.Handler();

    private boolean isComplete = true;
    Subscription mDisposable;
    public static final String EVENT_NAME = "EventName";
    public static final String EVENT_NAME_ANOTHER = "EventNameAnother";
    public static final String EVENT_NAME_INT = "EventNameInt";
    private RxBus.Event<String> mEvent;
    private RxBus.Event<String> mEventString;
    private RxBus.Event<Integer> mEventInt;

    private int mCode = 100;
    private int mCodeAnother = 101;
    private int mCodeInt = 111;
    ObservableEmitter<Object> mEmitter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_rxjava;
    }

    @Override
    protected void init() {
        testConcatDelayError();
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                mEmitter = emitter;
            }
        })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        Log.i(TAG, "retryWhen ==> apply(TestRxjavaActivity.java:63): ");
                        return  throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                boolean flag = false;
                                if (throwable instanceof ClassCastException){
                                    flag = true;
                                }
                                return flag ? Observable.just(1) : Observable.error(throwable);
                            }
                        });
                    }
                })
                .subscribe(integer -> Log.d(TAG,integer.toString()),throwable -> Log.w(TAG, "init(TestRxjavaActivity.java:67): ", throwable));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run onError ");
                mEmitter.onError(new ClassCastException("TEST ERROR"));
            }
        }, 1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run onNext ");
                mEmitter.onError(new ClassCastException("TEST ERROR onNext"));
                mEmitter.onNext("Test onNext");
            }
        }, 2000);
//        mEvent = new RxBus.Event<>();
//        mEvent.setEventName(EVENT_NAME);
//        mEvent.setData("test data");
//
//        mEventString = new RxBus.Event<>();
//        mEventString.setEventName(EVENT_NAME_ANOTHER);
//        mEventString.setData("another test data");
//
//        mEventInt = new RxBus.Event<>();
//        mEventInt.setEventName(EVENT_NAME_INT);
//        mEventInt.setData(100);

//        RxBus.getInstance().doSubscribe(this, EVENT_NAME, new Consumer<RxBus.Event<String>>() {
//            @Override
//            public void accept(RxBus.Event<String> event) throws Exception {
//                Log.i("Main", "accept(MainActivity.java:147) data: " + event.getData() + ",event:" + event.getEventName());
//            }
//        });
//
//
//        RxBus.getInstance().doSubscribe(this, EVENT_NAME_INT, new Consumer<RxBus.Event<Integer>>() {
//            @Override
//            public void accept(RxBus.Event<Integer> event) throws Exception {
//                Log.i(TAG, "accept(MainActivity.java:147) data: "+event.getData()+",event:"+event.getEventName());
//            }
//        });
//
//        RxBus.getInstance().doSubscribe(this, EVENT_NAME_ANOTHER, new Consumer<RxBus.Event<String>>() {
//            @Override
//            public void accept(RxBus.Event<String> event) throws Exception {
//                Log.i(TAG, "accept(MainActivity.java:147) data: "+event.getData()+",event:"+event.getEventName());
//            }
//        });


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

        List<Map<String, Integer>> list = new ArrayList<>();
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        map1.put("key", 1);
        map2.put("key", 2);
        list.add(map1);
        list.add(map2);
//        list.add(3+"");
//        list.add(4+"");
//        Observable.fromIterable(list)
//                .subscribeOn(Schedulers.io())
//                .flatMap(new Function<Map<String,Integer>, ObservableSource<Map<String,Integer>>>() {
//                    @Override
//                    public ObservableSource<Map<String,Integer>> apply(Map<String,Integer> value) throws Exception {
//                        Observable<Map<String,Integer>> observable = Observable.create(new ObservableOnSubscribe<Map<String,Integer>>() {
//                            @Override
//                            public void subscribe(ObservableEmitter<Map<String,Integer>> emitter) throws Exception {
//                                Log.i(TAG, "apply(TestRxjavaActivity.java:79): " + value);
//                                if (value.get("key") == 2) {
//                                    Thread.sleep(1000);
//                                    value.put("key",3);
//                                    Log.i(TAG, "apply(TestRxjavaActivity.java:84): wake up");
//                                }
//                                emitter.onNext(value);
//                                emitter.onComplete();
//                            }
//                        }).subscribeOn(Schedulers.newThread())
//                                .observeOn(AndroidSchedulers.mainThread());
//
//                        return observable;
//                    }
//                })
//                .subscribe(new Observer<Map<String, Integer>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.i(TAG, "onSubscribe(TestRxjavaActivity.java:113): ");
//                    }
//
//                    @Override
//                    public void onNext(Map<String, Integer> stringIntegerMap) {
//                        Log.i(TAG, "onNext(TestRxjavaActivity.java:118): "+stringIntegerMap);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.w(TAG, "onError(TestRxjavaActivity.java:123): ",e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.i(TAG, "onComplete(TestRxjavaActivity.java:128): ");
//                    }
//                });

//        Disposable subscribe = Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
//            Log.i(TAG, "emitter 1");
//            emitter.onNext(1);
//
////            Thread.sleep(600);
//            Log.i(TAG, "emitter 2");
//            emitter.onNext(2);
//
////            Thread.sleep(100);
//            Log.i(TAG, "emitter 3");
//            emitter.onNext(3);
//
////            Thread.sleep(100);
//            Log.i(TAG, "emitter 4");
//            emitter.onNext(4);
//
////            Thread.sleep(600);
//            Log.i(TAG, "emitter 5");
//            emitter.onNext(5);
//        }).debounce(500, TimeUnit.MILLISECONDS)
//
//                .subscribe(integer -> {
//                    Log.i(TAG, "init(TestRxjavaActivity.java:134): " + integer);
//                });
    }

    public void post(View v) {
        RxBus.getInstance().post(mEvent);
        RxBus.getInstance().post(mEventString);
        RxBus.getInstance().post(mEventInt);
    }


    public void testConcatDelayError(){
        List<Observable<Integer>> list = new ArrayList<>();
        list.add(Observable.just(1).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                throw new NullPointerException();
            }
        }));
//        list.add(Observable.just(2));
//        list.add(Observable.just(3).doOnNext(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                throw new MyException("s");
//            }
//        }));
//        list.add(Observable.just(4));
        Observable.concatDelayError(list).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext:"+integer);
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof CompositeException) {
                    CompositeException errors = (CompositeException) e;
                    List<Throwable> exceptions = errors.getExceptions();
                    for (Throwable error : exceptions) {
                        Log.e(TAG, "each error:", error);
                    }
                } else {
                    Log.e(TAG, "onError", e);
                }
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

    class MyException extends RuntimeException {
        MyException(String msg) {
            super(msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubscribe(this);
        RxBus.getInstance().unSubscribe(this);
        RxBus.getInstance().unSubscribe(this);
    }
}
