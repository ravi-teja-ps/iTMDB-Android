package com.imoviedb.app.domain.popularshows.showslist.paging

 import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imoviedb.app.data.models.popular.Show
import com.imoviedb.app.data.networking.apiservice.PopularShowService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopularShowsPagingSource @Inject constructor(private var popularShowService : PopularShowService) :PagingSource<Int, Show>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {

        return  try {
            val pageIndex = params.key ?: STARTING_PAGE_INDEX
            val response = popularShowService.popularShows(page = pageIndex)
            val showsResponse = response.body()?.shows
            showsResponse?.let { shows->
                val nextKey =
                    if (shows.isEmpty()) {
                        null
                    } else {
                        pageIndex + (params.loadSize / NETWORK_SIZE)
                    }
                LoadResult.Page(
                    data = shows,
                    prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                    nextKey = nextKey
                )
            }?:
            LoadResult.Page(
                data = emptyList(),
                prevKey = STARTING_PAGE_INDEX,
                nextKey = null
            ) ///Defaults to start page with empty data in case of response null for initial

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, Show>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object{
        const  val STARTING_PAGE_INDEX = 1
        const val NETWORK_SIZE = 25
    }
}