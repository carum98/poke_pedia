package com.example.pokepedia.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pokepedia.db.dao.PokemonDAO
import com.example.pokepedia.db.dao.UserDAO
import com.example.pokepedia.db.emtities.UserEntity
import com.example.pokepedia.db.emtities.PokemonRecentsEntity
import com.example.pokepedia.db.entities.PokemonEntity


@Database(
    entities = [PokemonEntity::class,
                PokemonRecentsEntity::class,
                UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase: RoomDatabase() {
    abstract fun pokemonDAO(): PokemonDAO
    abstract fun userDAO(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
      /*  val MIGRATION: Migration = object : Migration(1, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
                database.execSQL("ALTER TABLE pokemon_recents "
                        + " ADD COLUMN timeStamp INTEGER");
            }
        }*/
        fun getDatabase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "PokePedia"
                ).allowMainThreadQueries()
                 //  .addMigrations(MIGRATION)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
