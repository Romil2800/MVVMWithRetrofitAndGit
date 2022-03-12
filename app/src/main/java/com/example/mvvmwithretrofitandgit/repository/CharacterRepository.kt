package com.example.mvvmwithretrofitandgit.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmwithretrofitandgit.models.CharacterList
import com.example.mvvmwithretrofitandgit.retrofitService.CharacterData
import java.lang.Exception

class CharacterRepository(
    private val characterService: CharacterData,
) {

    private val characterLiveData = MutableLiveData<CharacterList>()

    val characters: LiveData<CharacterList>
        get() = characterLiveData

    suspend fun getCharacter(page: Int) {
        val result = characterService.getCharacter(page)
        if (result.body() != null) {
            characterLiveData.postValue(result.body())
        }
    }

}