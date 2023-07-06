package com.drcmind.androidretrofitcompose.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drcmind.androidretrofitcompose.data.repository.QuotesRepository
import com.drcmind.androidretrofitcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: QuotesRepository
) : ViewModel() {

    val uiState = mutableStateOf(UiState())

    init {
        getQuotes()
    }

    fun getQuotes(){
        viewModelScope.launch {
            repository.getNotes().collect{ressource->
                when (ressource){
                    is Resource.Loading->{
                        uiState.value = uiState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                    is Resource.Success->{
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            quotes = ressource.data,
                            error = null
                        )
                    }
                    is Resource.Error->{
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            quotes = ressource.data,
                            error = ressource.message
                        )
                    }
                }
            }
        }
    }
}