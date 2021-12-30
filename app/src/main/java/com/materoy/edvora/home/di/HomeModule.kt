package com.materoy.edvora.home.di

import com.materoy.edvora.home.data.EdvoraApi
import com.materoy.edvora.home.data.repository.ProductRepositoryImpl
import com.materoy.edvora.home.domain.repository.ProductRepository
import com.materoy.edvora.home.domain.use_case.GetProducts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideEdvoraApi(): EdvoraApi {
       return Retrofit.Builder()
        .baseUrl(EdvoraApi.BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
        .create(EdvoraApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: EdvoraApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(repository: ProductRepository): GetProducts {
        return GetProducts(repository)
    }
}