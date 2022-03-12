package com.example.mvvmwithretrofitandgit.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmwithretrofitandgit.database.CharacterDatabase
import com.example.mvvmwithretrofitandgit.models.CharacterList
import com.example.mvvmwithretrofitandgit.retrofitService.CharacterData
import com.example.mvvmwithretrofitandgit.util.NetworkUtils

class CharacterRepository(
    private val characterService: CharacterData,
    private val characterDatabase: CharacterDatabase,
    private val applicationContext: Context
) {

    private val characterLiveData = MutableLiveData<CharacterList>()

    val characters: LiveData<CharacterList>
        get() = characterLiveData

    suspend fun getCharacter(page: Int) {

        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = characterService.getCharacter(page)
            if (result.body() != null) {
                characterDatabase.characterDao().addCharacter(result.body()!!.results)
                characterLiveData.postValue(result.body())
            }
        } else {
            val characters = characterDatabase.characterDao().getCharacters()
            val characterList = CharacterList(characters)
            characterLiveData.postValue(characterList)
        }
    }

}