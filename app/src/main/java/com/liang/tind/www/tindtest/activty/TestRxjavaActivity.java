package com.liang.tind.www.tindtest.activty;

import android.util.Log;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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


        HashSet<String> set = new HashSet<>();
        set.add("1");
        set.add("1");


        try {
            getTest(1);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
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


        MyHashSet<Integer> myHashSet = new MyHashSet<>();

        Observable.just(1, 2, 3)
                .distinct(integer -> integer, () -> myHashSet)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        myHashSet.distinct();

                        for (Integer integer : myHashSet) {
                            Log.i(TAG, "integer =="+integer);
                        }
                    }
                });


    }

    class MyHashSet<E> extends HashSet<E> {
        private Set<E> mHashSet = new HashSet<>();
        @Override
        public boolean add(E e) {
            boolean add = super.add(e);
            if (!add){
                mHashSet.add(e);
            }
            return add;
        }

        public void distinct(){
            for (E e : mHashSet) {
                remove(e);
            }
        }
    }

    private boolean getTest(int i) throws Throwable {
        if (i == 7) {
            throw new Throwable("test");
        }
        if (1 == 5) {
            return true;
        }
        Log.e(TAG, "getTest: ");
        Log.e(TAG, "getTest: ");
        Log.e(TAG, "getTest: ");
        Log.e(TAG, "getTest: ");
        return false;
    }

    class Item {
        public List<String> getList() {
            return new ArrayList<>();
        }
    }
}
