package com.raywenderlich.android.datadrop.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.ContentValues
import android.util.Log
import com.raywenderlich.android.datadrop.ViewModel.ClearAllDropsListener
import com.raywenderlich.android.datadrop.ViewModel.ClearDropsListenr
import com.raywenderlich.android.datadrop.ViewModel.DropInsertListener
import com.raywenderlich.android.datadrop.app.DataDropApplication
import com.raywenderlich.android.datadrop.model.DropDBSchema.DropTable.Columns
import java.io.IOException

class SQLiteRepository {

    private val database = DropBDHelper(DataDropApplication.getAppContext()).writableDatabase

     fun addDrop(drop: Drop) {
        val contentValues = getDropContentValues(drop)
        val result = database.insert(DropDBSchema.DropTable.NAME,null,contentValues)
//        if (result != -1L){
//            listener.dropInsert(drop)
//        }
    }

     fun getDrops(): LiveData<List<Drop>> {

        val liveData = MutableLiveData<List<Drop>>()
        val drops = mutableListOf<Drop>()
        val cursor = queryrops(null,null)

        try {
            cursor.moveToFirst()
            while (!cursor.isAfterLast){
                drops.add(cursor.getDrop())
                cursor.moveToNext()
            }
        }catch (e:IOException){
            Log.e(SQLiteRepository::class.java.toString(),"Error reading ${e.localizedMessage}")

        }finally {
            cursor.close()
        }
        liveData.value = drops

        return liveData
    }

    fun clearDrop(drop: Drop, listenr: ClearDropsListenr) {
        val result = database.delete(
                DropDBSchema.DropTable.NAME,
                Columns.ID + " = ?" , arrayOf(drop.id)
        )

        if (result != 0)listenr.dropCleared(drop)
    }

    fun clearAllDrops(listenr: ClearAllDropsListener) {
        val result = database.delete(DropDBSchema.DropTable.NAME,null,null)
        if (result != 0)listenr.allDropsCleared()

    }

    private fun getDropContentValues(drop:Drop):ContentValues{
       val contentValues = ContentValues()
        contentValues.put(Columns.ID,drop.id)
        contentValues.put(Columns.LATITUDE,drop.latLng.latitude)
        contentValues.put(Columns.LONGITUDE,drop.latLng.longitude)
        contentValues.put(Columns.DROP_MESSAGE,drop.dropMessage)
        contentValues.put(Columns.MARKER_COLOR,drop.markerColor)
        return contentValues
    }

    private fun queryrops(where:String?, whereArgs:Array<String>?):DropCursorWrapper{
        val cursor = database.query(
                DropDBSchema.DropTable.NAME,
                null, //Select All columns
                where,
                whereArgs,
                null, //Group By
                null , //having
                null
        )

        return DropCursorWrapper(cursor)
    }
}