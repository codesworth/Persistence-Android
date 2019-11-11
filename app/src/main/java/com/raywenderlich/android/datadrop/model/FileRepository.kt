package com.raywenderlich.android.datadrop.model

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.raywenderlich.android.datadrop.app.DataDropApplication
import java.io.*
import java.lang.StringBuilder

object FileRepository:DropRepository {


    private val gson:Gson
        get() {
            val builder = GsonBuilder()
            builder.registerTypeAdapter(Drop::class.java,DropTypeAdapter())
            return builder.create()
        }


    private fun getContext() = DataDropApplication.getAppContext()

    override fun addDrop(drop: Drop) {
        val string = gson.toJson(drop)
        try {
            val dropStream = dropOutputStream(drop)
            dropStream.write(string.toByteArray())
            dropStream.close()
        }catch (err:IOException){
            Log.d(FileRepository.toString(),"Error thrown with exception ${err.localizedMessage}")
        }

    }

    override fun getDrops(): List<Drop> {
        val drops = mutableListOf<Drop>()
        val filelist = dropsDirectory().list()
        filelist.map { convertStreamToString(dropInputStream(it)) }.mapTo(drops,{
            gson.fromJson(it,Drop::class.java)
        })
        return  drops
    }

    override fun clearDrop(drop: Drop) {
        dropFile(dropFilename(drop)).delete()
    }

    override fun clearAllDrops() {
        try {
            dropsDirectory().list().map { dropFile(it).delete() }
            dropsDirectory().delete()
        }catch (e:IOException){
            Log.d("IOExceprion", "Error occuured: ${e.localizedMessage}")
        }
    }

    private fun dropsDirectory():File{
        val dropsDir = File(getContext().getExternalFilesDir(null),"drops")
        if (!dropsDirectory().exists()){
            dropsDir.mkdir()
        }
        return dropsDir
    } //= getContext().getDir("drops",Context.MODE_PRIVATE)

    private fun dropFile(named:String) = File(dropsDirectory(),named)

    private fun dropFilename(drop: Drop) = drop.id + ".drop"

    private fun dropOutputStream(drop: Drop):FileOutputStream{
        return FileOutputStream(dropFile(dropFilename(drop)))
    }

    private fun dropInputStream(filename:String): FileInputStream{
        return FileInputStream(dropFile(filename))

    }

    @Throws(IOException::class)
    private fun convertStreamToString(input:InputStream):String{
        val reader = BufferedReader(InputStreamReader(input))
        val sb = StringBuilder()

       try {
           var line:String? = reader.readLine()
           while (line != null){
               sb.append(line).append("\n")
               line = reader.readLine()
           }
       }catch (e:IOException){
           Log.d("IOExceprion", "Error occuured: ${e.localizedMessage}")
       }

        reader.close()
        return  sb.toString()
    }
}