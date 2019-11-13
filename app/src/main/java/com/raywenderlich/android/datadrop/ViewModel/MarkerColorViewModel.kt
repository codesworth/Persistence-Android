package com.raywenderlich.android.datadrop.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.raywenderlich.android.datadrop.app.DataDropApplication

class MarkerColorViewModel(application:Application):AndroidViewModel(application) {

    private val markerColorDao = DataDropApplication.database.markerDAO()

    private val allmarkers = markerColorDao.getAllMarkerColors()

    fun getAllMarkerColors() = allmarkers;


}