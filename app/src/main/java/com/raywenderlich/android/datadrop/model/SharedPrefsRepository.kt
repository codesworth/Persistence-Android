package com.raywenderlich.android.datadrop.model
/*
import android.content.Context
import com.google.gson.Gson
import com.raywenderlich.android.datadrop.app.DataDropApplication

object SharedPrefsRepository:DropRepository {

    private const val SHARED_PREFS_REPO = "SHARED_PREFS_REPO"

    private val gson = Gson()

    private fun sharedPrefs() = DataDropApplication.getAppContext().getSharedPreferences(SHARED_PREFS_REPO,Context.MODE_PRIVATE)

    override fun addDrop(drop: Drop) {
        sharedPrefs().edit().putString(drop.id, gson.toJson(drop).toString()).apply()
    }

    override fun getDrops(): List<Drop> {
        return sharedPrefs().all.keys.map { sharedPrefs().getString(it,"") }
                .filterNot { it.isNullOrBlank() }
                .map {  gson.fromJson(it,Drop::class.java) }
    }

    override fun clearDrop(drop: Drop) {
        sharedPrefs().edit().remove(drop.id).apply()
    }
    override fun clearAllDrops() {
        sharedPrefs().edit().clear().apply()
    }
}*/