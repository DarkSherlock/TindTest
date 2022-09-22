package com.liang.tind.www.tindtest.activty;

import com.liang.tind.www.tindtest.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class GenericsActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void init() {
       // https://mp.weixin.qq.com/s/aHmN5deBpZ88SMSN1ugcpA
        Plate<Fruit> p1 = new Plate<>(new Apple());
        p1.set(new Apple());
        // 但是他只能精确到由我们定义的Fruit
        Fruit apple = p1.get();

        // 协变 能get不能set
        Plate<? extends Fruit> p2 = new Plate<>(new Apple());
//        p2.set(new Apple()); // error
//        p2.set(new Fruit()); // error
        Fruit fruit = p2.get();

        // 逆变 能set不能get
        Plate<? super Fruit> p3 = new Plate<>(null);
        p3.set(new Apple());
        p3.set(new Fruit());
//        p3.set(new Food());
//        Fruit object = p3.get(); // error 除非用object接收
        Object object = p3.get();

        List<Apple> apples = new ArrayList<>();
        List<Fruit> fruits = new ArrayList<>();
//        fruits = apples; // error
        List<? extends Fruit> fruits1 = apples; // OK
        List<? super Apple> fruits2 = apples;
        List<? super Apple> fruits3 = fruits;

//        List<?> anyList = new ArrayList<>();
//        anyList.add(new Apple());
//        anyList.add(new Fruit());
//        Object object = anyList.get(0);
//
//        List<? super Apple> superAppleList = new ArrayList<>();
//        superAppleList.add(new Apple());
//        superAppleList.add(new Fruit());
//        Object object1 = superAppleList.get(0);
//
//        List<? extends Fruit> extendsAppleList = new ArrayList<>();
//        extendsAppleList.add(new Apple());
//        extendsAppleList.add(new Fruit());
//        Fruit apple1 = extendsAppleList.get(0);

    }

    static class Plate<T> {
        private T item;

        public Plate(T t) {
            item = t;
        }

        public void set(T t) {
            item = t;
        }

        public T get() {
            return item;
        }
    }

    // Lev 1
    static class Food {
    }

    // Lev 2
    static class Fruit extends Food {
    }

    //Lev 3
    static class Apple extends Fruit {
    }
}
