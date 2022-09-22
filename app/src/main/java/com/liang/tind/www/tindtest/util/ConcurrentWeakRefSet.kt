package com.liang.tind.www.tindtest.util

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.liang.tind.www.tindtest.BuildConfig
import java.lang.ref.WeakReference

/**
 * Created by jimmie.yang on 1/15/22.
 * for single thread, concurrent safely
 */
class ConcurrentWeakRefSet<E> {

    private val targetWeakRefElements = mutableSetOf<WeakReference<E>>()
    private val waitingAddElements = mutableSetOf<WeakReference<E>>()

    private val expectId = Thread.currentThread().id
    private val expectName = Thread.currentThread().name
    private var concurrentFlag = 0

    val size: Int
        get() = targetWeakRefElements.size

    fun add(element: E): Boolean {
        if (!checkThreadSafely()) return false

        val old = targetWeakRefElements.find { it.get() == element }
        if (old != null) {
            return false
        }
        return if (concurrentFlag == 0) {
            targetWeakRefElements.add(WeakReference(element))
        } else {
            waitingAddElements.add(WeakReference(element))
        }
    }

    fun remove(element: E): Boolean {
        if (!checkThreadSafely()) return false

        return if (concurrentFlag == 0) {
            targetWeakRefElements.removeAll { it.get() == element }
        } else {
            targetWeakRefElements.forEach {
                if (it.get() == element) it.clear()
            }
            true
        }
    }

    fun clear(): Boolean {
        if (!checkThreadSafely()) return false

        this.forEach { remove(it) }

        return true
    }

    fun contains(element: E): Boolean {
        return targetWeakRefElements.find { it.get() == element } != null
    }

    fun isEmpty(): Boolean {
        return targetWeakRefElements.isEmpty()
    }

    fun forEach(action: (E) -> Unit) {
        if (!checkThreadSafely()) return

        concurrentFlag += 1
        forEachWithIndex(action, 0)
        concurrentFlag -= 1
        if (concurrentFlag == 0) {
            targetWeakRefElements.removeAll { it.get() == null }
        }
    }

    private fun forEachWithIndex(action: (E) -> Unit, index: Int) {
        val size = targetWeakRefElements.size
        for (i in (index until size)) {
            val element = targetWeakRefElements.elementAt(i).get()
            element?.let(action)
            if (waitingAddElements.isNotEmpty()) {
                targetWeakRefElements.addAll(waitingAddElements)
                waitingAddElements.clear()
                forEachWithIndex(action, i + 1)
                return
            }
        }
    }

    @VisibleForTesting
    fun checkThreadSafely(): Boolean {
        if (!NEED_CHECK) return true

        val actualId = Thread.currentThread().id
        val actualName = Thread.currentThread().name
        if (actualId != expectId) {
            val stackTrace = Throwable().stackTraceToString()
            val message =
                "thread not safely, can't handle for multi thread, expectThread:$expectName($expectId), actualThread:$actualName($actualId), stack=$stackTrace"
            if (!BuildConfig.DEBUG) {
                MainThreadExecutor.post {
                    throw ConcurrentModificationException(message)
                }
            } else {
                Log.e(TAG, message)
            }
            return false
        }
        return true
    }

    companion object {
        private const val TAG = "ConcurrentWeakRefSet"

        // useful in UT,
        // UT runs in worker thread,
        // should not check thread safely
        @VisibleForTesting
        var NEED_CHECK = true
    }
}