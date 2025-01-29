package org.example.multiplatform.viewmodel

import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import network.ApiStatus
import network.NetworkRepository
import org.example.multiplatform.AppDispatchers
import org.example.multiplatform.HomeScreen
import org.example.multiplatform.models.MealsCategoriesResponse

class HomeViewModel(val networkRepository: NetworkRepository) {

    private val mealsData = MutableStateFlow<MealsCategoriesResponse?>(null)
    private val loaderData = MutableStateFlow<Boolean?>(false)
    val meals = mealsData.asStateFlow()
    val loader = loaderData.asStateFlow()

    init {
        getMeals()
    }
    fun getMeals() {
        CoroutineScope(AppDispatchers.io).launch {
            networkRepository.getProductList().collect { response ->
                loaderData.value = response.status == ApiStatus.LOADING
                when (response.status) {
                    ApiStatus.SUCCESS -> mealsData.value = response.data
                    ApiStatus.ERROR -> {}
                    ApiStatus.LOADING -> {}
                }

            }
        }
    }

}