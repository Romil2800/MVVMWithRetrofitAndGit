package com.example.mvvmwithretrofitandgit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmwithretrofitandgit.adapter.DataAdapter
import com.example.mvvmwithretrofitandgit.databinding.ActivityMainBinding
import com.example.mvvmwithretrofitandgit.models.Result
import com.example.mvvmwithretrofitandgit.repository.CharacterRepository
import com.example.mvvmwithretrofitandgit.retrofitService.CharacterData
import com.example.mvvmwithretrofitandgit.retrofitService.RetrofitHelper
import com.example.mvvmwithretrofitandgit.viewModel.MainViewModel
import com.example.mvvmwithretrofitandgit.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainVieModel: MainViewModel
    lateinit var adapter: DataAdapter
    private lateinit var binding: ActivityMainBinding
    private var result = mutableListOf<Result>()
    var pageNum = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val characterData = RetrofitHelper.getInstance().create(CharacterData::class.java)
        val repository = CharacterRepository(characterData)
        mainVieModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainVieModel.character.observe(this) { data ->

            val character = data.results
            layoutManager(character)
            getList(character)
        }

        binding.apply {
            characterRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DataAdapter(this@MainActivity, result)
            characterRecyclerView.adapter = adapter


        }
    }


    private fun layoutManager(character: List<Result>) {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.characterRecyclerView.layoutManager = linearLayoutManager

        binding.characterRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItemCount = linearLayoutManager.childCount
                val pastVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount
                if ((visibleItemCount + pastVisibleItem) >= total) {
                    pageNum++
                    getList(character)
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }


    private fun getList(character: List<Result>) {
        result.addAll(character)
        adapter.notifyDataSetChanged()
    }
}