package com.example.skeleton.di

import com.example.skeleton.data.repository.OrderRepositoryImpl
import com.example.skeleton.domain.repository.OrderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun provideOrderRepository(repository: OrderRepositoryImpl): OrderRepository
}