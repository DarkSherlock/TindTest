package com.liang.tind.www.tindtest.activty.room

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 *@author lonnie.liang
 *@date 2020/03/22 22:36
 *
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: Model)

    @Delete
    fun delete(user: Model)

    @Update
    fun update(user: Model)

    @Query("SELECT * FROM user WHERE id=:id")
    fun queryById(id: Long): LiveData<Model>

    @Query("SELECT * FROM user")
    fun queryAll() : LiveData<List<Model>>
}