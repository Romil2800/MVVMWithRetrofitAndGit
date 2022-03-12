package com.example.mvvmwithretrofitandgit.retrofitService

import com.example.mvvmwithretrofitandgit.models.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterData {
    @GET("character/")
    suspend fun getCharacter(@Query("page")page:Int): Response<CharacterList>
}