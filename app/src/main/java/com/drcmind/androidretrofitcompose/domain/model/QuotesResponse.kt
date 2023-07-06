package com.drcmind.androidretrofitcompose.domain.model

data class QuotesResponse(
    val quotes : List<Quote>,
    val total : Int
)
