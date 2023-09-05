package com.vimlesh.jokesunlimit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vimlesh.jokesunlimit.data.JokesModule
import com.vimlesh.jokesunlimit.data.local.JokesEntity
import com.vimlesh.jokesunlimit.data.JokesRepository
import com.vimlesh.jokesunlimit.utils.Common
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer

class JokesViewModel(private val repository:JokesRepository ):ViewModel(){
    private val _jokesLiveData = MutableLiveData<JokesModule?>()
    val jokesLiveData:LiveData<JokesModule?> = _jokesLiveData
    private val _jokesLiveDataList = MutableLiveData<List<JokesEntity>>()
    val jokesLiveDataList:LiveData<List<JokesEntity>> = _jokesLiveDataList
    val error = MutableLiveData<String>()
    private val timer = Timer()
    private fun fetchAJoke(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.fetchAJoke()
            if(response?.isSuccessful == true) {
                response?.let {
                    _jokesLiveData.postValue(it.body())
                    insertAJoke(it.body()?.joke)
                }
            }
            else{
               error.postValue("Something went wrong")
            }
        }
    }
    private fun insertAJoke(joke: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertJoke(joke)
        }
    }

    fun fetchJokes() {
        viewModelScope.launch(Dispatchers.IO) {
           val jokeList = repository.fetchJokes()
            _jokesLiveDataList.postValue(jokeList)
            initiateACallOfFetchingAJoke()
        }
    }

    fun deleteOldJokes(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteOldJokes()
        }
    }

   private fun initiateACallOfFetchingAJoke(){
        Common.fetchAJokeInInterval(timer) { fetchAJoke() }
   }

}