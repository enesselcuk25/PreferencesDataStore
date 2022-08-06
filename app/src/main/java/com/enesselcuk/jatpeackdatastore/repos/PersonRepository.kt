package com.enesselcuk.jatpeackdatastore.repos

import com.enesselcuk.jatpeackdatastore.data.Person
import com.enesselcuk.jatpeackdatastore.data.PersonPriority
import kotlinx.coroutines.flow.flowOf
import java.text.SimpleDateFormat
import java.util.*


object PersonRepository {

    private val dataFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    val person = flowOf(
        listOf(
            Person(
                name = "ali",
                date = dataFormat.parse("10/02/2008")!!,
                address = "istanbul sarıyer",
                priority = PersonPriority.LOW,
                completed = true

            ),
            Person(
                name = "mehmet",
                date = dataFormat.parse("10/02/2003")!!,
                address = "Erzurum hınıs",
                priority = PersonPriority.MEDIUM,
                completed = true

            ),
            Person(
                name = "sezer",
                date = dataFormat.parse("10/02/2002")!!,
                address = "sivas divriği",
                priority = PersonPriority.HIGH,
                completed = true

            ),
            Person(
                name = "yeşim",
                date = dataFormat.parse("10/02/2001")!!,
                address = "bayburt",
                priority = PersonPriority.MEDIUM,
                completed = true

            ),
            Person(
                name = "zeynep",
                date = dataFormat.parse("10/02/2007")!!,
                address = "izmir ",
                priority = PersonPriority.LOW,
                completed = true

            ),
            Person(
                name = "ahmet",
                date = dataFormat.parse("10/02/2009")!!,
                address = "ağrı doğubeyazıt",
                priority = PersonPriority.HIGH,
                completed = true

            ),
            Person(
                name = "ramazan",
                date = dataFormat.parse("11/03/2004")!!,
                address = "Gaziantep",
                priority = PersonPriority.MEDIUM,
                completed = true
            )
        )
    )

}