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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllDrops() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private class InsertAsyncTask internal constructor(private val dao:DropDAO):AsyncTask<Drop,Void,Void>(){
        override fun doInBackground(vararg params: Drop): Void? {
            dao.insertDrop(params[0])
            return null
        }
    }
}