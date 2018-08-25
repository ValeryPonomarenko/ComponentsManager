package me.vponomarenko.feature.b.di

import dagger.Module
import dagger.Provides
import me.vponomarenko.core.TextHolder

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class FeatureBModule {
    @FeatureBScope
    @TextHolderForFeatureB
    @Provides
    fun providerTextHolderForFeatureB() = TextHolder()
}