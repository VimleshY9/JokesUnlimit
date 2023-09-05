package com.vimlesh.jokesunlimit.data.local.dbMigration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE jokes_table ADD COLUMN new_column TEXT")
    }
}