package com.vimlesh.jokesunlimit.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes_table")
data class JokesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id") val id: Long = 0,
    @ColumnInfo(name = "joke") val joke: String?
)