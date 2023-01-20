package com.imoviedb.app.data.repository.popularshows.showslist.paging

import com.imoviedb.app.data.dto.popular.PopularShowsDto
import com.imoviedb.app.data.dto.popular.ShowDto
import com.imoviedb.app.data.networking.apiservice.PopularShowService
import retrofit2.Response

class FakePopularShowService : PopularShowService {
    override suspend fun popularShows(
        mediaType: String, timeWindow: String, api_key: String, page: Int
    ): Response<PopularShowsDto> {

        val mockShowResponse = PopularShowsDto()
        mockShowResponse.page = 1
        mockShowResponse.shows = listOf(ShowDto(), ShowDto(), ShowDto())
        mockShowResponse.totalPages = 1
        return Response.success(mockShowResponse)
    }
}