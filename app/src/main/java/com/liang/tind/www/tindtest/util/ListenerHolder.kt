package com.liang.tind.www.tindtest.util

/**
 *@author: lonnie.liang
 *date: 2022/09/05 15:08
 *
 */
class ListenerHolder<T> : IListenerHolder<T> {

    private val listeners = ConcurrentWeakRefSet<T>()

    override fun registerListener(listener: T) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: T) {
        listeners.remove(listener)
    }

    override fun notifyEach(action: T.() -> Unit) {
        listeners.forEach(action)
    }
}