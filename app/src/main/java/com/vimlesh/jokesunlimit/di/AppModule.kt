package com.vimlesh.jokesunlimit.di

import androidx.room.Room
import com.vimlesh.jokesunlimit.data.remote.JokesService
import com.vimlesh.jokesunlimit.data.JokesRepository
import com.vimlesh.jokesunlimit.ui.JokesViewModel
import com.vimlesh.jokesunlimit.data.local.JokesDatabase
import com.vimlesh.jokesunlimit.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokesService::class.java)
    }
    factory { JokesRepository(jokesService = get(), jokesDao = get()) }
//    factory { JokeAdapter(ArrayList()) }
    viewModelOf(::JokesViewModel)
    single {
        Room.databaseBuilder(
            androidContext(),
            JokesDatabase::class.java,
            "jokes_database"
        ).build()
    }
    single { get<JokesDatabase>().jokesDao() }

    }