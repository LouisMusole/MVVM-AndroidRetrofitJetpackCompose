package com.drcmind.androidretrofitcompose.data.datasource

import com.drcmind.androidretrofitcompose.domain.model.QuotesResponse
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {
    @GET("/quotes")
    suspend fun getQuotes() : Response<QuotesResponse>
}