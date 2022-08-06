package com.enesselcuk.jatpeackdatastore.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.enesselcuk.jatpeackdatastore.data.Person
import com.enesselcuk.jatpeackdatastore.repos.PersonRepository
import com.enesselcuk.jatpeackdatastore.repos.PreferencesRepository
import com.enesselcuk.jatpeackdatastore.repos.SortOrder
import com.enesselcuk.jatpeackdatastore.repos.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val personRepos: PersonRepository,
    private val personRepository: PreferencesRepository
) : ViewModel() {

    private val userPreferencesFlow = personRepository.userPreferencesFlow

    val personUiModelFlow = combine(
        personRepos.person,
        userPreferencesFlow
    ) { personRepos: List<Person>, userPreferences: UserPreferences ->
        return@combine PersonUiModel(
            person = filterShowPerson(
                personRepos,
                userPreferences.showCompleted,
                userPreferences.sortOrder
            ),
            showCompleted = userPreferences.showCompleted,
            sortOrder = userPreferences.sortOrder
        )
    }.asLiveData()

    private fun filterShowPerson(
        person: List<Person>,
        showCompleted: Boolean,
        sortOrder: SortOrder
    ): List<Person> {
        val filteredPerson = if (showCompleted) {
            person
        } else {
            person.filter {
                it.completed
            }
        }

        return when (sortOrder) {
            SortOrder.NONE -> filteredPerson
            SortOrder.BY_DEADLINE -> filteredPerson.sortedByDescending { it.name }
            SortOrder.BY_PRIORITY -> filteredPerson.sortedBy { it.address }
            SortOrder.BY_DEADLINE_AND_PRIORITY -> filteredPerson.sortedWith(
                compareByDescending<Person> { it.name }.thenBy { it.address }
            )
        }
    }

    fun showCompletedPerson(showCompleted: Boolean) {
        viewModelScope.launch {
            personRepository.updateShowCompleted(showCompleted)
        }
    }

    fun enableSortByDeadline(enable: Boolean) {
        viewModelScope.launch {
            personRepository.enableSortByDeadline(enable)
        }
    }

    fun enableSortByPriority(enable: Boolean) {
        viewModelScope.launch {
            personRepository.enableSortByPriority(enable)
        }
    }

}