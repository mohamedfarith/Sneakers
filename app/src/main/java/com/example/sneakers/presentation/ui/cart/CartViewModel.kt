package com.example.sneakers.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers.constants.AppConstants
import com.example.sneakers.data.models.cart.SneakerItemDao
import com.example.sneakers.domain.local.localusecase.cart.LocalCartUseCase
import com.example.sneakers.network.NetworkState
import com.example.sneakers.presentation.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val usecase: LocalCartUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _cartItemsList = MutableStateFlow<NetworkState>(NetworkState.Loading)
    val cartItemList = _cartItemsList.asStateFlow()

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    fun addItemToCart(item: SneakerItemDao) {
        _cartItemsList.value = NetworkState.Loading
        viewModelScope.launch(dispatcherProvider.default) {
            usecase.insertCartItems(items = arrayListOf(item)).collect { it ->
                //success response
                getCartItems()
                _toastMessage.emit(AppConstants.SUCCESSFULLY_ADDED_TO_CART)
            }
        }
    }

    fun removeItemFromCart(item: SneakerItemDao) {
        _cartItemsList.value = NetworkState.Loading
        viewModelScope.launch(dispatcherProvider.default) {
            usecase.deleteCartItem(item = item).collect { it ->
                //success response
                getCartItems()
                _toastMessage.emit(AppConstants.SUCCESSFULLY_REMOVED_FROM_CART)
            }
        }
    }

    fun getCartItems() {
        _cartItemsList.value = NetworkState.Loading
        viewModelScope.launch(dispatcherProvider.default) {
            usecase.getAllCartItems().collect {
                _cartItemsList.value = NetworkState.Success(it)
            }
        }
    }
}