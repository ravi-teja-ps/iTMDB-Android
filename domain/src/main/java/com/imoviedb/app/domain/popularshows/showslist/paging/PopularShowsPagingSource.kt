package com.imoviedb.app.domain.popularshows.showslist.paging

 import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imoviedb.app.data.models.popular.Show
import com.imoviedb.app.data.networking.apiservice.PopularShowService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopularShowsPagingSource @Inject constructor(private var popularShowService : PopularShowService) :PagingSource<Int, Show>() {
    private  val TMDB_STARTING_PAGE_INDEX = 1
    private val NETWORK_PAGE_SIZE = 25


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {

        return  try {
            val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
            val response = popularShowService.popularShows(page = pageIndex)
            val shows = response.body()!!.shows
            val nextKey =
                if (shows?.size == 0) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = shows!!,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
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
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}