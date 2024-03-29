package com.raywenderlich.android.datadrop.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters


@Database(entities = [(Drop::class), (MarkerColor::class)],version = 1)
@TypeConverters(LatLngConverter::class)
abstract class DropDatabase:RoomDatabase() {


    abstract fun dropDao():DropDAO

    abstract fun markerDAO(): MarkerDAO

}