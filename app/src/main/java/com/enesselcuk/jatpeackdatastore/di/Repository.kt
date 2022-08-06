package com.enesselcuk.jatpeackdatastore.di

import androidx.datastore.core.DataStore
import com.enesselcuk.jatpeackdatastore.repos.PersonRepository
import com.enesselcuk.jatpeackdatastore.repos.PreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object Repository {
    @Provides
    fun providesPersonRepos() = PersonRepository
}