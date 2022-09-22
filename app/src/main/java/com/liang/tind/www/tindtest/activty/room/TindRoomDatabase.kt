package com.liang.tind.www.tindtest.activty.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 *@author lonnie.liang
 *@date 2020/03/22 22:47
 *
 */
@Database(entities = [Model::class], version = RoomContants.DB_VERSION)
abstract class TindRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun testDao(): TestDao

    companion object {
        @Volatile
        private var INSTANCE: TindRoomDatabase? = null

        fun getInstance(context: Context): TindRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                        context.applicationContext,
                        TindRoomDatabase::class.java,
                        RoomContants.DB_NAME
                    )
                    .allowMainThreadQueries()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}