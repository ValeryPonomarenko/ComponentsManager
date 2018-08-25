package me.vponomarenko.componentmanager.di

import dagger.Module
import dagger.Provides
import me.vponomarenko.core.TextHolder
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideTextHolder() = TextHolder()
}