package com.liang.tind.www.tindtest.util;


import android.util.Log;
import android.util.SparseArray;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


public class RxBus {
    private static final String TAG = "Rxbus";
    private static volatile RxBus mInstance;
    private SparseArray<Disposable> mSubscriptionSpa;
    private Subject<Object> mBus;

    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }

    /**
     * 单例 双重锁
     *
     * @return
     */
    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送一个新的事件
     *
     * @param event
     */
    public void post(Event event) {
        mBus.onNext(event);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     *
     * @param type
     * @param <T>
     * @return
     */
    private <T> Observable<T> tObservable(final Class<T> type) {
        //ofType操作符只发射指定类型的数据，其内部就是filter+cast
        return mBus.ofType(type);
    }

    public <T> void doSubscribe(int code, String eventName, Consumer<Event<T>> subscriber) {
        Disposable subscribe = tObservable(Event.class)
                .filter(post -> post.getEventName().equals(eventName))
                .flatMap((Function<Event, ObservableSource<Event<T>>>) Observable::just)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        addSubscription(code, subscribe);
    }


    private void addSubscription(int code, Disposable disposable) {
        if (mSubscriptionSpa == null) {
            mSubscriptionSpa = new SparseArray<>();
        }
        if (mSubscriptionSpa.get(code) == null) {
            mSubscriptionSpa.put(code, disposable);
        } else {
            Log.w(TAG, "Add the duplicate code", new IllegalArgumentException() );
        }
    }

    public void unSubscribe(int code) {
        if (mSubscriptionSpa == null) {
            return;
        }
        Disposable disposable = mSubscriptionSpa.get(code);
        if (disposable != null) {
            disposable.dispose();
        }
        mSubscriptionSpa.remove(code);
    }


    public static class Event<T> {
        String eventName;
        T data;

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
