package com.vimlesh.jokesunlimit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vimlesh.jokesunlimit.data.JokesModule
import com.vimlesh.jokesunlimit.databinding.ItemLayoutBinding

class JokeAdapter(private val jokeList: List<JokesModule>): RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val itemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokeViewHolder(itemLayoutBinding)
    }

    override fun getItemCount(): Int {
       return jokeList.size
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
      with(holder){
          with(jokeList[position]){
              binding.joke.text = joke
          }
      }
    }
    inner class JokeViewHolder(val binding: ItemLayoutBinding):RecyclerView.ViewHolder(binding.root)
}