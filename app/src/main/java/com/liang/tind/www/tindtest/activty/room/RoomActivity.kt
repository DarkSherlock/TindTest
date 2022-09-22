package com.liang.tind.www.tindtest.activty.room

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.liang.tind.www.tindtest.R
import com.liang.tind.www.tindtest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *@author lonnie.liang
 *@date 2020/03/22 23:01
 *
 */
class RoomActivity : BaseActivity() {
    private var id = 1L
    override fun init() {
        val userDao = TindRoomDatabase.getInstance(this).userDao()
        insert.setOnClickListener {
            val user = Model("Name$id")
            Log.i(TAG, "insert=$user")
            userDao.insert(user)
        }

        delete.setOnClickListener {
            val user = Model("Name$id")
            Log.i(TAG, "delete=$user")
            userDao.delete(user)
        }

        update.setOnClickListener {
            val user = Model("Name$id")
            Log.i(TAG, "update=$user")
            userDao.update(user)
        }

        query.setOnClickListener {
            val userLiveData = userDao.queryById(id)
            Log.i(TAG, "findByid:$id = ${userLiveData.value}")
        }

        queryAll.setOnClickListener {
            val allUsers = userDao.queryAll()
            allUsers.observe(LifecycleOwner { lifecycle }, Observer {
                Log.i(TAG, "queryAll = ${it}")
            })
        }

        CoroutineScope(Dispatchers.Main).launch {
            Log.i(TAG,"Thread.name=${Thread.currentThread().name}")
            Log.i("tind","data=${getData()}")
        }

    }

    suspend fun getData(): String? {
        return withContext(Dispatchers.IO) {
            Log.i(TAG,"Thread.name=${Thread.currentThread().name}")
            try {
                "ss"
            }catch (e: Exception){
                null
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_room
    }
}