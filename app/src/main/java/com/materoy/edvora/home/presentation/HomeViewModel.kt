package com.materoy.edvora.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.materoy.edvora.core.util.Resource
import com.materoy.edvora.home.domain.model.Product
import com.materoy.edvora.home.domain.use_case.GetProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getProducts: GetProducts
) : ViewModel() {

    private val _state =
        mutableStateOf(HomeState(uniqueNames = emptyList(), products = emptyList()))

    val state: State<HomeState> = _state

    init {
        viewModelScope.launch {
            getProducts().onEach { result: Resource<List<Product>> ->
                when (result) {
                    is Resource.Success -> {
                        val uniqueNames: ArrayList<String> = ArrayList()

                        result.data?.forEach { product ->
                            if (! uniqueNames.contains(product.productName))
                                uniqueNames.add(product.productName)
                        }

                        _state.value = state.value.copy(
                            isLoading = false,
                            products = result?.data ?: emptyList(),
                            uniqueNames = uniqueNames
                        )

                        println(_state.value.uniqueNames.size)
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

}


