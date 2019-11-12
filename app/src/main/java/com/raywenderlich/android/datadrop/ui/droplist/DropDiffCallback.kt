package com.raywenderlich.android.datadrop.ui.droplist

import android.support.v7.util.DiffUtil
import com.raywenderlich.android.datadrop.model.Drop

class DropDiffCallback(private val oldDrops:List<Drop>, private val newDrops:List<Drop>):DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldDrops.get(oldItemPosition).id == newDrops.get(newItemPosition).id
    }

    override fun getOldListSize() = oldDrops.size

    override fun getNewListSize() = newDrops.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int):Boolean{
        val old = oldDrops[oldItemPosition]
        val new = newDrops[newItemPosition]

        return old.latLng == new.latLng && old.markerColor == new.markerColor && old.dropMessage == new.dropMessage
    }
}