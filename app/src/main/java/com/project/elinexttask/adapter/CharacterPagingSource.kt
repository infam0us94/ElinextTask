package com.project.elinexttask.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.elinexttask.api.ApiInterface
import com.project.elinexttask.api.model.Image
import retrofit2.HttpException
import java.io.IOException

class CharacterPagingSource(
    private val apiService: ApiInterface,
    private val token: String,
    private val limit: Int
) : PagingSource<Int, Image>() {

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {

        val page: Int = params.key ?: FIRST_PAGE_INDEX

        return try {
            val response = apiService.getImages(token, limit, page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == FIRST_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}