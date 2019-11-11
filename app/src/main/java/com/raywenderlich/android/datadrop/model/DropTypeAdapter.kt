package com.raywenderlich.android.datadrop.model

import com.google.android.gms.maps.model.LatLng
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException

class DropTypeAdapter: TypeAdapter<Drop>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Drop) {
        out.beginObject()
        out.name("latitude").value(value.latLng.latitude)
        out.name("longitude").value(value.latLng.longitude)
        out.name("dropMessage").value(value.dropMessage)
        out.name("id").value(value.id)
        out.endObject()


    }


    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Drop? {
        var latitude = 0.0
        var longitude = 0.0
        var dropMessage = ""
        var id = ""
        `in`.beginObject()
        while (`in`.hasNext()){
            when(`in`.nextName()){
                "latitude" -> latitude = `in`.nextDouble()
                "longitude" -> longitude = `in`.nextDouble()
                "dropMessage" -> dropMessage = `in`.nextString()
                "id" -> id = `in`.nextString()
            }
        }

        `in`.endObject()
        return Drop(LatLng(latitude,longitude),dropMessage,id)
    }
}