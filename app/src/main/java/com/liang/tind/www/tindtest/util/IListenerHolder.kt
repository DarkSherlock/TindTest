package com.liang.tind.www.tindtest.util

/**
 *@author: lonnie.liang
 *date: 2022/09/06 01:07
 *
 */
interface IListenerHolder<T> {

    fun registerListener(listener: T)

    fun unregisterListener(listener: T)

    fun notifyEach(action: T.() -> Unit)
}
