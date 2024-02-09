package com.example.sneakers.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers.domain.remote.usecase.home.FetchSneakersUseCase
import com.example.sneakers.network.NetworkState
import com.example.sneakers.presentation.ui.SneakerUiDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: FetchSneakersUseCase) : ViewModel() {

    private var _sneakerListData = MutableStateFlow<NetworkState>(NetworkState.Loading)
    val sneakerListData: StateFlow<NetworkState>
        get() = _sneakerListData

    private var _sneakerData = MutableStateFlow<SneakerUiDto?>(null)
    val sneakerData:StateFlow<SneakerUiDto?>
        get() = _sneakerData

    fun fetchSneakerData() {
        viewModelScope.launch {
            useCase.fetchSneakerList().collect {
                _sneakerListData.value = it
            }
        }
    }

    fun setSneakerData(sneakerUiDto: SneakerUiDto){
        _sneakerData.value = sneakerUiDto
    }

}