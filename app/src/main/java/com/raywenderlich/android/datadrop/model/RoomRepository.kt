package com.raywenderlich.android.datadrop.model

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.raywenderlich.android.datadrop.app.DataDropApplication

class RoomRepository:DropRepository {

    private val dropDAO = DataDropApplication.database.dropDao()
    private val alldrops:LiveData<List<Drop>>

    init {
       alldrops = dropDAO.getAllDrops()
    }

    override fun addDrop(drop: Drop) {
        InsertAsyncTask(dropDAO).execute(drop)
    }

    override fun getDrops() = alldrops

    override fun clearDrop(drop: Drop) {
        DeleteAsyncTask(dropDAO).execute(drop)
    }

    override fun clearAllDrops() {
        val drops = alldrops.value?.toTypedArray()
        if (drops != null){
            DeleteAsyncTask(dropDAO).execute(*drops)
        }
    }

    private class InsertAsyncTask internal constructor(private val dao:DropDAO):AsyncTask<Drop,Void,Void>(){
        override fun doInBackground(vararg params: Drop): Void? {
            dao.insertDrop(params[0])
            return null
        }
    }


    private class DeleteAsyncTask internal constructor(private val dao:DropDAO):AsyncTask<Drop, Void, Void>(){
        override fun doInBackground(vararg params: Drop): Void? {
            dao.clearDrops(*params)
            return null
        }
    }
}