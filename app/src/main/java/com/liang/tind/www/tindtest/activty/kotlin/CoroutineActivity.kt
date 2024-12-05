package com.liang.tind.www.tindtest.activty.kotlin

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.liang.tind.www.tindtest.R
import com.liang.tind.www.tindtest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine

/**
 *@author lonnie.liang
 *@date 2020/04/09 16:31
 *
 */
class CoroutineActivity : BaseActivity() {
    val mainScope = MainScope()
    val ioScope = CoroutineScope(Dispatchers.IO)
    override fun init() {
        test()

//        ioScope.launch { testThrowException() }
        parseWithCoroutine.setOnClickListener {
            val entity = Entity("TestName", 1, "male", 18)
            parseWithCoroutine(Gson().toJson(entity))
        }

        parse.setOnClickListener {
            val entity = Entity("TestName", 1, "male", 18)
            parse(Gson().toJson(entity))
        }

        parseListWithCoroutine.setOnClickListener {
            val entity = Entity("TestName", 1, "male", 18)
            val list = arrayListOf<Entity>()
            for (i in 0 until 100) {
                list.add(entity)
            }
            parseListWithCoroutine(Gson().toJson(list))
        }

        parseList.setOnClickListener {
            val entity = Entity("TestName", 1, "male", 18)
            val list = arrayListOf<Entity>()
            for (i in 0 until 100) {
                list.add(entity)
            }
            parseList(Gson().toJson(list))
        }
        Log.i(TAG, "init: end")

        GlobalScope.launch {
            Log.i(TAG, "global thread:${Thread.currentThread().name} ")

            val job = async {
                Log.i(TAG, "async thread:${Thread.currentThread().name} ")
                delay(1000)
                Log.i(TAG, "delay thread:${Thread.currentThread().name} ")
                "tets"
            }

            Log.i(TAG, "after async global thread:${Thread.currentThread().name} ")
            val result = job.await()
            Log.i(TAG, "result= $$result, global thread:${Thread.currentThread().name} ")
        }
    }

    override fun getLayoutId() = R.layout.activity_coroutine


    fun parseWithCoroutine(json: String) {
        val startTime = System.currentTimeMillis()
        CoroutineScope(Dispatchers.Main).launch {
            val entity: Entity = withContext(Dispatchers.IO) {
                Gson().fromJson(json, Entity::class.java)
            }
            val endTime = System.currentTimeMillis()
            Log.i(TAG, "parseWithCoroutine():耗时：${endTime - startTime}")
        }
    }

    fun parseListWithCoroutine(json: String) {
        val startTime = System.currentTimeMillis()
        CoroutineScope(Dispatchers.Main).launch {
            val entity: ArrayList<Entity> = withContext(Dispatchers.IO) {
                val type = object : TypeToken<ArrayList<Entity>>() {}.type
                val list: ArrayList<Entity> = Gson().fromJson(json, type)
                list
            }
            val endTime = System.currentTimeMillis()
            Log.i(TAG, "parseListWithCoroutine():耗时：${endTime - startTime}")
        }
    }

    fun parse(json: String) {
        val startTime = System.currentTimeMillis()
        val entity = Gson().fromJson(json, Entity::class.java)
        val endTime = System.currentTimeMillis()
        Log.i(TAG, "parse():耗时：${endTime - startTime}")
    }

    fun parseList(json: String) {
        val startTime = System.currentTimeMillis()
        val type = object : TypeToken<ArrayList<Entity>>() {}.type
        val entity: ArrayList<Entity> = Gson().fromJson(json, type)
        val endTime = System.currentTimeMillis()
        Log.i(TAG, "parseList():耗时：${endTime - startTime}")
    }

    data class Entity constructor(val name: String, val id: Int, val sex: String, val age: Int)


    private fun test() {
        mainScope.launch {

            Log.i(TAG, "launch: start")

//            val time = measureTimeMillis {
//                val value1 = async {
//                    delay(1000)
//                    1000
//                }
//                val value2 = async {
//                    delay(2000)
//                    2000
//                }
//                Log.i(TAG, "test: ${value1.await() + value2.await()}")
//                Log.i(TAG, "async end")
//            }
//            Log.i(TAG, "test: time=$time")
             testCancel()
            Log.i(TAG, " launch end")
        }
        Log.i(TAG, "test: end")

    }

    private suspend fun testCancel() {
        val result = withContext(Dispatchers.IO) {
            delay(3000)
            Log.i(TAG, "testCancel: ")
            "result"
        }
        println("result="+result)
    }

    private suspend fun testThrowException() {
        Log.i(TAG, "job:${coroutineContext[Job]} ")
        coroutineScope {
            val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
                Log.e(TAG, "coroutineContext: ${coroutineContext[Job]}", throwable)
            }
            val job = launch(Dispatchers.IO + errorHandler) {
                Log.i(TAG, "job:${coroutineContext[CoroutineName]} ")
                Log.i(TAG, "testThrowException: ")
                throw IllegalAccessException("simulate crash")
            }
        }


        val errorHandler1 = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(TAG, "errorHandler1: coroutineContext=${coroutineContext[Job]}", throwable)
        }
        CoroutineScope(Dispatchers.IO + errorHandler1).launch {
            Log.i(TAG, "job:${coroutineContext[CoroutineName]} ")
            Log.i(TAG, "testThrowException: ")
            throw IllegalAccessException("simulate crash")
        }
    }
}