package com.example.sneakers.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers.constants.AppConstants
import com.example.sneakers.data.models.cart.SneakerItemDao
import com.example.sneakers.domain.local.localusecase.cart.LocalCartUseCase
import com.example.sneakers.network.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val usecase: LocalCartUseCase) : ViewModel() {

    private val _cartItemsList = MutableStateFlow<NetworkState>(NetworkState.Loading)
    val cartItemList = _cartItemsList.asStateFlow()

    private val _dbOperationState = MutableSharedFlow<String>()
    val dbOperationState = _dbOperationState.asSharedFlow()

    fun addItemToCart(item: SneakerItemDao) {
        _cartItemsList.value = NetworkState.Loading
        viewModelScope.launch(Dispatchers.Default) {
            usecase.insertCartItems(items = arrayListOf(item)).collect { it ->
                //success response
                _dbOperationState.emit(AppConstants.SUCCESSFULLY_ADDED_TO_CART)
            }
        }
    }

    fun removeItemFromCart(item: SneakerItemDao) {
        _cartItemsList.value = NetworkState.Loading
        viewModelScope.launch(Dispatchers.Default) {
            usecase.deleteCartItem(item = item).collect { it ->
                //success response
                getCartItems()
                _dbOperationState.emit(AppConstants.SUCCESSFULLY_REMOVED_FROM_CART)
            }
        }
    }

    fun getCartItems() {
        _cartItemsList.value = NetworkState.Loading
        viewModelScope.launch(Dispatchers.Default) {
            usecase.getAllCartItems().collect {
                _cartItemsList.value = NetworkState.Success(it)
            }
        }
    }
}