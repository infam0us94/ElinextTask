package com.project.elinexttask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.elinexttask.adapter.CharacterPagingSource
import com.project.elinexttask.api.ApiInterface
import com.project.elinexttask.api.RetroBuilder
import com.project.elinexttask.api.model.Image
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel : ViewModel() {

    private val token = "c26dfb95-d5ac-424f-bdd8-a303af965953"
    private val limit = 100

    private var retroService: ApiInterface =
        RetroBuilder.getRetroInstance().create(ApiInterface::class.java)

    fun getListData(): Flow<PagingData<Image>> {
        return Pager(config = PagingConfig(pageSize = 3, maxSize = 70, enablePlaceholders = false),
            pagingSourceFactory = {
                CharacterPagingSource(
                    retroService,
                    token,
                    limit
                )
            }).flow.cachedIn(viewModelScope)
    }
}