package com.example.kotlinmemoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room Component룸 구성요소
 * Entity, DAO, Database
 *
 * Memo Database 생성
 */
@Database(entities = [MemoData::class], version = 1)
abstract class MemoDB : RoomDatabase() {
    abstract fun memoDao(): MemoDataDAO

    companion object {
        private var Instance: MemoDB? = null
        fun getInstance(context: Context): MemoDB? {
            if (Instance == null) {
                // 여러 스레드가 접근하지 못하도록 한다
                synchronized(MemoDB::class) {
                    Instance = Room.databaseBuilder(context.applicationContext,
                        MemoDB::class.java,
                        "memo.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }

            }
            return Instance
        }
    }

    fun destoryInstance() {
        Instance = null
    }
}