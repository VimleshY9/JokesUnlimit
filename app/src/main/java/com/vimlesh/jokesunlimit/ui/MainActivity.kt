package com.vimlesh.jokesunlimit.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vimlesh.jokesunlimit.R
import com.vimlesh.jokesunlimit.data.JokesModule
import com.vimlesh.jokesunlimit.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val jokesViewModel: JokesViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private var jokesList = mutableListOf<JokesModule>()
    private lateinit var jokeAdapter: JokeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var image: ImageView
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        image = binding.imageView
        jokeAdapter = JokeAdapter(jokesList)
        binding.rcLayout.adapter = jokeAdapter
        recyclerView = binding.rcLayout
        recyclerView.layoutManager = LinearLayoutManager(this)
        Glide.with(this).load(R.drawable.happy).into(image)
        textView = binding.textView
        jokesViewModel.fetchJokes()
        jokesViewModel.jokesLiveDataList.observe(this) { it ->
            it?.let { jokeEntity ->
                jokeEntity.forEach { jokesList.add(JokesModule(it.joke)) }
            }
        }
        jokesViewModel.jokesLiveData.observe(this) {
            updateUI(it)
        }
        jokesViewModel.error.observe(this){
           Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(jokesModule: JokesModule?) {
        jokesModule?.let { it ->
            image.visibility = View.GONE
            textView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            if (jokesList.size >= 10) {
                jokesList.removeAt(jokesList.size - 1)
                jokeAdapter.notifyItemRemoved(jokesList.size)
            }
            jokesList.add(0, it)
            jokeAdapter.notifyItemInserted(0)
            recyclerView.scrollToPosition(0)
        }
    }

}