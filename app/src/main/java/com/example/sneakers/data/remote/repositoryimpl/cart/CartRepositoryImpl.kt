package com.example.sneakers.data.repositoryimpl.cart

import com.example.sneakers.domain.local.localdatasource.cart.LocalCartDataSource
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val datasource: LocalCartDataSource) {

}