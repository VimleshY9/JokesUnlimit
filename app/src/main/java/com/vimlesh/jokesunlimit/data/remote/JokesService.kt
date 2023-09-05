package com.vimlesh.jokesunlimit.data.remote

import com.vimlesh.jokesunlimit.data.JokesModule
import retrofit2.Response
import retrofit2.http.GET

interface JokesService {
    @GET("api?format=json")
   suspend fun fetchAJoke(): Response<JokesModule>?
}