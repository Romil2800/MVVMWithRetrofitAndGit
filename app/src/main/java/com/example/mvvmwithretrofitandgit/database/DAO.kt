package com.example.mvvmwithretrofitandgit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmwithretrofitandgit.models.Result

@Dao
interface CharacterDAO {

    @Insert
    suspend fun addCharacter(character:List<Result>)

    @Query("SELECT * FROM characters Order by id")
    suspend fun getCharacters():List<Result>

}