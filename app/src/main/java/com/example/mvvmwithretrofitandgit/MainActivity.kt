package com.example.mvvmwithretrofitandgit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithretrofitandgit.repository.CharacterRepository
import com.example.mvvmwithretrofitandgit.retrofitService.CharacterData
import com.example.mvvmwithretrofitandgit.retrofitService.RetrofitHelper
import com.example.mvvmwithretrofitandgit.viewModel.MainViewModel
import com.example.mvvmwithretrofitandgit.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainVieModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterData = RetrofitHelper.getInstance().create(CharacterData::class.java)
        val repository = CharacterRepository(characterData)
        mainVieModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainVieModel.character.observe(this) { data ->
            Log.d("Data", data.results.toString())
        }
    }
}