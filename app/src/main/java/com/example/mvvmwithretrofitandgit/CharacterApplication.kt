package com.example.mvvmwithretrofitandgit

import android.app.Application
import com.example.mvvmwithretrofitandgit.database.CharacterDatabase
import com.example.mvvmwithretrofitandgit.repository.CharacterRepository
import com.example.mvvmwithretrofitandgit.retrofitService.CharacterData
import com.example.mvvmwithretrofitandgit.retrofitService.RetrofitHelper

class CharacterApplication : Application() {

    lateinit var characterRepository: CharacterRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }
    private fun initialize() {
        val characterService = RetrofitHelper.getInstance().create(CharacterData::class.java)
        val database = CharacterDatabase.getDatabase(applicationContext)
        characterRepository = CharacterRepository(characterService, database,applicationContext)
    }
}