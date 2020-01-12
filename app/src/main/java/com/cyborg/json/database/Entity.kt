package com.cyborg.json.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cyborg.json.domain.DomainToy

@Entity(tableName = "toys table")
data class DatabaseToy constructor(
    @PrimaryKey val id: Int, val title: String, val thumbnailUrl: String, val url: String
)

fun List<DatabaseToy>.asDomainToy(): List<DomainToy> {
    return map {
        DomainToy(
            id = it.id, title = it.title, thumbnailUrl = it.thumbnailUrl, url = it.url
        )
    }
}
