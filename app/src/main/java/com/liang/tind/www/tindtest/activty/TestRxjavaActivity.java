package com.liang.tind.www.tindtest.activty;

import android.util.Log;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
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


        Flowable<Integer> observable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                int i = 0;
                while (isComplete) {
//                    Log.i(TAG, "subscribe: on while");
//                    while (emitter.requested() == 0) {
//                        if (emitter.isCancelled()) {
//                            break;
//                        }
//                    }
                    Log.e(TAG, "emitter ==" + i + ",  requested == " + emitter.requested());
                    emitter.onNext(i++);

                    if (i == 10) {
                        mDisposable.request(96);
                        Log.e(TAG, "request:96 ");
                    }

                }
            }
        }, BackpressureStrategy.ERROR);

        FlowableSubscriber<Integer> integerObserver = new FlowableSubscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                mDisposable = s;
                Log.e(TAG, "onSubscribe: ");
//                mDisposable.request(1);
            }


            @Override
            public void onNext(Integer o) {
//                mDisposable.request(1);
                Log.e(TAG, "onNext: " + o);


                if (o >= (10 + 96)) {
                    mDisposable.cancel();
                    Log.e(TAG, "onNext: cancel");
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        };

        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(integerObserver);


    }

}
