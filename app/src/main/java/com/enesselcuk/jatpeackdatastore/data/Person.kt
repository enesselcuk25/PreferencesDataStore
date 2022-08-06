package com.enesselcuk.jatpeackdatastore.data

import java.util.*

data class Person(
    val name: String,
    val date: Date,
    val address: String,
    val priority: PersonPriority,
    val completed: Boolean = false
)

enum class PersonPriority {
    HIGH, MEDIUM, LOW
}
