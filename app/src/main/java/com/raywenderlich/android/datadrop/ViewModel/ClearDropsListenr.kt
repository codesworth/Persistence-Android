package com.raywenderlich.android.datadrop.ViewModel

import com.raywenderlich.android.datadrop.model.Drop

interface ClearDropsListenr {

    fun dropCleared(drop:Drop)

}