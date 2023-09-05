package com.vimlesh.jokesunlimit.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JokesEntity::class], version = 1, exportSchema = false)
abstract class JokesDatabase : RoomDatabase() {
    abstract fun jokesDao(): JokesDao
}