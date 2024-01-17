package com.pollub.masterand.db.repositories.scores

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ScoresModule {
    @Binds
    abstract fun bindScoresRepository(scoresRepositoryImpl: ScoresRepositoryImpl): ScoresRepository
}
