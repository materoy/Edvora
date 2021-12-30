package com.materoy.edvora.home.presentation

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
): ViewModel() {

    init {
        viewModelScope.launch {
            getProducts().onEach { result: Resource<List<Product>> ->
                when(result){
                    is Resource.Success -> {
                        println(result.data?.size)
                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Error -> {

                    }
                }
            }.launchIn(this)
        }
    }
}


