package com.project.elinexttask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.elinexttask.api.ApiInterface
import com.project.elinexttask.api.RetroBuilder
import com.project.elinexttask.api.model.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {

    private val token = "c26dfb95-d5ac-424f-bdd8-a303af965953"
    private val limit = 100

    private val listImageMutableLiveData: MutableLiveData<List<Image>> = MutableLiveData()

    fun getPost(): MutableLiveData<List<Image>> {
        return listImageMutableLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retro = RetroBuilder.getRetroInstance().create(ApiInterface::class.java)
            val response = retro.getImages(
                token,
                limit
            )
            listImageMutableLiveData.postValue(response)
        }
    }
}