package com.example.sneakers.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers.data.models.home.SneakerListDto
import com.example.sneakers.data.remote.datasourceimpl.home.HomeDataSourceImpl
import com.example.sneakers.domain.local.localusecase.search.LocalSearchUseCase
import com.example.sneakers.network.NetworkState
import com.example.sneakers.presentation.ui.toSneakerUiDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: LocalSearchUseCase) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _sneakersList = MutableStateFlow<NetworkState>(NetworkState.Loading)
    val sneakerList = _searchText.combine(_sneakersList) { text, sneakersList ->
        if (text.isNotBlank() && sneakersList is NetworkState.Success) {
            val list = (sneakersList.data as? SneakerListDto)?.list ?: arrayListOf()
            NetworkState.Success(list.filter {
                it.toSneakerUiDto().doesMatchSearchQuery(text) ?: false
            })
        } else {
            sneakersList
        }
    }


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun fetchSneakerData() {
        viewModelScope.launch(Dispatchers.Default) {
            useCase.getSneakersList().collect {
                _sneakersList.value = it
            }
        }
    }
}

