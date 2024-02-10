package com.example.sneakers

import app.cash.turbine.test
import com.example.sneakers.data.local.localdatasourceimpl.search.LocalSearchDataSourceImpl
import com.example.sneakers.data.local.localrepositoryimpl.search.LocalSearchRepositoryImpl
import com.example.sneakers.data.local.localusecaseimpl.search.LocalSearchUseCaseImpl
import com.example.sneakers.data.models.home.SneakerItemDto
import com.example.sneakers.data.remote.datasourceimpl.home.HomeDataSourceImpl
import com.example.sneakers.data.repositoryimpl.home.HomeRepositoryImpl
import com.example.sneakers.data.usecaseimpl.home.FetchSneakersUseCaseImpl
import com.example.sneakers.domain.local.localdatasource.search.LocalSearchDataSource
import com.example.sneakers.domain.local.localrepository.search.LocalSearchRepository
import com.example.sneakers.network.NetworkState
import com.example.sneakers.presentation.ui.home.HomeViewModel
import com.example.sneakers.presentation.ui.search.SearchViewModel
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchViewModelTest {

    private var searchDataSource = LocalSearchDataSourceImpl()
    private var searchRepository = LocalSearchRepositoryImpl(searchDataSource)
    private var useCase = LocalSearchUseCaseImpl(searchRepository)

    private var viewModel = SearchViewModel(useCase, TestDispatcherProvider())

    @Test
    fun `check data source for sneakersList`() {
        runTest {
            viewModel.fetchSneakerData()
            viewModel.sneakerList.test {
                val state = awaitItem()
                TestCase.assertEquals(true, state is NetworkState.Success)
            }
        }
    }

    @Test
    fun `get matched data valid case`() {
        runTest {
            viewModel.fetchSneakerData()
            viewModel.onSearchTextChange("F")
            viewModel.sneakerList.test {
                val state = awaitItem()
                val list = (state as? NetworkState.Success)?.data as? List<SneakerItemDto>
                TestCase.assertEquals(true, list?.isNotEmpty())
            }
        }
    }
    @Test
    fun `get matched data empty case`() {
        runTest {
            viewModel.fetchSneakerData()
            viewModel.onSearchTextChange("Ft")
            viewModel.sneakerList.test {
                val state = awaitItem()
                val list = (state as? NetworkState.Success)?.data as? List<SneakerItemDto>
                TestCase.assertEquals(true, list?.isEmpty())
            }
        }
    }
}