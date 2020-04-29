package com.liang.tind.www.tindtest.activty.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *@author lonnie.liang
 *@date 2020/03/22 22:26
 *
 */
@Entity(tableName = RoomContants.TABLE_NAME)
data class Model(
    @ColumnInfo(name = RoomContants.NAME) var name: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RoomContants.ID)
    var id: Long = 0
}

