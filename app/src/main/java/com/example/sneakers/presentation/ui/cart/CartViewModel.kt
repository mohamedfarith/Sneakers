package com.example.sneakers.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers.data.models.cart.SneakerItemDao
import com.example.sneakers.domain.local.localusecase.cart.LocalCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val usecase: LocalCartUseCase) : ViewModel() {

    private val _cartItemsList = MutableStateFlow<List<SneakerItemDao?>>(arrayListOf())
    val cartItemList: StateFlow<List<SneakerItemDao?>>
        get() = _cartItemsList

    fun addItemToCart(item: SneakerItemDao) {
        viewModelScope.launch(Dispatchers.Default) {
            usecase.insertCartItems(items = arrayListOf(item)).collect { it ->
                //success response
            }
        }
    }

    fun removeItemFromCart(item: SneakerItemDao) {
        viewModelScope.launch(Dispatchers.Default) {
            usecase.deleteCartItem(item = item).collect { it ->
                //success response
            }
        }
    }

    fun getCartItems() {
        viewModelScope.launch(Dispatchers.Default) {
            usecase.getAllCartItems().collect {
                _cartItemsList.value = it
            }
        }
    }
}