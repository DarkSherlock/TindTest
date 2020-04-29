package com.liang.tind.www.tindtest.activty.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 *@author lonnie.liang
 *@date 2020/03/22 22:47
 *
 */
@Database(entities = [Model::class], version = RoomContants.DB_VERSION)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getInstance(context: Context): UserRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                        context.applicationContext,
                        UserRoomDatabase::class.java,
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