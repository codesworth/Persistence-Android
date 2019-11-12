package com.raywenderlich.android.datadrop.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.raywenderlich.android.datadrop.app.Injection
import com.raywenderlich.android.datadrop.model.Drop

class DropsViewModel(application: Application):AndroidViewModel(application) {

    private val repository = Injection.provideDropRepository()

    private val allDrops = repository.getDrops()

    fun getDrops() = allDrops

    fun insert(drop:Drop) = repository.addDrop(drop)

    fun clearDrop(drop: Drop) = repository.clearDrop(drop)

    fun clearAllDrops() = repository.clearAllDrops()
}