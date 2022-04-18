package com.example.rickandmortypersons.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortypersons.data.mappers.CharacterResponseRawToCharacterListDomainMapper
import com.example.rickandmortypersons.data.source.RickAndMortyAPI
import com.example.rickandmortypersons.domain.entities.CharacterListDomain
import retrofit2.HttpException

class CharactersPagingDataSource(private val api: RickAndMortyAPI) :
    PagingSource<Int, CharacterListDomain>() {
    private val mapper = CharacterResponseRawToCharacterListDomainMapper()

    override fun getRefreshKey(state: PagingState<Int, CharacterListDomain>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterListDomain> {
        try {
            val pageNumber = params.key ?: 1
            val response = api.getCharactersByPage(pageNumber)

            if (response.isSuccessful) {
                val raw = response.body()!!
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

