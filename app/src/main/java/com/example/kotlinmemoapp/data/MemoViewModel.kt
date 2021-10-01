package com.example.kotlinmemoapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MemoViewModel(application: Application) : AndroidViewModel(application) {
    private val respository = MemoRespository(application)
    private val allItems = respository.getAll()

    fun insert(memoData: MemoData) {
        respository.insert(memoData)
    }

    fun getAll(): LiveData<List<MemoData>> {
        return allItems
    }
}