package com.example.kotlinmemoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Component룸 구성요소
 * Entity, DAO, Database
 *
 * Memo Entity 생성
 */
@Entity(tableName = "Memo")
class MemoData(
    @PrimaryKey
    var index: Int?,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "subTitle")
    var subTitle: String?,
) {
}