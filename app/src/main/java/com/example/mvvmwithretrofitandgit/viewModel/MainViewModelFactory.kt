package com.example.mvvmwithretrofitandgit.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithretrofitandgit.repository.CharacterRepository

class MainViewModelFactory (private val repository: CharacterRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}