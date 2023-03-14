package com.example.senya.arch

import android.content.Context
import com.example.senya.R
import com.example.senya.data.Attraction
import com.example.senya.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class AttractionsRepository {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun parseAttractions(context: Context): List<Attraction> {
        val textFromFile = context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val type = Types.newParameterizedType(List::class.java, Attraction::class.java)
        val adapter: JsonAdapter<AttractionsResponse> = moshi.adapter(AttractionsResponse::class.java)
        return adapter.fromJson(textFromFile)!!.attractions
    }
}