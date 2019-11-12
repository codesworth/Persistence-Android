package com.raywenderlich.android.datadrop.model

import android.arch.persistence.room.TypeConverter
import com.google.android.gms.maps.model.LatLng
import java.util.*

class LatLngConverter {

    @TypeConverter
    fun fromLatLng(latLng: LatLng?):String?{
        if (latLng != null){
            val string = String.format(Locale.getDefault(),"%f,%f",latLng.latitude,latLng.longitude)
            return string
        }

        return null
    }

    @TypeConverter
    fun toLatLng(value: String?):LatLng? {
        if (value != null){
            val pieces = value.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val lat = pieces.first().toDouble()
            val long = pieces.last().toDouble()
            return LatLng(lat,long)
        }

        return null
    }
}