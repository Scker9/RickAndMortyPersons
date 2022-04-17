package com.example.rickandmortypersons.data.source.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortypersons.data.entities.Character
import com.example.rickandmortypersons.data.mappers.CharacterResponseRawToCharacterMapper
import com.example.rickandmortypersons.data.source.RickAndMortyAPI
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException

class CharactersPagingDataSource :
    PagingSource<Int, Character>(), KoinComponent {
    private val mapper = CharacterResponseRawToCharacterMapper()
    private val api: RickAndMortyAPI by inject()

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        try {
            val pageNumber = params.key ?: 1
            val response = api.getCharactersByPage(pageNumber)

            if (response.isSuccessful) {
                val raw = response.body()!!
                Log.d("CharactersPagingDataSource", Thread.currentThread().toString())
                val characters = mapper.convert(response.body()!!)
                val nextPageNumber = raw.info.next?.filter { it.isDigit() }?.toInt()
                val prevPageNumber = raw.info.prev?.filter { it.isDigit() }?.toInt()
                return LoadResult.Page(characters, prevPageNumber, nextPageNumber)
            } else {
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}

