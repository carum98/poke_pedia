package com.example.pokepedia.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokepedia.db.dao.PokemonDAO
import com.example.pokepedia.db.dao.UserDAO
import com.example.pokepedia.db.emtities.UserEntity
import com.example.pokepedia.db.entities.PokemonEntity


@Database(entities = arrayOf(PokemonEntity::class, UserEntity::class), version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase() {
    abstract fun pokemonDAO(): PokemonDAO
    abstract fun userDAO(): UserDAO

    companion object {
        @Volatile
    private var INSTANCE: DataBase? = null
    fun getDatabase(context: Context): DataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                "PokePedia"
            ).allowMainThreadQueries().build()
            INSTANCE = instance
            instance
        }
    }
    }
}
