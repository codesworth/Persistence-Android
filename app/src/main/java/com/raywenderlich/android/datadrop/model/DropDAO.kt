package com.raywenderlich.android.datadrop.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface DropDAO {


    @Insert
    fun insertDrop(drop: Drop)

    @Delete
    fun clearDrops(vararg drop:Drop)

    @Query("SELECT * FROM drop_table ORDER BY dropMessage ASC")
    fun getAllDrops():LiveData<List<Drop>>
}