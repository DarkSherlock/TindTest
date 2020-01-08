package com.liang.tind.www.tindtest.util;

/**
 * @author 梁天德
 * @date 2020/01/07 16:02
 * desc
 */
public abstract class Singleton<T> {
    private volatile T mInstance;

    protected abstract T create();

    public final T get() {
        if (mInstance == null) {
            synchronized (this) {
                if (mInstance == null) {
                    mInstance = create();
                }
            }
        }
        return mInstance;
    }
}
