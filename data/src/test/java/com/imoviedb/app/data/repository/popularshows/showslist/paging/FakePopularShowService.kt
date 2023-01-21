package com.imoviedb.app.data.repository.popularshows.showslist.paging

import com.imoviedb.app.data.dto.popular.PopularShowsDto
import com.imoviedb.app.data.networking.apiservice.PopularShowService
import com.imoviedb.app.data.utils.mockedPopularShowsDto
import retrofit2.Response

class FakePopularShowService : PopularShowService {
    override suspend fun popularShows(
        mediaType: String, timeWindow: String, api_key: String, page: Int
    ): Response<PopularShowsDto> {
        return Response.success(mockedPopularShowsDto)
    }
}