package com.example.sneakers

import app.cash.turbine.test
import com.example.sneakers.data.remote.datasourceimpl.home.HomeDataSourceImpl
import com.example.sneakers.data.repositoryimpl.home.HomeRepositoryImpl
import com.example.sneakers.data.usecaseimpl.home.FetchSneakersUseCaseImpl
import com.example.sneakers.domain.remote.usecase.home.FetchSneakersUseCase
import com.example.sneakers.network.NetworkState
import com.example.sneakers.presentation.ui.home.HomeViewModel
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    private var homeDataSource = HomeDataSourceImpl()
    private var dispatcherProvider = TestDispatcherProvider()
    private var homeRepository = HomeRepositoryImpl(homeDataSource)
    private var useCase = FetchSneakersUseCaseImpl(homeRepository)

    private var viewModel = HomeViewModel(useCase, TestDispatcherProvider())

    @Test
    fun `check data source for sneakersList`() {
        runTest {
            viewModel.fetchSneakerData()
            viewModel.sneakerListData.test {
                val state = awaitItem()
                TestCase.assertEquals(true, state is NetworkState.Success)
            }
        }
    }
}