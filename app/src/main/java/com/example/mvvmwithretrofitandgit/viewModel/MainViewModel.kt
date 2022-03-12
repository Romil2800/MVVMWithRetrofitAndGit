package com.example.mvvmwithretrofitandgit.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmwithretrofitandgit.models.CharacterList
import com.example.mvvmwithretrofitandgit.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (private val repository: CharacterRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacter(1)
        }
    }

    val character: LiveData<CharacterList>
        get() = repository.characters

}