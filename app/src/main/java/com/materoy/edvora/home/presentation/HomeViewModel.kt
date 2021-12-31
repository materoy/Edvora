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
        mutableStateOf(
            HomeState(
                products = emptyList(),
                filters = Filters(
                    productNames = emptyList(),
                    states = emptyList(),
                    cities = emptyList()
                )
            )
        )

    val state: State<HomeState> = _state

    init {
        viewModelScope.launch {
            getProducts().onEach { result: Resource<List<Product>> ->
                when (result) {
                    is Resource.Success -> {
                        val products: List<Product> = result?.data ?: emptyList()
                        val productNames = getProductNames(products)
                        val states = getStates(products)
                        val cities = getCities(products)

                        _state.value = state.value.copy(
                            isLoading = false,
                            products = products,
                            filters = state.value.filters.copy(
                                productNames = productNames,
                                states = states,
                                cities = cities
                            ),
                        )

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

    private fun getProductNames(products: List<Product>): List<String> {
        val productNames: ArrayList<String> = ArrayList()
        products.forEach { product ->
            if (!productNames.contains(product.productName))
                productNames.add(product.productName)
        }
        return productNames
    }

    private fun getStates(products: List<Product>): List<String> {
        val states: ArrayList<String> = ArrayList()
        products.forEach { product ->
            if (!states.contains(product.address.state))
                states.add(product.address.state)
        }
        return states
    }

    private fun getCities(products: List<Product>): List<String> {
        val cities: ArrayList<String> = ArrayList()
        products.forEach { product ->
            if (!cities.contains(product.address.city))
                cities.add(product.address.city)
        }
        return cities
    }

    // Adds a selected filter to the filter list in any category

    fun addFilter(category: Categories, item: String) {
        when (category) {
            Categories.Products -> {
                val productFilterList: ArrayList<String> =
                    ArrayList(state.value.filters.productFilterList)
                // Adds to the list if non existent and removes if the item exists in the list
                if (productFilterList.contains(item)) productFilterList.remove(item)
                else productFilterList.add(item)

                _state.value = state.value.copy(
                    filters = state.value.filters.copy(
                        productFilterList = productFilterList
                    )
                )
            }
            Categories.City -> {
                val cityFilterList: ArrayList<String> =
                    ArrayList(state.value.filters.citiesFilterList)
                if (cityFilterList.contains(item)) cityFilterList.remove(item)
                else cityFilterList.add(item)

                _state.value = state.value.copy(
                    filters = state.value.filters.copy(
                        citiesFilterList = cityFilterList
                    )
                )
            }
            Categories.State -> {
                val stateFilterList: ArrayList<String> =
                    ArrayList(state.value.filters.stateFilterList)
                if (stateFilterList.contains(item)) stateFilterList.remove(item)
                else stateFilterList.add(item)

                _state.value = state.value.copy(
                    filters = state.value.filters.copy(
                        stateFilterList = stateFilterList
                    )
                )
            }
        }
    }

    // Updates the items displayed to the user after the filters have been applied
    fun onUpdateFilters() {
        println("Update items")
        val products = state.value.products.filter { product ->  state.value.filters.productFilterList.contains(product.productName) }
        _state.value = state.value.copy(
            filteredProducts = products,
            filters = state.value.filters.copy(
                cities = products.map { it.address.city }.toSet().toList(),
                states = products.map { it.address.city }.toSet().toList(),
                citiesFilterList = emptyList(),
                stateFilterList = emptyList()
            )
        )
    }

    // Removes all applied filters and return to original query state
    fun clearFilter() {
        _state.value = state.value.copy(
            filteredProducts = emptyList(),
            filters = state.value.filters.copy(
                stateFilterList = emptyList(),
                productFilterList = emptyList(),
                citiesFilterList = emptyList(),
            )
        )
    }

}


