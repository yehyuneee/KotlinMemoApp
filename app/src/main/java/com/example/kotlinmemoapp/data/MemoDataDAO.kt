package com.example.kotlinmemoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Room Component룸 구성요소
 * Entity, DAO, Database
 *
 * Memo DAO 생성
 */
@Dao
interface MemoDataDAO {
    @Query("SELECT * FROM Memo")
    fun getAllMemo(): LiveData<List<MemoData>>

    @Query("DELETE FROM Memo WHERE `index` = (:removeIndex)")
    fun removeMemo(removeIndex: Int)

    @Insert
    fun insertMemo(insertMemo: MemoData)

}