package com.cyborg.json.network

import com.cyborg.json.database.DatabaseToy
import com.cyborg.json.domain.DomainToy
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkToy(
    val id: Int, val title: String, val url: String, val thumbnailUrl: String
)

fun List<NetworkToy>.asDomainToy(): List<DomainToy> {
    return map {
        DomainToy(
            id = it.id, title = it.title, thumbnailUrl = it.thumbnailUrl, url = it.url
        )

    }
}

fun List<NetworkToy>.asDatabaseToy(): List<DatabaseToy> {
    return map {
        DatabaseToy(
            id = it.id, title = it.title, thumbnailUrl = it.thumbnailUrl, url = it.url
        )

    }
}