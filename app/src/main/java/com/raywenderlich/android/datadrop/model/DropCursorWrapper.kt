package com.raywenderlich.android.datadrop.model

import android.database.Cursor
import android.database.CursorWrapper
import com.google.android.gms.maps.model.LatLng

class DropCursorWrapper(cursor:Cursor):CursorWrapper(cursor){

    fun getDrop():Drop{
        val id = getString(getColumnIndex(DropDBSchema.DropTable.Columns.ID))
        val latitude = getDouble(getColumnIndex(DropDBSchema.DropTable.Columns.LATITUDE))
        val longitude = getDouble(getColumnIndex(DropDBSchema.DropTable.Columns.LONGITUDE))
        val dropMessage = getString(getColumnIndex(DropDBSchema.DropTable.Columns.DROP_MESSAGE))
        val markerColor = getInt(getColumnIndex(DropDBSchema.DropTable.Columns.MARKER_COLOR))

        return Drop(LatLng(latitude,longitude),dropMessage,id,markerColor)

    }
}