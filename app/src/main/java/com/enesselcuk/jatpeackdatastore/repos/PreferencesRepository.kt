package com.enesselcuk.jatpeackdatastore.repos

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.enesselcuk.jatpeackdatastore.util.PreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val sortOrder =
                SortOrder.valueOf(preferences[PreferencesKey.SORT_ORDER] ?: SortOrder.NONE.name)
            val showCompleted = preferences[PreferencesKey.SHOW_COMPLETED] ?: false
            UserPreferences(showCompleted, sortOrder)
        }

    // update switch
    suspend fun updateShowCompleted(showCompleted: Boolean) {
        dataStore.edit {
            it[PreferencesKey.SHOW_COMPLETED] = showCompleted
        }
    }

    suspend fun enableSortByDeadline(enable: Boolean) {
        dataStore.edit {
            val currentOrder =
                SortOrder.valueOf(it[PreferencesKey.SORT_ORDER] ?: SortOrder.NONE.name)

            val newSortOrder =
                if (enable) {
                    if (currentOrder == SortOrder.BY_PRIORITY) {
                        SortOrder.BY_DEADLINE_AND_PRIORITY
                    } else {
                        SortOrder.BY_DEADLINE
                    }
                } else {
                    if (currentOrder == SortOrder.BY_DEADLINE_AND_PRIORITY) {
                        SortOrder.BY_PRIORITY
                    } else {
                        SortOrder.NONE
                    }
                }
            it[PreferencesKey.SORT_ORDER] = newSortOrder.name
        }
    }

    suspend fun enableSortByPriority(enable: Boolean) {
        dataStore.edit {
            val currentOrder =
                SortOrder.valueOf(it[PreferencesKey.SORT_ORDER] ?: SortOrder.NONE.name)

            if (enable) {
                if (currentOrder == SortOrder.BY_DEADLINE) {
                    SortOrder.BY_DEADLINE_AND_PRIORITY
                } else {
                    SortOrder.BY_PRIORITY
                }
            } else {
                if (currentOrder == SortOrder.BY_DEADLINE_AND_PRIORITY) {
                    SortOrder.BY_DEADLINE
                } else {
                    SortOrder.NONE
                }
            }
        }
    }
}
