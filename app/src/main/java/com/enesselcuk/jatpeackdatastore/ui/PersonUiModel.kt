package com.enesselcuk.jatpeackdatastore.ui

import com.enesselcuk.jatpeackdatastore.data.Person
import com.enesselcuk.jatpeackdatastore.repos.SortOrder

data class PersonUiModel(
    val person: List<Person>,
    val showCompleted: Boolean,
    val sortOrder: SortOrder
)
