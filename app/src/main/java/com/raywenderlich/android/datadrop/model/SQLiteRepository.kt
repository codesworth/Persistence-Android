package com.raywenderlich.android.datadrop.model

import android.content.ContentValues
import android.util.Log
import com.raywenderlich.android.datadrop.app.DataDropApplication
import com.raywenderlich.android.datadrop.model.DropDBSchema.DropTable.Columns
import java.io.IOException

class SQLiteRepository : DropRepository{

    private val database = DropBDHelper(DataDropApplication.getAppContext()).writableDatabase

    override fun addDrop(drop: Drop) {
        val contentValues = getDropContentValues(drop)
        database.insert(DropDBSchema.DropTable.NAME,null,contentValues)
    }

    override fun getDrops(): List<Drop> {
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

        return drops
    }

    override fun clearDrop(drop: Drop) {
        database.delete(
                DropDBSchema.DropTable.NAME,
                Columns.ID + " = ?" , arrayOf(drop.id)
        )
    }

    override fun clearAllDrops() {
        database.delete(DropDBSchema.DropTable.NAME,null,null)
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