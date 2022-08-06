package com.enesselcuk.jatpeackdatastore.repos

data class UserPreferences(
    val showCompleted: Boolean,
    val sortOrder: SortOrder
)

enum class SortOrder {
    NONE, BY_DEADLINE, BY_PRIORITY, BY_DEADLINE_AND_PRIORITY
}