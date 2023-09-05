package com.vimlesh.jokesunlimit.data

import com.vimlesh.jokesunlimit.data.local.JokesDao
import com.vimlesh.jokesunlimit.data.local.JokesEntity
import com.vimlesh.jokesunlimit.data.remote.JokesService
import retrofit2.Response

class JokesRepository(private val jokesService: JokesService, private val jokesDao: JokesDao){

    suspend fun fetchAJoke():Response<JokesModule>?{
        return jokesService.fetchAJoke()
    }

    suspend fun insertJoke(joke: String?){
            jokesDao.insertJoke(JokesEntity(joke=joke))
    }

    suspend fun fetchJokes():List<JokesEntity>{
        return jokesDao.getTop10Jokes()
    }

   suspend fun deleteOldJokes(){
        jokesDao.deleteOldJokes()
    }
}