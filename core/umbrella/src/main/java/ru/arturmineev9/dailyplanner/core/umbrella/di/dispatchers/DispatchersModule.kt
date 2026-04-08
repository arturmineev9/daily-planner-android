package ru.arturmineev9.dailyplanner.core.umbrella.di.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.arturmineev9.dailyplanner.core.common.dispatcher.DefaultDispatchersProvider
import ru.arturmineev9.dailyplanner.core.common.dispatcher.DispatchersProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Singleton
    fun provideDispatchersProvider(): DispatchersProvider {
        return DefaultDispatchersProvider()
    }
}
