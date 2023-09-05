package com.vimlesh.jokesunlimit.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vimlesh.jokesunlimit.data.local.JokesEntity

@Dao
interface JokesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(jokesEntity: JokesEntity)

    @Query("SELECT * FROM jokes_table LIMIT 10")
   suspend fun getTop10Jokes(): List<JokesEntity>

    @Query("DELETE FROM jokes_table WHERE id NOT IN (SELECT id FROM jokes_table ORDER BY id DESC LIMIT 50)")
    suspend fun deleteOldJokes()
}