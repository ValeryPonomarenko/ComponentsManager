package me.vponomarenko.feature.c.di

import dagger.Module
import dagger.Provides
import me.vponomarenko.core.TextHolder

/**
 * Author: Valery Ponomarenko
 * Date: 28/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class FeatureCModule {
    @FeatureCScope
    @TextHolderForFeatureC
    @Provides
    fun providerTextHolderForFeatureC() = TextHolder()
}