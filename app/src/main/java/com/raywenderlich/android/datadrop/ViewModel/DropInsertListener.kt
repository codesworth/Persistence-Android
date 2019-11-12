package com.raywenderlich.android.datadrop.ViewModel

import com.raywenderlich.android.datadrop.model.Drop

interface DropInsertListener{

    fun dropInsert(drop:Drop)
}