package com.raywenderlich.android.datadrop.model

import android.content.ContentValues
import com.raywenderlich.android.datadrop.app.DataDropApplication
import com.raywenderlich.android.datadrop.model.DropDBSchema.DropTable.Columns

class SQLiteRepository : DropRepository{

    private val database = DropBDHelper(DataDropApplication.getAppContext()).writableDatabase

    override fun addDrop(drop: Drop) {
        val contentValues = getDropContentValues(drop)
        database.insert(DropDBSchema.DropTable.NAME,null,contentValues)
    }

    override fun getDrops(): List<Drop> {
        return listOf()
    }

    override fun clearDrop(drop: Drop) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllDrops() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getDropContentValues(drop:Drop):ContentValues{
       val contentValues = ContentValues()
        contentValues.put(Columns.ID,drop.id)
        contentValues.put(Columns.LATITUDE,drop.latLng.latitude)
        contentValues.put(Columns.LONGITUDE,drop.latLng.longitude)
        contentValues.put(Columns.DROP_MESSAGE,drop.dropMessage)
        return contentValues
    }
}