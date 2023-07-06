package com.drcmind.androidretrofitcompose.ui

import com.drcmind.androidretrofitcompose.domain.model.Quote

data class UiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val quotes: List<Quote>? = emptyList()
)
