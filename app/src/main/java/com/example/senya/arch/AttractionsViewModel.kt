package com.example.senya.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.senya.data.Attraction

class AttractionsViewModel: ViewModel() {

    private val repository = AttractionsRepository()

    // home fragment
    val attractionListLiveData = MutableLiveData<ArrayList<Attraction>>()
    // attraction detail fragment
    val selectedAttractionLiveData = MutableLiveData<Attraction>()
    // for main activity
    val locationSelectedLiveData = MutableLiveData<Attraction>()

    fun init(context: Context) {
        val attractionsList = repository.parseAttractions(context)
        attractionListLiveData.postValue(attractionsList)
    }

    fun onAttractionSelected(attractionId: String) {
        val attraction = attractionListLiveData.value?.find {
            it.id == attractionId
        } ?: return

        selectedAttractionLiveData.postValue(attraction)
    }
}