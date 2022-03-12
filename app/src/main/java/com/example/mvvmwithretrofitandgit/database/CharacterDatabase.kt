package com.example.mvvmwithretrofitandgit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao():CharacterDAO

    companion object{

        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        fun getDatabase(context: Context): CharacterDatabase {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        CharacterDatabase::class.java,
                        "characters")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}